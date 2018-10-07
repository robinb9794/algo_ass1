package gui.elements.menu;

import javax.swing.JMenuItem;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;

public class ChooseImagesItem extends JMenuItem implements GUIElement{
	private SuperFactory elementActionFactory;
	
	public ChooseImagesItem() {
		this.elementActionFactory = FactoryProducer.getFactory("ElementAction");				
	}
	
	@Override
	public void init() {
		setText("Choose images");
		addActionListener(elementActionFactory.getElementAction("ChooseImages"));
	}
}
