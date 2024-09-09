/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfx;

import Classe.Files;
import Classe.Tags;
import Classe.User;
import ConnectionDB.DBconnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dahmen
 */
public class AcceuilAdminController implements Initializable {

    @FXML
    private Tab liste_util;
    @FXML
    private TableView<User> tab;
    @FXML
    private TableColumn<User,String> name;
    @FXML
    private TableColumn<User,String> last;
    @FXML
    private TableColumn<User,String> mail;
    @FXML
    private Tab favor_util;
    @FXML
    private Tab mod_ad;
    @FXML
    private Tab tag_nbrutil;
    @FXML
    private Tab util_partag;
    @FXML
    private Button out;
    @FXML
    private Label bvn;


    @FXML
    private TableView<Files> Tabfich;

    @FXML
    private TableColumn<Files,String> idfich;

    @FXML
    private TableColumn<Files,String> aute;

    @FXML
    private TableColumn<Files,String> titr;

    @FXML
    private TableView<User> Listeutils;

    @FXML
    private TableColumn<User,String> idutil;

    @FXML
    private TableColumn<User,String> nomutil;

    @FXML
    private TableColumn<User,String> preutil;
    
    @FXML
    private Button cons;
    @FXML
    private TextField idd;
    
    @FXML
    private Label xs;
       @FXML
    private TableView<Tags> Tabfortag;
    @FXML
    private TableColumn<Tags,String> taggs;
    @FXML
    private TextField util_id;
    @FXML
    private Button Valid;
     @FXML
    private TableView<Tags> Tagdiff;
    @FXML
    private TableColumn<Tags,String> itag;
    @FXML
    private Label nbru;
    
    
com.mysql.jdbc.Connection connection =null;
     PreparedStatement preparedStatement=null;
    @FXML
    private Button Nbr;
    @FXML
    private TableView<User> Utiliss;
    @FXML
    private TableColumn<User,String> Nomutils;
    @FXML
    private TableColumn<User,String> prenutils;
    @FXML
    private TableColumn<User,String> ndutil;
    @FXML
    private TextField Cherchtag;
    @FXML
    private Button Cherutil;
   
 
   
    public AcceuilAdminController(){connection = DBconnection.getCnx();
}
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         name.setCellValueFactory( new PropertyValueFactory<>("nom"));
        last.setCellValueFactory(new PropertyValueFactory <>("prenom"));
        mail.setCellValueFactory(new PropertyValueFactory <>("mail"));
        tab.setItems(RecupBaseUtil()); 
        // TODO
         bvn.setText("Bienvenu"+" "+User.connecte.getNom()+" "+User.connecte.getPrenom());
         idutil.setCellValueFactory( new PropertyValueFactory<>("id"));
         nomutil.setCellValueFactory( new PropertyValueFactory<>("nom"));
        preutil.setCellValueFactory(new PropertyValueFactory <>("prenom"));
        Listeutils.setItems(RecupBaseUtil2()); 
         itag.setCellValueFactory(new PropertyValueFactory <>("tags"));
        Tagdiff.setItems(RecupererTags()); 
        idd.setDisable(true);
        idd.setVisible(false);
        
        
              Listeutils.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
        
    }
});
        
    }   
    
    
