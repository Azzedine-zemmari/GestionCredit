import gestion_credit.model.*;
import gestion_credit.repository.CreditRepository;
import gestion_credit.repository.EcheanceRepository;
import gestion_credit.repository.EmployeRepository;
import gestion_credit.repository.ProfessionalRepository;
import gestion_credit.service.CreditService;
import gestion_credit.service.EcheanceService;
import gestion_credit.service.ScoreService;
import gestion_credit.ui.Menu;
import gestion_credit.utils.connnection.Connect;
import gestion_credit.utils.enums.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Employe e = new Employe(
//                UUID.randomUUID(),          // id
//                "Ali",                      // nom
//                "El Mansouri",              // prenom
//                LocalDate.of(1994, 5, 10),  // dateNaissance (30 ans)
//                "Casablanca",               // ville
//                1,                          // nombreEnfants
//                true,                       // invistisement
//                false,                      // placement
//                SituationFamilly.MARIE,     // situationFamilly
//                LocalDate.of(2020, 1, 1),   // created_at (ancien client)
//                0,                          // SCORE (will be calculated later)
//                12000.0,                    // salaire
//                5,                          // ancienneté
//                "Chef de Projet",           // post
//                TypeContrat.CDI_PUBLIC,     // typeContrat
//                Secteur.PUBLIC              // secteur (tقدر تبدلها إلا عندك autre enum)
//        );
//        EmployeRepository ep = new EmployeRepository();
//        ep.createEmploye(e);


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
//        EcheanceRepository er = new EcheanceRepository();
//        rp.creeCredit(credit);
//        UUID uuid = UUID.fromString("0e1ffba1-f315-4ac8-aa2a-1362578107f0");
//        Echeance echeance = er.getEcheanceById(uuid);
//        EcheanceService ech = new EcheanceService();
//        UUID id = UUID.fromString("08984741-7b36-43f0-9673-6d33e6fd755c");
//        CreditRepository cr = new CreditRepository();
//        Credit credit = cr.getCreditById(id);
//        ech.modifierEncheanceStaus(echeance,credit);
//        CreditService cr = new CreditService();
//        EmployeRepository em = new EmployeRepository();
//        UUID uuid = UUID.fromString("a1243d4b-71bd-4df2-ae07-779e83b6cff9");
//        Employe employe = em.consulterProfileEmploye(uuid);
//        cr.creeCredit(employe);


//        UUID id = UUID.fromString("217d8008-ddae-49f1-89b8-1b509e670013");
//        Employe e1 = ep.consulterProfileEmploye(id);
//        ScoreService sc = new ScoreService();
//        System.out.println(sc.countScoreForClient(e1));

        Menu menu1 = new Menu();
        menu1.start();
    }
}