/**
 * optimized thumbnail code is written by Kamanashis Roy.
 *
 * http://miniim.blogspot.com/2008/05/image-thumbnail-in-optimized-way-for.html
 */
package com.sugree.twitter.views;

import java.io.IOException;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
//#ifdef polish.api.mmapi

//#endif
import javax.microedition.media.MediaException;

import com.substanceofcode.twitter.Settings;
import com.substanceofcode.utils.Log;
import com.substanceofcode.infrastructure.Device;

import com.sugree.twitter.TwitterController;

// use fully qualified classname, make sure it use native GUI, and not Polish GUI
public class SnapshotScreen  {
//#ifdef polish.api.mmapi
	private TwitterController controller;

	private Player player;
	private String status;
	private byte[] snapshotRaw;

	private boolean visible;

	private Command snapCommand;
	private Command okCommand;
	private Command cancelCommand;
	private Command retryCommand;
	private Command visibleCommand;
//#endif

	public SnapshotScreen(TwitterController controller, String status) throws Exception {
		
//#endif
	}

	public void playerUpdate(Player player, String event, Object eventData) {
		Log.info("playerUpdate "+event);
	}

//#ifdef polish.api.mmapi
	private void init() throws Exception {
			}

//#ifdef polish.midp1
	public void deleteAll() {
		
	}
//#endif

	public void start(boolean visibleFlag) {
		
	}

	private void destroy() {
		
	}

	public void quickSnapshot() throws Exception {
		quickSnapshot(false);
	}

	public void quickSnapshot(boolean background) throws Exception {
		
	}

	private void getSnapshot() {
		
	}
//#endif

	

//#if polish.midp2 || polish.midp3

    /**
     * Gets the thumbnail that fit with given screen width, height and padding.
     *
     * @param image The source image
     * @param padding padding to the screen
     * @return scaled image
     */
	

    /**
     * Gets thumbnail with a height and width specified ..
     * @param image
     * @param thumbWidth
     * @param thumbHeight
     * @return scaled image
     */
	
//#else
	

}
