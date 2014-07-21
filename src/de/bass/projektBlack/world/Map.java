/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.world;

import com.jme3.scene.Node;
import de.bass.projektBlack.exception.MapNotRectangularException;

/**
 *
 * @author Sven
 */
public class Map extends Node{
    private int groesseX;
    private int groesseY;
    private Tile[][] map;
    
    Map(String name, Tile[][] map) throws MapNotRectangularException {
        //check if array is rectangular
        {
            int x = map.length;
            int y = map[0].length;
            int ytemp;
            for(Tile[] array : map){
                ytemp = array.length;
                if(y != ytemp){
                    throw new MapNotRectangularException();
                }
            }
            this.groesseX = x;
            this.groesseY = y;
        }
        
        this.map = map;
        this.name = name;
        
        //attach each Tile from the array with its respective translation
        for(int x = 0; x < groesseX; x++){
            for(int y = 0; y < groesseY; y++){
                Tile t = map[x][y];
                attachChild(t);
                t.setLocalTranslation(1 + x, 1 + y, 0.0f);
            }
        }
    }
}