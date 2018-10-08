package gui.elements.menu;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.ImageContainerManager;
import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;
import models.ViewModel;

public class ImageChooser extends JFileChooser implements GUIElement{
	private ViewModel viewModel;
	
	private SuperFactory guiElementFactory;
	
	private ImageContainerManager imageContainerManager;
	
	public ImageChooser(ViewModel viewModel) {
		this.viewModel = viewModel;
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");
	}
	
	public void init() {
		setMultiSelectionEnabled(true);
		setDialogTitle("Please select images:");
		setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg/gif", "jpg", "gif");
		setFileFilter(filter);		
		int returnValue = showOpenDialog(this);
		handleUserAction(returnValue);		
	}
	
	private void handleUserAction(int returnValue) {
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = getSelectedFiles();
			provideFiles(selectedFiles);
		}
	}
	
	private void provideFiles(File[] selectedFiles) {
		viewModel.setSelectedFiles(selectedFiles);
		this.imageContainerManager = new ImageContainerManager(viewModel);
		this.imageContainerManager.handleProvidedFiles();
	}
}
