/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfx;
import Classe.User;
import ConnectionDB.DBconnection;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dahmen
 */


public class InscriptionController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField pren;
    @FXML
    private TextField mail;
    @FXML
    private TextField numero;
    @FXML
    private PasswordField mdp;
    
   
    @FXML
    private Button SaveData;
      
 Connection connection =null;
    PreparedStatement preparedStatement=null;
    public InscriptionController() {connection = DBconnection.getCnx();}
    


    //Procedure permet d'inserer un utilisateur dans la base
    private void EnregistrerVersBase() {
             
           try {
            String st = "INSERT INTO user (role,nom,prenom,mail,nomd,mdp) VALUES ('utilisateur',?,?,?,?,?)";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, nom.getText());
            preparedStatement.setString(2, pren.getText());
            preparedStatement.setString(3, mail.getText());
            preparedStatement.setString(4, numero.getText());
            preparedStatement.setString(5, mdp.getText());
        
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
  
        // TODO
    }    
//Boutton pour retourner a l'interface de login
    @FXML
    private void Retourner(
            ActionEvent event) throws IOException {  Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    
                    stage.show();  
    }
    
    
//Boutton pour inscrir l'utilisateur
    @FXML
    private void Enregistrer(ActionEvent event) throws SQLException {
         try{
                
           if (mail.getText().isEmpty()|| nom.getText().isEmpty() || pren.getText().isEmpty() || mdp.getText().isEmpty() || numero.getText().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier les paramétres");
            
            alert.setContentText("Remplir tous les paramétres");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(VerifNomDutil())  {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Changer votre nom d'utilisateur");
            
            alert.setContentText("Nom d'utilisateur déja utilisé ");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(!VerifEmail(mail.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Email incorrecte");
            
            alert.setContentText("Veuillez verifier la structure d'email");
            Optional<ButtonType> result = alert.showAndWait();
           }
           
           
         else {
               //verifier l'email s'il existe our pas 
            String sql = "select * from user where mail = ?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1,mail.getText());
            
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("email deja existe");
            
            alert.setContentText("vous etes deja inscrit avec ce email");
            Optional<ButtonType> result = alert.showAndWait();}
            
            
            else{        
           EnregistrerVersBase();
           JOptionPane.showMessageDialog(null,"Votre compte a été créé avec succès");
           
           nom.clear();
           pren.clear();
           mail.clear();
           
           numero.clear();
           mdp.clear();
           User u= new User();
           u.setMail(mail.getText());
           u.setNom(nom.getText());
           u.setPrenom(pren.getText());
           u.setMdp(mdp.getText());
           u.setNomd(numero.getText());
           
        }
           }
       
         }catch(HeadlessException | SQLException e){JOptionPane.showMessageDialog(null,"Error");}
        
    }
    //Procedure pour verifier la construction d'email
          public  boolean VerifEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
 //Verifier le nom d'utilisateur s'il existe ou pas 
  public boolean VerifNomDutil() throws SQLException{
      
            String sql = "select * from user where Nomd=?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1,numero.getText());
            ResultSet rs = preparedStatement.executeQuery();
     boolean ok=false;
     if (rs.next()) {         
         if (rs.getString(6).equals(numero.getText()))
             ok=true;
     }
     return ok;
          }
          
}
