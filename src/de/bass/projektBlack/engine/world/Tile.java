package de.bass.projektBlack.engine.world;

/**
 *
 * @author Sven
 */
public class Tile {
    private String texture;

    public Tile(){}
    
    public Tile(String texture){
        this.texture = texture;
    }
    
    public void setTexture(String texture) {
        this.texture = texture;
    }
    
    public String getTexture(){
        return this.texture;
    }
}