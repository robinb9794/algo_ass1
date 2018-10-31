package workers.actions;

import models.math.Matrix;
import workers.PixelCoordinator;
import workers.SuperUserInteractionHandler;

public class ResetActionHandler extends SuperUserInteractionHandler{
	public static void handle() {
		resetSelectionPoints();
		resetDisplayedImage();
		resetMatrix();
		enableOrDisableButtonsAfterSelection(false);
		disableButtons();
	}
	
	private static void resetSelectionPoints() {
		viewModel.resetSelectionPoints();
	}
	
	private static void resetDisplayedImage() {
		PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
		gui.reloadScreen();
	}
	
	private static void resetMatrix() {
		Matrix matrix = new Matrix();
		viewModel.setMorphMatrix(matrix);;
	}
	
	private static void disableButtons() {
		disableSingleButton("Reset");
		disableSingleButton("Save");
	}
}
