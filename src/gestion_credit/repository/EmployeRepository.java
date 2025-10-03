package gestion_credit.repository;

import gestion_credit.model.Employe;
import gestion_credit.utils.connnection.Connect;
import gestion_credit.utils.enums.Secteur;
import gestion_credit.utils.enums.SituationFamilly;
import gestion_credit.utils.enums.TypeContrat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeRepository {
    private Connection connection;
    public EmployeRepository(){
        try{
            this.connection = Connect.getInstance();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("failed to connect db "+ e);
        }
    }
    public void createEmploye(Employe employe){
        String sql = "INSERT INTO employe(id,nom,prenom,date_naissance,ville,nombre_enfants,invistisement,placement,situation_familly,created_at,score,salaire,anciennete,post,type_contrat,secteur) VALUES(?,?,?,?,?,?,?,?,CAST(? AS situation_familly),?,?,?,?,?,CAST(? as typeContrat),cast(? as secteur))";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1, employe.getId());
            stmt.setString(2, employe.getNom());
            stmt.setString(3, employe.getPrenom());
            stmt.setDate(4, java.sql.Date.valueOf(employe.getDateNaissance()));
            stmt.setString(5, employe.getVille());
            stmt.setInt(6, employe.getNombreEnfants());
            stmt.setBoolean(7, employe.getInvistisement());
            stmt.setBoolean(8, employe.getPlacement());
            stmt.setString(9, employe.getSituationFamilly().name()); // enum -> string
            stmt.setDate(10, java.sql.Date.valueOf(employe.getCreated_at()));
            stmt.setInt(11, employe.getScore());
            stmt.setDouble(12, employe.getSalaire());
            stmt.setInt(13, employe.getAncienneté());
            stmt.setString(14, employe.getPost());
            stmt.setString(15, employe.getTypeContrat().name()); // enum -> string
            stmt.setString(16, employe.getSecteur().name());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erreur pour insert Employe " + e);
        }
    }
    public void modifierEmploye(Employe employe){
        String sql = "UPDATE employe SET salaire = ? , ville = ? , situation_familly = CAST(? as situation_familly) , score = ? where id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDouble(1,employe.getSalaire());
            stmt.setString(2,employe.getVille());
            stmt.setString(3,employe.getSituationFamilly().name());
            stmt.setInt(4,employe.getScore());
            stmt.setObject(5,employe.getId());
            stmt.executeUpdate();
            System.out.println("Modifier done");
        }catch (SQLException e){
            System.out.println("erreur f modifierEmploye" + e);
        }
    }
    public Employe consulterProfileEmploye(UUID id) {
        String sql = "SELECT * FROM employe WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employe(
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
                        rs.getDouble("salaire"),
                        rs.getInt("anciennete"),
                        rs.getString("post"),
                        TypeContrat.valueOf(rs.getString("type_contrat")),
                        Secteur.valueOf(rs.getString("secteur"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur pour consulter Employe: " + e);
        }

        return null;
    }
    public void supprimerEmploye(UUID id){
        String sql = "DELETE FROM employe where id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erreur " + e);
        }
    }
    public List<Employe> allClient(){
        ArrayList<Employe> employes = new ArrayList<>();
        String sql = "Select * from employe";
        try(Statement stmt = connection.createStatement()){
           ResultSet rs =  stmt.executeQuery(sql);
           while(rs.next()){
               employes.add(new Employe(
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
                       rs.getDouble("salaire"),
                       rs.getInt("anciennete"),
                       rs.getString("post"),
                       TypeContrat.valueOf(rs.getString("type_contrat")),
                       Secteur.valueOf(rs.getString("secteur"))
               ));
           }
           return employes;
        }catch (SQLException e){
            System.out.println("erreur " + e);
        }
        return null;
    }
}