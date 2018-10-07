package controllers.actions.menu;

import java.awt.event.ActionEvent;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.ElementAction;
import interfaces.GUIElement;

public class ChooseImagesActionHandler implements ElementAction {
	private SuperFactory guiElementFactory;
	
	private GUIElement imageChooser;
	
	public ChooseImagesActionHandler() {
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.imageChooser = guiElementFactory.getGUIElement("ImageChooser");
		imageChooser.init();
	}
}
