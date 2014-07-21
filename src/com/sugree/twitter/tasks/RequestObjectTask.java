package com.sugree.twitter.tasks;

import java.util.Vector;

import com.substanceofcode.twitter.model.Status;
import com.substanceofcode.tasks.AbstractTask;
import com.substanceofcode.twitter.TwitterApi;
import com.sugree.twitter.TwitterController;
import com.sugree.twitter.TwitterException;

public class RequestObjectTask extends AbstractTask {
	private TwitterController controller;
	private TwitterApi api;
	private int objectType;
	private String id;

	public final static int FAVORITE_CREATE = 0;
	public final static int FAVORITE_DESTROY = 1;
	public final static int TEST = 2;
	public final static int SCHEDULE_DOWNTIME = 3;

	public RequestObjectTask(TwitterController controller, TwitterApi api, int objectType, String id) {
		this.controller = controller;
		this.api = api;
		this.objectType = objectType;
		this.id = id;
	}

    @Override
    public void doTask() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	
}
