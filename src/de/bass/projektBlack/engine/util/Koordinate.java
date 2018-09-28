/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bass.projektBlack.engine.util;

/**
 *
 * @author Sven
 */
public class Koordinate {
    private final int x;
    private final int y;

    public Koordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Koordinate)) return false;
        Koordinate key = (Koordinate) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        int result = this.x;
        result = 31 * result + this.y;
        return result;
    }
}