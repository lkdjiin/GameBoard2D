package gameboard2d.box;

import java.awt.*;

/**
 * Represents a box of the board, referenced by its top-left corner.
 */
public class Box {

    public Point point;
    public Dimension dimension;
    public Color cacheColor;

    /**
     * Sole constructor.
     * @param point
     * @param color the color of the cached area.
     * @param dimension
     */
    public Box(Point point, Color color, Dimension dimension) {
        this.point = point;
        this.cacheColor = color;
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
        if (this.cacheColor != other.cacheColor && (this.cacheColor == null || !this.cacheColor.equals(other.cacheColor))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.point != null ? this.point.hashCode() : 0);
        hash = 31 * hash + (this.cacheColor != null ? this.cacheColor.hashCode() : 0);
        return hash;
    }



    
}
