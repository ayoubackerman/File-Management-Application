/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import java.util.List;

/**
 *
 * @author Dahmen
 */
public class Tags {
    private int id_tag;
    private int id_file;
    private String tags;
    private List<Files> lstF;

    public Tags(int id_tag, int id_file, String tags) {
        this.id_tag = id_tag;
        this.id_file = id_file;
        this.tags = tags;
    }

    public Tags() {
       
    }

    public int getId_tag() {
        return id_tag;
    }

    public void setId_tag(int id_tag) {
        this.id_tag = id_tag;
    }

    public int getId_file() {
        return id_file;
    }

    public void setId_file(int id_file) {
        this.id_file = id_file;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    
    
    
}
