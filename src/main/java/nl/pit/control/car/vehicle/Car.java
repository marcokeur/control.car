package nl.pit.control.car.vehicle;

import nl.pit.control.car.exception.BlockException;
import nl.pit.control.car.exception.PositionException;
import nl.pit.control.car.control.ControllerManager;
import nl.pit.control.car.layout.block.Block;
import nl.pit.control.car.layout.direction.Direction;
import nl.pit.control.car.layout.direction.Position;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Car implements Vehicle {

    final static Logger logger = LogManager.getLogger(Car.class.getName());


    private Integer address = 3;

    /* speed step, index for the speedTable */
    private Integer speed = 0;

    /* speedtable translates the speedstep to an actual speed in mm/s */
    private Double[] speedTable = new Double[15];

    private Integer length = null;

    private Position currentPosition = new Position();

    public void updatePosition(){
        try {
            Double distanceTraveled = currentPosition.updatePosition(speedTable[speed]);
            logger.debug("Car traveled " + distanceTraveled + " since last updatePosition()");
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
    }

    @Override
    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public void setAddress(Integer address) {
        this.address = address;
    }

    @Override
    public void addSpeedStep(Integer index, Double speed) {
        logger.debug("Speedstep {} added, speed is {} mm/s.", index, speed);
        speedTable[index] = speed;
    }

    public Block getCurrentBlock() {
        return currentPosition.currentBlock;
    }

    public void setCurrentBlock(Block currentBlock) {
        this.currentPosition.currentBlock = currentBlock;
    }
}
