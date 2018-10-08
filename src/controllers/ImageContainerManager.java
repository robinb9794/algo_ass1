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
import interfaces.ImageBar;
import models.LoadedImage;
import models.ViewModel;

public class ImageContainerManager {
	private ViewModel viewModel;
	private SuperFactory guiElementFactory;
	
	private ImageBar imageBar;
	
	public ImageContainerManager(ViewModel viewModel) {
		this.viewModel = viewModel;
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");
		this.imageBar = (ImageBar) this.guiElementFactory.getGUIElement("ImageContainer");
	}
	
	public void handleProvidedFiles() {
		try {
			File[] selectedFiles = viewModel.getSelectedFiles();
			for(int i = 0; i < selectedFiles.length; i++) {
				File selectedFile = selectedFiles[i];
				if(selectedFile.isDirectory()) {
					File[] filesFromDirectory = selectedFile.listFiles();
					selectedFiles = resizeAndFill(selectedFiles, filesFromDirectory);
					selectedFile = selectedFiles[i];
				}
				BufferedImage originalImage = ImageIO.read(selectedFile);
				handleOriginalImage(originalImage);				
				ImageIcon icon = new ImageIcon(originalImage.getScaledInstance(100,  100, Image.SCALE_SMOOTH));
			}
		}catch(Exception ex) {
			System.out.println(ex.toString());
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
	
	private void handleOriginalImage(BufferedImage originalImage) {
		LoadedImage loadedImage = new LoadedImage(originalImage);
		loadedImage.grabPixels(viewModel.getScreenWidth(), viewModel.getScreenHeight());
		viewModel.addLoadedImage(loadedImage);
	}
	
	public void grabPixels(int width, int height) {
		try {
			this.originalImage = getScaledImage(width, height);
			this.pixels = new int[width * height];
			PixelGrabber pixelGrabber = new PixelGrabber(this.originalImage, 0, 0, width, height, this.pixels, 0, width);
			pixelGrabber.grabPixels();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	private BufferedImage getScaledImage(int width, int height) {
		Image tmp = originalImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
		BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.SCALE_SMOOTH);
		Graphics2D g2d = scaledImage.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return scaledImage;
	}
}
