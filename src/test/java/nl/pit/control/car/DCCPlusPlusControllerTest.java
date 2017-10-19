package nl.pit.control.car;

import nl.pit.control.car.control.ControllerInterface;
import nl.pit.control.car.control.ControllerManager;
import org.junit.jupiter.api.Test;

public class DCCPlusPlusControllerTest {

    @Test
    void connect() throws InterruptedException {
        ControllerInterface controller = ControllerManager.getControllerInstance();
        controller.connect();
        controller.turnTrackPowerOn();
        controller.setVehicleThrottleStep(3, 20);
        controller.setVehicleThrottleStep(3, 100);
        controller.setVehicleThrottleStep(3, 20);
        controller.turnTrackPowerOff();
        controller.disconnect();
        Thread.sleep(1000);
    }
}
