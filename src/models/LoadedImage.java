package models;

import java.awt.Image;

import javax.swing.ImageIcon;

public class LoadedImage {
	private Image originalImage;
	private ImageIcon icon;
	private int[] grabbedPixels;
	private int index;
	
	public LoadedImage(Image originalImage, ImageIcon icon, int[] grabbedPixels, int index) {
		this.originalImage = originalImage;
		this.icon = icon;
		this.grabbedPixels = grabbedPixels;
		this.index = index;
	}	
	
	public ImageIcon getIcon() {
		return this.icon;
	}
}
