package factories;

import gui.elements.buttons.FadeButton;
import gui.elements.buttons.LensButton;
import gui.elements.containers.ButtonContainer;
import interfaces.GUIElement;
import interfaces.View;
import models.ViewModel;

public class ButtonFactory extends SuperFactory{
	private ViewModel viewModel;
	
	public ButtonFactory(ViewModel viewModel) {
		this.viewModel = viewModel;
	}
	
	@Override
	public GUIElement getGUIElement(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIElement getButtonField(String type) {
		switch(type) {		
		case "Selection":
			return new FadeButton(viewModel);
		case "Reset":
			return new LensButton(viewModel);
		case "Fade":
			return new LensButton(viewModel);
		case "Translate":
			return new LensButton(viewModel);
		case "Rotate":
			return new LensButton(viewModel);
		case "Lens":
			return new LensButton(viewModel);
		case "Scale":
			return new FadeButton(viewModel);
		case "Shear":
			return new LensButton(viewModel);
		case "Lines":
			return new LensButton(viewModel);
		case "Circles":
			return new LensButton(viewModel);
		case "Start":
			return new LensButton(viewModel);
		case "Stop":
			return new LensButton(viewModel);
		}
		return null;
	}

}
