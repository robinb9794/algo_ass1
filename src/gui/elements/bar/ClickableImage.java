package gui.elements.bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import interfaces.View;
import interfaces.bar.DisplayedImage;
import interfaces.bar.DisplayedImageContainer;
import models.LoadedImage;
import models.ViewModel;
import workers.PixelCoordinator;

public class ClickableImage extends JLabel implements DisplayedImage, MouseListener{	
	private View gui;
	private ViewModel viewModel;
	
	private DisplayedImageContainer displayedImageContainer;
	private LoadedImage loadedImage;
	
	private boolean selected;
	
	public ClickableImage(View gui, ViewModel viewModel) {
		this.gui = gui;
		this.viewModel = viewModel;
	}
	
	@Override
	public void init() {
		addMouseListener(this);
	}
	
	@Override
	public void setDisplayedImageContainer(DisplayedImageContainer displayedImageContainer) {
		this.displayedImageContainer = displayedImageContainer;
	}

	@Override
	public void addImageIconFromLoadedImage(LoadedImage loadedImage) {
		this.loadedImage = loadedImage;
		setPreferredSize(new Dimension(100, 100));
		setIcon(loadedImage.getIcon());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {		
		if(!selected) {
			viewModel.addSelectedImage(this.loadedImage);
			PixelCoordinator.setSourcePixels(this.loadedImage.getGrabbedPixels());
			PixelCoordinator.setTargetPixels(this.loadedImage.getGrabbedPixels());
			displayedImageContainer.setContainerBackground(Color.GREEN);
		}else {
			viewModel.removeSelectedImage(this.loadedImage);
			viewModel.updateDisplayedImage();
			displayedImageContainer.setContainerBackground(Color.WHITE);
		}
		selected = !selected;
		this.gui.reloadScreen();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(!selected)
			displayedImageContainer.setContainerBackground(Color.GRAY);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(!selected)
			displayedImageContainer.setContainerBackground(Color.WHITE);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
