package br.ufsc.ine5605.ShardRPG.Screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class RegisterCreate extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -3674591728914083493L;

	private static RegisterCreate instance;


	public static RegisterCreate getInstance() {
		if (instance == null) {
			instance = new RegisterCreate();
		}
		return instance;
	}

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	Image cursorImage = toolkit.getImage("./img/cursor.png");

	ImageIcon questionMark = new ImageIcon("./img/questionmark.png");

	ImageIcon backgroundImage = new ImageIcon("./img/menuBG.jpg");

	ImageIcon register = new ImageIcon("./img/register.png");

	ImageIcon nameImg = new ImageIcon("./img/enterName15.png");

	ImageIcon raceImg = new ImageIcon("./img/selectRace.png");

	ImageIcon classImg = new ImageIcon("./img/selectClass.png");

	ImageIcon rogue = new ImageIcon("./img/rogue.png");

	ImageIcon mage = new ImageIcon("./img/mage.png");

	ImageIcon warrior = new ImageIcon("./img/warrior.png");

	Color brown = new Color(26, 0, 0);

	Cursor sword = toolkit.createCustomCursor(cursorImage, new Point(getX(), getY()), "img");

	JLabel background = new JLabel(backgroundImage);

	JLabel imageHolder = new JLabel("");

	JLabel title = new JLabel(register);

	JLabel nameLabel = new JLabel(nameImg);

	JLabel raceLabel = new JLabel(raceImg);

	JLabel roleLabel = new JLabel(classImg);

	JTextField name = new JTextField();

	String race[] = {"Human", "Orc"};

	JComboBox<String> selectRace = new JComboBox<>(race);

	String role[] = {"Rogue", "Warrior", "Mage"};

	JComboBox<String> selectRole = new JComboBox<>(role);

	JLabel characterPlaceHolder = new JLabel(questionMark);

	JButton submit = new JButton("Submit");

	LineBorder line = new LineBorder(brown, 1, true);

	Font font1 = new Font("SansSerif", Font.TRUETYPE_FONT, 28);


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
		add(nameLabel);
		nameLabel.setBounds(35, 90, 150, 20);
		name.setBounds(30, 120, 325, 40);
		name.setBorder(line);
		name.setFont(font1);
		name.setBackground(Color.black);
		name.setForeground(Color.WHITE);
		add(title);
		title.setBounds(30, -10, 400, 100);
		add(characterPlaceHolder);
		characterPlaceHolder.setBounds(450, 75, 300, 400);
		add(selectRace);
		add(raceLabel);
		raceLabel.setBounds(35, 210, 150, 20);
		selectRace.setBounds(35, 250, 325, 40);
		selectRace.setBackground(Color.black);
		selectRace.setForeground(Color.white);
		selectRace.setFont(font1);
		add(selectRole);
		add(roleLabel);
		roleLabel.setBounds(35, 340, 150, 20);
		selectRole.setBounds(35, 370, 325, 40);
		selectRole.setFont(font1);
		selectRole.setBackground(Color.black);
		selectRole.setForeground(Color.white);
		add(imageHolder);
		imageHolder.setBounds(425, 75, 350, 400);
		imageHolder.setOpaque(true);
		imageHolder.setBackground(Color.white);
		add(submit);
		submit.setBounds(300, 500, 200, 40);
		submit.setBackground(Color.black);
		submit.setForeground(Color.white);
		submit.setFont(font1);
		submit.setFocusPainted(false);
		add(background);
		background.setBounds(0, 0, 800, 600);
		background.setOpaque(true);
		background.setBackground(brown);

		final ActionManager actnMngr = new ActionManager();
		selectRole.addActionListener(actnMngr);

	}

	private class ActionManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			final JComboBox<String> cb = (JComboBox<String>) e.getSource();
			final String role = (String) cb.getSelectedItem();
			if (role.equals("Rogue")) {
				characterPlaceHolder.setIcon(rogue);
			} else if (role.equals("Warrior")) {
				characterPlaceHolder.setIcon(warrior);
			} else if (role.equals("Mage")) {
				characterPlaceHolder.setIcon(mage);
			}
		}
	}

}
