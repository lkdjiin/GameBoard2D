package gameboard2d.board;

import gameboard2d.box.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

/**
 * Manages the board of your game.
 * For now, there is no needs to use this class directly.
 */
public class Board {

    private BufferedImage boardImage;
    private BufferedImage cacheImage;
    protected HashMap<Integer, Box> boxes;

    /**
     * Sole constructor.
     * @param boardFile the image of the board
     * @param cacheFile the image of the board's cache
     * @throws IOException if a file doesn't exist (or is unreadable, ...)
     */
    public Board(File boardFile, File cacheFile) throws IOException {
        boxes = new HashMap<Integer, Box>();
        boardImage = ImageIO.read(boardFile);
        cacheImage = ImageIO.read(cacheFile);
    }

    /**
     * Add a box for this board.
     * @param id the id of the box. Must be uniq. Could be any integer, even negative's one.
     * @param cache the Color of the cache area for this box.
     * @param reference the coordinates of the point that references this box.
     * @param d the dimension of this box area
     * @todo explain concept of "cache area" and "reference point"
     * @todo think hard: I don't want a dimension for a box !
     * @todo id must be uniq: check it !
     * @todo cache must be uniq: check it !
     */
    public void addBox(int id, Color cache, Point reference, Dimension d) {
        boxes.put(id, new Box(reference, cache, d));
    }

    /**
     * Return the image of this board as a BufferedImage.
     */
    public BufferedImage getImage() {
        return boardImage;
    }

    /**
     * Return the id of the box that is pointed to by point.
     * @param point the coordinate of the point for which you want to know the box's id.
     * @return the box's id
     */
    public int getBoxId(Point point) {
        Color c = colorOfPoint(point);
        Integer id = boxIdFromColor(c);
        if(id == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return id;
    }

    private Color colorOfPoint(Point point) {
        int pixel = cacheImage.getRGB(point.x, point.y);
        int red   = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue  = (pixel) & 0xff;
        return new Color(red, green, blue);
    }

    private Integer boxIdFromColor(Color color) {
        Integer ret = null;
        for(Map.Entry<Integer, Box> e : boxes.entrySet()) {
            Box box = e.getValue();
            if(box.cacheColor.equals(color)) {
                ret = e.getKey();
            }
        }
        return ret;
    }

    /**
     * Return the box referenced by id.
     * @param id the box you want
     * @return a box
     */
    public Box getBox(int number) {
        return boxes.get(number);
    }

}
