package nl.pit.control.car.occupancy;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.util.BitSet;

public class ArduinoDetector {
    SerialPort serialPort = null;

    private void debug(String message){
        System.out.println("ArduinoDetector " + message);
    }

    public boolean connect() {
        //serialPort = SerialPort.getCommPorts()[0];
        serialPort = SerialPort.getCommPort("/dev/ttyUSB0");
        serialPort.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
        serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[serialPort.bytesAvailable()];
                serialPort.readBytes(newData, newData.length);

                BitSet bitset = BitSet.valueOf(newData);
                for(int i = 0; i < 8; i++){
                    if(bitset.get(i) == false){
                        /* inverse trigger */
                        DetectorManager.getInstance().triggerDetector(i);
                    }
                }

            }
        });

        return serialPort.openPort();
    }

    public boolean disconnect() {
        if(serialPort != null) {
            serialPort.closePort();
            serialPort = null;

            debug("disconnect()");

            return true;
        }else{
            return false;
        }
    }

}
