package com.milo.questionpaper;

import java.awt.Frame;
import java.awt.List;
import java.awt.Window;

public class PhraseBuilder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Frame appFrame = new Frame("Sentence Builder");
		//Window appWindow = new Window (appFrame);
		appFrame.setSize(200,200);
		appFrame.setLayout(null);
		appFrame.setLocation(100,200);
		
		List nounList = new List();
		List adjList = new List();
		List verbList = new List();
		nounList.setSize(60, 200);
		nounList.setLocation(0,50);
		nounList.add("chien");
		nounList.add("chat");
		nounList.setMultipleMode(true);
		verbList.setSize(60, 200);
		verbList.setLocation(66,50);
		verbList.add("faire");
		adjList.setSize(60, 200);
		adjList.setLocation(132,50);
		adjList.add("grande");
		
		appFrame.add(adjList);
		appFrame.add(nounList);
		appFrame.add(verbList);
        appFrame.setTitle("Sentence Builder");
		appFrame.setVisible(true);
        //appWindow.setVisible(true);

	}

}
