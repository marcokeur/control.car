package nl.pit.control.car.layout.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BasicBlockTest {
    Block b = null;

    @Test
    void initialize(){
        b = new BasicBlock();
        assertNotNull(b);
    }

    @Test
    void length(){
        Block b1 = BlockFactory.getBlock("Basic");
        b1.setLength(100);
        assertEquals((Integer)100, b1.getLength());
    }

    @Test
    void name(){
        Block b1 = BlockFactory.getBlock("Basic");
        b1.setName("test");
        assertEquals("test", b1.getName());
    }
/*
    @Test
    void next(){
        Block b1 = BlockFactory.getBlock("Basic");
        Block b2 = BlockFactory.getBlock("Basic");

        b1.setNext(b2);

        assertEquals(b1.getNext(), b2);
        assertEquals(b2.getPrev(), b1);
    }

    @Test
    void prev(){
        Block b1 = BlockFactory.getBlock("Basic");
        Block b2 = BlockFactory.getBlock("Basic");

        b1.setPrev(b2);

        assertEquals(b1.getPrev(), b2);
        assertEquals(b2.getNext(), b1);
    }
    */
}
