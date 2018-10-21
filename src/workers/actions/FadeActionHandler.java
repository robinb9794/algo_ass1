package workers.actions;

import java.util.Map.Entry;

import javax.swing.JOptionPane;

import interfaces.buttons.ButtonField;
import models.LoadedImage;
import models.Mode;
import workers.UserInteractionHandler;

public class FadeActionHandler extends UserInteractionHandler{
	public static void handle() {
		if(userHasSelectedImagesToFade() && !handlingAction) {
			viewModel.setCurrentMode(Mode.FADE);
			disableOtherButtons();
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
		}else {
			showErrorDialog("Please select at least two images.");
		}
	}
	
	private static void disableOtherButtons() {
		for(Entry<ButtonField, String> entry : viewModel.getButtons().entrySet()) {
		    if(!entry.getValue().equals("Stop")) {
		    	entry.getKey().enableButton(false);
		    }
		}
	}
		
	private static boolean shouldFade() {
		return handlingAction && !viewModel.userHasClosedGUI() && userHasSelectedImagesToFade();
	}
	
	private static boolean userHasSelectedImagesToFade() {
		return viewModel.getSelectedImages().size() >= 2;
	}
	
	private static void fade(LoadedImage firstImageToFade, LoadedImage secondImageToFade) {
		try {
			int p = 0;
			while(p <= 100 && handlingAction) {
				fade(firstImageToFade, secondImageToFade, p);
				gui.reloadScreen();
				Thread.sleep(100);
				p += 2;
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
}
