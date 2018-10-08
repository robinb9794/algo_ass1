package gui.elements.menu;

import javax.swing.JMenuItem;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;

public class ChooseImagesItem extends JMenuItem implements GUIElement{
	private SuperFactory singleActionFactory;
	
	public ChooseImagesItem() {
		this.singleActionFactory = FactoryProducer.getFactory("SingleAction");				
	}
	
	@Override
	public void init() {
		setText("Choose images");
		addActionListener(singleActionFactory.getSingleAction("ChooseImages"));
	}
}
