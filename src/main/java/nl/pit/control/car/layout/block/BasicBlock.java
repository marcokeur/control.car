package nl.pit.control.car.layout.block;

import nl.pit.control.car.occupancy.Detector;

public class BasicBlock implements Block {
    Integer id = null;
    String name = null;
    Integer length = null;

    Integer nextId = null;
    Integer prevId = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setNextId(Integer id) {
        nextId = id;
    }

    @Override
    public void setPrevId(Integer id) {
        prevId = id;
    }

    @Override
    public Integer getPrevId() {
        return prevId;
    }

    @Override
    public Integer getNextId() {
        return nextId;
    }

    @Override
    public void detectorWasTriggered(Detector detector) {

    }
}
