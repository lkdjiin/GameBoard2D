package gameboard2d.box;

import java.awt.Color;

/**
 * This class represents a cached area of the board.
 * @todo merge this class with Box ?
 */
public class CacheBox {

    public int id;
    public Color color;

    /**
     * Sole constructor.
     * @param id the id of the box.
     * @param color the color of the cached area.
     */
    public CacheBox(int id, Color color) {
        this.id = id;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CacheBox other = (CacheBox) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.color != other.color && (this.color == null || !this.color.equals(other.color))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + (this.color != null ? this.color.hashCode() : 0);
        return hash;
    }

    
}
