package gui.elements.buttons;

import java.awt.event.ActionEvent;

import models.Mode;
import models.ViewModel;

public class FadeButton extends SuperButton {
	private ViewModel viewModel;
	
	public FadeButton(ViewModel viewModel) {
		super();
		this.viewModel = viewModel;
	}
	
	@Override
	public void init() {
		setText("Fade");		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.setCurrentMode(Mode.FADE);
	}
}
