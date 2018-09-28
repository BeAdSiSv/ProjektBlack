package de.bass.projektBlack.engine.world;

import de.bass.projektBlack.engine.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Sven
 */
class MapManager {
    private final String ordner = "maps/";
    private final String endung = ".map";
    private ArrayList<Map> worldMaps;
    private HashMap<String, Map> allMaps;
    
    MapManager(){
        this.worldMaps = new ArrayList();
        this.allMaps = new HashMap<String, Map>();
        
        ArrayList<File> al = FileUtil.searchFiles(this.ordner, this.endung, true);
        for(File f : al){
            try {
                InputStream is = new FileInputStream(f);
                Yaml yaml = new Yaml();
                Map m = yaml.loadAs(is, Map.class);
                if(m.isWorldMap()) this.worldMaps.add(m);
                this.allMaps.put(m.getName(), m);
                is.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MapManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MapManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    Map getMap(String name){
        return this.allMaps.get(name);
    }
    
    ArrayList<Map> getWorldMaps() {
        return this.worldMaps;
    }

    void saveMaps() {
        Collection<Map> col = this.allMaps.values();
        
        for(Map m : col){
            File f = new File(FileUtil.ASSET_DIRECTORY + this.ordner + m.getName() + this.endung);
            String toFile = new Yaml().dump(m);
            
            try {
                FileWriter out = new FileWriter(f);
                out.write(toFile);
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(MapManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}