package gui.elements.bar;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import interfaces.bar.DisplayedImage;
import models.LoadedImage;
import models.ViewModel;

public class ClickableImage extends JLabel implements DisplayedImage, MouseListener{	
	private ViewModel viewModel;
	
	private LoadedImage loadedImage;
	
	public ClickableImage(ViewModel viewModel) {
		this.viewModel = viewModel;
	}
	
	@Override
	public void init() {
		setSize(new Dimension(100, 100));
		addMouseListener(this);
	}

	@Override
	public void addImageIconFromLoadedImage(LoadedImage loadedImage) {
		this.loadedImage = loadedImage;
		setIcon(loadedImage.getIcon());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("jo");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
