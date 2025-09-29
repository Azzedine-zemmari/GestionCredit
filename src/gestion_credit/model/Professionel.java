package gestion_credit.model;

import gestion_credit.utils.SituationFamilly;

import java.time.LocalDate;
import java.util.UUID;

public class Professionel extends Person{
    private Double revenu;
    private String immatriculationFiscale;
    private String secteurActivite;
    private String activite;

    public Professionel(UUID id, String nom, String prenom, LocalDate dateNaissance, String ville, Integer nombreEnfants, String placement, SituationFamilly situationFamilly, LocalDate created_at, Integer score, Double revenu, String immatriculationFiscale, String secteurActivite, String activite) {
        super(id, nom, prenom, dateNaissance, ville, nombreEnfants, placement, situationFamilly, created_at, score);
        this.revenu = revenu;
        this.immatriculationFiscale = immatriculationFiscale;
        this.secteurActivite = secteurActivite;
        this.activite = activite;
    }

    public Double getRevenu() {
        return revenu;
    }

    public void setRevenu(Double revenu) {
        this.revenu = revenu;
    }

    public String getImmatriculationFiscale() {
        return immatriculationFiscale;
    }

    public void setImmatriculationFiscale(String immatriculationFiscale) {
        this.immatriculationFiscale = immatriculationFiscale;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    @Override
    public String toString() {
        return "Professionel{" +
                ", id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", ville='" + ville + '\'' +
                ", nombreEnfants=" + nombreEnfants +
                ", placement='" + placement + '\'' +
                ", situationFamilly=" + situationFamilly +
                ", created_at=" + created_at +
                ", score=" + score +
                "revenu=" + revenu +
                ", immatriculationFiscale='" + immatriculationFiscale + '\'' +
                ", secteurActivite='" + secteurActivite + '\'' +
                ", activite='" + activite + '\'' +
                '}';
    }
}
