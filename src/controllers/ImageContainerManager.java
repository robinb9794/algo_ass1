package controllers;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;
import interfaces.LoadingScreen;
import interfaces.bar.DisplayedImage;
import interfaces.bar.ImageBar;
import models.LoadedImage;
import models.ViewModel;

public class ImageContainerManager extends GUIManager implements Runnable{
	private ViewModel viewModel;
	
	private SuperFactory guiElementFactory;
	
	private ImageBar imageBar;
	private GUIElement scrollBar;
	private LoadingScreen loadingScreen;
	
	public ImageContainerManager(ViewModel viewModel) {
		this.viewModel = viewModel;
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");
		this.imageBar = (ImageBar) this.guiElementFactory.getGUIElement("ImageContainer");
		this.loadingScreen = (LoadingScreen) this.guiElementFactory.getGUIElement("LoadingWindow");
	}
	
	public void startWork() {
		initImageBar();
		initLoadingScreen();
		handleFiles(viewModel.getSelectedFiles());
		makeImageBarScrollable();
		updateGUIAndViewModel();		
	}
	
	private void initImageBar() {
		this.imageBar.init();		
	}	
	
	private void initLoadingScreen() {
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			this.loadingScreen.init();
			while(!viewModel.imagesAreAddedToContainer()) {
				Thread.sleep(50);
			}
			this.loadingScreen.closeScreen();
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
	}
	
	private void handleFiles(File[] files) {
		for(int i = 0; i < files.length; i++) {
			File file = files[i];
			if(file.isDirectory()) {
				File[] filesFromDirectory = file.listFiles();
				handleFiles(filesFromDirectory);
			}else if(fileIsImage(file)){
				handleImage(file, i);
			}
		}	
	}
	
	private boolean fileIsImage(File file) {
		return file.getPath().endsWith("png") || file.getPath().endsWith("jpg");
	}
	
	private void handleImage(File selectedFile, int i) {
		try {
			Image originalImage = ImageIO.read(selectedFile);
			int[] grabbedPixels = getGrabbedPixels(originalImage, viewModel.getScreenWidth(), viewModel.getScreenHeight());
			ImageIcon icon = new ImageIcon(originalImage.getScaledInstance(100,  100, Image.SCALE_SMOOTH));
			LoadedImage loadedImage = getLoadedImage(originalImage, icon, grabbedPixels, i);
			createIconAndAddToBar(originalImage, loadedImage);
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	
	
	private LoadedImage getLoadedImage(Image originalImage, ImageIcon icon, int[] grabbedPixels, int i) {
		LoadedImage loadedImage = new LoadedImage(originalImage, icon, grabbedPixels, i);
		viewModel.addLoadedImage(loadedImage);
		return loadedImage;
	}
	
	private int[] getGrabbedPixels(Image originalImage, int width, int height) {
		int[] pixels;
		try {
			originalImage = getScaledImage(originalImage, width, height);
			pixels = new int[width * height];
			PixelGrabber pixelGrabber = new PixelGrabber(originalImage, 0, 0, width, height, pixels, 0, width);
			pixelGrabber.grabPixels();
			return pixels;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	private BufferedImage getScaledImage(Image originalImage, int width, int height) {
		Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = scaledImage.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return scaledImage;
	}
	
	private void createIconAndAddToBar(Image originalImage, LoadedImage loadedImage) {
		GUIElement imageField = getInitializedImageField(loadedImage);				
		imageBar.addImageField(imageField);
	}
	
	private GUIElement getInitializedImageField(LoadedImage loadedImage) {
		DisplayedImage imageField = (DisplayedImage) guiElementFactory.getGUIElement("ImageField");
		imageField.init();
		imageField.addImageIconFromLoadedImage(loadedImage);
		return imageField;
	}
	
	private void makeImageBarScrollable() {
		this.viewModel.setImageBar(imageBar);
		this.scrollBar = this.guiElementFactory.getGUIElement("ImageScroller");
		this.scrollBar.init();
	}
	
	private void updateGUIAndViewModel() {
		this.gui.addElement(BorderLayout.SOUTH, scrollBar);
		this.gui.reorder();
		this.viewModel.setImagesAreAddedToContainer(true);
	}
}
