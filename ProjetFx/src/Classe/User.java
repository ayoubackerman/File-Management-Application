/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import java.util.List;

/**
 *
 * @author berssellou
 */
public class User {
    private int id;
    private String role;
    private String nom;
    private String prenom;
    private String mail;
    private String nomd;
    private String mdp;
   
    public static User connecte;
    public static int code;
    private List<Files> lstF;

    public User(int id, String role, String nom, String prenom, String mail, String nomd, String mdp,  List<Files> lstF) {
        this.id = id;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
        
   
        this.lstF = lstF;
    }
    
    public User(int id, String role, String nom, String prenom, String mail, String nomd, String mdp, String emploi) {
        this.id = id;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
        
        
        
    }
  public User( String role, String nom, String prenom, String mail, String nomd, String mdp,  String emploi, int valide) {
        
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
       
    }
  public User( int id,String role, String nom, String prenom, String mail, String nomd, String mdp) {
        this.id=id;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
       
    }
  

    public User(String role, String nom, String prenom, String mail, String nomd, String mdp) {
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
        
       
    }

    public User(int id, String role, String nom, String prenom, String mail, int nomd, String mdp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Files> getLstF() {
        return lstF;
    }

    public void setLstF(List<Files> lstF) {
        this.lstF = lstF;
    }

    
    
    public User() {
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getNomd() {
        return nomd;
    }

    public String getMdp() {
        return mdp;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNomd(String nomd) {
        this.nomd = nomd;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }



    @Override
    public String toString() {
        return "User{" + "id=" + id + ", role=" + role + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", nomd=" + nomd + ", mdp=" + mdp + ", lstF=" + lstF + '}';
    }




  
    
}