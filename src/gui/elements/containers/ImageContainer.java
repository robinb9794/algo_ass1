package gui.elements.containers;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JPanel;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;
import models.ViewModel;

public class ImageContainer extends JPanel implements GUIElement{
	private ViewModel viewModel;
	
	private SuperFactory guiElementFactory;
	private SuperFactory elementActionFactory;
	
	public ImageContainer(ViewModel viewModel) {
		this.viewModel = viewModel;
		this.guiElementFactory = FactoryProducer.getFactory("GUIElement");
		this.elementActionFactory = FactoryProducer.getFactory("ElementAction");
	}
	
	@Override
	public void init() {
		setLayout(new FlowLayout());
		handleProvidedFiles();
	}
	
	private void handleProvidedFiles() {
		File[] selectedFiles = viewModel.getSelectedFiles();
		for(int i = 0; i < selectedFiles.length; i++) {
			File selectedFile = selectedFiles[i];
			if(selectedFile.isDirectory()) {
				File[] filesFromDirectory = selectedFile.listFiles();
				selectedFiles = resizeAndFill(selectedFiles, filesFromDirectory);
				selectedFile = selectedFiles[i + 1];
			}
		}
	}
	
	private File[] resizeAndFill(File[] files, File[] filesFromDirectory) {
		int oldNumberOfFiles = files.length - 1;
		int newNumberOfFiles = oldNumberOfFiles + filesFromDirectory.length;
		File[] combinedFiles = new File[newNumberOfFiles];
		for(int i = 0; i < newNumberOfFiles; i++) {
			combinedFiles[i] = i < oldNumberOfFiles ? files[i] : filesFromDirectory[i];
		}
		return combinedFiles;
	}
}
