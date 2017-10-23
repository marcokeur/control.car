package nl.pit.control.car.layout.block;

import nl.pit.control.car.occupancy.DetectorListener;

public interface Block extends DetectorListener {

    Integer getId();
    void setId(Integer id);

    void setNextId(Integer id);

    void setPrevId(Integer id);

    Integer getPrevId();

    Integer getNextId();

    void setLength(Integer l);

    Integer getLength();

    String getName();

    void setName(String test);
}
