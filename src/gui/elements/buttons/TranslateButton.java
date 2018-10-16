package gui.elements.buttons;

import java.awt.event.ActionEvent;

import models.Mode;

public class TranslateButton extends SuperButton{
	public TranslateButton() {
		super();
	}
	
	@Override
	public void init() {
		setToolTipText("Translate");
		setIconFromUrl("http://icons.iconarchive.com/icons/iconsmind/outline/256/Cursor-Move-icon.png");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.setCurrentMode(Mode.TRANSLATE);
	}	
}