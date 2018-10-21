package workers.actions;

import java.util.Map.Entry;

import interfaces.buttons.ButtonField;
import models.Mode;
import workers.UserInteractionHandler;

public class StopActionHandler extends UserInteractionHandler{
	public static void handle() {
		viewModel.setCurrentMode(Mode.STOP);
		handlingAction = false;
		enableButtons();
	}
	
	private static void enableButtons() {
		for(Entry<ButtonField, String> entry : viewModel.getButtons().entrySet()) {
			entry.getKey().enableButton(true);
		}
	}
}
