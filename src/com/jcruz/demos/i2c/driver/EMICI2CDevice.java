/* 
 * 2014 Jose Cruz <joseacruzp@gmail.com>.
 */
package com.jcruz.demos.i2c.driver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interface to EMIC-2 sound synthetizer
 *
 * @author jcruz
 */
public class EMICI2CDevice {

    private SC16IS750Device sc;
    private String[] emic2Msgs=null;

    /**
     * Define a UART device config to interface to Emic-2
     *
     * @param emic2Msgs
     * @throws IOException
     */
    public EMICI2CDevice(String[] emic2Msgs) throws IOException {
        //I2C Emic interface using SC16IS750
        sc = new SC16IS750Device();
        this.emic2Msgs=emic2Msgs;
    }
    
    /**
     *
     * @param msgnum
     */
    public void Msg(int msgnum) {
        write(emic2Msgs[msgnum]);
        Logger.getGlobal().log(Level.FINE,emic2Msgs[msgnum]);
    }
    
    /**
     *
     * @param num
     * @return
     */
    public String getMsg(int num) {
        return emic2Msgs[num];
    }

    /**
     *
     * @param cad String to send to Emic-2
     */
    public void write(String cad) {
        cad = "S " + cad.concat("\r\n");
        sc.write(cad);
        // Wait for response from Emic-2. It respond to all commands with :
        waitResponse(2);
    }
    
    /**
     *
     * @param cad
     */
    public void writeCommand(String cad) {
        cad = cad.concat("\r\n");
        sc.write(cad);
        // Wait for response from Emic-2. It respond to all commands with :
        waitResponse(5);
    }

    /**
     *
     * Wait for response from UART. It respond to all commands with car
     *
     * @param nrobytes
     */
    public void waitResponse(int nrobytes) {
        int work = 0;
        //wait two chars :\n
        while (work!=nrobytes) {
            work = sc.bytesToRead();
        }
        for(;0<work;work--)
        //while (0 < work) {
           //read it from input buffer  
           sc.read();
        //   work--;
        //}
    }
    
    /**
     * Free Uart resource
     *
     */
    public void close() {
        sc.close();
    }

}
