/* 
 * 2014 Jose Cruz <joseacruzp@gmail.com>.
 */
package com.jcruz.demos.i2c;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.dio.DeviceManager;
import jdk.dio.i2cbus.I2CDevice;
import jdk.dio.i2cbus.I2CDeviceConfig;


/**
 * Define the device interface to Arduino Due to use with wii remote control and
 * DC motors controls
 *
 * @author Jose Cuz
 */
public class I2CDue {

    private final int i2cdueAddress = 0x04;  //I2C address from Arduino Due
    private I2CDeviceConfig config;

    /**
     * Save device pointer to Arduino Due I2C address
     */
    public static I2CDevice arduino = null;

    /**
     * Define the connection to Arduino Due
     * 
     */
    //TODO Change to Singleton Object Pattern 
    public I2CDue() {
        if (arduino == null) {
            config = new I2CDeviceConfig(1,
                    i2cdueAddress,
                    I2CDeviceConfig.ADDR_SIZE_7,
                    100000);

            try {
                arduino = (I2CDevice) DeviceManager.open(I2CDevice.class, config);
            } catch (IOException ex) {
                Logger.getGlobal().log(Level.WARNING,ex.getMessage());
            }

        }
    }

    /**
     * Free connection to Arduino Due
     *
     */
    public void close() {
        try {
            arduino.close();
        } catch (IOException ex) {
            Logger.getGlobal().log(Level.WARNING,ex.getMessage());
        }
    }
}
