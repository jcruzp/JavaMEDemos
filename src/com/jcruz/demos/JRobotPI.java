/* 
 * 2014 Jose Cruz <joseacruzp@gmail.com>.
 */
package com.jcruz.demos;

import com.jcruz.demos.log.LoggingHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.midlet.MIDlet;

/**
 *
 * JRobotPI Java Firmware v2.0.2
 * State Machine to control Robot.
 * Use I2C bus for all sensors data and send data to Arduino Due with a I2C controller
 * to read all wii remote buttons and activate DC Motors.
 * @author jcruz
 */
public class JRobotPI extends MIDlet {
    
    private LoggingHandler loggerHandler = LoggingHandler.getInstance();
    
    private Processor processor = null;

    /**
     * Start App Midlet
     */
    @Override
    public void startApp() {
        try {
            loggerHandler.start();
            Logger.getGlobal().setLevel(Level.INFO);
            
            Logger.getGlobal().log(Level.INFO, "************************************");
            Logger.getGlobal().log(Level.INFO, "*     Starting JRobotPI v2.3.0...  *");
            Logger.getGlobal().log(Level.INFO, "************************************");

            //TODO Convert to Thread
            processor = new Processor();
            processor.Start();
            
        } catch (IOException ex) {
            Logger.getGlobal().log(Level.WARNING, ex.getMessage());
        } finally {
           destroyApp(true);
        }
    }


    public void destroyApp(boolean unconditional){
        if (processor != null) {
            processor.Stop();
        }
        Logger.getGlobal().log(Level.INFO, "End Program...");
        notifyDestroyed ();
    }
}
