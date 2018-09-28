package de.bass.projektBlack;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import de.bass.projektBlack.engine.world.World;
import java.io.IOException;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    //2D-Cam_Zoom
    private float frustumSize = 5;
    
    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        World w = new World();
        stateManager.attach(w);
        
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