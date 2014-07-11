/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.stuff;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sven
 */
public class WorldLoader implements AssetLoader{
    private HashMap<String, URI> mapsMap;
    
    
    public WorldLoader(){
        ArrayList<File> al = searchMapFiles();
        mapsMap = new HashMap();
        for(File f : al){
            if(f != null){
                mapsMap.put(f.getName(), f.toURI());
            }
        }
    }
    
    public Map load(String Filename){
        File f = new File(mapsMap.get(Filename));
        
        return new Map(f);
    }
    
    /**
     * Sucht und Speichert alle Map-Dateien im Ordner {@code "assets/maps"}
     */
    private ArrayList<File> searchMapFiles() {
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

    @Override
    public Map load(AssetInfo assetInfo){
        return new Map(assetInfo.openStream());
    }
}