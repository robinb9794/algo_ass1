package gui.elements.containers;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import interfaces.GUIElement;
import interfaces.bar.ImageBar;

public class ImageContainer extends JPanel implements ImageBar{	
	@Override
	public void init() {
		setLayout(new FlowLayout());
	}

	@Override
	public void addImageField(GUIElement imageField) {
		add((JPanel) imageField);
	}	
}