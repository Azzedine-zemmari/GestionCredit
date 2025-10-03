package gestion_credit.repository;

import gestion_credit.model.Echeance;
import gestion_credit.utils.connnection.Connect;
import gestion_credit.utils.enums.StatusPaiment;
import gestion_credit.utils.enums.TypeContrat;

import java.security.spec.EncodedKeySpec;
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
        String sql = "INSERT INTO echeance(id, dateencheance, mensualite, datepaiment, status_paiment, credit_id) VALUES(?,?,?,?,CAST(? as status_paiment),?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, echeance.getId());
            stmt.setDate(2, Date.valueOf(echeance.getDateEncheance()));
            stmt.setDouble(3, echeance.getMensualite());
            if(echeance.getDatePaiment() != null){
            stmt.setDate(4, Date.valueOf(echeance.getDatePaiment()));
            }else{
                stmt.setNull(4, Types.DATE);
            }
            if(echeance.getStatusPaiment() != null){
            stmt.setString(5, echeance.getStatusPaiment().name());
            }else{
            stmt.setNull(5,Types.VARCHAR);
            }
            stmt.setObject(6, echeance.getCreditId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur createEcheance: " + e);
        }
    }

    // UPDATE
    public void updateEcheance(Echeance echeance) {
        String sql = "UPDATE echeance SET dateencheance=?, mensualite=?, datepaiment=?, status_paiment=?, credit_id=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(echeance.getDateEncheance()));
            stmt.setDouble(2, echeance.getMensualite());
            if (echeance.getDatePaiment() != null) {
                stmt.setDate(3, Date.valueOf(echeance.getDatePaiment()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }
            if (echeance.getStatusPaiment() != null) {
                stmt.setObject(4, echeance.getStatusPaiment(), java.sql.Types.OTHER); // Pass ENUM directly
            } else {
                stmt.setNull(4, java.sql.Types.OTHER);
            }
            stmt.setObject(5, echeance.getCreditId());
            stmt.setObject(6, echeance.getId());
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
//    Get all echances by credit id
    public List<Echeance> getAllEcheancesByCreditId(UUID creditId){
        List<Echeance> echeances = new ArrayList<>();
        String sql = "SELECT * FROM echeance where credit_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,creditId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                echeances.add(mapResultSetToEcheance(rs));
            }
            return echeances;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  null;
    }

    // HELPER METHOD
    private Echeance mapResultSetToEcheance(ResultSet rs) throws SQLException {
        return new Echeance(
                (UUID) rs.getObject("id"),
                rs.getDate("dateencheance").toLocalDate(),
                rs.getDouble("mensualite"),
                rs.getDate("datepaiment") != null ? rs.getDate("datepaiment").toLocalDate() : null,
                rs.getString("status_paiment") != null ? StatusPaiment.valueOf(rs.getString("status_paiment")) : null,
                (UUID) rs.getObject("credit_id")
        );
    }

}
