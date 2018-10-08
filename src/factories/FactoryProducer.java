package factories;

import controllers.GUIManager;

public class FactoryProducer extends GUIManager{
	public static SuperFactory getFactory(String type) {
		switch(type) {
		case "GUIElement":
			return new GUIElementFactory(viewModel);
		case "SingleAction":
			return new SingleActionFactory();
		}
		return null;
	}
}
