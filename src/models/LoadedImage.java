package models;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

public class LoadedImage {
	private Image originalImage;
	private int[] pixels;
	
	public LoadedImage(BufferedImage originalImage) {
		this.originalImage = originalImage;
	}
	
	
}
