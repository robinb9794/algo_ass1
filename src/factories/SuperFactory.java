package factories;

import interfaces.GUIElement;
import interfaces.actions.SingleAction;

public abstract class SuperFactory {
	public abstract GUIElement getGUIElement(String type);
	public abstract SingleAction getMenuAction(String type);
}
