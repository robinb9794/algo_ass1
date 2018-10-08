package gui.elements.bar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.bar.DisplayedImage;

public class ImageField extends JPanel implements DisplayedImage{	
	private SuperFactory guiElementFactory;
	
	private DisplayedImage clickableImage;
	
	public ImageField() {
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");
		this.clickableImage = (DisplayedImage) this.guiElementFactory.getGUIElement("ClickableImage");
		this.clickableImage.init();
	}
	
	@Override
	public void init() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));	
		add((JLabel) clickableImage);
	}

	@Override
	public void addImageIcon(ImageIcon icon) {
		clickableImage.addImageIcon(icon);
	}
}
