package com.sugree.twitter.views;

import java.util.Vector;
import java.util.Enumeration;


import com.substanceofcode.twitter.model.Status;
import com.substanceofcode.utils.Log;

import com.sugree.twitter.TwitterController;
import com.sugree.utils.DateUtil;

// use fully qualified classname, make sure it use native GUI, and not Polish GUI
public class InsertScreen  {
	private TwitterController controller;
	private Command insertCommand;
	private Command cancelCommand;

	private Vector custom;
	private Vector words;

	public InsertScreen(TwitterController controller) {
		//super("Insert", Choice.IMPLICIT);
		this.controller = controller;

		custom = new Vector();
		words = new Vector();

		
	}

	public void removeAll() {
		
	}

	public void setCustom(String[] words) {
		custom.removeAllElements();
		for(int i=0; i<words.length; i++) {
			custom.addElement(words[i]);
		}
	}

	public void setWords(Vector words) {
		if (words != null) {
			this.words = words;
		}
		updateInsert();
	}

	private void updateInsert() {
		
	}

	
}
