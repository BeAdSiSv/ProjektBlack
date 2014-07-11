/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.stuff;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sven
 */
public class TileLoader {    
    private static TileLoader instance;
    private HashMap<String, URI> tilesMap;
    
    private TileLoader(){
        ArrayList<File> al = searchTileFiles();
        for(File f : al){
            tilesMap.put(f.getName(), f.toURI());
        }
    }
    
    public Tile load(String FileName){
        File f = new File(tilesMap.get(FileName));
        
        return new Tile(f);
    }
    
    public static TileLoader getInstance(){
        if(instance == null){
            instance = new TileLoader();
        }
        return instance;
    }
    
    /**
     * Sucht und Speichert alle File-Dateien im Ordner {@code "assets/tiles"}
     */
    private ArrayList<File> searchTileFiles() {
        File dir = new File("assets/tiles");
        String find = ".pbt";
        
        File[] files = dir.listFiles();
        ArrayList<File> matches = new ArrayList<File> ();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().endsWith(find)) {
                    matches.add(files[i]);
                }
            }
        }
        
        return matches;
    }
}
