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
     * @throws IllegalArgumentException if the id or the cache color already exists
     * @see Box "What is a Box for the GameBoard2D library ?"
     */
    public void addBox(int id, Color cache, Point reference) {
        if(boxes.containsKey(id)) {
            throw new IllegalArgumentException("Duplicated id");
        }
        for(Box e : boxes.values()) {
            if(e.getCacheColor().equals(cache)) {
                throw new IllegalArgumentException("Duplicated cache color");
            }
        }
        boxes.put(id, new Box(reference, cache));
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
     * @throws ArrayIndexOutOfBoundsException if the point is out of the board
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
            if(box.getCacheColor().equals(color)) {
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
    public Box getBox(int id) {
        return boxes.get(id);
    }

}
