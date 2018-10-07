package gui.elements.containers;

import java.awt.GridLayout;

import javax.swing.JPanel;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;

public class ButtonContainer extends JPanel implements GUIElement{
	private SuperFactory guiElementFactory;
	
	public ButtonContainer() {
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");
	}
	
	@Override
	public void init() {
		setLayout(new GridLayout(5, 2));
	}
}
