package gui.elements.containers;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;
import interfaces.bar.ImageBar;
import models.ViewModel;

public class ImageContainer extends JPanel implements ImageBar{
	private ViewModel viewModel;
	
	private SuperFactory guiElementFactory;
	private SuperFactory elementActionFactory;
	
	public ImageContainer(ViewModel viewModel) {
		this.viewModel = viewModel;
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");
		this.elementActionFactory = FactoryProducer.getFactory("ElementAction");
	}
	
	@Override
	public void init() {
		setLayout(new FlowLayout());
	}

	@Override
	public void addImageField(GUIElement imageField) {
		add((JPanel) imageField);
	}	
}