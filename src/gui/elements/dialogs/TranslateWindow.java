package gui.elements.dialogs;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class TranslateWindow extends JDialog{		
	public JButton left, right, up, down;
	
	public TranslateWindow() {
		setTitle("Translate");
		setModal(true);
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(300, 80));		
	}
	
	public void initButtons() {
		BufferedImage buttonIcon;
		try {
			left = new JButton();
			buttonIcon = ImageIO.read(new URL("https://image.freepik.com/free-icon/arrow-bold-left-ios-7-interface-symbol_318-34824.jpg"));
			left.setIcon(new ImageIcon(buttonIcon.getScaledInstance(16,  16, Image.SCALE_SMOOTH)));
			add(left);
			
			right = new JButton();
			buttonIcon = ImageIO.read(new URL("https://image.freepik.com/free-icon/arrow-bold-right-ios-7-symbol_318-35504.jpg"));
			right.setIcon(new ImageIcon(buttonIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
			add(right);
			
			up = new JButton();
			buttonIcon = ImageIO.read(new URL("https://image.freepik.com/free-icon/up-arrow_318-123025.jpg"));
			up.setIcon(new ImageIcon(buttonIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
			add(up);
			
			down = new JButton();
			buttonIcon = ImageIO.read(new URL("https://image.freepik.com/free-icon/side-down_318-125102.jpg"));
			down.setIcon(new ImageIcon(buttonIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
			add(down);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
