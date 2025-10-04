package gestion_credit.service;

import gestion_credit.model.Credit;
import gestion_credit.model.Employe;
import gestion_credit.model.Person;
import gestion_credit.model.Professionel;
import gestion_credit.repository.CreditRepository;
import gestion_credit.repository.EmployeRepository;
import gestion_credit.utils.enums.SituationFamilly;
import gestion_credit.utils.enums.TypeContrat;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analytics {
    private EmployeService employeService = new EmployeService();
    private ProfessionalService professionalService = new ProfessionalService();
    private CreditRepository creditRepository = new CreditRepository();

    public List<Person> rechercheClient(Person p) {
        if (p instanceof Employe) {
            List<Employe> employes = employeService.getAllEmployes();
            return employes.stream()
                    .filter(employe -> employe.getSalaire() > 4000)
                    .filter(employe -> employe.getTypeContrat().equals(TypeContrat.CDI_PUBLIC))
                    .filter(employe -> employe.getScore() > 70)
                    .filter(employe -> employe.getSituationFamilly().equals(SituationFamilly.MARIE))
                    .filter(employe -> {
                        int age = Period.between(LocalDate.now(), employe.getDateNaissance()).getYears();
                        return age > 25 && age < 50;
                    })
                    .map(employe -> (Person) employe)
                    .collect(Collectors.toList());
        } else {
            List<Professionel> professionels = professionalService.getAllProfessionals();
            return professionels.stream()
                    .filter(pro -> pro.getRevenu() > 4000)
                    .filter(pro -> pro.getScore() > 70)
                    .filter(pro -> pro.getSituationFamilly().equals(SituationFamilly.MARIE))
                    .filter(employe -> {
                        int age = Period.between(LocalDate.now(), employe.getDateNaissance()).getYears();
                        return age > 25 && age < 50;
                    })
                    .map(pro -> (Person) pro)
                    .collect(Collectors.toList());
        }
    }

    public List<Person> trie(Person p) {
        if (p instanceof Employe) {
            List<Employe> employes = employeService.getAllEmployes();
            return employes.stream()
                    .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                    .sorted(Comparator.comparing(Employe::getSalaire).reversed())
                    .sorted(new Comparator<Employe>() {
                        @Override
                        public int compare(Employe o1, Employe o2) {
                            return Integer.compare(o2.getAncienneté(), o1.getAncienneté());
                        }
                    })
                    .map(employe -> (Person) employe)
                    .collect(Collectors.toList());
        } else {
            List<Professionel> professionels = professionalService.getAllProfessionals();
            return professionels.stream()
                    .sorted(Comparator.comparing(Professionel::getScore).reversed())
                    .sorted((p1, p2) -> Double.compare(p2.getRevenu(), p1.getRevenu()))
                    .map(professionel -> (Person) professionel)
                    .collect(Collectors.toList());
        }

    }
    public void RepartitionDuType(Employe employe){
        List<Employe> employes = employeService.getAllEmployes();
        employes.stream()
                .collect(Collectors.groupingBy(Employe::getTypeContrat))
                .forEach(((typeContrat, list) ->{
                    int total = list.size();
                    double averageScore = list.stream().mapToInt(Employe::getScore).average().orElse(0);
                    double avergaeMoyen = list.stream().mapToDouble(Employe::getSalaire).average().orElse(0);
                    long approves = list.stream().filter(employe1 -> employe1.getScore() > 70).count();
                    double tauxApproves = total > 0 ? (approves * 100.0)/total : 0;

                    System.out.println(typeContrat + " " + total);
                    System.out.println("Score revenu : " + String.format("%.2f",averageScore));
                    System.out.println("Moyen revenu : " + String.format("%.0f",avergaeMoyen) + " DH");
                    System.out.println("taux approbation : " + String.format("%.2f",tauxApproves));
                } ));
    }
    public void usageBisniss(){
        List<Professionel> professionels = professionalService.getAllProfessionals();
        List<Credit> credits = creditRepository.getAllCredit();
        professionels.stream()
                .filter(professionel -> professionel.getScore() > 65 && professionel.getScore() < 85)
                .filter(professionel -> professionel.getRevenu() > 4000 && professionel.getRevenu() < 8000)
                .filter(professionel -> {
                    int age = Period.between(professionel.getDateNaissance() , LocalDate.now()).getYears();
                    return age > 28 && age < 45;
                })
                .flatMap(professionel ->
                     credits.stream()
                            .filter(credit -> credit.getPersonId().equals(professionel.getId()))
                            .filter(credit -> {
                                int months = Period.between(credit.getDateDeCredit(),LocalDate.now()).getYears()*12 +
                                        Period.between(credit.getDateDeCredit() , LocalDate.now()).getMonths();
                                return months > credit.getDureEnMois();
                            })
                            .map(credit -> professionel)
                )
                .distinct()
                .forEach(System.out::println);
    }

}
