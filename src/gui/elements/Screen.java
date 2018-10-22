package gui.elements;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import interfaces.ImageDisplay;
import models.ViewModel;

public class Screen extends JComponent implements ImageDisplay{
	private ViewModel viewModel;
	
	private Image displayedImage;
		
	public Screen(ViewModel viewModel) {
		this.viewModel = viewModel;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(10, 10, viewModel.getScreenWidth(), viewModel.getScreenHeight());
		if(viewModel.getMemoryImageSource() != null) {
			viewModel.getMemoryImageSource().newPixels();
			this.displayedImage = createImage(viewModel.getMemoryImageSource());
			g.drawImage(displayedImage, 10, 10, viewModel.getScreenWidth(), viewModel.getScreenHeight(), null);
		}
	}
	
	@Override
	public void init() {
	}

	@Override
	public int getScreenWidth() {
		return this.getWidth();
	}
	
	@Override
	public int getScreenHeight() {
		return this.getHeight();
	}

	@Override
	public void addCustomMouseListener(MouseListener mouseListener) {
		addMouseListener(mouseListener);
	}
	
	@Override
	public void addCustomMouseMotionListener(MouseMotionListener mouseMotionListener) {
		addMouseMotionListener(mouseMotionListener);
	}

	@Override
	public void setCustomCursor(Cursor cursor) {
		setCursor(cursor);
	}
}
