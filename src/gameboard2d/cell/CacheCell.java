package gameboard2d.cell;

import java.awt.Color;

/**
 * This class represent a cached area of the board.
 * @todo merge this class with Cell ?
 */
public class CacheCell {

    public int number;
    public Color color;

    /**
     * Sole constructor.
     * @param number the number of the box.
     * @param color the color of the cached area.
     */
    public CacheCell(int number, Color color) {
        this.number = number;
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
        final CacheCell other = (CacheCell) obj;
        if (this.number != other.number) {
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
        hash = 89 * hash + this.number;
        hash = 89 * hash + (this.color != null ? this.color.hashCode() : 0);
        return hash;
    }

    
}
