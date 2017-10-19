package nl.pit.control.car.control;

import nl.pit.control.car.layout.direction.Direction;

public interface ControllerInterface {
    boolean connect();
    boolean disconnect();

    boolean turnTrackPowerOn();
    boolean turnTrackPowerOff();

    boolean setVehicleThrottleStep(Integer address, Integer step);
    boolean setVehicleFunction(Integer address, Boolean functionArray[]);

    boolean setAccessoryFunction(Integer address, Integer subAddress, Boolean active);
    boolean setTurnoutFunction(Integer address, Boolean active);

}