package factories;

import interfaces.GUIElement;
import interfaces.SingleAction;

public abstract class SuperFactory {
	public abstract GUIElement getGUIElement(String type);
	public abstract SingleAction getSingleAction(String type);
}
