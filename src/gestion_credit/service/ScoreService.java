package gestion_credit.service;

import gestion_credit.model.Person;
import gestion_credit.utils.enums.SituationFamilly;
import gestion_credit.utils.enums.TypeContrat;

import java.time.LocalDate;
import java.time.Period;

public class ScoreService {

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



}
