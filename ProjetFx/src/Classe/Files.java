/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dahmen
 */
public class Files {
    
    private int id_file;
    private int id;
    private String url;
    private String auteur;
    private String titre ;
    
    private String resume;
    private String commentaire;
    

    public Files() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Files(int id_file, int id, String url, String auteur, String titre, String resume, String commentaire) {
        this.id_file = id_file;
        this.id = id;
        this.url = url;
        this.auteur = auteur;
        this.titre = titre;
        
        this.resume = resume;
        this.commentaire = commentaire;
       
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Files(int id_file, int id, String auteur, String titre, String resume, String commentaire) {
        this.id_file = id_file;
        this.id = id;
        this.auteur = auteur;
        this.titre = titre;
        
        this.resume = resume;
        this.commentaire = commentaire;
        
    }

    public Files(int id_file, String auteur, String titre, String resume, String commentaire) {
        this.id_file = id_file;
        this.auteur = auteur;
        this.titre = titre;
        
        this.resume = resume;
        this.commentaire = commentaire;
        
    }

  



 

    public int getId_file() {
        return id_file;
    }

    public void setId_file(int id_file) {
        this.id_file = id_file;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

   
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "File{" + "id_file=" + id_file + ", auteur=" + auteur + ", titre=" + titre + ", resume=" + resume + ", commentaire=" + commentaire + '}';
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Files other = (Files) obj;
        if (this.id_file != other.id_file) {
            return false;
        }
        if (!Objects.equals(this.auteur, other.auteur)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        
        
        }
        if (!Objects.equals(this.resume, other.resume)) {
            return false;
        }
        if (!Objects.equals(this.commentaire, other.commentaire)) {
            return false;
        }
        return true;
    }

    public String getAteur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
