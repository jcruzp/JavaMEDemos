package com.sugree.twitter.views;

import java.io.IOException;
import java.util.Vector;
import java.util.Enumeration;

import javax.microedition.rms.RecordStoreException;

import org.json.me.JSONException;

import com.substanceofcode.infrastructure.Device;
import com.substanceofcode.twitter.model.Status;
import com.substanceofcode.twitter.Settings;
import com.substanceofcode.utils.Log;
import com.sugree.twitter.TwitterController;

// use fully qualified classname, make sure it use native GUI, and not Polish GUI
public class SetupScreen {
	private final String[] gatewaysLabel = {
		"Custom",
		"twitter.com",
		"api.twitter.com",
		"Birdnest appspot",
		"Birdnest onedd",
	};
	private final String[] gatewaysValue = {
		null,
		"http://twitter.com/",
		"http://api.twitter.com/1/",
		"http://nest.appspot.com/text/",
		"http://nest.onedd.net/text/",
	};

	private final String[] pictureGatewaysLabel = {
		"Custom",
		"TwitPic",
		"TwitGoo",
		"yfrog",
		"upic.me",
		"Birdnest onedd TwitPic",
		"Birdnest onedd TwitGoo",
		"Birdnest onedd yfrog",
		"Birdnest onedd upic.me",
	};
	private final String[] pictureGatewaysValue = {
		null,
		"http://api.twitpic.com/2/upload.json",
		"http://twitgoo.com/api/upload",
		"http://yfrog.com/api/xauth_upload",
		"http://upic.me/api/upload",
		"http://nest.onedd.net/text/twitpic/2/upload.json",
		"http://nest.onedd.net/text/twitgoo/api/upload",
		"http://nest.onedd.net/text/yfrog/api/xauth_upload",
		"http://nest.onedd.net/text/upicme/api/upload",
	};

	private final String[] startsLabel = {
		"Empty",
		"Tweet",
		"Home",
		"Friends",
		"@Replies",
	};

	private final String[] flagsLabel = {
		"Optimize bandwidth",
		"Alternate authentication",
		"Fullscreen picture",
		"160-characters tweet (non-standard)",
		"Resize thumbnail",
		"Wrap timeline",
		"Enable squeeze",
		"Enable GPS",
		"Enable reverse geocoder",
		"Enable cell ID",
		"Enable refresh",
		"Enable refresh alert",
		"Enable refresh vibrate",
		"Enable refresh counter",
		"Swap minimize and refresh",
		"Enable auto tweet",
		"Enable auto tweet picture",
		"Force no Host",
		"Enable GZIP",
	};

//#ifdef polish.api.mmapi
	private final String[] captureDevicesLabel = {
		"Custom",
		"capture://video",
		"capture://image",
		"capture://devcam0",
		"capture://devcam1",
	};
	private final String[] captureDevicesValue = {
		null,
		"capture://video",
		"capture://image",
		"capture://devcam0",
		"capture://devcam1",
	};

	private String[] snapshotEncodingsLabel;
//#endif

	private TwitterController controller;

	private Command saveCommand;
	private Command cancelCommand;
	private Command togglePasswordCommand;
	private Command oauthRequestCommand;
	private Command oauthAccessCommand;
	private Command oauthDisableCommand;

	public SetupScreen(TwitterController controller) {
		
	}



}
