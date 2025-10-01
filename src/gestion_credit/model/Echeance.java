package gestion_credit.model;

import gestion_credit.utils.enums.StatusPaiment;

import java.time.LocalDate;
import java.util.UUID;

public class Echeance {
    private UUID id;
    private LocalDate dateEncheance;
    private Double mensualite;
    private LocalDate datePaiment;
    private StatusPaiment statusPaiment;
    private UUID creditId;

    public Echeance(UUID id, LocalDate dateEncheance, Double mensualite, LocalDate datePaiment, StatusPaiment statusPaiment, UUID creditId) {
        this.id = id;
        this.dateEncheance = dateEncheance;
        this.mensualite = mensualite;
        this.datePaiment = datePaiment;
        this.statusPaiment = statusPaiment;
        this.creditId = creditId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateEncheance() {
        return dateEncheance;
    }

    public void setDateEncheance(LocalDate dateEncheance) {
        this.dateEncheance = dateEncheance;
    }

    public Double getMensualite() {
        return mensualite;
    }

    public void setMensualite(Double mensualite) {
        this.mensualite = mensualite;
    }

    public LocalDate getDatePaiment() {
        return datePaiment;
    }

    public void setDatePaiment(LocalDate datePaiment) {
        this.datePaiment = datePaiment;
    }

    public StatusPaiment getStatusPaiment() {
        return statusPaiment;
    }

    public void setStatusPaiment(StatusPaiment statusPaiment) {
        this.statusPaiment = statusPaiment;
    }

    public UUID getCreditId() {
        return creditId;
    }

    public void setCreditId(UUID creditId) {
        this.creditId = creditId;
    }


    @Override
    public String toString() {
        return "Echeance{" +
                "id=" + id +
                ", dateEncheance=" + dateEncheance +
                ", mensualite=" + mensualite +
                ", datePaiment=" + datePaiment +
                ", statusPaiment=" + statusPaiment +
                ", creditId=" + creditId +
                '}';
    }
}
