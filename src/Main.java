import gestion_credit.model.*;
import gestion_credit.repository.CreditRepository;
import gestion_credit.repository.EcheanceRepository;
import gestion_credit.repository.EmployeRepository;
import gestion_credit.repository.ProfessionalRepository;
import gestion_credit.service.EcheanceService;
import gestion_credit.service.ScoreService;
import gestion_credit.utils.connnection.Connect;
import gestion_credit.utils.enums.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Person employe = new Employe(
//                UUID.randomUUID(),                  // id
//                "Azzedine",                         // nom
//                "Zemmari",                          // prenom
//                LocalDate.of(1990, 5, 20),          // dateNaissance
//                "Casablanca",                        // ville
//                2,                                   // nombreEnfants
//                true,                                // invistisement
//                false,                               // placement
//                SituationFamilly.MARIE,             // situationFamilly
//                LocalDate.now(),                     // created_at
//                0,                                   // score initial
//                12000.0,                             // salaire
//                6,                                   // ancienneté
//                "Developpeur Senior",               // post
//                TypeContrat.CDI_PRIVE_GRANDE_ENTREPRISE, // typeContrat
//                Secteur.PUBLIC                  // secteur
//        );
//        Credit credit = new Credit(
//                UUID.randomUUID(),                   // id unique du crédit
//                LocalDate.now(),                     // date du crédit
//                100000.0,                            // montant octroyé (100.000 DH)
//                0.06,                                // taux d'intérêt (6%)
//                60,                                  // durée en mois (5 ans)
//                "Crédit Personnel",                  // type de crédit
//                Decision.ACCORDIMMEDIAT,                    // décision
//                UUID.randomUUID(),                   // personId (id du client relié)
//                PersonType.EMPLOYE                   // type de personne (EMPLOYE ou PROFESSIONEL)
//        );
        EcheanceRepository er = new EcheanceRepository();
//        rp.creeCredit(credit);
        UUID uuid = UUID.fromString("0e1ffba1-f315-4ac8-aa2a-1362578107f0");
        Echeance echeance = er.getEcheanceById(uuid);
        EcheanceService ech = new EcheanceService();
        UUID id = UUID.fromString("08984741-7b36-43f0-9673-6d33e6fd755c");
        CreditRepository cr = new CreditRepository();
        Credit credit = cr.getCreditById(id);
        ech.modifierEncheanceStaus(echeance,credit);


//        EmployeRepository ep = new EmployeRepository();
//        UUID id = UUID.fromString("217d8008-ddae-49f1-89b8-1b509e670013");
//        Employe e1 = ep.consulterProfileEmploye(id);
//        ScoreService sc = new ScoreService();
//        System.out.println(sc.countScoreForClient(e1));
    }
}