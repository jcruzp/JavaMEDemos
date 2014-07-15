/* 
 * 2014 Jose Cruz <joseacruzp@gmail.com>.
 */
package com.jcruz.demos.test;

import com.jcruz.demos.uart.driver.GPSEM406Device;
import java.io.IOException;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author jcruz
 */
public class TestGPSEM406 extends MIDlet {

    private GPSEM406Device gps;
    @Override
    public void startApp() {
        try {
            gps=new GPSEM406Device();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void destroyApp(boolean unconditional) {
        gps.close();
    }
}
