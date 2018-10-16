package gui.elements.buttons;

import java.awt.event.ActionEvent;

import models.Mode;
import models.ViewModel;

public class LensButton extends SuperButton{
	private ViewModel viewModel;
	
	public LensButton(ViewModel viewModel) {
		this.viewModel = viewModel;
	}
	
	@Override
	public void init() {
		setText("Lens");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.setCurrentMode(Mode.LENS);
	}
}
