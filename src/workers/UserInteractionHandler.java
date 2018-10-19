package workers;

import interfaces.View;
import models.LoadedImage;
import models.ViewModel;

public class UserInteractionHandler {
	private static ViewModel viewModel;
	private static View gui;
	
	private static boolean handlingAction;
	
	public static void init(ViewModel viewModel, View gui) {
		UserInteractionHandler.viewModel = viewModel;
		UserInteractionHandler.gui = gui;
		handlingAction = false;
	}
	
	public static void handleInteraction(){
		switch(viewModel.getCurrentMode()) {
		case FADE:
			handleFadeAction();
			break;
		case STOP:
			handleStopAction();
		}
	}
	
	private static void handleFadeAction() {
		if(userHasSelectedImagesToFade() && !handlingAction) {
			new Thread() {
				@Override
				public void run() {
					try {
						handlingAction = true;
						int counter = 0, currentNumberOfSelectedImages;
						LoadedImage firstImageToFade, secondImageToFade;
						while(shouldFade()) {
							currentNumberOfSelectedImages = viewModel.getSelectedImages().size();
							firstImageToFade = viewModel.getSelectedImageByIndex(counter % currentNumberOfSelectedImages);
							secondImageToFade = viewModel.getSelectedImageByIndex((counter + 1) % currentNumberOfSelectedImages);
							fade(firstImageToFade, secondImageToFade);
							Thread.sleep(50);
							counter++;
						}
						handlingAction = false;
					}catch(Exception ex) {
						ex.printStackTrace();
					}					
				}
			}.start();
		}
	}
	
	private static boolean userHasSelectedImagesToFade() {
		return viewModel.getSelectedImages().size() >= 2;
	}
		
	private static boolean shouldFade() {
		return handlingAction && !viewModel.userHasClosedGUI() && userHasSelectedImagesToFade();
	}
	
	private static void fade(LoadedImage firstImageToFade, LoadedImage secondImageToFade) {
		try {
			for (int p = 0; p <= 100; p += 2) {
				fade(firstImageToFade, secondImageToFade, p);
				gui.reloadScreen();
				Thread.sleep(50);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
	}
	
	private static void fade(LoadedImage firstImageToFade, LoadedImage secondImageToFade, int p) {
		int[] fadedPixels = new int[viewModel.getScreenWidth() * viewModel.getScreenHeight()];
		for (int i = 0; i < viewModel.getScreenWidth() * viewModel.getScreenHeight(); i++) {
			fadedPixels[i] = colorShuffle(firstImageToFade.getGrabbedPixels()[i], secondImageToFade.getGrabbedPixels()[i], p);
		}
		viewModel.setTargetPixels(fadedPixels);
	}

	private static int colorShuffle(int pix1, int pix2, int p) {
		int red = singleShuffle((pix1 >> 16) & 255, (pix2 >> 16) & 255, p);
		int green = singleShuffle((pix1 >> 8) & 255, (pix2 >> 8) & 255, p);
		int blue = singleShuffle((pix1) & 255, (pix2) & 255, p);
		return (255 << 24) | (red << 16) | (green << 8) | blue;
	}
	
	private static int singleShuffle(int part1, int part2, int p) {
		return part1 + (part2 - part1) * p / 100;
	}
	
	private static void handleStopAction() {
		handlingAction = false;
	}
}
