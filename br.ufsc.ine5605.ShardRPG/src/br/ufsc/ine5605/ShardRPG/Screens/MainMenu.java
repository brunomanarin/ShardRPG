package br.ufsc.ine5605.ShardRPG.Screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.applet.*;

import javax.swing.*;

public class MainMenu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5786210545170621619L;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image cursorImage =  toolkit.getImage("./img/cursor.png");
	Cursor sword = toolkit.createCustomCursor(cursorImage, new Point(this.getX(), this.getY()), "img");
	ImageIcon shardLogo = new ImageIcon("./img/shard2.png");
	ImageIcon background = new ImageIcon("./img/menuBG.jpg");
	JLabel titleLogo = new JLabel(shardLogo);
	JLabel menuBackground = new JLabel(background);
	JLabel optionsSquare = new JLabel();
	JButton newGame = new JButton("Start New Game");
	JButton modifyData =  new JButton("Modify Data");
	JButton quit = new JButton("Quit to desktop");
	File lofi = new File("./wav/menuTheme.wav");
	java.net.URL lofiURL;
	AudioClip mainAmbience = Applet.newAudioClip(convertToUrl());
	
	File buttonPress = new File("./wav/buttonPress.wav");
	java.net.URL buttonSoundURL;
	AudioClip buttonSound = Applet.newAudioClip(convertToUrl2());
	
	public java.net.URL convertToUrl2() {
		try {
			buttonSoundURL = buttonPress.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buttonSoundURL;
	}
	
	public java.net.URL convertToUrl() {
		try {
			lofiURL = lofi.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lofiURL;
	}
	
	
	public MainMenu() {
		setTitle("SHARD v0.5");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLayout(null);
		setVisible(true);
		setAlwaysOnTop(true);
		setCursor(sword);
		add(titleLogo);
		titleLogo.setBounds(135,90,530,180);
		newGame.setBounds(325,300,150,40);
		modifyData.setBounds(325,350,150,40);
		quit.setBounds(325,400,150,40);
		buttonStyling(newGame);
		buttonStyling(modifyData);
		buttonStyling(quit);
		add(newGame);
		add(modifyData);
		add(quit);
		add(menuBackground);
		menuBackground.setBounds(0,0,800,600);
		
		ButtonManager btnManager = new ButtonManager();
		newGame.addActionListener(btnManager);
		modifyData.addActionListener(btnManager);
		quit.addActionListener(btnManager);
		mainAmbience.play();
	}
	public void buttonStyling(JButton button) {
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFocusPainted(false);
	}
	
	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
	}
	
	private class ButtonManager implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == newGame) {
				buttonSound.play();
				
			}else if(e.getSource() == modifyData) {
				buttonSound.play();
				
			} else if(e.getSource() == quit) {
				buttonSound.play();
				mainAmbience.stop();
				dispose();
				System.exit(0);
			}
		}
	}
}

