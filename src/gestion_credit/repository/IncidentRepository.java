package gestion_credit.repository;

import gestion_credit.model.Incident;
import gestion_credit.utils.connnection.Connect;
import gestion_credit.utils.enums.StatusPaiment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IncidentRepository {
    private Connection connection;

    public IncidentRepository() {
        try {
            connection = Connect.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur de connection au IncidentRepository");
        }
    }

    // CREATE
    public void createIncident(Incident incident) {
        String sql = "INSERT INTO incident(id, dateincident, scoreimpact, type_incident, echeance_id) VALUES(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, incident.getId());
            stmt.setDate(2, Date.valueOf(incident.getDateIncident()));
            stmt.setInt(3, incident.getScoreImpact());
            stmt.setString(4, incident.getTypeIncident().name());
            stmt.setObject(5, incident.getEcheance_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur createIncident: " + e);
        }
    }

    // UPDATE
    public void updateIncident(Incident incident) {
        String sql = "UPDATE incident SET dateincident=?, scoreimpact=?, type_incident=?, echeance_id=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(incident.getDateIncident()));
            stmt.setInt(2, incident.getScoreImpact());
            stmt.setString(3, incident.getTypeIncident().name());
            stmt.setObject(4, incident.getEcheance_id());
            stmt.setObject(5, incident.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur updateIncident: " + e);
        }
    }

    // DELETE
    public void deleteIncident(UUID id) {
        String sql = "DELETE FROM incident WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur deleteIncident: " + e);
        }
    }

    // FIND BY ID
    public Incident findById(UUID id) {
        String sql = "SELECT * FROM incident WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToIncident(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur findById: " + e);
        }
        return null;
    }

    // FIND BY ECHEANCE_ID
    public List<Incident> findByEcheanceId(UUID echeanceId) {
        List<Incident> incidents = new ArrayList<>();
        String sql = "SELECT * FROM incident WHERE echeance_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, echeanceId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                incidents.add(mapResultSetToIncident(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur findByEcheanceId: " + e);
        }
        return incidents;
    }

    // HELPER
    private Incident mapResultSetToIncident(ResultSet rs) throws SQLException {
        return new Incident(
                (UUID) rs.getObject("id"),
                rs.getDate("dateincident").toLocalDate(),
                rs.getInt("scoreimpact"),
                StatusPaiment.valueOf(rs.getString("type_incident")),
                (UUID) rs.getObject("echeance_id")
        );
    }
}
