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
import com.jcruz.demos.gpio.driver.HCSR501Device;
import javax.microedition.midlet.MIDlet;
import jdk.dio.gpio.PinEvent;
import jdk.dio.gpio.PinListener;

/**
 * Midlet to Test all sensors connected to Raspberry Pi
 *
 * @author jcruz
 */
public class TestSensors extends MIDlet {

    DFR0076Device flame;
    private static final int FLAME_DETECTOR_PIN = 22;
    
    HCSR501Device pir;
    private static final int MOTION_DETECTOR_PIN = 24;
    
    @Override
    public void startApp() {
        //Initialize Flame Sensor
        flame = new DFR0076Device(FLAME_DETECTOR_PIN);
        flame.setListener(new FlameSensor());
        //Initialize PIR sensor
        pir = new HCSR501Device(MOTION_DETECTOR_PIN);
        pir.setListener(new PirSensor());
    }

    @Override
    public void destroyApp(boolean unconditional) {
        flame.close();
        pir.close();
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
