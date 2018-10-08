package controllers;
import controllers.GUIManager;
import models.ViewModel;

public class AssignmentInitializer {		
	public static void main(String args[]) {
		new AssignmentInitializer().initialize();
	}
	
	private void initialize() {
		ViewModel viewModel = getInitializedViewModel();
		GUIManager guiManager = new GUIManager(viewModel);
		guiManager.startWork();
	}
	
	private ViewModel getInitializedViewModel() {
		return new ViewModel("Assignment 01 - Robin Beyer", 800, 600);
	}
}
