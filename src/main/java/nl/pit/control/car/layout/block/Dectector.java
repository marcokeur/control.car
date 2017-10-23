package nl.pit.control.car.layout.block;

import nl.pit.control.car.layout.direction.Position;

import java.sql.Timestamp;

public class Dectector {
    Position position = null;
    Boolean active = null;
    Timestamp lastUpdate = null;
    Integer id = null;

    public Integer getId(){
        return id;
    }

}
