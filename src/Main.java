import gestion_credit.model.Employe;
import gestion_credit.repository.EmployeRepository;
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
        EmployeRepository e1 = new EmployeRepository();
        Employe emp1 = new Employe(
                UUID.randomUUID(),
                "Ahmed",
                "El Malki",
                LocalDate.of(1990, 5, 12),
                "Casablanca",
                2,
                true,
                false,
                SituationFamilly.MARIE,
                LocalDate.now(),
                75,
                6500.00,
                5,
                "Ingénieur",
                TypeContrat.CDI_PUBLIC,
                Secteur.PUBLIC
        );
        System.out.println(e1.allClient());
    }
}