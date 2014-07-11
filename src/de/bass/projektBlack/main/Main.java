package de.bass.projektBlack.main;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import de.bass.projektBlack.stuff.WorldLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
        assetManager.registerLoader(WorldLoader.class, "map");
        
        assetManager.loadAsset("maps/empty.map");
        
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);
        
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        
        rootNode.attachChild(geom);
        
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