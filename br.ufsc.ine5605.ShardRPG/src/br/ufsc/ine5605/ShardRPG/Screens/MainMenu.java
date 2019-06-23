package br.ufsc.ine5605.ShardRPG.Screens;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.ufsc.ine5605.ShardRPG.Control.ScreenHandler;

public class MainMenu extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -5786210545170621619L;

	private static MainMenu instance;


	public static MainMenu getInstance() {
		if (instance == null) {
			instance = new MainMenu();
		}
		return instance;
	}

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	Image cursorImage = toolkit.getImage("./img/cursor.png");

	ImageIcon sound = new ImageIcon("./img/volume.png");

	ImageIcon mute = new ImageIcon("./img/mute.png");

	JButton toggleSound = new JButton(sound);

	// Cursor sword = toolkit.createCustomCursor(cursorImage, new Point(getX(), getY()), "img");

	ImageIcon shardLogo = new ImageIcon("./img/shard2.png");

	ImageIcon background = new ImageIcon("./img/menuBG.jpg");

	JLabel titleLogo = new JLabel(shardLogo);

	JLabel menuBackground = new JLabel(background);

	JLabel optionsSquare = new JLabel();

	JButton newGame = new JButton("Start New Game");

	JButton modifyData = new JButton("Modify Data");

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
		} catch (final MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buttonSoundURL;
	}


	public java.net.URL convertToUrl() {
		try {
			lofiURL = lofi.toURI().toURL();
		} catch (final MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lofiURL;
	}


	public MainMenu() {
		setTitle("SHARD v0.5");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(null);
		setVisible(true);
		setAlwaysOnTop(true);
		// setCursor(sword);
		add(titleLogo);
		titleLogo.setBounds(135, 90, 530, 180);
		newGame.setBounds(325, 300, 150, 40);
		modifyData.setBounds(325, 350, 150, 40);
		quit.setBounds(325, 400, 150, 40);
		buttonStyling(newGame);
		buttonStyling(modifyData);
		buttonStyling(quit);
		add(toggleSound);
		toggleSound.setBounds(30, 500, 50, 50);
		toggleSound.setBackground(null);
		toggleSound.setBorderPainted(false);
		toggleSound.setContentAreaFilled(false);
		toggleSound.setFocusPainted(false);
		toggleSound.setOpaque(false);
		add(newGame);
		add(modifyData);
		add(quit);
		add(menuBackground);
		menuBackground.setBounds(0, 0, 800, 600);

		final ButtonManager btnManager = new ButtonManager();
		newGame.addActionListener(btnManager);
		modifyData.addActionListener(btnManager);
		quit.addActionListener(btnManager);
		toggleSound.addActionListener(btnManager);
		mainAmbience.play();
	}


	public void buttonStyling(JButton button) {
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFocusPainted(false);
	}

	private class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == newGame) {
				buttonSound.play();
				mainAmbience.stop();
				ScreenHandler.getInstance().openRegisterCreate(MainMenu.getInstance().getX(),
					MainMenu.getInstance().getY());
				ScreenHandler.getInstance().closeMainMenu();
			} else if (e.getSource() == modifyData) {
				buttonSound.play();
				try {
					ScreenHandler.getInstance().openLoadGame(MainMenu.getInstance().getX(),
						MainMenu.getInstance().getY());
					ScreenHandler.getInstance().closeMainMenu();
				} catch (final IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (e.getSource() == quit) {
				buttonSound.play();
				mainAmbience.stop();
				dispose();
				System.exit(0);
			} else if (e.getSource() == toggleSound) {
				if (toggleSound.getIcon().equals(sound)) {
					buttonSound.play();
					mainAmbience.stop();
					toggleSound.setIcon(mute);
				} else {
					buttonSound.play();
					mainAmbience.play();
					toggleSound.setIcon(sound);
				}
			}
		}
	}
}
