package gui.elements.bar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;
import interfaces.bar.DisplayedImage;

public class ImageField extends JPanel implements GUIElement{	
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
		add(Box.createVerticalStrut(15));
		add(new JSeparator(SwingConstants.VERTICAL));
		setBorder(BorderFactory.createEmptyBorder(5,  0,  20,  3));
	}
}
