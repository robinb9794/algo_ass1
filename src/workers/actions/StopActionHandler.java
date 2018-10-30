package workers.actions;

import java.util.Map.Entry;

import interfaces.buttons.ButtonField;
import workers.SuperUserInteractionHandler;

public class StopActionHandler extends SuperUserInteractionHandler{
	public static void handle() {
		isFading = false;
		resetScreenListener();
		enableAndDisableButtons();
	}
	
	private static void enableAndDisableButtons() {
		for(Entry<ButtonField, String> entry : viewModel.getButtons().entrySet()) {
			ButtonField button = entry.getKey();
			String value = entry.getValue();
			switch(value) {
			case "Selection":
				button.enableButton(true);
				break;
			case "Fade":
				button.enableButton(true);
				break;
			case "Lens":
				button.enableButton(true);
				break;
			default:
				button.enableButton(false);
			}
		}
	}
}
