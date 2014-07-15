/* 
 * 2014 Jose Cruz <joseacruzp@gmail.com>.
 */
package com.jcruz.demos.test;

import com.jcruz.demos.i2c.driver.SC16IS750Device;
import com.jcruz.demos.log.LoggingHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author jcruz
 */
public class SC16IS750Test extends MIDlet {

    private LoggingHandler loggerHandler = LoggingHandler.getInstance();

    @Override
    public void startApp() {
        loggerHandler.start();
        Logger.getGlobal().setLevel(Level.ALL);

        Logger.getGlobal().log(Level.INFO, "************************************");
        try {
            SC16IS750Device sc = new SC16IS750Device();
            sc.write("STest It 1\r\n");
            
            sc.write("STest It 2\r\n");
            
            sc.write("STest It 3\r\n");
            
            sc.write("STest It 4\r\n");
            
            sc.write("STest It 5\r\n");
            
            sc.write("STest It 6\r\n");
            
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void destroyApp(boolean unconditional) {
    }
}
