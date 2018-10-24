package workers.actions;

import java.util.Map.Entry;

import interfaces.buttons.ButtonField;
import models.LoadedImage;
import workers.PixelCoordinator;
import workers.SuperUserInteractionHandler;

public class FadeActionHandler extends SuperUserInteractionHandler{
	public static void handle() {
		if(userHasSelectedImagesToFade()) {
			resetScreenListener();
			disableOtherButtons();
			new Thread() {
				@Override
				public void run() {
					try {
						isFading = true;
						int counter = viewModel.getSelectedImages().size() - 1, currentNumberOfSelectedImages;
						LoadedImage firstImageToFade, secondImageToFade;
						while(shouldFade()) {
							currentNumberOfSelectedImages = viewModel.getSelectedImages().size();
							firstImageToFade = viewModel.getSelectedImageByIndex(counter % currentNumberOfSelectedImages);
							secondImageToFade = viewModel.getSelectedImageByIndex((counter + 1) % currentNumberOfSelectedImages);
							fade(firstImageToFade, secondImageToFade);
							Thread.sleep(50);
							counter++;
						}
						isFading = false;
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
		    if(entry.getValue().equals("Stop")) {
		    	entry.getKey().enableButton(true);
		    }else {
		    	entry.getKey().enableButton(false);
		    }
		}
	}
		
	private static boolean shouldFade() {
		return isFading && !viewModel.userHasClosedGUI() && userHasSelectedImagesToFade();
	}
	
	private static boolean userHasSelectedImagesToFade() {
		return viewModel.getSelectedImages().size() >= 2;
	}
	
	private static void fade(LoadedImage firstImageToFade, LoadedImage secondImageToFade) {
		try {
			int p = 0;
			while(p <= 100 && shouldFade()) {
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
		PixelCoordinator.resetTargetPixels();
		for(int i = 0; i < viewModel.getScreenWidth(); i++) {
			for(int j = 0; j < viewModel.getScreenHeight(); j++) {
				int index = PixelCoordinator.getPixelIndex(i, j);
				int fadedPixel = PixelCoordinator.colorShuffle(firstImageToFade.getGrabbedPixels()[index], secondImageToFade.getGrabbedPixels()[index], p);
				PixelCoordinator.setSingleTargetPixel(index, fadedPixel);
			}
		}
	}
}
