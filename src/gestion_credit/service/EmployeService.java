package gestion_credit.service;

import gestion_credit.model.Employe;
import gestion_credit.repository.EmployeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmployeService {

    private final EmployeRepository employeRepository = new EmployeRepository();

    public boolean createEmploye(Employe employe) {
        try {
            employeRepository.createEmploye(employe);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de l'employé : " + e.getMessage());
            return false;
        }
    }

    public boolean updateEmploye(Employe employe) {
        try {
            employeRepository.modifierEmploye(employe);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour de l'employé : " + e.getMessage());
            return false;
        }
    }

    public Optional<Employe> getEmployeById(UUID id) {
        try {
            Employe employe = employeRepository.consulterProfileEmploye(id);
            return Optional.ofNullable(employe);
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération de l'employé : " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean deleteEmploye(UUID id) {
        try {
            employeRepository.supprimerEmploye(id);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'employé : " + e.getMessage());
            return false;
        }
    }

    public List<Employe> getAllEmployes() {
        try {
            return employeRepository.allClient();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des employés : " + e.getMessage());
            return null;
        }
    }
}
