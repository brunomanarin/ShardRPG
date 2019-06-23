package br.ufsc.ine5605.ShardRPG.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import br.ufsc.ine5605.ShardRPG.Control.JsonDao;
import br.ufsc.ine5605.ShardRPG.Control.ScreenHandler;
import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.PlayerRace;
import br.ufsc.ine5605.ShardRPG.Info.PlayerType;

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

	JsonDao dao;

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	Image cursorImage = toolkit.getImage("./img/cursor.png");

	ImageIcon questionMark = new ImageIcon("./img/register-create/questionmark.png");

	ImageIcon backgroundImage = new ImageIcon("./img/menuBG.jpg");

	ImageIcon register = new ImageIcon("./img/register-create/register.png");

	ImageIcon nameImg = new ImageIcon("./img/register-create/enterName.png");

	ImageIcon raceImg = new ImageIcon("./img/register-create/selectRace.png");

	ImageIcon classImg = new ImageIcon("./img/register-create/selectClass.png");

	ImageIcon rogue = new ImageIcon("./img/register-create/rogue.png");

	ImageIcon mage = new ImageIcon("./img/register-create/mage.png");

	ImageIcon warrior = new ImageIcon("./img/register-create/warrior.png");

	Color brown = new Color(26, 0, 0);

	// Cursor sword = toolkit.createCustomCursor(cursorImage, new Point(getX(), getY()), "img");

	JLabel background = new JLabel(backgroundImage);

	JLabel imageHolder = new JLabel("");

	JLabel title = new JLabel(register);

	JLabel nameLabel = new JLabel(nameImg);

	JLabel raceLabel = new JLabel(raceImg);

	JLabel roleLabel = new JLabel(classImg);

	JTextField name = new JTextField();

	JComboBox<PlayerRace> selectRace = new JComboBox<>(PlayerRace.values());

	JComboBox<PlayerType> selectRole = new JComboBox<>(PlayerType.values());

	JLabel characterPlaceHolder = new JLabel(questionMark);

	JButton submit = new JButton("Submit");

	LineBorder line = new LineBorder(brown, 1, true);

	Font font1 = new Font("SansSerif", Font.TRUETYPE_FONT, 28);

	Player player;

	ImageIcon back = new ImageIcon("./img/back.png");

	JButton goBack = new JButton(back);

	ImageIcon sound = new ImageIcon("./img/volume.png");

	ImageIcon mute = new ImageIcon("./img/mute.png");

	JButton toggleSound = new JButton(sound);


	public RegisterCreate() {
		setTitle("SHARD v0.5");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(null);
		setVisible(true);
		setAlwaysOnTop(true);
		// setCursor(sword);
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
		add(goBack);
		goBack.setBounds(30, 500, 50, 50);
		goBack.setBackground(null);
		goBack.setBorderPainted(false);
		goBack.setContentAreaFilled(false);
		goBack.setFocusPainted(false);
		goBack.setOpaque(false);
		add(background);
		background.setBounds(0, 0, 800, 600);
		background.setOpaque(true);
		background.setBackground(brown);
		dao = new JsonDao();

		final JComboHandler actnMngr = new JComboHandler();
		selectRole.addActionListener(actnMngr);

		final ButtonsHandler bttnMngr = new ButtonsHandler();
		toggleSound.addActionListener(bttnMngr);
		goBack.addActionListener(bttnMngr);

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerRace playerRace = null;
				switch (selectRace.getSelectedIndex()) {
				case 0:
					playerRace = PlayerRace.orc;
					break;
				case 1:
					playerRace = PlayerRace.human;
				default:
					//////////////////
					break;
				}

				PlayerType playerType = null;
				switch (selectRole.getSelectedIndex()) {
				case 0:
					playerType = PlayerType.warrior;
					break;
				case 1:
					playerType = PlayerType.mage;
					break;
				case 2:
					playerType = PlayerType.rogue;
					break;
				default:
					///////////
					break;
				}
				player = new Player(name.getText(), playerType, playerRace, 0, name.getText().toUpperCase());
				try {
					dao.registerPlayer(player);
					ScreenHandler.getInstance().setPlayer(player);
					ScreenHandler.getInstance().closeRegisterCreate();

				} catch (final IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		;
	}

	private class JComboHandler implements ActionListener {

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

	private class ButtonsHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == toggleSound) {
				if (toggleSound.getIcon().equals(sound)) {
					// buttonSound.play();
					// mainAmbience.stop();
					toggleSound.setIcon(mute);
				} else {
					// buttonSound.play();
					// mainAmbience.play();
					toggleSound.setIcon(sound);
				}
			} else if (e.getSource() == goBack) {
				ScreenHandler.getInstance().closeRegisterCreate();
				ScreenHandler.getInstance().openMainMenu(RegisterCreate.getInstance().getX(),
					RegisterCreate.getInstance().getY());
			}

		}
	}

}
