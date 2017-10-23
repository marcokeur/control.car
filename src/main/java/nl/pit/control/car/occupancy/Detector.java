package nl.pit.control.car.occupancy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Detector {

    DetectorManager detectorManager;
    Integer id;
    Timestamp lastTrigger = null;
    List<DetectorListener> detectorListenerList = new ArrayList<>();

    public Detector(DetectorManager detectorManager, Integer id){
        this.detectorManager = detectorManager;
        this.id = id;
    }


    private Timestamp getNow() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        return new Timestamp(now.getTime());
    }

    public void trigger(){
        lastTrigger = getNow();

        for(DetectorListener dl : detectorListenerList){
            dl.detectorWasTriggered(this);
        }
    }

    public void addListener(DetectorListener dl){
        detectorListenerList.add(dl);
    }

    public void removeListener(DetectorListener dl){
        detectorListenerList.remove(dl);
    }

    public Integer getId() {
        return id;
    }
}
