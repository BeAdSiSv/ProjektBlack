package de.bass.projektBlack.engine.world;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import de.bass.projektBlack.Main;
import de.bass.projektBlack.engine.util.Koordinate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sven
 */
public class World extends AbstractAppState{
    private final float tileGroesse = 0.5f; //TEMP: evtl errechnen oä
    private Main app;
    private MapManager mapManager;
    private TileManager tileManager;
    private HashMap<Koordinate , Geometry> world; //wich coordinate belongs to wich Tile
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (Main) app;
        this.mapManager = new MapManager();
        this.tileManager = new TileManager();
        loadWorld();
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
    }

    @Override
    public void cleanup() {
        super.cleanup();
        this.mapManager.saveMaps();
    }
    
    private Geometry createGeometry(Tile tile){
        AssetManager am = this.app.getAssetManager();
        Geometry geo = new Geometry();
        
        Quad q = new Quad(this.tileGroesse, this.tileGroesse);
        geo.setMesh(q);
        
        Material m = new Material(am, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture tex = am.loadTexture(tile.getTexture());
        m.setTexture("ColorMap", tex);
        
        return geo;
    }
    
    private void loadWorld(){
        ArrayList<Map> maps = this.mapManager.getWorldMaps();
        HashMap<String, Tile> knownTiles = new HashMap<String, Tile>();
        
        for(Map m : maps){
            String[][] array = m.getMapArray();
            int mgx = m.getGroesseX();
            int mgy = m.getGroesseY();
            int mx = m.getCoordX();
            int my = m.getCoordY();
            
            for(int x = 0; x < mgx; x++){
                for(int y = 0; y < mgy; x++){
                    String name = array[x][y];
                    Tile t;
                    if(!knownTiles.containsKey(name)){
                        t = this.tileManager.loadTile(array[x][y]);
                        knownTiles.put(name, t);
                    }else {
                        t = knownTiles.get(name);
                    }
                    Koordinate k = new Koordinate(x + mx, y + my);
                    //TEMP: alle Tiles an die RootNode
                    Geometry geo = createGeometry(t);
                    this.app.getRootNode().attachChild(geo);
                    geo.setLocalTranslation(k.getX(), k.getY(), 0.0f);
                    this.world.put(k, geo);
                }
            }
        }
    }
}