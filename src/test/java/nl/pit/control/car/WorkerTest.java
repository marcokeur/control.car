package nl.pit.control.car;

import nl.pit.control.car.layout.LayoutManager;
import nl.pit.control.car.vehicle.Car;
import nl.pit.control.car.vehicle.Vehicle;
import nl.pit.control.car.vehicle.VehicleManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class WorkerTest {
    @BeforeAll
    static void loadLayout(){
        ClassLoader classLoader = SaxParserTest.class.getClassLoader();
        File file = new File(classLoader.getResource("layout.xml").getFile());
        try {
            InputStream input = new FileInputStream(file);
            SaxParser.parse(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        VehicleManager vm = VehicleManager.getInstance();
        Vehicle v = vm.getVehicle(0);

        v.getPostion().currentBlock = LayoutManager.getInstance().getBlock(1);
    }

    @Test
    void doWork() throws InterruptedException {
        TimerTask task = new Worker();

        Timer timer = new Timer();

        long delay = 0;         /* no delay to first start */
        long interval = 200;    /* run with interval of 200ms */

        timer.scheduleAtFixedRate(task, delay, interval);

        Thread.sleep(1000);

        VehicleManager.getInstance().getVehicle(0).setSpeed(12);

        Thread.sleep(10000);
    }
}
