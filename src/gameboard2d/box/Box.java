package gameboard2d.box;

import java.awt.*;

/**
 * <h2>What is a Box for the GameBoard2D library ?</h2>
 * <p>
 * For the GameBoard2D library, a box is a concept.
 * A box represents 2 things, depending of the point of view.
 * <p>
 * First, a box is <b>an area</b> of the board. Those areas are
 * physically viewable in the "cache" image file you provided
 * to the GameBoard2D constructor.<br/>
 * An area doesn't have to be rectangular. It can be a square,
 * a circle or whatever you want. All the areas of the board
 * could be different (think at the Risk game).<br/>
 * With this point of view, a box is an area of a uniq color
 * in the "cache" image file. It gives the library a way to
 * know which box the player had clicked.
 * <p>
 * Secondly, a box is <b>a point of reference</b> in the board.
 * This point (x,y coordinate) will be used as the top-left corner
 * reference to draw a piece.
 * .
 */
public class Box {

    private Point point;
    private Color cacheColor;

    /**
     * Sole constructor.
     * @param point
     * @param color the color of the cached area.
     */
    public Box(Point point, Color color) {
        this.point = point;
        this.cacheColor = color;
    }

    public Color getCacheColor() {
        return cacheColor;
    }

    public Point getPoint() {
        return point;
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
