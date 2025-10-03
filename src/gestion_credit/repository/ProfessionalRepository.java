package gestion_credit.repository;

import gestion_credit.model.Professionel;
import gestion_credit.utils.connnection.Connect;
import gestion_credit.utils.enums.SituationFamilly;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfessionalRepository {
    private Connection connection;

    public ProfessionalRepository() {
        try {
            this.connection = Connect.getInstance();
        } catch (SQLException e) {
            System.out.println("erreur " + e);
        }
    }
    public void createProfessional(Professionel p){
        String sql = "Insert into professional(id,nom,prenom,date_naissance,ville,nombre_enfants,invistisement,placement,situation_familly,created_at,score,revenu,immatriculation_Fiscale,secteurActivite,activite) VALUES(?,?,?,?,?,?,?,?,cast(? as situation_familly),?,?,?,?,?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1, p.getId());
            stmt.setString(2, p.getNom());
            stmt.setString(3, p.getPrenom());
            stmt.setDate(4, java.sql.Date.valueOf(p.getDateNaissance()));
            stmt.setString(5, p.getVille());
            stmt.setInt(6, p.getNombreEnfants());
            stmt.setBoolean(7, p.getInvistisement());
            stmt.setBoolean(8, p.getPlacement());
            stmt.setString(9, p.getSituationFamilly().name()); // enum -> string
            stmt.setDate(10, java.sql.Date.valueOf(p.getCreated_at()));
            stmt.setInt(11, p.getScore());
            stmt.setDouble(12,p.getRevenu());
            stmt.setString(13,p.getImmatriculationFiscale());
            stmt.setString(14,p.getSecteurActivite());
            stmt.setString(15,p.getActivite());
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("erreur "+ e);
        }
    }
    public void modifierProfessional(Professionel professionel){
        String sql = "UPDATE professional SET revenu = ? , ville = ? , situation_familly = CAST(? as situation_familly) , score = ? where id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDouble(1,professionel.getRevenu());
            stmt.setString(2,professionel.getVille());
            stmt.setString(3,professionel.getSituationFamilly().name());
            stmt.setInt(4,professionel.getScore());
            stmt.setObject(5,professionel.getId());
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("erreur " + e);
        }
    }

    public Professionel consulterProfileProfessionel(UUID id) {
        String sql = "SELECT * FROM professional WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Professionel(
                        (UUID) rs.getObject("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("date_naissance").toLocalDate(),
                        rs.getString("ville"),
                        rs.getInt("nombre_enfants"),
                        rs.getBoolean("invistisement"),
                        rs.getBoolean("placement"),
                        SituationFamilly.valueOf(rs.getString("situation_familly")),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getInt("score"),
                        rs.getDouble("revenu"),
                        rs.getString("immatriculation_fiscale"),
                        rs.getString("secteurActivite"),
                        rs.getString("activite")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur pour consulter Professionel: " + e);
        }

        return null;
    }

    public void supprimerProfessionall(UUID id){
        String sql = "DELETE FROM professional where id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erreur " + e);
        }
    }

    public List<Professionel> afficherAllProfessional(){
        ArrayList<Professionel> professionels = new ArrayList<>();
        String sql = "Select * from professional";
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                professionels.add(new Professionel(
                        (UUID) rs.getObject("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("date_naissance").toLocalDate(),
                        rs.getString("ville"),
                        rs.getInt("nombre_enfants"),
                        rs.getBoolean("invistisement"),
                        rs.getBoolean("placement"),
                        SituationFamilly.valueOf(rs.getString("situation_familly")),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getInt("score"),
                        rs.getDouble("revenu"),
                        rs.getString("immatriculation_fiscale"),
                        rs.getString("secteurActivite"),
                        rs.getString("activite")
                ));
            }
            return professionels;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("erreur  " + e);
        }
        return null;
    }

}
