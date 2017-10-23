package nl.pit.control.car.occupancy;

import java.util.ArrayList;
import java.util.List;

public class DetectorManager {

    List<Detector> detectorList = new ArrayList<>();

    public void addDetector(Integer id){
        Detector d = new Detector(this, id);
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
