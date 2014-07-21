package com.sugree.twitter.tasks;

import com.substanceofcode.tasks.AbstractTask;
import com.sugree.twitter.TwitterController;
import com.sugree.twitter.TwitterConsumer;

public class OAuthTask extends AbstractTask {
	private TwitterController controller;
	private TwitterConsumer oauth;
	private int objectType;
	private String pin;

	public final static int REQUEST_TOKEN = 0;
	public final static int ACCESS_TOKEN = 1;

	public OAuthTask(TwitterController controller, TwitterConsumer oauth, int objectType, String pin) {
		this.controller = controller;
		this.oauth = oauth;
		this.objectType = objectType;
		this.pin = pin;
	}

	public void doTask() {
            
        }
}
