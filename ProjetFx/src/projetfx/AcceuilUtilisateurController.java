/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfx;
import Classe.Tags;
import Classe.Files;
import Classe.User;
import ConnectionDB.DBconnection;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Dahmen
 */
public class AcceuilUtilisateurController implements Initializable {

  
    @FXML
    private TextField act;

    @FXML
    private TextField titre;

    @FXML
    private TextField tags;

    @FXML
    private TextArea res;

    @FXML
    private TextArea comm;

    @FXML
    private Button Ajoute;

    @FXML
    private TextField urlx;

    @FXML
    private Button save;

    @FXML
    private Tab supprimer_fichier;

    @FXML
    private Button Supp_fich;

    @FXML
    private TableView<Files> Fichier;

    @FXML
    private TableColumn<Files,String> Id_file;

    @FXML
    private TableColumn<Files,String> Furl;

    @FXML
    private TableColumn<Files,String> Faut;

    @FXML
    private TableColumn<Files,String> Ftitre;

    @FXML
    private TextField idx;

    @FXML
    private Tab mod_ad;

    @FXML
    private TableView<Files> ad_mod;

    @FXML
    private TableColumn<Files,String> mod_id;

    @FXML
    private TableColumn<Files,String> mod_url;

    @FXML
    private TableColumn<Files,String> mod_auteur;

    @FXML
    private TableColumn<Files,String> mod_titre;

    

    @FXML
    private TableColumn<Files,String> mod_resume;

    @FXML
    private TableColumn<Files,String> mod_commentaire;

    @FXML
    private TextField titremodif;


    @FXML
    private Button Modiff;

    @FXML
    private TextArea resume;

    @FXML
    private TextArea commentaire;

    @FXML
    private TextField idmodif;

    @FXML
    private TextField auteurmodif;

    @FXML
    private TextField recherche;

    @FXML
    private Button out;

    @FXML
    private Label bvn;
   

    /**
     * Initializes the controller class.
     */
    Files fi= new Files();
    
       Connection connection =null;
     PreparedStatement preparedStatement=null;
     Files f= new Files();
    @FXML
    private TextField autretag;
    @FXML
    private Label autags;
    @FXML
    private Button atags;
    @FXML
    private Button Ann;
    @FXML
    private Text ptag;
    @FXML
    private Text ptitre;
    @FXML
    private Text rcomm;
   
    public AcceuilUtilisateurController(){connection = DBconnection.getCnx();
}
    
    ObservableList<Files> List;
            ObservableList<Files> Cherchefichier;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Ann.setVisible(false);
        autretag.setVisible(false);
        autags.setVisible(false);
         atags.setVisible(false);
          act.setDisable(true);
        titre.setDisable(true);
        res.setDisable(true);
        comm.setDisable(true);
        tags.setDisable(true);
        urlx.setDisable(true);
        idx.setVisible(false);
        idmodif.setVisible(false);
        tablesupp();
        tablemodif();
        ChercheFichier();
        bvn.setText("Bienvenu"+" "+User.connecte.getNom()+" "+User.connecte.getPrenom());
        
