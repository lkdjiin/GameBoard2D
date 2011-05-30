package gameboard2d.cell;

import gameboard2d.box.CacheBox;
import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CacheCellTest {

    CacheBox cc;

    public CacheCellTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        cc = new CacheBox(123, new Color(10, 10, 10));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testNumber() {
        assertEquals(123, cc.id);
    }

    @Test
    public void testColor() {
        assertEquals(new Color(10,10,10), cc.color);
    }
}