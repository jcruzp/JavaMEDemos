package com.sugree.twitter.views;

import java.io.IOException;
import java.util.Vector;
import java.util.Enumeration;


import com.substanceofcode.utils.TimeUtil;
import com.substanceofcode.utils.Log;
import com.substanceofcode.twitter.model.Status;
import com.sugree.twitter.TwitterController;

// use fully qualified classname, make sure it use native GUI, and not Polish GUI
public class OAuthScreen{
	private TwitterController controller;
	
	private Command okCommand;
	private Command retryCommand;
	private Command goCommand;
	private Command cancelCommand;

	private String url;

}
