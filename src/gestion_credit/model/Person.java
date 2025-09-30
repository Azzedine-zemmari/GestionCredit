package gestion_credit.model;

import gestion_credit.utils.enums.SituationFamilly;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Person {
    protected UUID id;
    protected String nom;
    protected String prenom;
    protected LocalDate dateNaissance;
    protected String ville;
    protected Integer nombreEnfants;
    protected Boolean invistisement;
    protected Boolean placement;
    protected SituationFamilly situationFamilly;
    protected LocalDate created_at;
    protected Integer score;

    public Person(UUID id, String nom, String prenom, LocalDate dateNaissance, String ville, Integer nombreEnfants,Boolean invistisement, Boolean placement, SituationFamilly situationFamilly, LocalDate created_at, Integer score) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.nombreEnfants = nombreEnfants;
        this.placement = placement;
        this.invistisement = invistisement;
        this.situationFamilly = situationFamilly;
        this.created_at = created_at;
        this.score = score;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getNombreEnfants() {
        return nombreEnfants;
    }

    public void setNombreEnfants(Integer nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }

    public Boolean getPlacement() {
        return placement;
    }

    public void setPlacement(Boolean placement) {
        this.placement = placement;
    }

    public SituationFamilly getSituationFamilly() {
        return situationFamilly;
    }

    public void setSituationFamilly(SituationFamilly situationFamilly) {
        this.situationFamilly = situationFamilly;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public Boolean getInvistisement(){
        return  this.invistisement;
    }
    public void setInvistisement(Boolean invistisement){
        this.invistisement = invistisement;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", ville='" + ville + '\'' +
                ", nombreEnfants=" + nombreEnfants +
                ", placement='" + placement + '\'' +
                ", situationFamilly=" + situationFamilly +
                ", created_at=" + created_at +
                ", score=" + score +
                '}';
    }
}
