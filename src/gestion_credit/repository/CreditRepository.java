package gestion_credit.repository;

import gestion_credit.model.Credit;
import gestion_credit.utils.connnection.Connect;
import gestion_credit.utils.enums.Decision;
import gestion_credit.utils.enums.PersonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class CreditRepository {
    private Connection connection;
    public CreditRepository(){
        try{
            connection = Connect.getInstance();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void creeCredit(Credit credit){
        String sql = "insert into credit(id,date_de_credit,montant_octroye,taux_interet,duree_en_mois,type_credit,decision,person_id,person_type) values(?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,credit.getId());
            stmt.setDate(2,java.sql.Date.valueOf(credit.getDateDeCredit()));
            stmt.setDouble(3,credit.getMontantOctroye());
            stmt.setDouble(4,credit.getTauxInteret());
            stmt.setInt(5,credit.getDureEnMois());
            stmt.setString(6,credit.getTypeCredit());
            stmt.setString(7, credit.getDecision().name());
            stmt.setObject(8,credit.getPersonId());
            stmt.setObject(9,credit.getPersonType().name());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erreur " + e);
        }
    }
    public void updateCredit(Credit credit){
        String sql = "UPDATE credit SET date_de_credit = ?, montant_octroye = ?, taux_interet = ?, duree_en_mois = ?, type_credit = ?, decision = ?, person_id = ?, person_type = ? WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1, java.sql.Date.valueOf(credit.getDateDeCredit()));
            stmt.setDouble(2, credit.getMontantOctroye());
            stmt.setDouble(3, credit.getTauxInteret());
            stmt.setInt(4, credit.getDureEnMois());
            stmt.setString(5, credit.getTypeCredit());
            stmt.setString(6, credit.getDecision().name());
            stmt.setObject(7, credit.getPersonId());
            stmt.setString(8, credit.getPersonType().name());
            stmt.setObject(9, credit.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la mise à jour du crédit : " + e);
        }
    }


}
