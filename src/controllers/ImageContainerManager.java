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
import interfaces.bar.DisplayedImage;
import interfaces.bar.ImageBar;
import models.LoadedImage;
import models.ViewModel;

public class ImageContainerManager extends GUIManager{
	private ViewModel viewModel;
	private SuperFactory guiElementFactory;
	
	private ImageBar imageBar;
	
	public ImageContainerManager(ViewModel viewModel) {
		this.viewModel = viewModel;
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");
		this.imageBar = (ImageBar) this.guiElementFactory.getGUIElement("ImageContainer");
	}
	
	public void startWork() {
		this.imageBar.init();
		handleFiles(viewModel.getSelectedFiles());
		gui.addElement(BorderLayout.SOUTH, imageBar);
		gui.reorder();
	}
	
	private void handleFiles(File[] files) {
		for(int i = 0; i < files.length; i++) {
			File file = files[i];
			if(file.isDirectory()) {
				File[] filesFromDirectory = file.listFiles();
				handleFiles(filesFromDirectory);
			}else {
				handleImage(file, i);
			}
		}	
	}
	
	private File[] resizeAndFill(File[] files, File[] filesFromDirectory) {
		int oldNumberOfFiles = files.length - 1;
		int newNumberOfFiles = oldNumberOfFiles + filesFromDirectory.length;
		File[] combinedFiles = new File[newNumberOfFiles];
		for(int i = 0; i < newNumberOfFiles; i++) {
			combinedFiles[i] = i < oldNumberOfFiles ? files[i] : filesFromDirectory[i];
		}
		return combinedFiles;
	}	
	
	private void handleImage(File selectedFile, int i) {
		try {
			Image originalImage = ImageIO.read(selectedFile);
			int[] grabbedPixels = getGrabbedPixels(originalImage, viewModel.getScreenWidth(), viewModel.getScreenHeight());
			loadOriginalImage(originalImage, grabbedPixels, i);
			createIconAndAddToBar(originalImage);
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	
	
	private void loadOriginalImage(Image originalImage, int[] grabbedPixels, int i) {
		LoadedImage loadedImage = new LoadedImage(originalImage, grabbedPixels, i);
		viewModel.addLoadedImage(loadedImage);
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
	
	private void createIconAndAddToBar(Image originalImage) {
		ImageIcon icon = new ImageIcon(originalImage.getScaledInstance(100,  100, Image.SCALE_SMOOTH));
		GUIElement imageField = getInitializedImageField(icon);				
		imageBar.addImageField(imageField);
	}
	
	private GUIElement getInitializedImageField(ImageIcon icon) {
		DisplayedImage imageField = (DisplayedImage) guiElementFactory.getGUIElement("ImageField");
		imageField.init();
		imageField.addImageIcon(icon);
		return imageField;
	}
}
