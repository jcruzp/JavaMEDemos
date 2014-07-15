/* 
 * 2014 Jose Cruz <joseacruzp@gmail.com>.
 */
package com.jcruz.demos.i2c;

import jdk.dio.i2cbus.I2CDevice;

/**
 *
 * @author jcruz
 */
public enum SC16IS750 {
   
    /**
     *
     */
    XHR(0x00),

    /**
     *
     */
    FCR(0x02),

    /**
     *
     */
    LCR(0x03),

    /**
     *
     */
    MCR(0x04),
    
    /**
     *
     */
    LSR(0x05),
    
    /**
     *
     */
    MSR(0x06),
    
    /**
     *
     */
    TXLVL(0x08),
    
    /**
     *
     */
    RXLVL(0x09),
    
    /**
     *
     */
    DLL(0x00),
    
    /**
     *
     */
    DLH(0x01);
    
    /**
     *
     */
    public int cmd;

    private SC16IS750(int cmd) {
        // SC16IS740 expects a R/W  bit first (only for spi interface), 
        //followed by the 4 bit register address of the byte.
        // So shift the bits left by three bits:
        this.cmd = cmd << 3;
    }
    /**
     *
     * @param device
     * @return
     */
    public int read(I2CDevice device) {
        return I2CUtils.read(device, this.cmd );
    }

    /**
     *
     * @param device
     * @param value
     */
    public void write(I2CDevice device, byte value) {
        I2CUtils.write(device, (byte) this.cmd, value);
    }
}
