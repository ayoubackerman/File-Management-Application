/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import Classe.Files;

import ConnectionDB.DBconnection;
import com.mysql.jdbc.Statement;
import iService.IServiceFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Dahmen
 */
public class ServiceFile  implements IServiceFile<Files>{

    private final Connection con=DBconnection.getCnx();
    private Statement ste;

    public ServiceFile() {
     

    }

    @Override
    public void ajouter(Files t) throws SQLException {
        
         ste = (Statement) con.createStatement();
        
         String requeteInsert = "INSERT INTO files.`file` (id_file, auteur,`titre`,`resume`,`commentaire`) VALUES (NULL, '" + t.getId_file()+"', '" + t.getTitre() + "', '" + t.getResume()+"', '" + t.getCommentaire()+"');";
        ste.executeUpdate(requeteInsert);
    }
    
    

    
    
  @Override
    public boolean delete(Files t) throws SQLException {
        if (search(t)==true){
         ste =(Statement) con.createStatement();
         String requeteDelete ="DELETE FROM file WHERE id_file="+ t.getId_file();
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("Fichier n'existe pas");
        }
         return true;
    }

 @Override
    public boolean update(Files t) throws SQLException {
        
        if (search(t)==true){
        PreparedStatement pre=con.prepareStatement("UPDATE files.`file` SET id_file = ?, auteur = ?,titre = ?,`resume` = ?,`commentaire` = ? WHERE 'id_file'=? ; ");
        pre.setInt(1,t.getId_file());
        pre.setString(2,t.getAuteur());
        pre.setString(3,t.getTitre()); 
        
        pre.setString(5,t.getResume());
        pre.setString(6,t.getCommentaire());
        
        pre.setInt(11,t.getId_file());
        pre.executeUpdate();
        return true;}
          else{
           System.out.println("Fichier n'existe pas");
           return true;
        }
    }

 @Override
    public boolean search(Files u) throws SQLException {
     
        ste= (Statement) con.createStatement();
    ResultSet rs=ste.executeQuery("select * from file");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
         if (rs.getInt(1)==u.getId_file())
             ok=true;
     }
     return ok;
    }    }



