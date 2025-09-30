import gestion_credit.model.Employe;
import gestion_credit.model.Professionel;
import gestion_credit.repository.EmployeRepository;
import gestion_credit.repository.ProfessionalRepository;
import gestion_credit.utils.connnection.Connect;
import gestion_credit.utils.enums.Secteur;
import gestion_credit.utils.enums.SituationFamilly;
import gestion_credit.utils.enums.TypeContrat;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProfessionalRepository e1 = new ProfessionalRepository();
        Professionel pro1 = new Professionel(
                UUID.randomUUID(),
                "Samir",
                "Benali",
                LocalDate.of(1985, 3, 20),
                "Casablanca",
                1,
                true,                               // investissement
                false,                              // placement
                SituationFamilly.MARIE,             // enum
                LocalDate.now(),                     // created_at
                80,                                  // score
                12000.00,                            // revenu
                "IF123456789",                       // immatriculationFiscale
                "Service",                            // secteurActivite
                "Avocat"
        );
        UUID id = UUID.fromString("2529c595-9783-4564-a6bd-3172dde77291");
//        e1.createProfessional(pro1);
        System.out.println(e1.afficherAllProfessional());
    }
}