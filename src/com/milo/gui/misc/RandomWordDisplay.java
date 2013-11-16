package com.milo.gui.misc;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

import com.milo.draw.DrawingCanvas;
import com.milo.draw.WordBlob;

public class RandomWordDisplay implements ActionListener{

	private static int position;
	private static Set<String> words;
	private static Frame frame;
	private static DrawingCanvas drawingCanvas;
	private static Button button;
	
	public RandomWordDisplay()
	{
		frame = new Frame();
		drawingCanvas = new DrawingCanvas();
		button = new Button ("next");
	}
	
	public Set<String> getWords() {
		return words;
	}
	public void setWords(Set<String> words) {
		this.words = words;
	}
	
	public static String next()
	{
		position++;
		return (String)words.toArray()[position%words.size()];
	}
	
	public static void launch ()
	{
		
		frame.setLayout(null);
		frame.addWindowListener(new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

		});

		WordBlob word = new WordBlob((String)words.toArray()[0],400,400);
				
		//button.addActionListener(arg0)
		
		drawingCanvas.add(word);
		drawingCanvas.setSize(800, 600);
		frame.add(drawingCanvas);
		frame.setLayout(new BorderLayout() );
		frame.add(button,BorderLayout.SOUTH);

		frame.setSize(800, 800);
		frame.setVisible(true);
	}
	
	public static void registerButtonListener(ActionListener listener)
	{
		button.addActionListener(listener);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] stringWords = new String[] {"plusieurs", "lors de", "milieu","personne","a cause de","lorsque","chose"};
		Set<String> setWords = new HashSet<String>();
		
		for(String stringWord : stringWords)
		{
			setWords.add(stringWord);
		}
		RandomWordDisplay randomWordDisplay = new RandomWordDisplay();
		randomWordDisplay.setWords(setWords);
		randomWordDisplay.registerButtonListener(randomWordDisplay);
		randomWordDisplay.launch();
		

	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		WordBlob wordBlob = new WordBlob(next(),400,400);
		drawingCanvas.add(wordBlob);
		drawingCanvas.repaint();
//		frame.repaint();
	}

}
