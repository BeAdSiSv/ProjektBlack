package de.bass.projektBlack.main;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import de.bass.projektBlack.world.Map;
import de.bass.projektBlack.world.MapLoader;
import de.bass.projektBlack.exception.MapNotFoundException;
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