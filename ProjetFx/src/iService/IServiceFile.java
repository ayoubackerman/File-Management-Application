/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iService;

import Classe.Files;
import java.sql.SQLException;


/**
 *
 * @author Dahmen
 * @param <File>
 */
public interface IServiceFile <File> {
    void ajouter(File t) throws SQLException;
    boolean delete(File t) throws SQLException;
    boolean update(File t) throws SQLException;
    public boolean search(File t) throws SQLException;
}
