package factories;

import interfaces.GUIElement;

public abstract class SuperFactory {
	public abstract GUIElement getGUIElement(String type);
}
