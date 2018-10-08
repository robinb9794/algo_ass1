package controllers;

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
		handleProvidedFiles();
		gui.packAndShow();
	}
	
	private void handleProvidedFiles() {
		try {
			File[] selectedFiles = viewModel.getSelectedFiles();
			for(int i = 0; i < selectedFiles.length; i++) {
				File selectedFile = selectedFiles[i];
				if(selectedFile.isDirectory()) {
					File[] filesFromDirectory = selectedFile.listFiles();
					selectedFiles = resizeAndFill(selectedFiles, filesFromDirectory);
					selectedFile = selectedFiles[i];
				}
				Image originalImage = ImageIO.read(selectedFile);
				handleOriginalImage(originalImage);				
				ImageIcon icon = new ImageIcon(originalImage.getScaledInstance(100,  100, Image.SCALE_SMOOTH));
				GUIElement imageField = getInitializedImageField(icon);
				imageBar.addImageField(imageField);
			}
		}catch(Exception ex) {
			System.out.println(ex);
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
	
	private void handleOriginalImage(Image originalImage) {
		LoadedImage loadedImage = new LoadedImage(originalImage);
		grabPixels(originalImage, viewModel.getScreenWidth(), viewModel.getScreenHeight());
		viewModel.addLoadedImage(loadedImage);
	}
	
	private int[] grabPixels(Image originalImage, int width, int height) {
		int[] pixels;
		try {
			originalImage = getScaledImage(originalImage, width, height);
			pixels = new int[width * height];
			PixelGrabber pixelGrabber = new PixelGrabber(originalImage, 0, 0, width, height, pixels, 0, width);
			pixelGrabber.grabPixels();
			return pixels;
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return null;
	}
	
	private Image getScaledImage(Image originalImage, int width, int height) {
		Image tmp = originalImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
		BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.SCALE_SMOOTH);
		Graphics2D g2d = scaledImage.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return scaledImage;
	}
	
	private GUIElement getInitializedImageField(ImageIcon icon) {
		GUIElement imageField = guiElementFactory.getGUIElement("ImageField");
		imageField.init();
		return imageField;
	}
}
