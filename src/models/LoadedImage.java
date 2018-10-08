package models;

import java.awt.Image;

public class LoadedImage {
	private Image originalImage;
	private int[] grabbedPixels;
	private int index;
	
	public LoadedImage(Image originalImage, int[] grabbedPixels, int index) {
		this.originalImage = originalImage;
		this.grabbedPixels = grabbedPixels;
		this.index = index;
	}	
}
