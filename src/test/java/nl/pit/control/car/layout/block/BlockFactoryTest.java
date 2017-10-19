package nl.pit.control.car.layout.block;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BlockFactoryTest {
    @Test
    void getNormalBlock(){
        Block b = BlockFactory.getBlock("Basic");
        assertEquals(BasicBlock.class, b.getClass());
    }

    @Test
    void getSwitchBlock(){
        Block b = BlockFactory.getBlock("Switch");
        assertEquals(TurnoutBlock.class, b.getClass());
    }

    @Test
    void getInvalidBlock(){
        Block b = BlockFactory.getBlock("Invalid");
        assertNull(b);
    }
}
