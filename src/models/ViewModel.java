package models;

import java.awt.image.MemoryImageSource;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gui.elements.Screen;
import gui.elements.bar.ClickableImage;
import interfaces.ImageDisplay;
import interfaces.bar.DisplayedImage;
import interfaces.bar.ImageBar;

public class ViewModel {	
	private String guiTitle;
	private int guiWidth, guiHeight;
	private int screenWidth, screenHeight;	

	private ImageDisplay screen;
	private int[] sourcePixels, targetPixels;
	private MemoryImageSource memoryImageSource;
	
	private File[] selectedFiles;
	private List<LoadedImage> loadedImages;
	
	private boolean guiIsLoaded;
	private ImageBar imageBar;
	
	private LoadedImage selectedImage;
	private Mode currentMode;
	
	public ViewModel(String guiTitle, int guiWidth, int guiHeight) {
		this.guiTitle = guiTitle;
		this.guiWidth = guiWidth;
		this.guiHeight = guiHeight;
		this.loadedImages = new ArrayList<LoadedImage>();
		this.guiIsLoaded = false;
	}
	
	public String getGUITitle() {
		return this.guiTitle;
	}
	
	public int getGUIWidth() {
		return this.guiWidth;
	}
	
	public int getGUIHeight() {
		return this.guiHeight;
	}
	
	public void setScreen(ImageDisplay screen) {
		this.screen = screen;
		this.screenWidth = screen.getScreenWidth() - 25;
		this.screenHeight = screen.getScreenHeight() - 150;
	}
	
	public int getScreenWidth() {
		return this.screenWidth;
	}
	
	public int getScreenHeight() {
		return this.screenHeight;
	}
	
	public void initPixels() {
		this.sourcePixels = new int[this.screenWidth * this.screenHeight];
		this.targetPixels = new int[this.screenWidth * this.screenHeight];
	}
	
	public void initMemoryImageSource() {
		this.memoryImageSource = new MemoryImageSource(this.screenWidth, this.screenHeight, this.targetPixels, 0, this.screenWidth);
		this.memoryImageSource.setAnimated(true);
	}
	
	public MemoryImageSource getMemoryImageSource() {
		return this.memoryImageSource;
	}
	
	public File[] getSelectedFiles() {
		return this.selectedFiles;
	}
	
	public void setSelectedFiles(File[] selectedFiles) {
		this.selectedFiles = selectedFiles;
	}
	
	public List<LoadedImage> getLoadedImages(){
		return this.loadedImages;
	}
	
	public void addLoadedImage(LoadedImage loadedImage) {
		this.loadedImages.add(loadedImage);
	}
	
	public void removeSelectedImage(LoadedImage selectedImage) {
		this.loadedImages.remove(selectedImage.getIndex());
	}
	
	public synchronized boolean guiIsLoaded() {
		return this.guiIsLoaded;
	}
	
	public void setGUIIsLoaded(boolean guiIsLoaded) {
		this.guiIsLoaded = guiIsLoaded;
	}
	
	public void setImageBar(ImageBar imageBar) {
		this.imageBar = imageBar;
	}
	
	public ImageBar getImageBar() {
		return this.imageBar;
	}
	
	public void setCurrentMode(Mode currentMode) {
		this.currentMode = currentMode;
	}
	
	public Mode getCurrentMode() {
		return this.currentMode;
	}
	
	public void setTargetPixels(int[] pixels) {
		for(int i = 0; i < this.screenWidth; i++) {
			for(int j = 0; j < this.screenHeight; j++) {
				int index = j * this.screenWidth + i;
				this.targetPixels[index] = pixels[index];
			}
		}
	}
}
