package factories;

import interfaces.GUIElement;
import interfaces.actions.SingleAction;
import controllers.actions.ChooseImagesActionHandler;
import controllers.actions.CreateHistogramActionHandler;

public class SingleActionFactory extends SuperFactory{
	@Override
	public GUIElement getGUIElement(String type) {
		return null;
	}

	@Override
	public SingleAction getMenuAction(String type) {
		switch(type) {
		case "ChooseImages":
			return new ChooseImagesActionHandler();
		case "CreateHistogram":
			return new CreateHistogramActionHandler();
		}
		return null;
	}
}
