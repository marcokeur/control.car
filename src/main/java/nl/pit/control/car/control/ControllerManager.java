package nl.pit.control.car.control;

public class ControllerManager {
    public volatile static ControllerInterface DCCPlusPlusController;

    public static ControllerInterface getControllerInstance(){

        if(DCCPlusPlusController == null) {
            DCCPlusPlusController = new DCCPlusPlusController();
        }

        return DCCPlusPlusController;
    }
}
