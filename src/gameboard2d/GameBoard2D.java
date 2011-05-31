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
     */
    public GameBoard2D(){
        pieces = new HashMap<String, Piece>();
    }

    /**
     * Set <em>"the"</em> board of the game.
     * There is only one board per game. If you call this method
     * a second time, board data will be replaced.
     * @param boardFile the image of the board
     * @param cacheFile the image of the board's cache
     * @throws IOException if a file doesn't exist (or is unreadable, ...)
     */
    public void setBoard(File boardFile, File cacheFile) throws IOException {
        board = new Board(boardFile, cacheFile);
        Dimension d = board.getDimension();
        offscreenImage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
        setSize(d);
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
     * Display a piece on the board, deleting the background first.
     * @param name the name of the piece to display
     * @param boxId the box id in wich to display the piece
     */
    public void drawPiece(String name, int boxId) {
        Graphics2D g = offscreenImage.createGraphics();
        BufferedImage background = board.getImage();
        Box box = board.getBox(boxId);
        BufferedImage piece = pieces.get(name).getImage();

        int x1 = box.getPoint().x;
        int y1 = box.getPoint().y;
        int x2 = x1 + piece.getWidth();
        int y2 = y1 + piece.getHeight();

        g.drawImage(background, x1, y1, x2, y2, x1, y1, x2, y2, null);
        g.drawImage(piece, x1, y1, null);
        repaint();
    }

    public void drawPieceOnTop(String name, int boxId) {
        Graphics2D g = offscreenImage.createGraphics();
        Box box = board.getBox(boxId);
        BufferedImage piece = pieces.get(name).getImage();

        int x1 = box.getPoint().x;
        int y1 = box.getPoint().y;

        g.drawImage(piece, x1, y1, null);
        repaint();
    }

    /**
     * Convenience method.
     * @see Board#addBox
     */
    public void addBox(int id, Color cache, Point reference) {
        board.addBox(id, cache, reference);
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
