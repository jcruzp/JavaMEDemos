/* 
 * 2014 Jose Cruz <joseacruzp@gmail.com>.
 */
package com.jcruz.demos.test;

import com.jcruz.demos.i2c.driver.EMICI2CDevice;
import com.jcruz.demos.log.LoggingHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author jcruz
 */
public class TestEmicI2C extends MIDlet {

    private LoggingHandler loggerHandler = LoggingHandler.getInstance();
    
    public final String[] emic2Msgs = {
        "S Emic 2 Ok.", //0
        "S Inicializing devices.", //1
        "S HCSR04 Ok.", //2
        "S BMP180 Ok.", //3
        "S HTU21D Ok.", //4
        "S Wii Remote Ok.", //5
        "S DC Motors Ok.", //6
        "S Servo Ok.", //7
        "S VCNL4000 Ok.", //8
        "S Xively Ok.", //9    
        "S PIR and your listener Ok.", //10
        "S Task to read devices created.",//11
        "S Close devices comunication.", //12
        "S Menu activated.", //13
        "S Menu deactivated.", //14
        "S Prepare to move.", //15
        "S Stop move.", //16
        "S Prepare to detect objects.", //17
        "S Stop searching objects.", //18
        "S Scanning.", //19
        "S Object detected at ", //20
        "S No Object detected.", //21
        "S PIR Activated", //22
        "S PIR Deactivated", //23
        "S HMC5883L Ok." //24   
    };

    @Override
    public void startApp() {
        loggerHandler.start();
        Logger.getGlobal().setLevel(Level.ALL);

        Logger.getGlobal().log(Level.INFO, "************************************");
        try {
            EMICI2CDevice sc = new EMICI2CDevice(emic2Msgs);
            
        sc.writeCommand("W200");
        sc.writeCommand("L0");
        sc.writeCommand("N0");
            sc.Msg(1);
            sc.Msg(2);
            sc.Msg(3);
            sc.Msg(4);
            
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void destroyApp(boolean unconditional) {
    }
}
