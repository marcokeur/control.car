package nl.pit.control.car.control;

public class SimulatedController implements ControllerInterface {

    boolean connected = false;

    @Override
    public boolean connect() {
        if(connected == true) {
            return false;
        }else{
            connected = true;
            return true;
        }
    }

    @Override
    public boolean disconnect() {
        if(connected == false){
            return false;
        }else{
            connected = false;
            return true;
        }
    }

    private boolean sendCmd(){
        return connected;
    }

    @Override
    public boolean turnTrackPowerOn() {
        return sendCmd();
    }

    @Override
    public boolean turnTrackPowerOff() {
        return sendCmd();
    }

    @Override
    public boolean setVehicleThrottleStep(Integer address, Integer step) {
        return sendCmd();
    }

    @Override
    public boolean setVehicleFunction(Integer address, Boolean[] functionArray) {
        return sendCmd();
    }

    @Override
    public boolean setAccessoryFunction(Integer address, Integer subAddress, Boolean active) {
        return sendCmd();
    }

    @Override
    public boolean setTurnoutFunction(Integer address, Boolean active) {
        return sendCmd();
    }
}
