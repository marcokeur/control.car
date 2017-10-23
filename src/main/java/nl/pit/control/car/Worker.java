package nl.pit.control.car;

import nl.pit.control.car.layout.LayoutManager;
import nl.pit.control.car.layout.block.Block;
import nl.pit.control.car.vehicle.Car;
import nl.pit.control.car.vehicle.Vehicle;
import nl.pit.control.car.vehicle.VehicleManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;

public class Worker extends TimerTask{
    final static Logger logger = LogManager.getLogger(Worker.class.getName());

    @Override
    public void run() {

        logger.debug("Worker does work");

        VehicleManager vm = VehicleManager.getInstance();

        vm.getVehicleCount();

        for(int i = 0; i < vm.getVehicleCount(); i++){
            Vehicle v = vm.getVehicle(i);
            v.updatePosition();
        }
    }
}
