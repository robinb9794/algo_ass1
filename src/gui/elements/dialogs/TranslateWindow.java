package gui.elements.dialogs;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JSlider;

public class TranslateWindow extends JDialog{
	public JSlider horizontalSlider, verticalSlider;
	
	public TranslateWindow() {
		setTitle("Translate");
		setModal(true);
		setLayout(new GridLayout(2, 2));
		setPreferredSize(new Dimension(400, 100));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
}
