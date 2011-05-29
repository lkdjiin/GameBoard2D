package gameboard2d;

import gameboard2d.board.Board;
import gameboard2d.cell.Cell;
import gameboard2d.piece.Piece;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 * This is the main class of the GameBoard2D library.
 * For now, this is the only class you need to import in your own project.
 */
public class GameBoard2D extends JPanel {

    private BufferedImage offscreenImage;
    private Board board;
    private HashMap<String, Piece> pieces;

    /**
     * Create a GameBoard2D you can use throughout your project.
     * @param boardFilename the name of the image of the board
     * @param cacheFilename the name of the image of the board's cache
     * @param dimension the dimension of the board
     * @throws IOException if a file doesn't exist (or is unreadable, ...)
     * @todo Remplacer les String par des File.
     * @todo Ne pas passer les dimensions, on peut retrouver ça depuis l'image.
     */
    public GameBoard2D(String boardFilename, String cacheFilename, Dimension dimension) throws IOException {
        board = new Board(boardFilename, cacheFilename);
        setSize(dimension);
        pieces = new HashMap<String, Piece>();
        offscreenImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Return the Board of the game. For now, you can do all the stuff from
     * within GameBoard2D, so you don't really need this method.
     */
    public Board board() {
        return board;
    }


    /**
     * Add a new piece of the game.
     * <p>
     * Remember that GameBoard2D only deals with the graphic part of your game. So, you don't
     * need to add EVERY pieces of your game. For example, in a chess game, you should add only
     * one "white pawn", not eigth.
     * @param name the name of the piece. You will retrieve the piece by its name.
     * @param filename the name of the image of the piece
     * @throws IOException if the file doesn't exist (or is unreadable, ...)
     * @todo remplacer String par File
     */
    public void addPiece(String name, String filename) throws IOException {
        pieces.put(name, new Piece(filename));
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
    public void displayBoard() {
        Graphics2D g = offscreenImage.createGraphics();
        g.drawImage(board.image(), 0, 0, null);
        repaint();
    }

    /**
     * Display a piece on the board.
     * @param name the name of the piece to display
     * @param cellNumber the cell in wich to display the piece
     * @todo peut être trouver un meilleur nom (displayPiece ?)
     * @todo changer Cell pour Box
     */
    public void putPiece(String name, int cellNumber) {
        Graphics2D g = offscreenImage.createGraphics();
        BufferedImage background = board.image();
        Cell cell = board.getCell(cellNumber);
        int x1 = cell.point.x;
        int y1 = cell.point.y;
        int x2 = x1 + cell.dimension.width;
        int y2 = y1 + cell.dimension.height;
        g.drawImage(background, x1, y1, x2, y2, x1, y1, x2, y2, null);

        BufferedImage piece = pieces.get(name).image;
        g.drawImage(piece, x1, y1, null);

        repaint();
    }

    /**
     * Convenience method.
     * @see Board#addCell(int, Color, Point, Dimension)
     */
    public void addCell(int number, Color cache, Point reference, Dimension d) {
        board.addCell(number, cache, reference, d);
    }

    /**
     * Convenience method.
     * @see Board#getCellNumber(Point) 
     */
    public int getCellNumber(Point point) {
        return board.getCellNumber(point);
    }

    /**
     * Return the version of this library.
     */
    public String getVersion() {
        return ApplicationVersion.VERSION;
    }

}