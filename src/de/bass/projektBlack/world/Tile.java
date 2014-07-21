/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.world;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;

/**
 *
 * @author Sven
 */
public class Tile extends Geometry{
    private String name;
    
    Tile(String name, Material mat){
        this.name = name;
        Quad qua = new Quad(1.0f, 1.0f);
        setMesh(qua);
        setMaterial(mat);
    }
}