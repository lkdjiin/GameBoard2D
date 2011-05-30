package gameboard2d.board;

import java.io.*;
import java.awt.*;
import gameboard2d.box.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws IOException {
        board = new Board(new File("test/images/ttt-board.png"), new File("test/images/ttt-board-cache.png"));
    }

    @After
    public void tearDown() {
    }

    private void initializeCache() {
        board.addBox(0, new Color(0,0,0), new Point(0,0));
        board.addBox(1, new Color(20,20,20), new Point(100,0));
        board.addBox(2, new Color(40,40,40), new Point(200,0));
        board.addBox(3, new Color(60,60,60), new Point(0,100));
        board.addBox(4, new Color(80,80,80), new Point(100,100));
        board.addBox(5, new Color(100,100,100), new Point(200,100));
        board.addBox(6, new Color(120,120,120), new Point(0,200));
        board.addBox(7, new Color(140,140,140), new Point(100,200));
        board.addBox(8, new Color(160,160,160), new Point(200,200));
    }

    @Test(expected= IOException.class)
    public void testMustThrowIOExceptionIfBoardFileDontExist() throws IOException {
        Board board1 = new Board(new File("test/images/unknown.png"), new File("test/images/ttt-board-cache.png"));
    }

    @Test(expected= IOException.class)
    public void testMustThrowIOExceptionIfCacheFileDontExist() throws IOException {
        Board board1 = new Board(new File("test/images/ttt-board.png"), new File("test/images/unknown.png"));
    }

    @Test(expected= IOException.class)
    public void testMustThrowIOExceptionIfBadPath() throws IOException {
        Board board1 = new Board(new File("bad/path/ttt-board.png"), new File("test/images/ttt-board-cache.png"));
    }

    @Test
    public void testAddBox() {
        Box box = new Box(new Point(0,0), new Color(0, 1, 2));
        board.addBox(123, new Color(0, 1, 2), new Point(0,0));
        assertTrue(board.boxes.containsKey(123));
        assertTrue(board.boxes.containsValue(new Box(new Point(0,0), new Color(0, 1, 2))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMustNotAcceptTwoBoxesWithSameId() {
        board.addBox(123, new Color(0, 1, 2), new Point(0,0));
        board.addBox(123, new Color(9, 9, 9), new Point(9,9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMustNotAcceptTwoBoxesWithSameCacheColor() {
        board.addBox(123, Color.black, new Point(0,0));
        board.addBox(456, Color.black, new Point(9,9));
    }

    @Test
    public void testGetBoxId() {
        initializeCache();
        int cell = board.getBoxId(new Point(150, 150));
        assertEquals(4, cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetBoxIdThatDontExist() {
        initializeCache();
        int cell = board.getBoxId(new Point(400, 400));
    }

    @Test
    public void testGetImage() {
        assertNotNull(board.getImage());
    }

    @Test
    public void testGetBox() {
        board.addBox(123, new Color(0, 1, 2), new Point(0,0));
        Box box = board.getBox(123);
        assertEquals(new Color(0, 1, 2), box.getCacheColor());
        assertEquals(new Point(0,0), box.getPoint());
    }
}