package gestion_credit.service;

import gestion_credit.model.Credit;
import gestion_credit.model.Echeance;
import gestion_credit.repository.EcheanceRepository;
import gestion_credit.utils.enums.StatusPaiment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class EcheanceService {
    EcheanceRepository echeanceRepository = new EcheanceRepository();
    public void creeEncheance(Credit credit){
        double mensulite = credit.getMontantOctroye() / credit.getDureEnMois();
        LocalDate dateDebut = credit.getDateDeCredit().plusMonths(1);

        for(int i = 0;i<credit.getDureEnMois();i++){
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
    public void modifierEncheanceStaus(Echeance echeance){
        LocalDate due = echeance.getDateEncheance();
        LocalDate payDate = echeance.getDatePaiment();
        LocalDate now = LocalDate.now();
        if(payDate == null){
            if(now.isAfter(due.plusDays(30))){
                echeance.setStatusPaiment(StatusPaiment.IMPAYE_NON_REGLE);
            }else if(now.isAfter(due.plusDays(5))){
                echeance.setStatusPaiment(StatusPaiment.EN_RETARD);
            }
        }else{
            if(payDate.isAfter(due.plusDays(30))){
                echeance.setStatusPaiment(StatusPaiment.IMPAYE_REGLE);
            }
            else if(payDate.isAfter(due.plusDays(5))){
                echeance.setStatusPaiment(StatusPaiment.PAYE_EN_RETARD);
            }else{
                echeance.setStatusPaiment(StatusPaiment.PAYE_A_TEMPS);
            }
        }
        echeanceRepository.updateEcheance(echeance);
    }
}
