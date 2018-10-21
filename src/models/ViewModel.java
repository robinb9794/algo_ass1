package models;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import interfaces.ImageDisplay;
import interfaces.bar.ImageBar;
import interfaces.buttons.ButtonField;

public class ViewModel {	
	private String guiTitle;
	private int guiWidth, guiHeight;
	private int screenWidth, screenHeight;	
	private boolean userHasClosedGUI;

	private ImageDisplay screen;
	private int[] sourcePixels, targetPixels;
	private MemoryImageSource memoryImageSource;
	
	private File[] selectedFiles;
	private List<LoadedImage> loadedImages;
	
	private boolean guiIsLoaded;
	private ImageBar imageBar;
	
	private LoadedImage selectedImage;
	private LinkedList<LoadedImage> selectedImages;
	private Mode currentMode;
	
	private Map<ButtonField, String> buttons;
	
	private Point selectionStartPoint, selectionEndPoint;
	private Rectangle lastDrawnSelectionRectangle;
	
	public ViewModel(String guiTitle, int guiWidth, int guiHeight) {
		this.guiTitle = guiTitle;
		this.guiWidth = guiWidth;
		this.guiHeight = guiHeight;
		this.userHasClosedGUI = false;
		this.loadedImages = new ArrayList<LoadedImage>();
		this.guiIsLoaded = false;
		this.selectedImages = new LinkedList<LoadedImage>();
		this.buttons = new HashMap<ButtonField, String>();
		this.selectionStartPoint = new Point();
		this.selectionEndPoint = new Point();
		this.lastDrawnSelectionRectangle = new Rectangle();
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
	
	public boolean userHasClosedGUI() {
		return this.userHasClosedGUI;
	}
	
	public void setUserHasClosedGUI(boolean userHasClosedGUI) {
		this.userHasClosedGUI = userHasClosedGUI;
	}
	
	public ImageDisplay getScreen() {
		return this.screen;
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
	
	public void addSelectedImage(LoadedImage loadedImage) {
		this.selectedImages.add(loadedImage);
	}
	
	public LoadedImage getSelectedImageByIndex(int index) {
		return this.selectedImages.get(index);
	}
	
	public LinkedList<LoadedImage> getSelectedImages(){
		return this.selectedImages;
	}
	
	public void removeSelectedImage(LoadedImage selectedImage) {
		this.selectedImages.remove(selectedImage);
	}
	
	public void updateDisplayedImage() {
		LoadedImage nextDisplayedImage = null;
		if(this.selectedImages.size() > 0) {
			nextDisplayedImage = this.selectedImages.getLast();
			setTargetPixels(nextDisplayedImage.getGrabbedPixels());
		}else
			resetTargetPixels();
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
	
	public void resetTargetPixels() {
		for(int i = 0; i < this.screenWidth; i++) {
			for(int j = 0; j < this.screenHeight; j++) {
				int index = j * this.screenWidth + i;
				this.targetPixels[index] = 0;
			}
		}
	}
	
	public void addButton(ButtonField button, String type) {
		buttons.put(button, type);
	}
	
	public Map<ButtonField, String> getButtons(){
		return this.buttons;
	}
	
	public Point getSelectionStartPoint() {
		return this.selectionStartPoint;
	}
	
	public void setSelectionStartPoint(int x, int y) {
		this.selectionStartPoint.setLocation(x, y);
	}
	
	public Point getSelectionEndPoint() {
		return this.selectionEndPoint;
	}
	
	public void setSelectionEndPoint(int x, int y) {
		this.selectionEndPoint.setLocation(x, y);
	}
	
	public Rectangle getLastDrawnSelectionRectangle() {
		return this.lastDrawnSelectionRectangle;
	}
	
	public void setLastDrawnSelectionRectangle(Rectangle lastDrawnSelectionRectangle) {
		this.lastDrawnSelectionRectangle = lastDrawnSelectionRectangle;
	}
}
