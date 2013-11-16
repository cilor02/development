package com.milo.gui.misc;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.milo.draw.DrawingCanvas;
import com.milo.draw.WordBlob;

public class RandomWordDisplay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Frame f = new Frame("test");
		f.setLayout(null);
		f.addWindowListener(new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

		});
		DrawingCanvas drgCnvs = new DrawingCanvas();

		WordBlob word = new WordBlob("plusieurs", 400, 400);
		Button button = new Button ("next");
		//button.setLocation(10, 100);
		
		drgCnvs.add(word);
		drgCnvs.setSize(800, 400);
		f.add(drgCnvs);
		f.setLayout(new FlowLayout() );
		f.add(button);
		//button.setVisible(true);

		f.setSize(800, 800);
		f.setVisible(true);

	}

}
