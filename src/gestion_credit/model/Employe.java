package gestion_credit.model;

import gestion_credit.utils.Secteur;
import gestion_credit.utils.SituationFamilly;
import gestion_credit.utils.TypeContrat;

import java.time.LocalDate;
import java.util.UUID;

public class Employe extends Person{
    private Double salaire;
    private Integer ancienneté;
    private String post;
    private TypeContrat typeContrat;
    private Secteur secteur;

    public Employe(UUID id, String nom, String prenom, LocalDate dateNaissance, String ville, Integer nombreEnfants, String placement, SituationFamilly situationFamilly, LocalDate created_at, Integer score, Double salaire, Integer ancienneté , String post ,TypeContrat typeContrat, Secteur secteur){
        super(id,nom,prenom,dateNaissance,ville,nombreEnfants,placement,situationFamilly,created_at,score);
        this.salaire = salaire;
        this.ancienneté = ancienneté;
        this.post = post;
        this.typeContrat = typeContrat;
        this.secteur = secteur;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Integer getAncienneté() {
        return ancienneté;
    }

    public void setAncienneté(Integer ancienneté) {
        this.ancienneté = ancienneté;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    @Override
    public String toString() {
        return "Employe{" +
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
                "salaire=" + salaire +
                ", ancienneté=" + ancienneté +
                ", post='" + post + '\'' +
                ", typeContrat=" + typeContrat +
                ", secteur=" + secteur +
                '}';
    }
}
