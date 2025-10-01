package gestion_credit.repository;

import gestion_credit.model.Credit;
import gestion_credit.utils.connnection.Connect;
import gestion_credit.utils.enums.Decision;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            stmt.setObject(8,credit.getPersonType().name());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erreur " + e);
        }
    }

}
