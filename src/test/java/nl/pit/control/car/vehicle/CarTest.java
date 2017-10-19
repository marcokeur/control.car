package nl.pit.control.car.vehicle;

import nl.pit.control.car.layout.LayoutManager;
import nl.pit.control.car.layout.block.BasicBlock;
import nl.pit.control.car.layout.block.Block;
import nl.pit.control.car.layout.block.BlockFactory;
import nl.pit.control.car.layout.direction.Direction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CarTest {
    @Test
    void initialization(){
        Car c = new Car();
    }

    @Test
    void updatePositionTest() throws InterruptedException {
        /* Create a circular route */
        Block b1 = BlockFactory.getBlock("Basic");
        b1.setLength(1000);
        Block b2 = BlockFactory.getBlock("Basic");

        LayoutManager.getInstance().addBlock(b1);
        LayoutManager.getInstance().addBlock(b2);

        b2.setLength(1000);
        LayoutManager.getInstance().setNext(b1, b2);
        LayoutManager.getInstance().setPrev(b2, b1);

        Car c = new Car();
        c.setCurrentBlock(b1);
        c.setDirection(Direction.NEXT);
        c.setDistance(100.00);
        c.updatePosition();
        c.updatePosition();
        assertTrue(c.getPostion().distance > (Double)100.0);
        assertTrue(c.getPostion().distance < (Double)100.1, "Error, distance too high");
        c.setSpeed(2);
        c.updatePosition();
        Thread.sleep(2000);
        c.updatePosition();

        Double d = c.getPostion().distance;
        assertTrue(d > (Double)160.0);
        assertTrue(d < (Double)161.0, "Error, distance too high");

        Thread.sleep(2000);
        c.updatePosition();

        d = c.getPostion().distance;
        assertTrue(d > (Double)220.0);
        assertTrue(d < (Double)221.0, "Error, distance too high");

        assertEquals(b1, c.getCurrentBlock());

        c.setSpeed(12);
        Thread.sleep(7000);
        c.updatePosition();

        assertEquals(b2, c.getCurrentBlock());
    }

    @AfterAll
    static void cleanLayout()
    {
        LayoutManager lm = LayoutManager.getInstance();
        int items = lm.getBlockListCount();

        while(lm.getBlockListCount() != 0){
            lm.removeBlock(1);
        }
    }
}
