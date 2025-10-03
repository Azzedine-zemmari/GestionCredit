package gestion_credit.service;

import gestion_credit.model.Echeance;
import gestion_credit.model.Employe;
import gestion_credit.model.Person;
import gestion_credit.model.Professionel;
import gestion_credit.repository.EmployeRepository;
import gestion_credit.repository.ProfessionalRepository;
import gestion_credit.utils.enums.SituationFamilly;
import gestion_credit.utils.enums.StatusPaiment;
import gestion_credit.utils.enums.TypeContrat;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class ScoreService {

    public int countScoreForClient(Person person){
        int score = 0;
        // 1) STABILIT PROFESSIONNELLE (Employe ou Professionnel)
        if(person instanceof Employe){
            Employe employe = (Employe) person;
            score += countScoreParTypeDemploi(employe.getTypeContrat());
            score += countScoreParAnciennete(employe.getAncienneté());
            score += countScoreParRevenu(employe.getSalaire());
        }else if(person instanceof Professionel){
            Professionel professionel = (Professionel) person;
            score += countScoreParRevenu(professionel.getRevenu());
        }

        // 2) RELATION CLIENT (Age + Situation familiale + Enfants)
        score += countScoreParRelationClient(person);

        // 3) CRITERES COMPLEMENTAIRES (Patrimoine / Investment)
        if(Boolean.TRUE.equals(person.getInvistisement()) || Boolean.TRUE.equals(person.getPlacement())){
            score+=10;
        }
//        score += countScoreParHistorique(echeances);
        return score;
    }

    public int countScoreParTypeDemploi(TypeContrat typeEmploi){
        switch (typeEmploi){
            case CDI_PUBLIC: return 25;
            case CDI_PRIVE_GRANDE_ENTREPRISE: return 15;
            case CDI_PRIVE_PME: return 12;
            case CDD_INTERIM: return 10;
            default: return 0;
        }
    }

    public int countScoreParAnciennete(int anciennete){
        if(anciennete >= 5) return 5;
        else if (anciennete >= 2) return 3;
        else if (anciennete >= 1) return 1;
        return 0;
    }

    public int countScoreParRevenu(double revenu){
        if(revenu >= 10000) return 30;
        else if (revenu >= 8000) return 25;
        else if (revenu >= 5000) return 20;
        else if (revenu >= 3000) return 15;
        return 10;
    }

    public int countScoreParRelationClient(Person p){
        int score = 0;
        int age = Period.between(p.getDateNaissance(), LocalDate.now()).getYears();

        // --- AGE ---
        if (age >= 18 && age <= 25) score += 4;
        else if (age >= 26 && age <= 35) score += 8;
        else if (age >= 36 && age <= 55) score += 10;
        else if (age > 55) score += 6;

        // --- SITUATION FAMILIALE ---
        if (p.getSituationFamilly() == SituationFamilly.MARIE) score += 3;
        else score += 2; // Célibataire

        // --- ENFANTS ---
        if (p.getNombreEnfants() == 0) score += 2;
        else if (p.getNombreEnfants() <= 2) score += 1;
        // sinon 0

        return score;
    }

    public int countScoreParHistorique(List<Echeance> echeances){
        int score = 0;
        int retards = 0;
        int impayesNonRegles = 0;
        int impayesRegles = 0;

        for(Echeance e : echeances){
            if(e.getStatusPaiment() == StatusPaiment.IMPAYE_REGLE){
                impayesRegles++;
            } else if (e.getStatusPaiment() == StatusPaiment.IMPAYE_NON_REGLE) {
                impayesNonRegles++;
            }else if(e.getStatusPaiment() == StatusPaiment.EN_RETARD || e.getStatusPaiment() == StatusPaiment.PAYE_EN_RETARD){
                retards++;
            }
        }
        if(impayesRegles > 0) score += 5;
        if(impayesNonRegles > 0 ) score -=10;
        if(retards >= 1 && retards<= 3) score-=3;
        if(retards >=4) score -=5;
        if(impayesNonRegles == 0  && impayesRegles == 0 && retards == 0) score +=10;

        return score;
    }
}
