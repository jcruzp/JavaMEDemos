/*
 * The MIT License
 *
 * Copyright 2014 jcruz.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.jcruz.demos.test;

import com.jcruz.demos.gpio.driver.DFR0076Device;
import com.jcruz.demos.gpio.driver.HCSR04Device;
import com.jcruz.demos.gpio.driver.HCSR501Device;
import com.jcruz.demos.i2c.I2CUtils;
import javax.microedition.midlet.MIDlet;
import jdk.dio.gpio.PinEvent;
import jdk.dio.gpio.PinListener;

/**
 * Midlet to Test all sensors connected to Raspberry Pi
 *
 * @author jcruz
 */
public class TestSensors extends MIDlet {

    //Define DFR0076 Device object
    DFR0076Device flame;
    private static final int FLAME_DETECTOR_PIN = 22;
    
    //Define HCSR501 Device object
    HCSR501Device pir;
    private static final int MOTION_DETECTOR_PIN = 24;
        
    //Define HCSR04 Device object
    HCSR04Device hcsr04;
    private static final int TRIGGER_PIN = 23;
    private static final int ECHO_PIN = 17;
    
    //Define execution of read sensors thread
    private volatile boolean shouldRun = true;
    private ReadSensors sensorstask;
    
    @Override
    public void startApp() {
        //Initialize Flame Sensor
        flame = new DFR0076Device(FLAME_DETECTOR_PIN);
        flame.setListener(new FlameSensor());
        //Initialize PIR sensor
        pir = new HCSR501Device(MOTION_DETECTOR_PIN);
        pir.setListener(new PirSensor());
        //Initialize Ultrasound sensor
        hcsr04=new HCSR04Device(TRIGGER_PIN, ECHO_PIN);
        //Start read sensors data thread
        sensorstask=new ReadSensors();
        sensorstask.start();
    }

    @Override
    public void destroyApp(boolean unconditional) {
        shouldRun=false;
        flame.close();
        pir.close();
        hcsr04.close();
    }
    
     /**
     * Thread to handle client request.
     */
    class ReadSensors extends Thread {
        private double distance=0.0;
        
        @Override
        public void run() {
            while (shouldRun){
                distance = hcsr04.pulse();
                if (distance>0) 
                    System.out.println("Object detected at " + distance + " cm.");
                I2CUtils.I2Cdelay(5000);
            }
        }
    }
    
    //Check flame sensor for flame detect
    private static int waitnext = 1;

    class FlameSensor implements PinListener {

        @Override
        public void valueChanged(PinEvent event) {
            if (event.getValue() && --waitnext == 0) {
                System.out.println("WARNING Flame detected!!!");
                waitnext = 10;
            }
        }
    }

    //Check PIR Sensor for motion detect
    class PirSensor implements PinListener {

        @Override
        public void valueChanged(PinEvent event) {
            if (event.getValue()) {
                System.out.println("WARNING Motion detected!!!");
            }

        }
    }

}
