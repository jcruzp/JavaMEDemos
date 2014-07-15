/* 
 * 2014 Jose Cruz <joseacruzp@gmail.com>.
 */
package com.jcruz.demos.i2c.driver;

import com.jcruz.demos.i2c.I2CRpi;
import com.jcruz.demos.i2c.I2CUtils;
import com.jcruz.demos.i2c.SC16IS750;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interface to SC16IS750 I2C/SPI to UART I use I2C interface to connect Emic-2
 * to I2C 0x90 address and free a UART port to use with GPS.
 *
 * @author Jose Cruz <joseacruzp@gmail.com>
 */
public class SC16IS750Device extends I2CRpi {

    private static final int SC16IS750_write = 0x48;

    /**
     *
     * @throws IOException
     */
    public SC16IS750Device() throws IOException {
        super(SC16IS750_write);
        configUARTregs();
    }

    private void configUARTregs() {
        //Config UART
        Logger.getGlobal().log(Level.FINE, "Config UART of SC16IS750");

        //Line Control Register: Enable Writing DLH & DLL
        //& set no Parity, 1 stop bit, and 8 bit word length
        SC16IS750.LCR.write(device, (byte) 0b10000011);

        //Division registers DLL & DLH
        // Write '96' to get 9600 baud rate
        //Assumes you have the version with the ~14MHz crystal
        // (16x9600 = 153600 = 14.7456Mhz/96)
        SC16IS750.DLL.write(device, (byte) 96);
        SC16IS750.DLH.write(device, (byte) 00);

        //Line Control Register: Disnable Writing DLH & DLL
        //Same setup 
        SC16IS750.LCR.write(device, (byte) 0b00000011);

        //Modem Control Register
        //Normal Operating Mode
        SC16IS750.MCR.write(device, (byte) 0b00000000);

        //FIFO Control Register: Enable the FIFO and no other features
        SC16IS750.FCR.write(device, (byte) 0b00000111);

        I2CUtils.I2Cdelay(2000);

        Logger.getGlobal().log(Level.FINE, "FIFO Control Register: " + SC16IS750.FCR.read(device));
        Logger.getGlobal().log(Level.FINE, "Line Control Register: " + SC16IS750.LCR.read(device));
        Logger.getGlobal().log(Level.FINE, "Modem Control Register: " + SC16IS750.MCR.read(device));

    }

    /**
     *
     * @param cad String to send to Emic-2
     */
    public void write(String cad) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(cad.length());
        buffer.put(cad.getBytes());
        buffer.clear();
        try {
            device.write(SC16IS750.XHR.cmd, 1, buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int bytesToRead(){
        return SC16IS750.RXLVL.read(device);
    }
    
    public int read(){
        return SC16IS750.XHR.read(device);
    }
            
    

}
