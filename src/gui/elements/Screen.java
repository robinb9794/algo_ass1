package gui.elements;

import java.awt.Graphics;

import javax.swing.JComponent;

import interfaces.ImageDisplay;

public class Screen extends JComponent implements ImageDisplay{
	@Override
	public void paintComponent(Graphics g) {
		
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getScreenWidth() {
		return this.getWidth();
	}
	
	@Override
	public int getScreenHeight() {
		return this.getHeight();
	}	
}
