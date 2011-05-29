package gameboard2d.board;

import gameboard2d.cell.*;
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
    protected ArrayList<CacheCell> cacheCells;
    protected HashMap<Integer, Cell> cells;

    /**
     * Sole constructor.
     * @param boardFilename the name of the image of the board
     * @param cacheFilename the name of the image of the board's cache
     * @throws IOException if a file doesn't exist (or is unreadable, ...)
     * @todo Remplacer les String par des File.
     */
    public Board(String boardFilename, String cacheFilename) throws IOException {
        cacheCells = new ArrayList<CacheCell>();
        cells = new HashMap<Integer, Cell>();
        boardImage = ImageIO.read(new File(boardFilename));
        cacheImage = ImageIO.read(new File(cacheFilename));
    }

    /**
     * Add a box for this board.
     * @param number the number of the boow. Must be uniq. Could be any integer, even negative's one.
     * @param cache the Color of the cache area for this box.
     * @param reference the coordinates of the point that references this box.
     * @param d the dimension of this box area
     * @todo explain concept of "cache area" and "reference point"
     * @todo think hard: I don't want a dimension for a box !
     * @todo number must be uniq: check it !
     * @todo cache must be uniq: check it !
     */
    public void addCell(int number, Color cache, Point reference, Dimension d) {
        cacheCells.add(new CacheCell(number, cache));
        cells.put(number, new Cell(reference, d));
    }

    /**
     * Return the image of this board as a BufferedImage.
     * @todo name it getImage for consistency
     */
    public BufferedImage image() {
        return boardImage;
    }

    /**
     * Return the number (ID) of the box that is pointed to by point.
     * @param point the coordinate of the point for wich you want to know the box's number.
     * @return the box's number
     */
    public int getCellNumber(Point point) {
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
        for(CacheCell cc : cacheCells) {
            if(cc.color.equals(color)) {
                ret = cc.number;
            }
        }
        return ret;
    }

    /**
     * Return the cell referenced by number.
     * @param number the Cell you want
     * @return a cell
     */
    public Cell getCell(int number) {
        return cells.get(number);
    }

}
