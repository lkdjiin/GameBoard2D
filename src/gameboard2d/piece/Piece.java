package gameboard2d.piece;

import gameboard2d.GameBoard2D;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Represents a piece of the game.
 * @see GameBoard2D#addPiece
 */
public class Piece {

    private BufferedImage image;

    /**
     * Sole constructor.
     * @param filename the name of the image of the piece
     * @throws IOException if a file doesn't exist (or is unreadable, ...)
     */
    public Piece(File file) throws IOException {
        image = ImageIO.read(file);
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public Dimension getDimension() {
        return new Dimension(image.getWidth(), image.getHeight());
    }
  
}
