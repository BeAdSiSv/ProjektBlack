/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.world;

import de.bass.projektBlack.exception.TileAlreadyExistsException;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.export.binary.BinaryImporter;
import com.jme3.material.Material;
import de.bass.projektBlack.exception.TileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sven
 */
public class TileLoader{
    private static TileLoader instance;
    private BinaryExporter biEx;
    private BinaryImporter biIn;
    private HashMap<String, URI> tileMap;
    
    private TileLoader(){
        tileMap = new HashMap<String, URI>();
        biEx = new BinaryExporter();
        biIn = new BinaryImporter();
        ArrayList<File> al = searchTileFiles();
        for(File f : al){
            tileMap.put(f.getName(), f.toURI());
        }
    }
    
    public Tile load(String name) throws TileNotFoundException{
        Tile t = null;
        if(!tileMap.containsKey(name)){
            throw new TileNotFoundException();
        }
        else{
            File f = new File(tileMap.get(name));
            try {
                t = (Tile) biIn.load(f);
            } catch (IOException ex) {
                Logger.getLogger(TileLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return t;
    }
    
    public Tile create(String name, Material mat) throws TileAlreadyExistsException {
        if(!tileMap.containsKey(name)){
            return new Tile(name, mat);
        }
        else {
            throw new TileAlreadyExistsException(); //TODO: evtl. verschieben zu save(Tile)
        }
    }
    
    public void save(Tile tile){
        File f = new File("assets/tiles/" + tile.getName());
        try {
            biEx.save(tile, f);
        } catch (IOException ex) {
            Logger.getLogger(TileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
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