        // TODO
        Fichier.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
              ad_mod.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onModif();
        
    }
});
    }
    
    //Recuperer les fichiers d'utilisateur connecté pour modifier
    public static ObservableList<Files> RecupBasePourModif(){
    ObservableList<Files> list = FXCollections.observableArrayList();
    int idu =   User.connecte.getId();
    java.sql.Connection conn;
     conn = DBconnection.getCnx();
          String sql = "select *from file where id ='"+idu+"'";
    try {
       
        PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
    ResultSet R = st.executeQuery();
    while (R.next()){
      Files f =new Files();
     f.setId_file((R.getInt(1)));
     f.setUrl(R.getString(3));
     f.setAuteur(R.getString(4));
     f.setTitre(R.getString(5));
     
     f.setResume(R.getString(6));
     f.setCommentaire(R.getString(7));
      list.add(f);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }    
    
  //Recuperer les fichiers d'utilisateur connecté pour supprimer
public static ObservableList<Files> RecupBasePourSupp(){
    ObservableList<Files> list = FXCollections.observableArrayList();
    int idu =   User.connecte.getId();
    java.sql.Connection conn;
     conn = DBconnection.getCnx();
          String sql = "select *from file where id ='"+idu+"'";
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
            Logger.getLogger(AcceuilUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);

    }
    }
    
    //Cette boutton permet de choisir un fichier sur la machine
   @FXML
    private void Ajoutefichier(ActionEvent event) {
              FileChooser f = new FileChooser();
        File S = f.showOpenDialog(null);
         if(S!=null){
        act.setDisable(false);
        titre.setDisable(false);
        res.setDisable(false);
        comm.setDisable(false);
        tags.setDisable(false);  
        
         String n = S.getAbsolutePath();
        urlx.setText(S.getAbsolutePath());
        urlx.setDisable(true);
        
         }
    }
    
    //procedure permet d'inserer un fichier dans la base de donnée
    public void fichier(){
    int id =   User.connecte.getId();
         String sql = "INSERT INTO file (id,url,auteur,titre,resume,commentaire ) values (?,?,?,?,?,?)";
         try{

            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2, urlx.getText());
            preparedStatement.setString(3, act.getText());
            preparedStatement.setString(4, titre.getText());
            
            preparedStatement.setString(5, res.getText());
            preparedStatement.setString(6, comm.getText());
            preparedStatement.executeUpdate();   
           
           f.setId(id);
           
           f.setAuteur(act.getText());
           f.setCommentaire(comm.getText());
           f.setUrl(urlx.getText());
           f.setTitre(titre.getText());
           f.setResume( res.getText());
           
            
        JOptionPane.showMessageDialog(null,"Fichier a été ajouté avec succés");
           }
        
        catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Error");

        } 
    }
    
    //Cette procedure permet d'inserer l'id d'un fichier dans un TextField
    public void idfile(){
    String sql = "select *from file";
            try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                f.setId_file(id);
                            }}catch(SQLException e){e.getMessage();}
    }
    
    //Procedure permet d'inserer un tag dans la base des tags avec l'id de fichier ajouter
    public void EnregTag() throws SQLException{
        idfile();
         String sql = "INSERT INTO tags (id_file,tag ) values (?,?)";
         
         
        
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, f.getId_file());
            preparedStatement.setString(2,tags.getText());
            preparedStatement.executeUpdate();   
            Tags x= new Tags();
            x.setTags(tags.getText());
    
    
    }
    
    //Procedure permet d'inserer un autre tag dans la base des tags avec l'id de fichier ajouter
     public void EnregAutreTag() {
        idfile();
         String sql = "INSERT INTO tags (id_file,tag ) values (?,?)";
         
try{
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, f.getId_file());
            preparedStatement.setString(2,autretag.getText());
            preparedStatement.executeUpdate();   
            Tags x= new Tags();
            x.setTags(autretag.getText());
            JOptionPane.showMessageDialog(null,"tag a été ajouté avec succés ");
           }
    
        catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Error");
    }
     }
     
     //Boutton pour enregistrer le fichier dans la base 
   @FXML
    private void Enrefichier(ActionEvent event) throws SQLException {
        if(tags.getText().isEmpty())
        
        
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier votre tag");
            
            alert.setContentText("Veuillez mettre un tag");
            Optional<ButtonType> result = alert.showAndWait();}
        else if(titre.getText().isEmpty())
        {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier votre titre");
            
            alert.setContentText("Veuillez mettre un titre");
            Optional<ButtonType> result = alert.showAndWait();
        
        }
         
        else{
        fichier();
        EnregTag();
        ptag.setVisible(false);
        ptitre.setVisible(false);
        Ann.setVisible(true);
        Ajoute.setVisible(false);
        save.setVisible(false);
        autretag.setVisible(true);
        autags.setVisible(true);
         atags.setVisible(true);
         act.setVisible(false);
        titre.setVisible(false);
        res.setVisible(false);
        comm.setVisible(false);
        tags.setVisible(false);
        urlx.setVisible(false);
        rcomm.setVisible(false);
        
        }
    }
    
    //Procedure permet d'inserer l'id de fichier dans un TextField pour supprimer
    public void onEdit() {
     
    if (Fichier.getSelectionModel().getSelectedItem() != null) {
          Files f = Fichier.getSelectionModel().getSelectedItem();
         int i = f.getId_file();
        String n = String.valueOf(i);
        String s = "select *from file where id_file ="+i;
        try {
          preparedStatement = (PreparedStatement) connection.prepareStatement(s);
          ResultSet r=preparedStatement.executeQuery();
          while(r.next()){
          idx.setText(n);
          
          }
          
                  
       
        }catch (SQLException e){e.getMessage();}
      
            
    }
}
    //Procedure permet d'inserer les donnés d'un fichier selectionné dans des TextFields pour les modifier 
     public void onModif() {
    if (ad_mod.getSelectionModel().getSelectedItem() != null) {
          Files f = ad_mod.getSelectionModel().getSelectedItem();
         int i = f.getId_file();
        String n = String.valueOf(i);
        
        
         
          idmodif.setText(n);
          auteurmodif.setText(f.getAuteur());
          titremodif.setText(f.getTitre());
          
          resume.setText(f.getResume());
          commentaire.setText(f.getCommentaire());         
    }
}   
     
     
     //procedure pour mise a jour le tableau apres les modifications
    public void tablemodif(){
    mod_id.setCellValueFactory( new PropertyValueFactory<>("id_file"));
    mod_url.setCellValueFactory( new PropertyValueFactory<>("url"));
    mod_auteur.setCellValueFactory( new PropertyValueFactory<>("auteur"));
    mod_titre.setCellValueFactory( new PropertyValueFactory<>("titre"));
    
    
    mod_resume.setCellValueFactory( new PropertyValueFactory<>("resume"));
    mod_commentaire.setCellValueFactory( new PropertyValueFactory<>("commentaire"));
    ad_mod.setItems(RecupBasePourModif()); 
    
    }
    
    
  //procedure pour mise a jour le table apres la suppression 
     public void tablesupp(){
         
        Id_file.setCellValueFactory( new PropertyValueFactory<>("id_file"));
        Furl.setCellValueFactory(new PropertyValueFactory <>("url"));
        Faut.setCellValueFactory(new PropertyValueFactory <>("auteur"));
        Ftitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
        Fichier.setItems(RecupBasePourSupp()); 



}
     
     //Procedure permet de supprimer les tags avec l'id donné
     public void Supptag(int i){
         String sql="delete from tags where id_file='"+i+"'";
        try{
        
        preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
      
        
       
        } catch(SQLException e){
            e.getMessage();}
     
     }
     
     //Procedure permet de supprimer le fichier avec son id
     public void Suppfich(int i){
       String sql="delete from file where id_file='"+i+"'";
        try{
        
        preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
      
        tablesupp();
       
        } catch(SQLException e){
            e.getMessage();}
        
    }

     //Boutton pour supprimer un fichier
     @FXML
    private void Supprimer(ActionEvent event) {
         
        
        String idf=idx.getText();
        int i=Integer.valueOf(idf);
        Supptag(i);
        Suppfich(i);
        
                JOptionPane.showMessageDialog(null,"Le fichier a été supprimer avec succés");
    
        
    }
    
    
    //Boutton pour modifier un fichier
         @FXML
      void Modifier(ActionEvent event) {
        String id= idmodif.getText();
        String aut= auteurmodif.getText();
        String tit= titremodif.getText();
        
        String ress= resume.getText();
        String com= commentaire.getText();
       
        String sql ="update file set auteur='"+aut+"',titre='"+tit+"',resume='"+ress+"',Commentaire='"+com+"' where id_file='"+id+"'";
        try {
         preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
         preparedStatement.executeUpdate();
         tablemodif();
          JOptionPane.showMessageDialog(null,"Le fichier a été modifier");
    }catch(SQLException ex){
        ex.getMessage();
    }
        

    }
      
      
      
   //Procedure permet de faire le recherche d'un tag  
    public void ChercheFichier(){
      Files f = new Files();
        mod_id.setCellValueFactory( new PropertyValueFactory<>("id_file"));
        
        mod_auteur.setCellValueFactory( new PropertyValueFactory<>("auteur"));
        mod_titre.setCellValueFactory( new PropertyValueFactory<>("titre"));
    
       
       
    Cherchefichier = RecupBasePourModif();
    ad_mod.setItems(RecupBasePourModif());
    FilteredList<Files> filtreddata;
     filtreddata = new FilteredList<>(Cherchefichier ,b ->true);
    recherche.textProperty().addListener((observable,oldValue,newValue)->{
      filtreddata.setPredicate((files  ->  {
          
          if((newValue ==null) || newValue.isEmpty())
          { return true;}
      
      String lowerCaseFilter = newValue.toLowerCase();
      if (files.getAuteur().toLowerCase().contains(lowerCaseFilter)){
      return true;
      } else if (files.getTitre().toLowerCase().contains(lowerCaseFilter))
          {return true;}
        
      else if (String.valueOf(files.getId_file()).contains(lowerCaseFilter))
          {return true;}
        
      return false;
      })); 
    });
    
    SortedList<Files> srt = new SortedList<>(filtreddata);
    srt.comparatorProperty().bind(ad_mod.comparatorProperty());
    ad_mod.setItems(srt);
    }

    
    //Boutton pour ajouter un autre tag si l'utilisateur veut ajouter un autre tag 
    @FXML
    private void Autretags(ActionEvent event){
        if(autretag.getText().isEmpty())
        
        
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier votre tag");
            
            alert.setContentText("Veuillez mettre un tag");
            Optional<ButtonType> result = alert.showAndWait();}
        else{
        EnregAutreTag();
        }
        
    }

    //Boutton pour retourner a la page d'ajouter un fichier 
    @FXML
    private void Annuler(ActionEvent event) {
        Ann.setVisible(false);
        Ajoute.setVisible(true);
        save.setVisible(true);
        autretag.setVisible(false);
        autags.setVisible(false);
         atags.setVisible(false);
         act.setVisible(true);
        titre.setVisible(true);
        res.setVisible(true);
        comm.setVisible(true);
        tags.setVisible(true);
        urlx.setVisible(true);
        ptag.setVisible(true);
        ptitre.setVisible(true);
        rcomm.setVisible(true);
        act.clear();
        titre.clear();
        res.clear();
        comm.clear();
        tags.clear();
        urlx.clear();
           act.setDisable(true);
        titre.setDisable(true);
        res.setDisable(true);
        comm.setDisable(true);
        tags.setDisable(true);
        urlx.setDisable(true);
        
        
        comm.clear();
    }
    

    
}

