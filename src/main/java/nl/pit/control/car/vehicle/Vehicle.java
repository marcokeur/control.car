package nl.pit.control.car.vehicle;

import nl.pit.control.car.layout.direction.Direction;
import nl.pit.control.car.layout.direction.Position;

public interface Vehicle {
    Position getPostion();
    void setDirection(Direction d);
    void setDistance(Double d);
}
