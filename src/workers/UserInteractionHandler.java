package workers;

import javax.swing.JOptionPane;

import interfaces.View;
import models.ViewModel;

public class UserInteractionHandler {
	protected static ViewModel viewModel;
	protected static View gui;
	
	protected static boolean handlingAction;
		
	public static void init(ViewModel viewModel, View gui) {
		UserInteractionHandler.viewModel = viewModel;
		UserInteractionHandler.gui = gui;
		handlingAction = false;		
	}
	
	protected static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.ERROR_MESSAGE);
	}
}
