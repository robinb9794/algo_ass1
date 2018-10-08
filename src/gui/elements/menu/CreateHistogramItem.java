package gui.elements.menu;

import javax.swing.JMenuItem;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;

public class CreateHistogramItem extends JMenuItem implements GUIElement{
	private SuperFactory singleActionFactory;
	
	public CreateHistogramItem() {
		this.singleActionFactory = FactoryProducer.getFactory("SingleAction");				
	}
	
	@Override
	public void init() {
		setText("Create histogram");
		addActionListener(singleActionFactory.getSingleAction("CreateHistogram"));
	}
}
