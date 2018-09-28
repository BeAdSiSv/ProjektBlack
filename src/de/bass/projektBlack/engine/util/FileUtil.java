/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.engine.util;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sven
 */
public class FileUtil {
    public static final String ASSET_DIRECTORY = "./assets/";
    
    /**
     * Gibt eine {@linkplain ArrayList} aller zutreffenden Dateien im angegebenen Verzeichnis aus.
     * 
     * @param ordner
     * @param endung
     * @param relative ob {@code ordner} relativ vom Asset-Verzeichnis angegeben wurde.
     * @return {@linkplain ArrayList} der zureffenden Dateien
     */
    public static ArrayList<File> searchFiles(String ordner, String endung, boolean relative){
        File dir;
        
        if(relative) dir = new File(ASSET_DIRECTORY.concat(ordner));
        else dir = new File(ordner);
        
        return searchFiles(dir, endung);
    }
    
    /**
     * Gibt eine {@linkplain ArrayList} aller zutreffenden Dateien im angegebenen Verzeichnis aus.
     * 
     * @param dir
     * @param endung
     * @return {@linkplain ArrayList} der zureffenden Dateien
     */
    public static ArrayList<File> searchFiles(File dir, String endung) {
        File[] files = dir.listFiles();
        ArrayList<File> matches = new ArrayList<File> ();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().endsWith(endung)) {
                    matches.add(files[i]);
                }
            }
        }
        Logger.getLogger(FileUtil.class.getName()).log(Level.INFO, "{0} {1}-Dateien im Verzeichnis: {2}", new Object[]{matches.size(), endung, dir});
        return matches;
    }
}