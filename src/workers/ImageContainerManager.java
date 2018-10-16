package workers;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import interfaces.GUIElement;
import interfaces.LoadingScreen;
import interfaces.bar.DisplayedImageContainer;
import interfaces.bar.ImageBar;
import models.LoadedImage;

public class ImageContainerManager extends GUIManager {		
	private static ImageBar imageBar;
	private static GUIElement scrollBar;
	
	public static void init() {
		imageBar = (ImageBar) guiElementFactory.getGUIElement("ImageContainer");
	}
	
	public static void startWork() {
		initImageBar();
		handleFiles(viewModel.getSelectedFiles());
		makeImageBarScrollable();
		updateGUIAndViewModel();		
	}
	
	private static void initImageBar() {
		imageBar.init();		
	}
	
	private static void handleFiles(File[] files) {
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
	
	private static boolean fileIsImage(File file) {
		return file.getPath().endsWith("png") || file.getPath().endsWith("jpg");
	}
	
	private static void handleImage(File selectedFile, int i) {
		try {
			Image originalImage = ImageIO.read(selectedFile);
			int[] grabbedPixels = getGrabbedPixels(originalImage, viewModel.getScreenWidth(), viewModel.getScreenHeight());
			ImageIcon icon = new ImageIcon(originalImage.getScaledInstance(95, 95, Image.SCALE_SMOOTH));
			LoadedImage loadedImage = getLoadedImage(originalImage, icon, grabbedPixels, i);
			createIconAndAddToBar(originalImage, loadedImage);
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	
	
	private static LoadedImage getLoadedImage(Image originalImage, ImageIcon icon, int[] grabbedPixels, int i) {
		LoadedImage loadedImage = new LoadedImage(originalImage, icon, grabbedPixels, i);
		viewModel.addLoadedImage(loadedImage);
		return loadedImage;
	}
	
	private static int[] getGrabbedPixels(Image originalImage, int width, int height) {
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
	
	private static BufferedImage getScaledImage(Image originalImage, int width, int height) {
		Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = scaledImage.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return scaledImage;
	}
	
	private static void createIconAndAddToBar(Image originalImage, LoadedImage loadedImage) {
		GUIElement imageField = getInitializedImageField(loadedImage);				
		imageBar.addImageField(imageField);
	}
	
	private static GUIElement getInitializedImageField(LoadedImage loadedImage) {
		DisplayedImageContainer imageField = (DisplayedImageContainer) guiElementFactory.getGUIElement("ImageField");
		imageField.init();
		imageField.passImageIconFromLoadedImageToClickableImage(loadedImage);
		return imageField;
	}
	
	private static void makeImageBarScrollable() {
		viewModel.setImageBar(imageBar);
		scrollBar = guiElementFactory.getGUIElement("ImageScroller");
		scrollBar.init();
	}
	
	private static void updateGUIAndViewModel() {
		gui.addElement(BorderLayout.SOUTH, scrollBar);
		viewModel.setGUIIsLoaded(true);
	}
}
