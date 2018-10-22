package workers;

import interfaces.View;
import models.ViewModel;

public class PixelCoordinator {
	private static ViewModel viewModel;
	private static View gui;
	
	public static void init(ViewModel viewModel, View gui) {
		PixelCoordinator.viewModel = viewModel;
		PixelCoordinator.gui = gui;
	}
	
	public static void setSourcePixels(int[] pixels) {
		for(int i = 0; i < viewModel.getScreenWidth(); i++) {
			for(int j = 0; j < viewModel.getScreenHeight(); j++) {
				int index = j * viewModel.getScreenWidth() + i;
				viewModel.getSourcePixels()[index] = pixels[index];
			}
		}
	}
	
	public static void setTargetPixels(int[] pixels) {
		for(int i = 0; i < viewModel.getScreenWidth(); i++) {
			for(int j = 0; j < viewModel.getScreenHeight(); j++) {
				int index = j * viewModel.getScreenWidth() + i;
				viewModel.getTargetPixels()[index] = pixels[index];
			}
		}
	}
	
	public static void resetSourcePixels() {
		for(int i = 0; i < viewModel.getScreenWidth(); i++) {
			for(int j = 0; j < viewModel.getScreenHeight(); j++) {
				int index = j * viewModel.getScreenWidth() + i;
				viewModel.getSourcePixels()[index] = 0;
			}
		}
	}
	
	public static void resetTargetPixels() {
		for(int i = 0; i < viewModel.getScreenWidth(); i++) {
			for(int j = 0; j < viewModel.getScreenHeight(); j++) {
				int index = j * viewModel.getScreenWidth() + i;
				viewModel.getTargetPixels()[index] = 0;
			}
		}
	}
	
	public static void setSelectionPixels() {
		int selectionStartX = getStartX();
		int selectionEndX= getEndX();
		int selectionStartY = getStartY();
		int selectionEndY = getEndY();
		for(int i = selectionStartX; i <= selectionEndX; i++) {
			for(int j = selectionStartY; j <= selectionEndY; j++) {
				if(pixelIsOnSelectionLine(i, j, selectionStartX, selectionEndX, selectionStartY, selectionEndY)) {
					int index = j * viewModel.getScreenWidth() + i;
					int sourcePixel = viewModel.getSourcePixels()[index];
					viewModel.getTargetPixels()[index] = getSelectionColor(index, sourcePixel);	
				}							
			}
		}
	}
	
	private static int getStartX() {
		return Math.min((int) viewModel.getSelectionStartPoint().getX(), (int) viewModel.getSelectionEndPoint().getX());
	}
	
	private static int getEndX() {
		return Math.max((int) viewModel.getSelectionStartPoint().getX(), (int) viewModel.getSelectionEndPoint().getX());
	}
	
	private static int getStartY() {
		return Math.min((int) viewModel.getSelectionStartPoint().getY(), (int) viewModel.getSelectionEndPoint().getY());
	}
	
	private static int getEndY() {
		return Math.max((int) viewModel.getSelectionStartPoint().getY(), (int) viewModel.getSelectionEndPoint().getY());
	}
	
	private static boolean pixelIsOnSelectionLine(int i, int j, int startX, int endX, int startY, int endY) {
		return i == startX || i == endX || j == startY || j == endY ;
	}
	
	private static int getSelectionColor(int index, int sourcePixel) {
		return 255 << 24 | 255 << 16 | 0 << 8 | 78;
	}
	
	public static int colorShuffle(int pix1, int pix2, int p) {
		int red = singleShuffle((pix1 >> 16) & 255, (pix2 >> 16) & 255, p);
		int green = singleShuffle((pix1 >> 8) & 255, (pix2 >> 8) & 255, p);
		int blue = singleShuffle((pix1) & 255, (pix2) & 255, p);
		return (255 << 24) | (red << 16) | (green << 8) | blue;
	}
	
	public static int singleShuffle(int part1, int part2, int p) {
		return part1 + (part2 - part1) * p / 100;
	}			
}
