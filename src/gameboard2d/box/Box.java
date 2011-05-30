package gameboard2d.box;

import java.awt.*;

/**
 * Represents a box of the board, referenced by its top-left corner.
 */
public class Box {

    public Point point;
    public Dimension dimension;

    /**
     * Sole constructor.
     * @param point
     * @param dimension
     */
    public Box(Point point, Dimension dimension) {
        this.point = point;
        this.dimension = dimension;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Box other = (Box) obj;
        if (this.point != other.point && (this.point == null || !this.point.equals(other.point))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.point != null ? this.point.hashCode() : 0);
        return hash;
    }

    
}
