package gui.elements.containers;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;

import factories.FactoryProducer;
import factories.SuperFactory;
import interfaces.GUIElement;
import interfaces.ImageBar;
import models.ViewModel;

public class ImageContainer extends JPanel implements ImageBar{
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












