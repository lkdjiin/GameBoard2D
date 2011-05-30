package gameboard2d;

import gameboard2d.board.Board;
import gameboard2d.box.Box;
import gameboard2d.piece.Piece;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 * This is the main class of the GameBoard2D library.
 * For now, this is the only class you need to import in your own project.
 */
public class GameBoard2D extends JPanel {

    private BufferedImage offscreenImage;
    private Board board;
    protected HashMap<String, Piece> pieces;

    /**
     * Create a GameBoard2D you can use throughout your project.
     * @param boardFile the image of the board
     * @param cacheFile the image of the board's cache
     * @param dimension the dimension of the board
     * @throws IOException if a file doesn't exist (or is unreadable, ...)
     */
    public GameBoard2D(File boardFile, File cacheFile, Dimension dimension) throws IOException {
        board = new Board(boardFile, cacheFile);
        setSize(dimension);
        pieces = new HashMap<String, Piece>();
        offscreenImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Return the Board of the game. For now, you can do all the stuff from
     * within GameBoard2D, so you don't really need this method.
     */
    public Board getBoard() {
        return board;
    }


    /**
     * Add a new piece of the game.
     * <p>
     * Remember that GameBoard2D only deals with the graphic part of your game. So, you don't
     * need to add EVERY pieces of your game. For example, in a chess game, you should add only
     * one "white pawn", not eigth.
     * @param name the name of the piece. You will retrieve the piece by its name.
     * @param file the image of the piece
     * @throws IOException if the file doesn't exist (or is unreadable, ...)
     */
    public void addPiece(String name, File file) throws IOException {
        pieces.put(name, new Piece(file));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(offscreenImage.getWidth(null), offscreenImage.getHeight(null));
    }

    @Override
    public int getWidth() {
        return offscreenImage.getWidth(null);
    }

    @Override
    public int getHeight() {
        return offscreenImage.getHeight(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(offscreenImage, 0, 0, null);
    }

    /**
     * Display the board, without any pieces.
     * Useful to initialize your screen.
     */
    public void drawBoard() {
        Graphics2D g = offscreenImage.createGraphics();
        g.drawImage(board.getImage(), 0, 0, null);
        repaint();
    }

    /**
     * Display a piece on the board.
     * @param name the name of the piece to display
     * @param boxId the box id in wich to display the piece
     */
    public void drawPiece(String name, int boxId) {
        Graphics2D g = offscreenImage.createGraphics();
        BufferedImage background = board.getImage();
        Box box = board.getBox(boxId);
        int x1 = box.getPoint().x;
        int y1 = box.getPoint().y;
        int x2 = x1 + box.getDimension().width;
        int y2 = y1 + box.getDimension().height;
        g.drawImage(background, x1, y1, x2, y2, x1, y1, x2, y2, null);

        BufferedImage piece = pieces.get(name).getImage();
        g.drawImage(piece, x1, y1, null);

        repaint();
    }

    /**
     * Convenience method.
     * @see Board#addBox(int, Color, Point, Dimension)
     */
    public void addBox(int id, Color cache, Point reference, Dimension d) {
        board.addBox(id, cache, reference, d);
    }

    /**
     * Convenience method.
     * @see Board#getBoxId(Point)
     */
    public int getBoxId(Point point) {
        return board.getBoxId(point);
    }

    /**
     * Return the version of this library.
     */
    public String getVersion() {
        return ApplicationVersion.VERSION;
    }

}
