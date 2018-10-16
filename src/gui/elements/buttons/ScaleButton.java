package gui.elements.buttons;

import java.awt.event.ActionEvent;

import models.Mode;

public class ScaleButton extends SuperButton{
	public ScaleButton() {
		super();
	}
	
	@Override
	public void init() {
		setToolTipText("Scale");
		setIconFromUrl("http://cdn.onlinewebfonts.com/svg/img_524792.png");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.setCurrentMode(Mode.SCALE);
	}

}
