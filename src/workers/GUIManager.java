package workers;

import java.awt.BorderLayout;

import factories.FactoryProducer;
import factories.SuperFactory;
import gui.GUI;
import interfaces.GUIElement;
import interfaces.ImageDisplay;
import interfaces.View;
import models.ViewModel;

public class GUIManager {
	protected static View gui;
	protected static ViewModel viewModel;
	
	protected static SuperFactory guiElementFactory;
	protected static SuperFactory buttonFactory;
		
	private static ImageDisplay screen;
	private static GUIElement menuBar;
	
	public GUIManager() {}
	
	public static void init(ViewModel viewModel) {
		GUIManager.viewModel = viewModel;
		gui = new GUI(viewModel);
		guiElementFactory = FactoryProducer.getFactory("GUIElement");
		buttonFactory = FactoryProducer.getFactory("Button");
	}
	
	public static void startWork() {
		buildGUI();
		initModelValues();
	}
	
	private static void buildGUI() {
		gui.init();

		ButtonContainerManager.init();
		ButtonContainerManager.startWork();
				
		screen = (ImageDisplay) guiElementFactory.getGUIElement("Screen");
		screen.init();
		gui.addElement(BorderLayout.CENTER, screen);	
		
		menuBar = guiElementFactory.getGUIElement("Menu");
		menuBar.init();
		gui.setMenuBar(menuBar);
				
		gui.packAndShow();				
	}
	
	private static void initModelValues() {
		viewModel.setScreen(screen);
		viewModel.initPixels();
		viewModel.initMemoryImageSource();
	}
	
	public static void initImageContainer() {
		ImageContainerManager.init();
		ImageContainerManager.startWork();
	}
}
