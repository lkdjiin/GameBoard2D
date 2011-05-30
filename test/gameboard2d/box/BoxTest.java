package gameboard2d.box;

import java.awt.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoxTest {

    private Box box;

    public BoxTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        box = new Box(new Point(111,222), Color.yellow, new Dimension(100, 200));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEqualsAgainstNull() {
        assertFalse(box.equals(null));
    }

    @Test
    public void testEqualsAgainstString() {
        assertFalse(box.equals("hello"));
    }

    @Test
    public void testNotEquals() {
        Box b1 = new Box(new Point(111,999), Color.yellow, new Dimension(100, 200));
        Box b2 = new Box(new Point(111,222), Color.red, new Dimension(100, 200));
        assertFalse(box.equals(b1));
        assertFalse(box.equals(b2));
    }

    @Test
    public void testEquals() {
        Box b1 = new Box(new Point(111,222), Color.yellow, new Dimension(100, 200));
        assertEquals(b1, box);
    }

    @Test
    public void testHashCode() {
        Box b1 = new Box(new Point(111,999), Color.yellow, new Dimension(100, 200));
        assertTrue(box.hashCode() != b1.hashCode());
    }

}