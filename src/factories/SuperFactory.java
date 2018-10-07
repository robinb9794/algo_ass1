package factories;

import interfaces.GUIElement;
import interfaces.ElementAction;

public abstract class SuperFactory {
	public abstract GUIElement getGUIElement(String type);
	public abstract ElementAction getElementAction(String type);
}
