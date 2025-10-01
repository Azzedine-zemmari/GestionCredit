package gestion_credit.model;

import gestion_credit.utils.enums.StatusPaiment;

import java.time.LocalDate;
import java.util.UUID;

public class Incident {
    private UUID id;
    private LocalDate dateIncident;
    private Integer scoreImpact;
    private StatusPaiment typeIncident;
    private UUID echeance_id;

    public Incident(UUID id, LocalDate dateIncident, Integer scoreImpact, StatusPaiment typeIncident,UUID echeance_id) {
        this.id = id;
        this.dateIncident = dateIncident;
        this.scoreImpact = scoreImpact;
        this.typeIncident = typeIncident;
        this.echeance_id = echeance_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateIncident() {
        return dateIncident;
    }

    public void setDateIncident(LocalDate dateIncident) {
        this.dateIncident = dateIncident;
    }

    public Integer getScoreImpact() {
        return scoreImpact;
    }

    public void setScoreImpact(Integer scoreImpact) {
        this.scoreImpact = scoreImpact;
    }

    public StatusPaiment getTypeIncident() {
        return typeIncident;
    }

    public void setTypeIncident(StatusPaiment typeIncident) {
        this.typeIncident = typeIncident;
    }

    public UUID getEcheance_id() {
        return echeance_id;
    }

    public void setEcheance_id(UUID echeance_id) {
        this.echeance_id = echeance_id;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", dateIncident=" + dateIncident +
                ", scoreImpact=" + scoreImpact +
                ", typeIncident=" + typeIncident +
                ", EcheanceId=" + echeance_id +
                '}';
    }
}
