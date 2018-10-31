package gui.elements.dialogs;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;

public class RotateWindow extends JDialog{
	public JButton clockwise, counterClockwise;
	
	public RotateWindow() {
		setTitle("Rotate");
		setModal(true);
		setLayout(new FlowLayout());
		clockwise = new JButton("Rotate clockwise");
		add(clockwise);
		setPreferredSize(new Dimension(200, 80));
	}
}
