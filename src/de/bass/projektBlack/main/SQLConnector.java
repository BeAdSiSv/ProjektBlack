/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sven
 */
public class SQLConnector {
    private SQLConnector instance;
    private Connection con;
    
    private SQLConnector(){
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:sample.db");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLConnector.class.getName()).log(Level.SEVERE,
                    "No JDBC-Driver found", ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public SQLConnector getInstance(){
        if(this.instance == null){
            this.instance = new SQLConnector();
        }
        return this.instance;
    }
}
