package gui.elements.dialogs;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;

public class ScaleWindow extends JDialog{
	public JButton scale;
	
	public ScaleWindow() {
		setTitle("Scale");
		setModal(true);
		setLayout(new FlowLayout());
		scale = new JButton("Scale");
		add(scale);
		setPreferredSize(new Dimension(200, 80));
	}
}
