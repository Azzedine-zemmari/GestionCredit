package gestion_credit.ui;

import gestion_credit.model.Credit;
import gestion_credit.model.Employe;
import gestion_credit.model.Professionel;
import gestion_credit.service.CreditService;
import gestion_credit.service.EcheanceService;
import gestion_credit.service.EmployeService;
import gestion_credit.service.ProfessionalService;
import gestion_credit.utils.enums.Secteur;
import gestion_credit.utils.enums.SituationFamilly;
import gestion_credit.utils.enums.TypeContrat;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private EmployeService employeService  = new EmployeService();
    private ProfessionalService professionalService = new ProfessionalService();
    private CreditService creditService = new CreditService();
    private EcheanceService echeanceService = new EcheanceService();
    public void start(){
        int choix = -1;
        while(choix != 0){
            System.out.println("1. create compte");
            System.out.println("2 . demand de credit");
            System.out.println("0 . Quitter");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix){
                case 1 :
                    System.out.println("1 . cree compte employe");
                    System.out.println("2 . cree compte Proffesional");
                    int CompteChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (CompteChoice){
                        case 1 : creeCompteEmploye();break;
                        case 2 : creeCompteProfessionel();break;
                        default:
                            System.out.println("Choissi un nombre d un la liste");
                            break;
                    }
                    break;
                case 2: demandCredit();break;
                case 0:
                    System.out.println("au revoir");
                    break;

            }
        }
    }
    public void creeCompteEmploye() {
        System.out.println("=== Création d'un compte Employé ===");

        System.out.print("Nom: ");
        String nom = scanner.nextLine();

        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();

        System.out.print("Date de naissance (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        LocalDate dateNaissance = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.print("Ville: ");
        String ville = scanner.nextLine();

        System.out.print("Nombre d'enfants: ");
        Integer nombreEnfants = Integer.parseInt(scanner.nextLine());
        scanner.nextLine();

        System.out.print("Investissement (true/false): ");
        Boolean invistisement = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Placement (true/false): ");
        Boolean placement = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Situation familiale (CELIBATAIRE/MARIE): ");
        SituationFamilly situationFamilly = SituationFamilly.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Salaire: ");
        Double salaire = Double.parseDouble(scanner.nextLine());
        scanner.nextLine();

        System.out.print("Ancienneté (en années): ");
        Integer anciennete = Integer.parseInt(scanner.nextLine());
        scanner.nextLine();

        System.out.print("Poste: ");
        String post = scanner.nextLine();

        System.out.print("Type de contrat (CDI_PUBLIC/CDI_PRIVE_GRANDE_ENTREPRISE/CDI_PRIVE_PME/CDD_INTERIM): ");
        TypeContrat typeContrat = TypeContrat.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Secteur (PUBLIC,GRANDE_ENTREPRISE, PME): ");
        Secteur secteur = Secteur.valueOf(scanner.nextLine().toUpperCase());

        // Création de l'Employé avec score par défaut à 0 et created_at à aujourd'hui
        Employe employe = new Employe(
                UUID.randomUUID(),
                nom,
                prenom,
                dateNaissance,
                ville,
                nombreEnfants,
                invistisement,
                placement,
                situationFamilly,
                LocalDate.now(),
                0,      // score initial
                salaire,
                anciennete,
                post,
                typeContrat,
                secteur
        );
        employeService.createEmploye(employe);
        System.out.println("Compte Employé créé avec succès!");
    }
    public void creeCompteProfessionel() {
        System.out.println("=== Création d'un compte Professionnel ===");

        System.out.print("Nom: ");
        String nom = scanner.nextLine();

        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();

        System.out.print("Date de naissance (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        LocalDate dateNaissance = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.print("Ville: ");
        String ville = scanner.nextLine();

        System.out.print("Nombre d'enfants: ");
        Integer nombreEnfants = Integer.parseInt(scanner.nextLine());

        System.out.print("Investissement (true/false): ");
        Boolean invistisement = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Placement (true/false): ");
        Boolean placement = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Situation familiale (CELIBATAIRE/MARIE): ");
        SituationFamilly situationFamilly = SituationFamilly.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Revenu: ");
        Double revenu = Double.parseDouble(scanner.nextLine());

        System.out.print("Immatriculation fiscale: ");
        String immatriculationFiscale = scanner.nextLine();

        System.out.print("Secteur d'activité: ");
        String secteurActivite = scanner.nextLine();

        System.out.print("Activité: ");
        String activite = scanner.nextLine();

        Professionel professionel = new Professionel(
                UUID.randomUUID(),
                nom,
                prenom,
                dateNaissance,
                ville,
                nombreEnfants,
                invistisement,
                placement,
                situationFamilly,
                LocalDate.now(), // created_at
                0,               // score initial
                revenu,
                immatriculationFiscale,
                secteurActivite,
                activite
        );
        professionalService.createProfessional(professionel);
        System.out.println("Compte Professionnel créé avec succès !");
    }

    public void demandCredit() {
        System.out.println("Entrer votre id : ");
        String str = scanner.nextLine();
        UUID id = UUID.fromString(str);

        Optional<Employe> em = employeService.getEmployeById(id);

        if (em.isPresent()) {
            Employe employe = em.get();
            Optional<Credit> creditOpt = creditService.creeCredit(employe);

            if (creditOpt.isPresent()) {
                    echeanceService.creeEncheance(creditOpt.get());
                System.out.println("echeance cree avec succes tra ra raa");
            } else {
                System.out.println(" Aucun crédit n'a été accordé à cet employé.");
            }
            return;
        }

        Optional<Professionel> pro = professionalService.getProfessionalById(id);

        if (pro.isPresent()) {
            Professionel professionel = pro.get();
            Optional<Credit> creditOpt = creditService.creeCredit(professionel);

            if (creditOpt.isPresent()) {
                echeanceService.creeEncheance(creditOpt.get());
                System.out.println("echeance cree avec succes tra ra raa");
            }
        } else {
            System.out.println("Aucun client trouvé avec cet ID.");
        }
    }

}
