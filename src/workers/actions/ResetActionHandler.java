package workers.actions;

import models.LoadedImage;
import models.math.Matrix;
import workers.PixelCoordinator;
import workers.SuperUserInteractionHandler;

public class ResetActionHandler extends SuperUserInteractionHandler{
	public static void handle() {
		enableImageBar();
		resetSelectionPoints();
		resetDisplayedImage();
		resetMatrix();
		enableOrDisableButtonsMorphButtons(false);
		disableAndEnableButtons();
		resetScreenListener();
	}
	
	private static void resetSelectionPoints() {
		viewModel.resetSelectionPoints();
	}
	
	private static void resetDisplayedImage() {
		LoadedImage displayedImage = viewModel.getSelectedImages().getLast();
		PixelCoordinator.setSourcePixels(displayedImage.getGrabbedPixels());
		PixelCoordinator.setTargetPixels(displayedImage.getGrabbedPixels());
		gui.reloadScreen();
	}
	
	private static void resetMatrix() {
		Matrix matrix = new Matrix();
		viewModel.setMorphMatrix(matrix);;
	}
	
	private static void disableAndEnableButtons() {
		disableSingleButton("Reset");
		disableSingleButton("Save");
		enableSingleButton("Selection");
		enableSingleButton("Fade");
		enableSingleButton("Lens");
	}
}
