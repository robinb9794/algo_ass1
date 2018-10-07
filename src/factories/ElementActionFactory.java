package factories;

import controllers.actions.menu.ChooseImagesActionHandler;
import controllers.actions.menu.CreateHistogramActionHandler;
import interfaces.GUIElement;
import interfaces.ElementAction;

public class ElementActionFactory extends SuperFactory{
	@Override
	public GUIElement getGUIElement(String type) {
		return null;
	}

	@Override
	public ElementAction getElementAction(String type) {
		switch(type) {
		case "ChooseImages":
			return new ChooseImagesActionHandler();
		case "CreateHistogram":
			return new CreateHistogramActionHandler();
		}
		return null;
	}
}
