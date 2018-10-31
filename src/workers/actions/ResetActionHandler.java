package workers.actions;

import workers.PixelCoordinator;
import workers.SuperUserInteractionHandler;

public class ResetActionHandler extends SuperUserInteractionHandler{
	public static void handle() {
		resetSelectionPoints();
		resetDisplayedImage();
		enableOrDisableButtonsAfterSelection(false);
		disableButtons();
	}
	
	private static void resetSelectionPoints() {
		viewModel.resetSelectionPoints();
	}
	
	private static void resetDisplayedImage() {
		PixelCoordinator.setTargetPixels(viewModel.getSelectedImages().getLast().getGrabbedPixels());
		gui.reloadScreen();
	}
	
	private static void disableButtons() {
		disableSingleButton("Reset");
		disableSingleButton("Save");
	}
}
