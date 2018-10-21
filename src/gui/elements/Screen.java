package gui.elements;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		g.drawRect(10, 10, getWidth(), getHeight());
		if(viewModel.getMemoryImageSource() != null) {
			viewModel.getMemoryImageSource().newPixels();
			this.displayedImage = createImage(viewModel.getMemoryImageSource());
			g.drawImage(displayedImage, 10, 10, getWidth(), getHeight(), null);
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

	@Override
	public void drawSelectionRectangle() {
		Graphics2D g = (Graphics2D) this.getGraphics();
		int startX = (int) viewModel.getSelectionStartPoint().getX();
		int startY = (int) viewModel.getSelectionStartPoint().getY();
		int endX = (int) viewModel.getSelectionEndPoint().getX();
		int endY = (int) viewModel.getSelectionEndPoint().getY();
		
		int rectX = Math.min(startX, endX);
		int rectY = Math.min(startY, endY);
		int width = Math.abs(startX - endX);
		int height = Math.abs(startY - endY);
		
		g.setColor(new Color(255, 0, 0, 0.50f));
		g.setStroke(new BasicStroke(3));
		g.fillRect(rectX, rectY, width, height);

	}	
}
