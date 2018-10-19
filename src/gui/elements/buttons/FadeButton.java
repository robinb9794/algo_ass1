package gui.elements.buttons;

import java.awt.event.ActionEvent;

import models.Mode;
import models.ViewModel;
import workers.UserInteractionHandler;

public class FadeButton extends SuperButton {	
	public FadeButton() {
		super();
	}
	
	@Override
	public void init() {
		setToolTipText("Fade");
		setIconFromUrl("https://static.thenounproject.com/png/151942-200.png");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.setCurrentMode(Mode.FADE);
		UserInteractionHandler.handleInteraction();
	}
}
