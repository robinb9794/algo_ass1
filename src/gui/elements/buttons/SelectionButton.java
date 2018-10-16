package gui.elements.buttons;

import java.awt.event.ActionEvent;

import models.Mode;
import models.ViewModel;

public class SelectionButton extends SuperButton{	
	public SelectionButton() {
		super();
	}

	@Override
	public void init() {
		setToolTipText("Area selection");
		setIconFromUrl("http://cdn.onlinewebfonts.com/svg/img_520210.png");	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.setCurrentMode(Mode.SELECTION);
	}
}
