package gui.elements.buttons;

import java.awt.event.ActionEvent;

import models.Mode;
import models.ViewModel;

public class ResetButton extends SuperButton{	
	public ResetButton() {
		super();
	}
	
	@Override
	public void init() {
		setToolTipText("Reset selection");
		setIconFromUrl("https://image.flaticon.com/icons/png/512/51/51032.png");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.setCurrentMode(Mode.RESET);
	}
}
