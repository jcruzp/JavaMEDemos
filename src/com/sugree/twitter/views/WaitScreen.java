package com.sugree.twitter.views;

import java.io.IOException;
import java.util.Vector;
import java.util.Enumeration;


import com.substanceofcode.tasks.AbstractTask;
import com.substanceofcode.utils.Log;
import com.sugree.twitter.TwitterController;
import com.sugree.utils.Loggable;

// use fully qualified classname, make sure it use native GUI, and not Polish GUI
public class WaitScreen{
	private TwitterController controller;
	private AbstractTask task;
	private int cancelScreen;

	private Command cancelCommand;

	private Thread thread;

	public WaitScreen(TwitterController controller, AbstractTask task, int cancelScreen) {
		
	}

	
}
