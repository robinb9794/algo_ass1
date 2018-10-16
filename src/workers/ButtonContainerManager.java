package workers;

import java.awt.BorderLayout;

import interfaces.buttons.ButtonField;
import interfaces.buttons.ButtonSelection;

public class ButtonContainerManager extends GUIManager{		
	private static ButtonSelection buttonSelection;
	
	public static void init() {
		buttonSelection = (ButtonSelection) guiElementFactory.getGUIElement("ButtonContainer");
		buttonSelection.init();
	}
	
	public static void startWork() {
		generateButtons();
		addSelectionToGUI();
	}
	
	private static void generateButtons() {
		ButtonField button = null;
		String type = "";
		for(int i = 0; i < 12; i++) {
			switch(i) {
			case 0:
				type = "Selection";
				break;
			case 1:
				type = "Reset";
				break;
			case 2:
				type = "Fade";
				break;
			case 3:
				type = "Lens";
				break;
			case 4:
				type = "Translate";
				break;
			case 5:
				type = "Rotate";
				break;
			case 6:
				type = "Scale";
				break;
			case 7:
				type = "Shear";
				break;
			case 8:
				type = "Lines";
				break;
			case 9:
				type = "Circles";
				break;
			case 10:
				type = "Start";
				break;
			case 11:
				type = "Stop";
				break;
			}
			button = (ButtonField) buttonFactory.getButtonField(type);
			button.init();
			buttonSelection.addButton(button);
		}
	}
	
	private static void addSelectionToGUI() {
		gui.addElement(BorderLayout.WEST, buttonSelection);
		//gui.reorder();
	}
}
