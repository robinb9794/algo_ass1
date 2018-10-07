package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewModel {
	private String guiTitle;
	private int guiWidth, guiHeight;
	private int screenWidth, screenHeight;
	
	private File[] selectedFiles;
	private List<LoadedImage> loadedImages;
	
	public ViewModel(String guiTitle, int guiWidth, int guiHeight) {
		this.guiTitle = guiTitle;
		this.guiWidth = guiWidth;
		this.guiHeight = guiHeight;
		this.loadedImages = new ArrayList<LoadedImage>();
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
	
	public int getScreenWidth() {
		return this.screenWidth;
	}
	
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	public int getScreenHeight() {
		return this.screenHeight;
	}
	
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
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
}
