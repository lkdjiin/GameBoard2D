package gameboard2d.piece;

import gameboard2d.GameBoard2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Represents a piece of the game.
 * @see GameBoard2D#addPiece
 */
public class Piece {

    public BufferedImage image;

    /**
     * Sole constructor.
     * @param filename the name of the image of the piece
     * @throws IOException if a file doesn't exist (or is unreadable, ...)
     */
    public Piece(String filename) throws IOException {
        image = ImageIO.read(new File(filename));
    }


}
