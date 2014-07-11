/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.stuff;

/**
 *
 * @author Sven
 */
public class Layer {
    private int groesseX;
    private int groesseY;
    private Tile[][] tiles;
    
    Layer(int groesseX, int groesseY, int position, String[][] tNames){
        this.groesseX = groesseX;
        this.groesseY = groesseY;
        TileLoader tl = TileLoader.getInstance();
        
        for(int x = 0; x <= groesseX; x++){
                    for(int y = 0; y <= groesseY; y++){
                        tiles[x][y] = tl.load(tNames[x][y]);
                    }
                }
    }
}
