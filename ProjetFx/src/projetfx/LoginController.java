/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfx;

import Classe.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Dahmen
 */
public class LoginController implements Initializable {
    @FXML
    private AnchorPane anch;
    @FXML
    private TextField mailField;
    @FXML
    private PasswordField mdpField;
    @FXML
    private Button Connect;
     
    
    

    /**
     * Initializes the controller class.
     * @param event
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
     @FXML
    private Hyperlink reg;
//HyperText pour acceder a l'interface d'inscription
    @FXML
    void Register(ActionEvent event) {
    try {
                     Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Inscription.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Inscription");
                    stage.show();  
                    
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

    }
    }
//Boutton pour connecter 
    @FXML
    public void Connexion(ActionEvent event) throws SQLException, IOException {

        ServiceUser met = new ServiceUser();
        User u = met.login(mailField.getText(), mdpField.getText(),mailField.getText());
        if (u.getId() == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Connexion echoué");
            alert.setHeaderText("Connexion impossible!!");
            alert.setContentText("Vérifier vos paramétres");
            Optional<ButtonType> result = alert.showAndWait();

        } else if(u.getRole().equals("utilisateur")) {

           Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AcceuilUtilisateur.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Espace Utilisateur");
                    stage.show(); 
        } else if(u.getRole().equals("admin")) {

            Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AcceuilAdmin.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Espace Admin");
                    stage.show(); 
        } 

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
// TODO

    }

}
