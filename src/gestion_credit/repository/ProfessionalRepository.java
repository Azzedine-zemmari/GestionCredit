package gestion_credit.repository;

import gestion_credit.model.Professionel;
import gestion_credit.utils.connnection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        String sql = "Insert into professional(id,nom,prenom,date_naissance,ville,nombre_enfants,invistisement,placement,situation_familly,created_at,score,revenu,immatriculation_Fiscale,secteurActivite,activite) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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

}
