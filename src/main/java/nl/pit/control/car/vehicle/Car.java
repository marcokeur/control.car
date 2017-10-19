package nl.pit.control.car.vehicle;

import nl.pit.control.car.BlockException;
import nl.pit.control.car.PositionException;
import nl.pit.control.car.control.ControllerManager;
import nl.pit.control.car.layout.block.Block;
import nl.pit.control.car.layout.direction.Direction;
import nl.pit.control.car.layout.direction.Position;


public class Car implements Vehicle {

    private Integer address = 3;

    /* speed step, index for the speedTable */
    private Integer speed = 0;

    /* speedtable translates the speedstep to an actual speed in mm/s */
    private Double[] speedTable = {10d, 20d, 30d, 40d, 50d, 60d, 70d, 80d,
                                    90d, 100d, 110d, 120d, 130d, 140d};

    private Position currentPosition = new Position();

    public void updatePosition(){
        try {
            currentPosition.updatePosition(speedTable[speed]);
        } catch (PositionException e) {
            e.printStackTrace();
        } catch (BlockException e) {
            e.printStackTrace();
        }
    }

    public void setSpeed(Integer speed) {
        /*if we change the speed ,first update the position */
        try {
            currentPosition.updatePosition(speedTable[speed]);
        } catch (PositionException e) {
            e.printStackTrace();
        } catch (BlockException e) {
            e.printStackTrace();
        }

        /*then set the new speed */
        this.speed = speed;

        /*transmit the change of speed */
        ControllerManager.getControllerInstance().setVehicleThrottleStep(this.address, this.speed);
    }

    public Integer getSpeed() {
        return speed;
    }

    @Override
    public Position getPostion() {
        return currentPosition;
    }

    @Override
    public void setDirection(Direction d) {
        currentPosition.direction = d;
    }

    @Override
    public void setDistance(Double d) {
        currentPosition.distance = d;

        //lastUpdate = getNow();
    }

    public Block getCurrentBlock() {
        return currentPosition.currentBlock;
    }

    public void setCurrentBlock(Block currentBlock) {
        this.currentPosition.currentBlock = currentBlock;
    }
}
