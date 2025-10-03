package gestion_credit.service;

import gestion_credit.model.*;
import gestion_credit.repository.*;
import gestion_credit.utils.enums.StatusPaiment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EcheanceService {
    EcheanceRepository echeanceRepository = new EcheanceRepository();
    IncidentRepository incidentRepository = new IncidentRepository();
    ScoreService scoreService = new ScoreService();
    EmployeRepository employeRepository = new EmployeRepository();
    ProfessionalRepository professionalRepository = new ProfessionalRepository();
    CreditRepository creditRepository = new CreditRepository();

    public void creeEncheance(Credit credit) {
        double mensulite = credit.getMontantOctroye() / credit.getDureEnMois();
        LocalDate dateDebut = credit.getDateDeCredit().plusMonths(1);

        for (int i = 0; i < credit.getDureEnMois(); i++) {
            Echeance e = new Echeance(
                    UUID.randomUUID(),
                    dateDebut.plusMonths(i),
                    mensulite,
                    null,
                    null,
                    credit.getId()
            );
//            e.setDateEncheance(dateDebut.plusMonths(i));
            echeanceRepository.createEcheance(e);
        }
    }

    //    modifier status du paiment en se base sur la date du paiment et date echeance
    public void modifierEncheanceStaus(Echeance echeance, Credit credit) {
        LocalDate due = echeance.getDateEncheance();
        LocalDate payDate = echeance.getDatePaiment();
        LocalDate now = LocalDate.now();

        StatusPaiment statusPaiment = null;

        // Déterminer le nouveau statut
        if (payDate == null) {
            if (now.isAfter(due.plusDays(30))) {
                statusPaiment = StatusPaiment.IMPAYE_NON_REGLE;
            } else if (now.isAfter(due.plusDays(5))) {
                statusPaiment = StatusPaiment.EN_RETARD;
            }
        } else {
            if (payDate.isAfter(due.plusDays(30))) {
                statusPaiment = StatusPaiment.IMPAYE_REGLE;
            } else if (payDate.isAfter(due.plusDays(5))) {
                statusPaiment = StatusPaiment.PAYE_EN_RETARD;
            } else {
                statusPaiment = StatusPaiment.PAYE_A_TEMPS;
            }
        }

        // Mise à jour du statut
        echeanceRepository.updateEcheance(echeance);

        // Si incident de paiement → appliquer impact
        if (statusPaiment != StatusPaiment.PAYE_A_TEMPS) {

            int impact = applyImpactToPersonScore(credit); // score calculé avec historique
            Incident incident = new Incident(UUID.randomUUID(), now, impact, statusPaiment, echeance.getId());
            incidentRepository.createIncident(incident);

            // On récupère le client
            UUID id = credit.getPersonId();
            Person person = employeRepository.consulterProfileEmploye(id);

            if (person != null) {
                // C'est un employé
                Employe emp = (Employe) person;
                emp.setScore(emp.getScore() + impact);
                employeRepository.modifierEmploye(emp);
                System.out.println("Score Employe mis à jour: id=" + id + " score=" + emp.getScore());
            } else {
                // Essayer Professionel
                person = professionalRepository.consulterProfileProfessionel(id);
                if (person != null) {
                    Professionel pro = (Professionel) person;
                    pro.setScore(pro.getScore() + impact);
                    professionalRepository.modifierProfessional(pro);
                    System.out.println("Score Professionel mis à jour: id=" + id + " score=" + pro.getScore());
                } else {
                    System.out.println("Aucune personne trouvée pour id=" + id);
                }
            }
        }
    }

    public int applyImpactToPersonScore(Credit credit) {

//        1 get echances by credit id
        List<Echeance> echances = echeanceRepository.getAllEcheancesByCreditId(credit.getId());
//        2 get impact 3la score bhad echances
        int impact = scoreService.countScoreParHistorique(echances);

        return impact;
    }
}
