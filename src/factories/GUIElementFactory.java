package factories;

import gui.elements.Screen;
import gui.elements.containers.ButtonContainer;
import gui.elements.containers.ImageContainer;
import gui.elements.containers.MenuItemContainer;
import gui.elements.menu.ChooseImagesItem;
import gui.elements.menu.CreateHistogramItem;
import gui.elements.menu.ImageChooser;
import gui.elements.menu.Menu;
import interfaces.GUIElement;
import models.ViewModel;
import interfaces.ElementAction;

public class GUIElementFactory extends SuperFactory{
	private ViewModel viewModel;
	
	public GUIElementFactory(ViewModel viewModel) {
		this.viewModel = viewModel;
	}
	
	@Override
	public GUIElement getGUIElement(String type) {
		switch(type) {
		case "Screen":
			return new Screen();
		case "Menu":
			return new Menu();
		case "MenuItemContainer":
			return new MenuItemContainer();
		case "ChooseImages":
			return new ChooseImagesItem();
		case "CreateHistogram":
			return new CreateHistogramItem();
		case "ImageChooser":
			return new ImageChooser(viewModel);
		case "ImageContainer":
			return new ImageContainer(viewModel);
		case "ButtonContainer":
			return new ButtonContainer();
		}
		return null;
	}

	@Override
	public ElementAction getElementAction(String type) {
		return null;
	}
}