package nl.pit.control.car;

import nl.pit.control.car.layout.LayoutManager;
import nl.pit.control.car.layout.block.Block;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SaxParserTest {
    @BeforeAll
    static void loadLayout(){
        ClassLoader classLoader = SaxParserTest.class.getClassLoader();
        File file = new File(classLoader.getResource("layout.xml").getFile());
        try {
            InputStream input = new FileInputStream(file);
            SaxParser.parse(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkBlockListCount(){
        LayoutManager layoutManager = LayoutManager.getInstance();
        int count = layoutManager.getBlockListCount();

        assertEquals(5, count);
    }

    @Test
    void checkBlockName(){
        LayoutManager layoutManager = LayoutManager.getInstance();
        Block b = null;

        b = layoutManager.getBlock(0);
        assertNull(b);

        b = layoutManager.getBlock(1);
        assertEquals("block2", b.getName());

        b = layoutManager.getBlock(2);
        assertEquals("block4", b.getName());

        b = layoutManager.getBlock(3);
        assertEquals("block3", b.getName());

        b = layoutManager.getBlock(4);
        assertEquals(null, b.getName());

        b = layoutManager.getBlock(5);
        assertEquals("BLOCK", b.getName());
    }

    @Test
    void checkBlockLength(){
        LayoutManager layoutManager = LayoutManager.getInstance();
        Block b = null;

        b = layoutManager.getBlock(0);
        assertNull(b);

        b = layoutManager.getBlock(1);
        assertEquals((Integer)130, b.getLength());

        b = layoutManager.getBlock(2);
        assertEquals((Integer)110, b.getLength());

        b = layoutManager.getBlock(3);
        assertEquals((Integer)800, b.getLength());

        b = layoutManager.getBlock(4);
        assertEquals((Integer)null, b.getLength());

        b = layoutManager.getBlock(5);
        assertEquals((Integer)10000, b.getLength());
    }

    @Test
    void checkBlockNextId(){
        LayoutManager layoutManager = LayoutManager.getInstance();
        Block b = null;

        b = layoutManager.getBlockByName("block2");
        assertEquals((Integer)3, b.getNextId());

        b = layoutManager.getBlockByName("block3");
        assertEquals((Integer)4, b.getNextId());

        b = layoutManager.getBlockByName("block4");
        assertEquals((Integer)2, b.getNextId());
    }


    @AfterAll
    static void cleanLayout()
    {
        LayoutManager lm = LayoutManager.getInstance();

        while(lm.getBlockListCount() != 0){
            lm.removeBlock(1);
        }
    }
}
