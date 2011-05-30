package gameboard2d.piece;

import java.io.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PieceTest {

    public PieceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(expected= IOException.class)
    public void testMustThrowIOExceptionIfFileDontExist() throws IOException {
        Piece p = new Piece(new File("test/images/unknown.png"));
    }

    @Test
    public void testMustHaveAccessToImage() throws IOException {
        Piece p = new Piece(new File("test/images/circle.png"));
        assertNotNull(p);
    }

    @Test
    public void testPieceKnowItsWidth() throws IOException {
        Piece p = new Piece(new File("test/images/circle.png"));
        assertEquals(100, p.getWidth());
    }

    @Test
    public void testPieceKnowItsHeight() throws IOException {
        Piece p = new Piece(new File("test/images/circle.png"));
        assertEquals(100, p.getHeight());
    }

}