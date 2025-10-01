package gestion_credit.repository;

import gestion_credit.model.Echeance;
import gestion_credit.utils.connnection.Connect;
import gestion_credit.utils.enums.StatusPaiment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EcheanceRepository {
    private Connection connection;
    public EcheanceRepository(){
        try{
            connection = Connect.getInstance();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    // CREATE
    public void createEcheance(Echeance echeance) {
        String sql = "INSERT INTO echeance(id, date_encheance, mensualite, date_paiment, status_paiment, credit_id) " +
                "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, echeance.getId());
            stmt.setDate(2, Date.valueOf(echeance.getDateEncheance()));
            stmt.setDouble(3, echeance.getMensualite());
            stmt.setDate(4, Date.valueOf(echeance.getDatePaiment()));
            stmt.setString(5, echeance.getStatusPaiment().name());
            stmt.setObject(6, echeance.getCreditId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur createEcheance: " + e);
        }
    }

    // UPDATE
    public void updateEcheance(Echeance echeance) {
        String sql = "UPDATE echeance SET date_encheance=?, mensualite=?, date_paiment=?, status_paiment=?, credit_id=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(echeance.getDateEncheance()));
            stmt.setDouble(2, echeance.getMensualite());
            stmt.setDate(3, Date.valueOf(echeance.getDatePaiment()));
            stmt.setString(4, echeance.getStatusPaiment().name());
            stmt.setObject(5, echeance.getCreditId());
            stmt.setObject(7, echeance.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur updateEcheance: " + e);
        }
    }

    // DELETE
    public void deleteEcheance(UUID id) {
        String sql = "DELETE FROM echeance WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur deleteEcheance: " + e);
        }
    }

    // GET BY ID
    public Echeance getEcheanceById(UUID id) {
        String sql = "SELECT * FROM echeance WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEcheance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur getEcheanceById: " + e);
        }
        return null;
    }

    // GET ALL
    public List<Echeance> getAllEcheances() {
        List<Echeance> echeances = new ArrayList<>();
        String sql = "SELECT * FROM echeance";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                echeances.add(mapResultSetToEcheance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur getAllEcheances: " + e);
        }
        return echeances;
    }

    // HELPER METHOD
    private Echeance mapResultSetToEcheance(ResultSet rs) throws SQLException {
        return new Echeance(
                (UUID) rs.getObject("id"),
                rs.getDate("date_encheance").toLocalDate(),
                rs.getDouble("mensualite"),
                rs.getDate("date_paiment").toLocalDate(),
                StatusPaiment.valueOf(rs.getString("status_paiment")),
                (UUID) rs.getObject("credit_id")
        );
    }

}
