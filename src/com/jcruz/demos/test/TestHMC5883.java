/* 
 * 2014 Jose Cruz <joseacruzp@gmail.com>.
 */
package com.jcruz.demos.test;

import com.jcruz.demos.i2c.HMC5883L;
import com.jcruz.demos.i2c.I2CUtils;
import com.jcruz.demos.i2c.driver.HMC5883LDevice;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author jcruz
 */
public class TestHMC5883 extends MIDlet {

    @Override
    public void startApp() {
        try {
            HMC5883LDevice hmc = new HMC5883LDevice();
            hmc.setScale(1.3F);
            hmc.setMeasurementMode(HMC5883LDevice.Measurement.Continuous);
            while (true) {
                System.out.println(hmc.calculateHeading());
                I2CUtils.I2Cdelay(2000);
                HMC5883LDevice.MagnetometerRaw values = hmc.readRawAxis();
                System.out.print("X:" + values.XAxis);
                System.out.print(" Y:" + values.YAxis);
                System.out.println(" Z:" + values.ZAxis);
            }

        } catch (IOException ex) {
            Logger.getGlobal().log(Level.WARNING,ex.getMessage());
        }

    }

    @Override
    public void destroyApp(boolean unconditional) {
    }
}
