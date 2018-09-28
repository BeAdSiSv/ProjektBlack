package de.bass.projektBlack.engine.world;

/**
 *
 * @author Sven
 */
public class Map {
    private String name;
    private String[][] map;
    private int coordX;
    private int coordY;
    private int groesseX;
    private int groesseY;
    private boolean worldMap;

    public Map(){}
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isWorldMap() {
        return this.worldMap;
    }

    public void setWorldMap(boolean isWorldMap) {
        this.worldMap = isWorldMap;
    }

    public void setMap(String[][] map) {
        this.map = map;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public void setGroesseX(int groesseX) {
        this.groesseX = groesseX;
    }

    public void setGroesseY(int groesseY) {
        this.groesseY = groesseY;
    }

    public int getCoordX() {
        return this.coordX;
    }

    public int getCoordY() {
        return this.coordY;
    }

    public int getGroesseX() {
        return this.groesseX;
    }

    public int getGroesseY() {
        return this.groesseY;
    }
    
    public String[][] getMapArray() {
        return this.map;
    }
}