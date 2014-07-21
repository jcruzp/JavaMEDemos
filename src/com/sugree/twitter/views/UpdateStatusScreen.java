package com.sugree.twitter.views;



import com.sugree.twitter.TwitterController;

// use fully qualified classname, make sure it use native GUI, and not Polish GUI
public class UpdateStatusScreen  {
	private TwitterController controller;

	private Command sendCommand;
	private Command cancelCommand;
	private Command insertCommand;
//#ifdef polish.api.mmapi
	private Command snapshotCommand;
	private Command quickSnapshotCommand;
//#endif
//#ifdef polish.api.locationapi
	private Command gpsCommand;
//#endif
	private Command reverseGeocoderCommand;
	private Command cellIdCommand;
	private Command statCommand;
	private Command squeezeCommand;
	
	public UpdateStatusScreen(TwitterController controller, String text) {

	}

	public void insert(String text) {
		
	}

	
		
}
