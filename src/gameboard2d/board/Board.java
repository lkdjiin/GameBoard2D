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
    protected ArrayList<CacheBox> cacheBoxes;
    protected HashMap<Integer, Box> boxes;

    /**
     * Sole constructor.
     * @param boardFilename the name of the image of the board
     * @param cacheFilename the name of the image of the board's cache
     * @throws IOException if a file doesn't exist (or is unreadable, ...)
     * @todo Remplacer les String par des File.
     */
    public Board(String boardFilename, String cacheFilename) throws IOException {
        cacheBoxes = new ArrayList<CacheBox>();
        boxes = new HashMap<Integer, Box>();
        boardImage = ImageIO.read(new File(boardFilename));
        cacheImage = ImageIO.read(new File(cacheFilename));
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
        cacheBoxes.add(new CacheBox(id, cache));
        boxes.put(id, new Box(reference, d));
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
        Integer cell = cellFromColor(c);
        if(cell == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return cell;
    }

    private Color colorOfPoint(Point point) {
        int pixel = cacheImage.getRGB(point.x, point.y);
        int red   = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue  = (pixel) & 0xff;
        return new Color(red, green, blue);
    }

    private Integer cellFromColor(Color color) {
        Integer ret = null;
        for(CacheBox cc : cacheBoxes) {
            if(cc.color.equals(color)) {
                ret = cc.id;
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
