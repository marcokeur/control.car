package nl.pit.control.car.occupancy;

import java.util.ArrayList;
import java.util.List;

public class DetectorManager {

    List<Detector> detectorList = new ArrayList<>();

    public volatile static DetectorManager detectorManager;

    private DetectorManager(){

    }

    public static DetectorManager getInstance(){
        if(detectorManager == null){
            synchronized (DetectorManager.class){
                if(detectorManager == null){
                    detectorManager = new DetectorManager();
                }
            }
        }

        return detectorManager;
    }

    public void triggerDetector(Integer address){
        for(Detector d : detectorList){
            if(d.getAddress() == address){
                d.trigger();
                break;
            }
        }
    }

    public void addDetector(Detector d){
        detectorList.add(d);
    }

    public void listenToDetector(DetectorListener dl, Integer id){
        for(Detector d : detectorList){
            if(d.getId() == id){
                d.addListener(dl);
            }
        }
    }
}
