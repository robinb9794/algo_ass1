package gui.elements.bar;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import interfaces.bar.DisplayedImage;

public class ClickableImage extends JLabel implements DisplayedImage{

	@Override
	public void init() {
		setSize(new Dimension(100, 100));
	}

	@Override
	public void addImageIcon(ImageIcon icon) {
		setIcon(icon);
	}
}
