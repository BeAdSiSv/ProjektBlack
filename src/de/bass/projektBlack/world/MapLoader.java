/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.world;

import de.bass.projektBlack.exception.MapNotRectangularException;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.export.binary.BinaryImporter;
import de.bass.projektBlack.exception.MapNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import de.bass.projektBlack.exception.MapAlreadyExistsException;

/**
 *
 * @author Sven
 */
public class MapLoader{
    private static MapLoader instance;
    private BinaryExporter biEx;
    private BinaryImporter biIn;
    private HashMap<String, URI> mapMap;
    
    private MapLoader(){
        mapMap = new HashMap<String, URI>();
        biEx = new BinaryExporter();
        biIn = new BinaryImporter();
        ArrayList<File> al = searchTileFiles();
        for(File f : al){
            mapMap.put(f.getName(), f.toURI());
        }
    }
    
    public Map load(String name) throws MapNotFoundException {
        Map m = null;
        if(!mapMap.containsKey(name)){
            throw new MapNotFoundException();
        }
        else{
            File f = new File(mapMap.get(name));
            try {
                m = (Map) biIn.load(f);
            } catch (IOException ex) {
                Logger.getLogger(MapLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return m;
    }
    
    public Map create(String name, Tile[][] map) throws MapNotRectangularException, MapAlreadyExistsException{
        if(!mapMap.containsKey(name)){
            return new Map(name, map);
        }
        else {
            throw new MapAlreadyExistsException();
        }
    }
    
    public void save(Map map){
        File f = new File("assets/maps/" + map.getName());
        try {
            biEx.save(map, f);
        } catch (IOException ex) {
            Logger.getLogger(MapLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MapLoader getInstance(){
        if(instance == null){
            instance = new MapLoader();
        }
        return instance;
    }
    
    /**
     * Sucht und Speichert alle File-Dateien im Ordner {@code "assets/tiles"}
     */
    private ArrayList<File> searchTileFiles() {
        File dir = new File("assets/maps");
        String find = ".map";
        
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