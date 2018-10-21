package gui.elements.buttons;

import java.awt.event.ActionEvent;

import models.Mode;

public class ShearButton extends SuperButton{
	public ShearButton() {
		super();
	}
	
	@Override
	public void init() {
		setToolTipText("Shear");
		setIconFromUrl("http://cdn.onlinewebfonts.com/svg/img_524800.png");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.setCurrentMode(Mode.SHEAR);
	}

}