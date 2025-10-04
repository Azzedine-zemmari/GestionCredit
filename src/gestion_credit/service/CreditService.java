package gestion_credit.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import gestion_credit.model.Credit;
import gestion_credit.model.Employe;
import gestion_credit.model.Person;
import gestion_credit.model.Professionel;
import gestion_credit.repository.CreditRepository;
import gestion_credit.repository.EmployeRepository;
import gestion_credit.repository.ProfessionalRepository;
import gestion_credit.utils.enums.Decision;
import gestion_credit.utils.enums.PersonType;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class CreditService {
    private ScoreService scoreService = new ScoreService();
    private CreditRepository creditRepository = new CreditRepository();
    private EmployeRepository employeRepository = new EmployeRepository();
    private ProfessionalRepository professionalRepository = new ProfessionalRepository();

    public Optional<Credit> creeCredit(Person person) {
        // hna kndir set score f db
        int score = scoreService.countScoreForClient(person);
        person.setScore(score);
        if (person instanceof Employe) {
            employeRepository.modifierEmploye((Employe) person);
        } else if (person instanceof Professionel) {
            professionalRepository.modifierProfessional((Professionel) person);
        }
        // hna kndir decision khss ikon accept
        Decision decision = calculDecision(person);
        Double montantOctroy = 0.0;
        Period period = Period.between(person.getCreated_at(), LocalDate.now());
        boolean ancienter = period.getYears() >= 1;
        PersonType personType = PersonType.EMPLOYE;
        if (decision.equals(Decision.ETUDEMANUELLE)) {
            System.out.println("notre decision et en etude manuelle");
            return Optional.empty();
        } else if (decision.equals(Decision.REFUS_AUTOMATIQUE)) {
            System.out.println("votre demande et refus automatique");
            return Optional.empty();
        }
        // hna kt7sb montantOctroy
        if (score > 70 && !ancienter) {
            if (person instanceof Employe) {
                Employe employe = (Employe) person;
                montantOctroy = employe.getSalaire() * 4;
            } else if (person instanceof Professionel) {
                Professionel professionel = (Professionel) person;
                montantOctroy = professionel.getRevenu() * 4;
                personType = PersonType.PROFESSIONNEL;
            }
        } else if (score > 60 && ancienter) {
            if (person instanceof Employe) {
                Employe employe = (Employe) person;
                montantOctroy = employe.getSalaire() * 7;

            } else if (person instanceof Professionel) {
                Professionel professionel = (Professionel) person;
                montantOctroy = professionel.getRevenu() * 7;
                personType = PersonType.PROFESSIONNEL;
            }
        }
        System.out.println("sa c'est le montant que on peut donner " + montantOctroy);
        System.out.println("true/false : ");
        Scanner scanner = new Scanner(System.in);
        Boolean answer = scanner.nextBoolean();
        if(answer){
            Credit credit = new Credit(UUID.randomUUID(), LocalDate.now(), montantOctroy, 0.03, 50, "Personnel", decision, person.getId(), personType);
            creditRepository.creeCredit(credit);
            System.out.println("Credit cree avec success , pour plus de details : \n");
            System.out.println(credit);
            return Optional.of(credit);
        }else{
            System.out.println("desoler en peut pas aider vous");
            return Optional.empty();
        }

    }

    public Decision calculDecision(Person person) {

        int score = person.getScore();
        boolean isAncien = person.getCreated_at().isBefore(LocalDate.now());
        System.out.println("DEBUG calculDecision -- SCORE = " + score + " | isAncien = " + isAncien);

        if (score >= 80) {
            return Decision.ACCORDIMMEDIAT;
        }

        if (isAncien) {
            if (score >= 50 && score < 80) {
                return Decision.ETUDEMANUELLE;
            }
            if (score < 50) {
                return Decision.REFUS_AUTOMATIQUE;
            }
        } else { // Nouveau client
            if (score >= 60 && score < 80) {
                return Decision.ETUDEMANUELLE;
            }
            if (score < 60) {
                return Decision.REFUS_AUTOMATIQUE;
            }
        }

        return null;
    }

}