//Boutton de deconnexion
    @FXML
    private void Logout(ActionEvent event) {
         try {
                     Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.show();  
                    
        } catch (IOException ex) {
            Logger.getLogger(AcceuilAdminController.class.getName()).log(Level.SEVERE, null, ex);

    }
    }
    
    
    
    //Recuperer les tags déja utilisé
    public static ObservableList<Tags> RecupererTags(){
    ObservableList<Tags> list = FXCollections.observableArrayList();
    
    Connection connection;
     connection = DBconnection.getCnx();
          String sql = "select DISTINCT tag from tags ";
    try {
       
        PreparedStatement st = (PreparedStatement) connection.prepareStatement(sql);
    ResultSet R = st.executeQuery();
    while (R.next()){
      Tags t =new Tags();
    t.setTags((R.getString("tag")));
      
      list.add(t);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }  
    
    
    
    //Recuperer les utilisateurs
    public static ObservableList<User> RecupBaseUtil(){
    ObservableList<User> list = FXCollections.observableArrayList();
    String user = "utilisateur";
    Connection connection;
     connection = DBconnection.getCnx();
          String sql = "select *from user where Role ='"+user+"'";
    try {
       
        PreparedStatement st = (PreparedStatement) connection.prepareStatement(sql);
    ResultSet R = st.executeQuery();
    while (R.next()){
      User u =new User();
    u.setNom((R.getString("nom")));
      u.setPrenom(R.getString("prenom"));
     u.setMail(R.getString("mail"));
      list.add(u);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }  
    
    
    
    //Recuperer les utilisateurs
      public static ObservableList<User> RecupBaseUtil2(){
    ObservableList<User> list = FXCollections.observableArrayList();
    String user = "utilisateur";
    Connection connection;
     connection = DBconnection.getCnx();
          String sql = "select *from user where Role ='"+user+"'";
    try {
       
        PreparedStatement st = (PreparedStatement) connection.prepareStatement(sql);
    ResultSet R = st.executeQuery();
    while (R.next()){
      User u =new User();
      u.setId(R.getInt("id"));
      u.setNom((R.getString("nom")));
      u.setPrenom(R.getString("prenom"));
      
      list.add(u);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }  
      
      
      //Cette boutton permet d'affchier les fichiers d'un utilisateur selectioné
       @FXML
    void Consulter(ActionEvent event) {
        int selectedIndex = Listeutils.getSelectionModel().getSelectedIndex();
         if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun utilisateur selectionné!");
            alert.setContentText("Veuiller selectionner un utilisateur à consuter");
            Optional<ButtonType> result = alert.showAndWait();
        } else if(!idd.getText().isEmpty())
                {
                   String idf=idd.getText();
                 int i=Integer.valueOf(idf);  
              String sql = "select *from user where id ='"+i+"'";
    try {
       
        PreparedStatement st = (PreparedStatement) connection.prepareStatement(sql);
    ResultSet R = st.executeQuery();
     while (R.next()){
      String nom = R.getString("nom");
      String prenom = R.getString("prenom");
     xs.setText("Fichiers de"+" "+nom+" "+prenom);
      
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
             idfich.setCellValueFactory( new PropertyValueFactory<>("id_file"));
             aute.setCellValueFactory( new PropertyValueFactory<>("auteur"));
    titr.setCellValueFactory( new PropertyValueFactory<>("titre"));
    Tabfich.setItems(RecupBaseFicher(Integer.valueOf(idd.getText()))); 
             
             
             

           
        }
        else{
        JOptionPane.showMessageDialog(null,"Selectionner un utilisateur");
        
        }

    }
    //Cette procedure permet d'inserer l'id d'utilisateur selectioné dans un TextField
        public void onEdit() {
    
    if (Listeutils.getSelectionModel().getSelectedItem() != null) {
          User u = Listeutils.getSelectionModel().getSelectedItem();
         int i = u.getId();
        String n = String.valueOf(i);
        idd.setText(n);
        
      
            
    }
}
        //Recuperer les fichiers d'un utilisateur donné
    public static ObservableList<Files> RecupBaseFicher(int n){
    ObservableList<Files> list = FXCollections.observableArrayList();
    
    java.sql.Connection conn;
     conn = DBconnection.getCnx();
          String sql = "select *from file where id ='"+n+"'";
    try {
       
        PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
    ResultSet R = st.executeQuery();
    while (R.next()){
      Files f =new Files();
     f.setId_file((R.getInt(1)));
     f.setUrl(R.getString(3));
     f.setAuteur(R.getString(4));
     f.setTitre(R.getString(5));
     
      list.add(f);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    } 
    
    
    //Recuperer les tags utilisé par un utilisateur donné
    public static ObservableList<Tags> RecuperTagsParUtil(int n){
    ObservableList<Tags> tags = FXCollections.observableArrayList();
    
    java.sql.Connection conn;
     conn = DBconnection.getCnx();
          String sql = "SELECT * FROM  tags T,user U, file F WHERE U.id=F.id and F.id_file=T.id_file and U.id = '"+n+"' GROUP BY T.tag" ;
    try {
       
        PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
    ResultSet R = st.executeQuery();
    while (R.next()){
      Tags t =new Tags();
     t.setId_tag((R.getInt(1)));
     t.setId_file((R.getInt(2)));
     t.setTags(R.getString(3));
     
     
      tags.add(t);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return tags;
    }   
    
    
    
//Cette boutton permet d'afficher les tags d'un utilisateur donné
    @FXML
    private void Valider(ActionEvent event) {
       
      taggs.setCellValueFactory(new PropertyValueFactory <>("tags"));
        Tabfortag.setItems(RecuperTagsParUtil(Integer.valueOf(util_id.getText())));  
    }
    
    
    
 //Cette procedure permet de compter le nombre d'utilisateur qui a utilisé un tag donné
public void NombreUtil() {
    
    if (Tagdiff.getSelectionModel().getSelectedItem() != null) {
          Tags f = Tagdiff.getSelectionModel().getSelectedItem();
        String ta = f.getTags();
          String count = "SELECT COUNT(*) As NB FROM user WHERE id in(SELECT (id) from file Where id_file in(select id_file from tags WHERE tag = '"+ta+"')) ";
        try {
         PreparedStatement st = (PreparedStatement) connection.prepareStatement(count);
          ResultSet r = st.executeQuery();
        if (r.next()){
        nbru.setText(r.getString("NB")+" "+"Utilisateurs utilise ce tag");
        }
        else {System.out.println("ghalta el requete");}
        }catch (SQLException e){e.getMessage();}
       
}
}




//Cette boutton affiche le nombre d'utilisateur d'un tag selectioné
    @FXML
    private void AfficheNbrUtil(ActionEvent event) {
        NombreUtil();
    }
    
    //Procedure pour recuperer les utilisateurs par un tag donné
    public static ObservableList<User> RecupererUtilParTag(String ch){
    ObservableList<User> list = FXCollections.observableArrayList();
    
    Connection connection;
     connection = DBconnection.getCnx();
          String sql = "select * from user u , file f , tags t where t.tag='"+ch+"' and t.id_file=f.id_file and f.id=u.id GROUP BY u.id";
    try {
       
        PreparedStatement st = (PreparedStatement) connection.prepareStatement(sql);
    ResultSet R = st.executeQuery();
    while (R.next()){
      User u =new User();
      
      u.setNom((R.getString("nom")));
      u.setPrenom(R.getString("prenom"));
      u.setNomd((R.getString("Nomd")));
      
      list.add(u);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }  
    
    
    
    //Cette boutton permet d'afficher la liste d'utilisateurs par un tag donné
    @FXML
    private void ChercheUtilParTag(ActionEvent event) {
        Nomutils.setCellValueFactory(new PropertyValueFactory <>("nom"));
        prenutils.setCellValueFactory(new PropertyValueFactory <>("prenom"));
        ndutil.setCellValueFactory(new PropertyValueFactory <>("nomd"));
        Utiliss.setItems(RecupererUtilParTag(Cherchtag.getText()));  
    }
}
