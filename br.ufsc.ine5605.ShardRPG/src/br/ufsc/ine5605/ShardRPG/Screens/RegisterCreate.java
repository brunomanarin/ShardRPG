package br.ufsc.ine5605.ShardRPG.Screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class RegisterCreate extends JFrame {

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	Image cursorImage = toolkit.getImage("./img/cursor.png");

	ImageIcon questionMark = new ImageIcon("./img/mage.png");

	Color brown = new Color(44, 14, 14);

	Cursor sword = toolkit.createCustomCursor(cursorImage, new Point(getX(), getY()), "img");

	JLabel background = new JLabel();

	JTextField name = new JTextField();

	String race[] = {"Human", "Orc"};

	JList<String> selectRace = new JList<>(race);

	String role[] = {"Rogue", "Warrior", "Mage"};

	JList<String> selectRole = new JList<>(role);

	JLabel characterPlaceHolder = new JLabel(questionMark);


	public RegisterCreate() {
		setTitle("SHARD v0.5");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(null);
		setVisible(true);
		setAlwaysOnTop(true);
		setCursor(sword);
		add(name);
		add(characterPlaceHolder);
		characterPlaceHolder.setBounds(400, 50, 300, 400);
		add(selectRace);
		selectRace.setBounds(0, 0, 100, 40);
		add(selectRole);
		selectRole.setBounds(0, 60, 100, 60);
		add(background);
		background.setBounds(0, 0, 800, 600);
		background.setOpaque(true);
		background.setBackground(brown);
	}


	public static void main(String[] args) {
		final RegisterCreate menu = new RegisterCreate();
	}
}
