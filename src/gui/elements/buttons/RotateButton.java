package gui.elements.buttons;

import java.awt.event.ActionEvent;

public class RotateButton extends SuperButton{
	public RotateButton() {
		super();
	}
	
	@Override
	public void init() {
		setToolTipText("Rotate");
		setIconFromUrl("https://static.thenounproject.com/png/120470-200.png");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
