package nl.pit.control.car.control;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class DCCPlusPlusController implements ControllerInterface{

    SerialPort serialPort = null;

    private void debug(String message){
        System.out.println("DCC++ " + message);
    }

    private boolean sendMessage(String message){
        byte[] messageBytes = message.getBytes();

        if(serialPort != null && serialPort.isOpen()){
            serialPort.writeBytes(messageBytes, messageBytes.length);
            debug(message);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
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
                int numRead = serialPort.readBytes(newData, newData.length);
                debug("rx: " + new String(newData));
            }
        });

        debug("connect()");

        return serialPort.openPort();
    }

    @Override
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

    @Override
    public boolean turnTrackPowerOn() {
        return sendMessage("<1>");
    }

    @Override
    public boolean turnTrackPowerOff() {
        return sendMessage("<0>");
    }

    /*
     *    sets the throttle for a given register/cab combination
     *
     *    REGISTER: an internal register number, from 1 through MAX_MAIN_REGISTERS (inclusive), to store the DCC packet used to control this throttle setting
     *    CAB:  the short (1-127) or long (128-10293) address of the engine decoder
     *    SPEED: throttle speed from 0-126, or -1 for emergency stop (resets SPEED to 0)
     *    DIRECTION: 1=forward, 0=reverse.  Setting direction when speed=0 or speed=-1 only effects directionality of cab lighting for a stopped train
     *
     *    returns: <T REGISTER SPEED DIRECTION>
     *
     */
    @Override
    public boolean setVehicleThrottleStep(Integer address, Integer step) {
        return sendMessage("<t 1 " + address.toString() + " " + step.toString() + " 1>");
    }

    /*
     *    turns on and off engine decoder functions F0-F28 (F0 is sometimes called FL)
     *    NOTE: setting requests transmitted directly to mobile engine decoder --- current state of engine functions is not stored by this program
     *
     *    CAB:  the short (1-127) or long (128-10293) address of the engine decoder
     *
     *    To set functions F0-F4 on (=1) or off (=0):
     *
     *    BYTE1:  128 + F1*1 + F2*2 + F3*4 + F4*8 + F0*16
     *    BYTE2:  omitted
     *
     *    To set functions F5-F8 on (=1) or off (=0):
     *
     *    BYTE1:  176 + F5*1 + F6*2 + F7*4 + F8*8
     *    BYTE2:  omitted
     *
     *    To set functions F9-F12 on (=1) or off (=0):
     *
     *    BYTE1:  160 + F9*1 +F10*2 + F11*4 + F12*8
     *    BYTE2:  omitted
     *
     *    To set functions F13-F20 on (=1) or off (=0):
     *
     *    BYTE1: 222
     *    BYTE2: F13*1 + F14*2 + F15*4 + F16*8 + F17*16 + F18*32 + F19*64 + F20*128
     *
     *    To set functions F21-F28 on (=1) of off (=0):
     *
     *    BYTE1: 223
     *    BYTE2: F21*1 + F22*2 + F23*4 + F24*8 + F25*16 + F26*32 + F27*64 + F28*128
     *
     *    returns: NONE
     *
     */
    @Override
    public boolean setVehicleFunction(Integer address, Boolean functionArray[]) {
        return sendMessage("<f " + address.toString() + functionArray.toString());
    }

    /*
     *    turns an accessory (stationary) decoder on or off
     *
     *    ADDRESS:  the primary address of the decoder (0-511)
     *    SUBADDRESS: the subaddress of the decoder (0-3)
     *    ACTIVATE: 1=on (set), 0=off (clear)
     *
     *    Note that many decoders and controllers combine the ADDRESS and SUBADDRESS into a single number, N,
     *    from  1 through a max of 2044, where
     *
     *    N = (ADDRESS - 1) * 4 + SUBADDRESS + 1, for all ADDRESS>0
     *
     *    OR
     *
     *    ADDRESS = INT((N - 1) / 4) + 1
     *    SUBADDRESS = (N - 1) % 4
     *
     *    returns: NONE
     */
    @Override
    public boolean setAccessoryFunction(Integer address, Integer subAddress, Boolean active) {
        Integer n = (address - 1) * + subAddress + 1;
        Integer a = 0;

        if(active){
            a = 1;
        }

        return sendMessage("<a " + address.toString() + " " + a.toString() + " >");
    }

    /*
     *   <T ID THROW>:                sets turnout ID to either the "thrown" or "unthrown" position
     *
     *   ID: the numeric ID (0-32767) of the turnout to control
     *   THROW: 0 (unthrown) or 1 (thrown)
     *
     *   returns: <H ID THROW> or <X> if turnout ID does not exist
     *
     *   *** SEE ACCESSORIES.CPP FOR COMPLETE INFO ON THE DIFFERENT VARIATIONS OF THE "T" COMMAND
     *   USED TO CREATE/EDIT/REMOVE/SHOW TURNOUT DEFINITIONS
     */
    @Override
    public boolean setTurnoutFunction(Integer address, Boolean active) {
        Integer thrown = 0;

        if(active == true) {
            thrown = 1;
        }

        return sendMessage("<T" + address.toString() + " " + thrown.toString() + " >");
    }
}
