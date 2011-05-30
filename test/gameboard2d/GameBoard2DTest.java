package gameboard2d;

import gameboard2d.board.Board;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoard2DTest {

    private GameBoard2D gb2d;

    public GameBoard2DTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws IOException {
        gb2d = new GameBoard2D(new File("test/images/ttt-board.png"),
                new File("test/images/ttt-board-cache.png"),
                new Dimension(300,300));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetBoard() {
        assertNotNull(gb2d.getBoard());
    }

    @Test
    public void testAddPiece() throws Exception {
        gb2d.addPiece("circle", new File("test/images/circle.png"));
        assertTrue(gb2d.pieces.containsKey("circle"));
    }

    @Test
    public void testGetPreferredSize() {
        assertEquals(new Dimension(300,300), gb2d.getPreferredSize());
    }

    @Test
    public void testGetWidth() {
        assertEquals(300, gb2d.getWidth());
    }

    @Test
    public void testGetHeight() {
        assertEquals(300, gb2d.getHeight());
    }

    @Test
    public void testGetVersion() {
        assertEquals(String.class, gb2d.getVersion().getClass());
    }

}