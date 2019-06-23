package br.ufsc.ine5605.ShardRPG.Screens;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import JFrames.CustomOutputStream;

public class GameContainer extends JFrame {

	private static GameContainer instance;


	public static GameContainer getInstance() {
		if (instance == null) {
			instance = new GameContainer();
		}
		return instance;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 8209345905250710021L;

	ImageIcon background = new ImageIcon("./img/game-background.jpg");

	JLabel backgroundImg = new JLabel(background);

	ImageIcon gameDisplay = new ImageIcon("./img/hoodedOrc.jpg");

	JLabel gameDisplayScreen = new JLabel(gameDisplay);

	JLabel textHolder = new JLabel();

	Color brown = new Color(44, 14, 14);

	JTextArea consoleOutput = new JTextArea("");

	JTextField inputArea = new JTextField("");

	JButton send = new JButton("Send");

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	Image cursorImage = toolkit.getImage("./img/cursor.png");

	// Cursor sword = toolkit.createCustomCursor(cursorImage, new Point(this.getX(),
	// this.getY()), "img");
	PrintStream printStream = new PrintStream(new CustomOutputStream(consoleOutput));


	public GameContainer() {
		setTitle("SHARD v0.5");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(null);
		setVisible(true);
		setAlwaysOnTop(true);
		// setCursor(sword);
		System.setOut(printStream);
		System.setErr(printStream);
		add(consoleOutput);
		consoleOutput.setBounds(50, 300, 700, 200);
		consoleOutput.setBackground(Color.gray);
		consoleOutput.setEditable(false);
		add(inputArea);
		inputArea.setBounds(50, 510, 600, 20);
		add(send);
		send.setBounds(660, 510, 90, 20);
		add(textHolder);
		textHolder.setOpaque(true);
		textHolder.setBackground(brown);
		textHolder.setBounds(25, 290, 750, 250);
		add(gameDisplayScreen);
		gameDisplayScreen.setBounds(150, 50, 500, 225);
		add(backgroundImg);
		backgroundImg.setBounds(0, 0, 800, 600);
	}

}
