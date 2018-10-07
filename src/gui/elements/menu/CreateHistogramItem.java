package gui.elements.menu;

import javax.swing.JMenuItem;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;

public class CreateHistogramItem extends JMenuItem implements GUIElement{
	private SuperFactory elementActionFactory;
	
	public CreateHistogramItem() {
		this.elementActionFactory = FactoryProducer.getFactory("ElementAction");				
	}
	
	@Override
	public void init() {
		setText("Create histogram");
		addActionListener(elementActionFactory.getElementAction("CreateHistogram"));
	}
}
