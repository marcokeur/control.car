import nl.pit.control.car.SaxParser;
import nl.pit.control.car.SaxParserTest;
import nl.pit.control.car.occupancy.ArduinoDetector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ArduinoDetectorTest {
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
    }

    @Test
    void doWork() throws InterruptedException {
        ArduinoDetector ad = new ArduinoDetector();

        ad.connect();

        Thread.sleep(10000);
    }
}
