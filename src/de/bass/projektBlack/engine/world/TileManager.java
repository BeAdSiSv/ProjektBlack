package de.bass.projektBlack.engine.world;

import de.bass.projektBlack.engine.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Sven
 */
class TileManager {
    private final String ordner = "tiles/";
    private final String endung = ".pbt";
    private HashMap<String, URI> tileMap;
    
    public TileManager(){
        this.tileMap = new HashMap<String, URI>();
        ArrayList<File> al = FileUtil.searchFiles(this.ordner, this.endung, true);
        for(File f : al){
            this.tileMap.put(f.getName(), f.toURI());
        }
    }
    
    Tile loadTile(String name) {
        Tile t = new Tile(FileUtil.ASSET_DIRECTORY + "textures/TileNotFound.png");
        File f = new File(this.tileMap.get(name));
        try {
            InputStream is = new FileInputStream(f);
            Yaml yaml = new Yaml();
            t = yaml.loadAs(is, Tile.class);
            is.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
}