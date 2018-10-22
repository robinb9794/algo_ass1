package workers;

import java.util.Map.Entry;

import javax.swing.JOptionPane;

import interfaces.View;
import interfaces.buttons.ButtonField;
import models.ViewModel;

public class SuperUserInteractionHandler {
	protected static ViewModel viewModel;
	protected static View gui;
	
	protected static boolean isFading;
		
	public static void init(ViewModel viewModel, View gui) {
		SuperUserInteractionHandler.viewModel = viewModel;
		SuperUserInteractionHandler.gui = gui;
		isFading = false;		
	}
	
	protected static void showInfoDialog(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	protected static void enableResetButton() {
		for(Entry<ButtonField, String> entry : viewModel.getButtons().entrySet()) {
		    if(entry.getValue().equals("Reset")) {
		    	entry.getKey().enableButton(true);
		    }
		}
	}
}
