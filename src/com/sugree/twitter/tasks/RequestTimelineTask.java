package com.sugree.twitter.tasks;

import java.util.Vector;

import com.substanceofcode.tasks.AbstractTask;
import com.substanceofcode.twitter.TwitterApi;
import com.sugree.twitter.TwitterController;
import com.sugree.twitter.TwitterException;

public class RequestTimelineTask extends AbstractTask {
	private TwitterController controller;
	private TwitterApi api;
	private int feedType;
	private boolean nonBlock;

	public final static int FEED_FRIENDS = 0;
	public final static int FEED_HOME = 1;
	public final static int FEED_REPLIES = 2;
	public final static int FEED_USER = 3;
	public final static int FEED_PUBLIC = 4;
	public final static int FEED_DIRECT = 5;
	public final static int FEED_FAVORITES = 6;

	public RequestTimelineTask(TwitterController controller, TwitterApi api, int feedType, boolean nonBlock) {
		this.controller = controller;
		this.api = api;
		this.feedType = feedType;
		this.nonBlock = nonBlock;
	}

	public void doTask() {
		Vector timeline = new Vector();

		
	}
}
