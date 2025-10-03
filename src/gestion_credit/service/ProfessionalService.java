package gestion_credit.service;

import gestion_credit.model.Professionel;
import gestion_credit.repository.ProfessionalRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProfessionalService {

    private final ProfessionalRepository professionalRepository = new ProfessionalRepository();

    public boolean createProfessional(Professionel professionel) {
        try {
            professionalRepository.createProfessional(professionel);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du professionnel : " + e.getMessage());
            return false;
        }
    }

    public boolean updateProfessional(Professionel professionel) {
        try {
            professionalRepository.modifierProfessional(professionel);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour du professionnel : " + e.getMessage());
            return false;
        }
    }

    public Optional<Professionel> getProfessionalById(UUID id) {
        try {
            Professionel pro = professionalRepository.consulterProfileProfessionel(id);
            return Optional.ofNullable(pro);
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération du professionnel : " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean deleteProfessional(UUID id) {
        try {
            professionalRepository.supprimerProfessionall(id);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression du professionnel : " + e.getMessage());
            return false;
        }
    }

    public List<Professionel> getAllProfessionals() {
        try {
            return professionalRepository.afficherAllProfessional();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des professionnels : " + e.getMessage());
            return null;
        }
    }
}
