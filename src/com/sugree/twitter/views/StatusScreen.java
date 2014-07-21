package com.sugree.twitter.views;

import java.util.Date;


//#if polish.midp2 || polish.midp3
import com.substanceofcode.utils.StringUtil;
//#if project.HyperlinkList
//#endif
//#endif
import com.substanceofcode.utils.TimeUtil;
import com.substanceofcode.utils.Log;
import com.substanceofcode.twitter.model.Status;
import com.sugree.twitter.TwitterController;
import java.util.List;

//#if polish.midp2 || polish.midp3
public class StatusScreen  {
//#else
//public class StatusScreen extends Form implements CommandListener {
//#endif
	private TwitterController controller;

	private Status status;
	private int timeOffset;

//#if !(polish.midp2 || polish.midp3) || project.HyperlinkList
	//private StringItem textField;
//#endif
	private Command replyCommand;
	private Command directCommand;
	private Command retweetCommand;
	private Command favoriteCommand;
	private Command backCommand;
//#if polish.midp2 || polish.midp3
	private Command linkCommand;
//#if project.HyperlinkList
	private Command linkOpenCommand;
	private Command linkBackCommand;
	private List linkList;
//#endif
//#endif

	public StatusScreen(TwitterController controller) {
		//super("");
		this.controller = controller;

	}

	public void setTimeOffset(String offset) {
		try {
			int i = Integer.parseInt(offset);
			timeOffset = (i/100*3600+i%100*60)*1000;
		} catch (NumberFormatException e) {
			Log.error(e.toString());
		}
	}

	public void setStatus(Status status) {
		
	}

//#if polish.midp2 || polish.midp3
	
//#endif

	
//#ifdef polish.usePolishGui
	
//#endif

//#if (polish.midp2 || polish.midp3) && project.HyperlinkList
	
//#endif
}
