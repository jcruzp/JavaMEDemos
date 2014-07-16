/*
 * 2014 Jose Cruz <joseacruzp@gmail.com>.
 */
package com.jcruz.demos.log;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

/**
 * Redirects log messages to remote console.
 *
 */
public class LoggingHandler extends StreamHandler {

    private static volatile LoggingHandler instance;

    private LoggingHandler() {
    }

    /**
     * Define our Log Handler
     *
     * @return
     */
    public static synchronized LoggingHandler getInstance() {
        if (instance == null) {
            instance = new LoggingHandler();
            instance.setLevel(Level.ALL);
        }
        return instance;
    }

    /**
     * Start handler. Output is directed to <code>System.out</code> and handler
     * is attached to Global Logger; After that, listening thread is started.
     *
     * @see #run
     */
    public void start() {
        setOutputStream(System.out);
        Logger.getGlobal().addHandler(this);
    }

}
