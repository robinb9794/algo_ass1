package gui.elements.bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import interfaces.View;
import interfaces.bar.DisplayedImage;
import interfaces.bar.DisplayedImageContainer;
import models.LoadedImage;
import models.ViewModel;
import workers.PixelCoordinator;

public class ClickableImage extends JLabel implements DisplayedImage{	
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
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		
				if(!selected) {
					viewModel.addSelectedImage(loadedImage);
					PixelCoordinator.setSourcePixels(loadedImage.getGrabbedPixels());
					PixelCoordinator.setTargetPixels(loadedImage.getGrabbedPixels());
					displayedImageContainer.setContainerBackground(Color.GREEN);
				}else {
					viewModel.removeSelectedImage(loadedImage);
					viewModel.updateDisplayedImage();
					displayedImageContainer.setContainerBackground(Color.WHITE);
				}
				checkActions();
				selected = !selected;
				gui.reloadScreen();
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
		});
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
	public void checkActions() {
		if(viewModel.getSelectedImages().size() < 2)
			viewModel.getScreen().resetMouseActions();
	}
}
