/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Classe.User;
import ConnectionDB.DBconnection;
import com.mysql.jdbc.Statement;
import iService.IServiceUser;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Dahmen
 */

public class ServiceUser  implements IServiceUser<User>{

    private final Connection con=DBconnection.getCnx();
    private Statement ste;

    public ServiceUser() {
     

    }

    @Override
    public void ajouter(User t) throws SQLException {
         ste = (Statement) con.createStatement();
        
         String requeteInsert = "INSERT INTO files.`user` (id, role,`nom`, prenom,`mail`,`nomd`,`mdp`) VALUES (NULL, '" + t.getRole()+"', '" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getMail()+"', '" + t.getNomd()+"', '" + t.getMdp()+"');";
        ste.executeUpdate(requeteInsert);
    }
    
    
    @Override
    public List<User> readAll() throws SQLException {
        List<User> arr=new ArrayList<>();
    ste=(Statement)con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String role=rs.getString(2);
               String nom=rs.getString(3);
               String prenom=rs.getString(4);
               String mail=rs.getString(5);
               String nomd=rs.getString(6);
               String mdp=rs.getString(7);
               
               User p=new User(id,role,nom,prenom,mail,nomd,mdp);
     arr.add(p);
     }
    return arr;

    }
    
  @Override
    public boolean delete(User t) throws SQLException {
        if (search(t)==true){
         ste =(Statement) con.createStatement();
         String requeteDelete ="DELETE FROM user WHERE id="+ t.getId();
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;
    }

 @Override
    public boolean update(User t) throws SQLException {
        
        if (search(t)==true){
        PreparedStatement pre=con.prepareStatement("UPDATE files.`user` SET ID = ?, role = ?,nom = ?,`prenom` = ?,`mail` = ?,`nomd` = ?,`mdp` = ? WHERE `Id`=? ;");
        pre.setInt(1,t.getId());
        pre.setString(2,t.getRole());
        pre.setString(3,t.getNom()); 
        pre.setString(4,t.getPrenom());
        pre.setString(5,t.getMail());
        pre.setString(6,t.getNomd());
        pre.setString(7,t.getMdp());
        
        pre.setInt(11,t.getId());
        pre.executeUpdate();
        return true;}
          else{
           System.out.println("L'utulisateur n'existe pas");
           return true;
        }
    }

 @Override
    public boolean search(User u) throws SQLException {
     
        ste= (Statement) con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
         if (rs.getInt(1)==u.getId())
             ok=true;
     }
     return ok;
    }
       public User login(String email , String mdpasse, String nomdu) throws SQLException {
        User p=new User();
        PreparedStatement pre = con.prepareStatement("select * from user WHERE mail=? or Nomd=? and mdp=? ");
         pre.setString(1, email);
         pre.setString(2, nomdu);
         pre.setString(3, mdpasse);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String role = rs.getString(2);
            String nom = rs.getString(3);
            String prenom = rs.getString(4);
            String mail = rs.getString(5);
            String nomd = rs.getString(6);
            String mdp = rs.getString(7);
            
            p = new User(id, role, nom, prenom, mail, nomd, mdp);
            User.connecte=new User(id, role, nom, prenom, mail, nomd, mdp);
        }
        return p;
    }
     
}


