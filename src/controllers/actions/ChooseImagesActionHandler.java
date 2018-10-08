package controllers.actions;

import java.awt.event.ActionEvent;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.SingleAction;
import interfaces.GUIElement;

public class ChooseImagesActionHandler implements SingleAction {
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
