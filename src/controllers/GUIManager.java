package controllers;

import java.awt.BorderLayout;

import factories.FactoryProducer;
import factories.SuperFactory;
import gui.GUI;
import interfaces.GUIElement;
import interfaces.View;
import interfaces.bar.ImageDisplay;
import models.ViewModel;

public class GUIManager {
	protected static ViewModel viewModel;
	protected static View gui;
	
	private SuperFactory guiElementFactory;
	
	private ImageDisplay screen;
	private GUIElement menuBar;
	private GUIElement buttonContainer;
	
	public GUIManager() {}
	
	public GUIManager(ViewModel viewModel) {
		this.viewModel = viewModel;
		this.gui = new GUI(viewModel);
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");
	}
	
	public void startWork() {
		initAndBuildGUI();
	}
	
	private void initAndBuildGUI() {
		gui.init();
		
		this.screen = (ImageDisplay) guiElementFactory.getGUIElement("Screen");
		screen.init();
		gui.addElement(BorderLayout.CENTER, screen);		
		
		this.menuBar = guiElementFactory.getGUIElement("Menu");
		menuBar.init();
		gui.setMenuBar(menuBar);
		
		gui.packAndShow();
		
		viewModel.setScreenWidth(screen.getScreenWidth());
		viewModel.setScreenHeight(screen.getScreenHeight());
	}
}
