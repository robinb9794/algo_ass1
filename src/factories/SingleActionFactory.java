package factories;

import interfaces.GUIElement;
import controllers.actions.ChooseImagesActionHandler;
import controllers.actions.CreateHistogramActionHandler;
import gui.elements.bar.ClickableImage;
import interfaces.SingleAction;

public class SingleActionFactory extends SuperFactory{
	@Override
	public GUIElement getGUIElement(String type) {
		return null;
	}

	@Override
	public SingleAction getSingleAction(String type) {
		switch(type) {
		case "ChooseImages":
			return new ChooseImagesActionHandler();
		case "CreateHistogram":
			return new CreateHistogramActionHandler();
		}
		return null;
	}
}
