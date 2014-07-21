package de.bass.projektBlack.main;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import de.bass.projektBlack.exception.MapAlreadyExistsException;
import de.bass.projektBlack.exception.MapNotRectangularException;
import de.bass.projektBlack.exception.TileAlreadyExistsException;
import de.bass.projektBlack.world.Map;
import de.bass.projektBlack.world.MapLoader;
import de.bass.projektBlack.world.Tile;
import de.bass.projektBlack.world.TileLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    //2D-Cam_Zoom
    private float frustumSize = 1;
    
    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(10f);
        
        TileLoader loader = TileLoader.getInstance();
        
        Tile[][] map = new Tile[3][3];
        Material matone = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Material mattwo = matone.clone();
        matone.setTexture("ColorMap", assetManager.loadTexture("textures/testbrown.png"));
        mattwo.setTexture("ColorMap", assetManager.loadTexture("textures/testgreen.png"));
        try {
            Tile one = loader.create("TestEins", matone);
            Tile two = loader.create("TestZwei", mattwo);
            
            map[0][0] = (Tile) one.clone();
            map[0][1] = (Tile) two.clone();
            map[0][2] = (Tile) one.clone();
            
            map[1][0] = (Tile) two.clone();
            map[1][1] = (Tile) one.clone();
            map[1][2] = (Tile) two.clone();
            
            map[2][0] = (Tile) one.clone();
            map[2][1] = (Tile) two.clone();
            map[2][2] = (Tile) one.clone();
            
            Map m = MapLoader.getInstance().create("test", map);
            rootNode.attachChild(m);
        } catch (TileAlreadyExistsException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MapNotRectangularException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MapAlreadyExistsException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //2D-Cam
        cam.setParallelProjection(true);
        float aspect = (float) cam.getWidth() / cam.getHeight();
        cam.setFrustum(-1000, 1000, -aspect * frustumSize, aspect * frustumSize, frustumSize, -frustumSize);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        
    }
    
    @Override
    public void simpleRender(RenderManager rm) {
        
    }
}