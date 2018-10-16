package gui.elements.buttons;

import java.awt.event.ActionEvent;

import models.Mode;

public class StartButton extends SuperButton{
	public StartButton() {
		super();
	}
	
	@Override
	public void init() {
		setToolTipText("Start");
		setIconFromUrl("https://cdn1.iconfinder.com/data/icons/ui-line-minimal/512/Power_Line-512.png");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.setCurrentMode(Mode.START);
	}

}
