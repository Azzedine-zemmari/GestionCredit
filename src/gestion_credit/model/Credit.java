package gestion_credit.model;

import gestion_credit.utils.enums.Decision;
import gestion_credit.utils.enums.PersonType;

import java.time.LocalDate;
import java.util.UUID;

public class Credit {
    private UUID id;
    private LocalDate dateDeCredit;
    private Double montantOctroye;
    private Double tauxInteret;
    private Integer dureEnMois;
    private String typeCredit;
    private Decision decision;
    private UUID personId;
    private PersonType personType;

    public Credit(UUID id, LocalDate dateDeCredit, Double montantOctroye, Double tauxInteret, Integer dureEnMois, String typeCredit, Decision decision,UUID personId,PersonType personType) {
        this.id = id;
        this.dateDeCredit = dateDeCredit;
        this.montantOctroye = montantOctroye;
        this.tauxInteret = tauxInteret;
        this.dureEnMois = dureEnMois;
        this.typeCredit = typeCredit;
        this.decision = decision;
        this.personId = personId;
        this.personType = personType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateDeCredit() {
        return dateDeCredit;
    }

    public void setDateDeCredit(LocalDate dateDeCredit) {
        this.dateDeCredit = dateDeCredit;
    }

    public Double getMontantOctroye() {
        return montantOctroye;
    }

    public void setMontantOctroye(Double montantOctroye) {
        this.montantOctroye = montantOctroye;
    }

    public Double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Integer getDureEnMois() {
        return dureEnMois;
    }

    public void setDureEnMois(Integer dureEnMois) {
        this.dureEnMois = dureEnMois;
    }

    public String getTypeCredit() {
        return typeCredit;
    }

    public void setTypeCredit(String typeCredit) {
        this.typeCredit = typeCredit;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }
    public UUID getPersonId(){
        return personId;
    }
    public void setPersonId(UUID personId){
        this.personId = personId;
    }
    public PersonType getPersonType(){
        return this.personType;
    }
    public void setPersonType(PersonType personType){
        this.personType = personType;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", dateDeCredit=" + dateDeCredit +
                ", montantOctroye=" + montantOctroye +
                ", tauxInteret=" + tauxInteret +
                ", dureEnMois=" + dureEnMois +
                ", typeCredit='" + typeCredit + '\'' +
                ", decision=" + decision +
                ", personId=" + personId +
                ". personType=" + personType +
                '}';
    }
}
