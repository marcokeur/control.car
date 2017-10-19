package nl.pit.control.car.layout;

import nl.pit.control.car.layout.block.Block;
import nl.pit.control.car.layout.block.BlockFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LayoutManagerTest {
    @Test
    void initialize(){
        LayoutManager layoutManager = LayoutManager.getInstance();
        assertNotNull(layoutManager);

    }
/*
    @Test
    void integration(){
        Block b;

        LayoutManager layoutManager = LayoutManager.getInstance();
        assertNotNull(layoutManager);

        assertEquals(layoutManager.getBlockListCount(), 0);

        b = BlockFactory.getBlock("Basic");
        assertNotNull(b);

        layoutManager.addBlock(b);
        assertEquals(layoutManager.getBlockListCount(), 1);

        b = null;
        assertNull(b);
        b = BlockFactory.getBlock("Basic");
        assertNotNull(b);

        layoutManager.addBlock(b);
        assertEquals(layoutManager.getBlockListCount(), 2);

        b = layoutManager.getBlock(1);
        b.setNext(layoutManager.getBlock(2));
        assertEquals(layoutManager.getBlock(2).getPrev(), b);

        layoutManager.removeBlock(1);
        layoutManager.removeBlock(1);

        assertEquals(layoutManager.getBlockListCount(), 0);

    }

    @Test
    */
    /* link 4 basic blocks in a circle and validate that it is never ending */
    /*
    void integration_circle(){
        Block b;

        Block b1 = BlockFactory.getBlock("Basic");
        Block b2 = BlockFactory.getBlock("Basic");
        Block b3 = BlockFactory.getBlock("Basic");
        Block b4 = BlockFactory.getBlock("Basic");

        b1.setNext(b2);
        b2.setNext(b3);
        b3.setNext(b4);
        b4.setNext(b1);

        b = b1;

        for(int i = 0; i < 100; i++){
            Block btmp = b;

            b = btmp.getNext();
            assertNotNull(b);
        }

        for(int i = 0; i < 100; i++){
            Block btmp = b;

            b = btmp.getPrev();
            assertNotNull(b);
        }
    }
    */
}
