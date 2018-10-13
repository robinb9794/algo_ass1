package gui.elements.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;

public class CreateHistogramItem extends JMenuItem implements GUIElement, ActionListener{
	private SuperFactory guiElementFactory;
	
	private GUIElement histogramCreator;
	
	public CreateHistogramItem() {
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");	
		this.histogramCreator = guiElementFactory.getGUIElement("HistogramCreator");
	}
	
	@Override
	public void init() {
		setText("Create histogram");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		histogramCreator.init();
	}
}
