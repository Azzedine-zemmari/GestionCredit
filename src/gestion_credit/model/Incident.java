package gestion_credit.model;

import gestion_credit.utils.enums.StatusPaiment;

import java.time.LocalDate;
import java.util.UUID;

public class Incident {
    private UUID id;
    private LocalDate dateIncident;
    private Integer scoreImpact;
    private StatusPaiment typeIncident;

    public Incident(UUID id, LocalDate dateIncident, Integer scoreImpact, StatusPaiment typeIncident) {
        this.id = id;
        this.dateIncident = dateIncident;
        this.scoreImpact = scoreImpact;
        this.typeIncident = typeIncident;
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

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", dateIncident=" + dateIncident +
                ", scoreImpact=" + scoreImpact +
                ", typeIncident=" + typeIncident +
                '}';
    }
}
