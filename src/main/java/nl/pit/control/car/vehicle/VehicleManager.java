package nl.pit.control.car.vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleManager {


        List<Vehicle> vehicleList = new ArrayList<>();

        public volatile static VehicleManager vehicleManager = null;

        private VehicleManager(){

        }

        public static VehicleManager getInstance(){
            if(vehicleManager == null){
                synchronized (VehicleManager.class){
                    if(vehicleManager == null){
                        vehicleManager = new VehicleManager();
                    }
                }
            }

            return vehicleManager;
        }

        public void addVehicle(Vehicle v){
            vehicleList.add(v);
        }

        public Integer getVehicleCount(){
            return vehicleList.size();
        }

        public Vehicle getVehicle(Integer index){
            return vehicleList.get(index);
        }
}
