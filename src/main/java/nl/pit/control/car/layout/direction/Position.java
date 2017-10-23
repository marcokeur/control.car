package nl.pit.control.car.layout.direction;

import nl.pit.control.car.exception.BlockException;
import nl.pit.control.car.exception.PositionException;
import nl.pit.control.car.layout.LayoutManager;
import nl.pit.control.car.layout.block.Block;
import nl.pit.control.car.vehicle.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Position {
    final static Logger logger = LogManager.getLogger(Position.class.getName());

    public Direction direction;
    public Double distance;
    public Block currentBlock;

    private Timestamp lastUpdate;

    public Position(){
        lastUpdate = getNow();
        direction = Direction.NEXT;
        distance = 0d;
    }

    private Timestamp getNow() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        return new Timestamp(now.getTime());
    }

    public Double updatePosition(Double speed) throws PositionException, BlockException {
        Timestamp now = getNow();

        if(speed == null){
            throw new PositionException("Illegal speed: " + speed);
        }

        /* Get the time difference since last update in ms */
        long timeDiff = now.getTime() - lastUpdate.getTime();
        /* Convert to seconds */
        Double d = (double)timeDiff / 1000;
        /* Calculate the traveled distance since last update */
        Double distanceTraveled = d * speed;
        /* Update the current position */
        distance += distanceTraveled;
        /* Update the last update time to "now" */
        lastUpdate = now;

        if(currentBlock == null){
            throw new PositionException("Current block unknown");
        }

        /* Check if we are out of bounds of currentBlock */
        if(distance > currentBlock.getLength()){
            /* Calculate the amount that was overshot and store for later */
            Double distanceTraveledInNewBlock =
                    distance - currentBlock.getLength();
            /* If so, get the next or previous block as current */
            if(direction == Direction.NEXT){
                currentBlock = LayoutManager.getInstance().getNext(currentBlock);
            }else if(direction == Direction.PREV){
                currentBlock = LayoutManager.getInstance().getPrev(currentBlock);
            }else{
                throw new PositionException("Direction unknown");
            }

            /* store the new distance */
            distance = distanceTraveledInNewBlock;

            logger.debug("Travaled " + distanceTraveledInNewBlock + "mm in the " + direction + " block " + currentBlock.getName() + "");
        }

        return distanceTraveled;
    }

}

