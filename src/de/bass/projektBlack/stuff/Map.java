/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.stuff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sven
 */
public class Map {
    private String name;
    private int groesseX;
    private int groesseY;
    private int anzLayer;
    private String[][][] map;
    private Layer[] layers;
    
    public Map(File f){
        InputStream fis = null;
        try {
            fis = new FileInputStream(f);
            
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            load(ois);
            
            ois.close();
            
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Map(InputStream is){
        try {
            ObjectInputStream ois = new ObjectInputStream(is);
            
            load(ois);
            
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void load(ObjectInputStream ois) {
        try {
            this.name = (String) ois.readObject();
            this.groesseX = (Integer) ois.readObject();
            this.groesseY = (Integer) ois.readObject();
            this.anzLayer = (Integer) ois.readObject();
            this.map = (String[][][]) ois.readObject();
            
            for(int i = 0; i < anzLayer; i++){
                String[][] tNames = new String[groesseX][groesseY];
                for(int x = 0; x < groesseX; x++){
                    for(int y = 0; y < groesseY; y++){
                        tNames[x][y] = map[i][x][y];
                    }
                }
                
                layers[i] = new Layer(groesseX, groesseY, i, tNames);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}