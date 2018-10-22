package gui.elements.buttons;

import java.awt.event.ActionEvent;

public class LensButton extends SuperButton{	
	public LensButton() {
		super();
	}
	
	@Override
	public void init() {
		setToolTipText("Lens");
		setIconFromUrl("https://image.flaticon.com/icons/png/512/23/23195.png");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
