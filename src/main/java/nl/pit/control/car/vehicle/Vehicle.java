package nl.pit.control.car.vehicle;

import nl.pit.control.car.layout.direction.Direction;
import nl.pit.control.car.layout.direction.Position;

public interface Vehicle {
    Position getPostion();

    void setDirection(Direction d);

    void setDistance(Double d);

    void setLength(Integer length);

    void setAddress(Integer address);

    void setSpeed(Integer speed);

    void addSpeedStep(Integer index, Double speed);

    void updatePosition();
}
