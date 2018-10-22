package workers.actions;

import java.util.Map.Entry;

import interfaces.buttons.ButtonField;
import workers.SuperUserInteractionHandler;

public class StopActionHandler extends SuperUserInteractionHandler{
	public static void handle() {
		isFading = false;
		enableAndDisableButtons();
	}
	
	private static void enableAndDisableButtons() {
		for(Entry<ButtonField, String> entry : viewModel.getButtons().entrySet()) {
			if(entry.getValue().equals("Reset") || entry.getValue().equals("Save") || entry.getValue().equals("Stop")) {
				entry.getKey().enableButton(false);
			}else {
				entry.getKey().enableButton(true);
			}
		}
	}
}
