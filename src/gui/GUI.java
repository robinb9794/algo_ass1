package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import interfaces.GUIElement;
import interfaces.View;
import models.ViewModel;

public class GUI extends JFrame implements View{
	private ViewModel viewModel;
	
	public GUI(ViewModel viewModel) {
		super();
		this.viewModel = viewModel;
	}
	
	@Override
	public void init() {
		setTitle(viewModel.getGUITitle());
		setPreferredSize(new Dimension(viewModel.getGUIWidth(), viewModel.getGUIHeight()));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	@Override
	public void addElement(String position, GUIElement guiElement) {
		getContentPane().add(position, (JComponent) guiElement);
	}
	
	@Override
	public void setMenuBar(GUIElement menuBar) {
		setJMenuBar((JMenuBar) menuBar);
	}
	
	@Override
	public void packAndShow() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void reorder() {
		pack();
		validate();
	}
	
	@Override
	public void reloadScreen() {
		repaint();
	}
}
