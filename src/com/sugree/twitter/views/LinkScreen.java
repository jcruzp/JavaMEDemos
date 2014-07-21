package com.sugree.twitter.views;

import java.util.Vector;
import java.util.Enumeration;


import com.substanceofcode.utils.Log;

import com.sugree.twitter.TwitterController;

// use fully qualified classname, make sure it use native GUI, and not Polish GUI
public class LinkScreen{
	private TwitterController controller;

	private static final String[] linkLabels = {
		"Official Home",
		"Project",
		"Stable JAD",
		"Stable JAR",
		"Beta JAD",
		"Beta JAR",
		"Mobile Twitter",
	};

	private final String[] linkValues = {
		"http://jibjib.org/",
		"http://code.google.com/p/jibjib/",
//#ifdef polish.name:defined
//#=	"http://jibjib.googlecode.com/svn/d/jibjib-${ polish.name }.jad",
//#=	"http://jibjib.googlecode.com/svn/d/jibjib-${ polish.name }.jar",
//#=	"http://jibjib.googlecode.com/svn/d/jibjib-beta-${ polish.vendor }_${ polish.name }.jad",
//#=	"http://jibjib.googlecode.com/svn/d/jibjib-beta-${ polish.vendor }_${ polish.name }.jar",
//#else
		"http://jibjib.googlecode.com/svn/d/jibjib.jad",
		"http://jibjib.googlecode.com/svn/d/jibjib.jar",
		"http://jibjib.googlecode.com/svn/d/jibjib-beta.jad",
		"http://jibjib.googlecode.com/svn/d/jibjib-beta.jar",
//#endif
		"http://m.twitter.com/",
	};

	private Command goCommand;
	private Command cancelCommand;

	public LinkScreen(TwitterController controller) {
		
	}

	
}
