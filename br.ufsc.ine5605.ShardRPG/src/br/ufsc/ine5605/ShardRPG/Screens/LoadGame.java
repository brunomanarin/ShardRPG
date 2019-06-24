package br.ufsc.ine5605.ShardRPG.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufsc.ine5605.ShardRPG.Control.Game;
import br.ufsc.ine5605.ShardRPG.Control.JsonDao;

public class LoadGame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static LoadGame instance;


	public static LoadGame getInstance() throws IOException {
		if (instance == null) {
			instance = new LoadGame();
		}
		return instance;
	}

	private final JPanel panel;

	private final JsonDao dao;

	final Font font;

	final Font font2;

	private final ImageIcon listLogo = new ImageIcon("./img/cooltext328224248713043.png");

	private final ImageIcon loadImg = new ImageIcon("./img/load.png");

	private final ImageIcon keyImg = new ImageIcon("./img/key.png");

	private final ImageIcon delImg = new ImageIcon("./img/delete.png");

	private final ImageIcon chanImg = new ImageIcon("./img/change.png");

	private final ImageIcon nameImg = new ImageIcon("./img/name.png");

	private final JTextArea jTextArea;

	private final JTextField textField1;

	private final JTextField textField2;

	private final JTextField textField3;

	private final JScrollPane scrollPane;

	private final JLabel players;

	private final JLabel load;

	private final JLabel key;

	private final JLabel key2;

	private final JLabel key3;

	private final JLabel delete;

	private final JLabel change;

	private final JLabel log;

	private final ImageIcon background = new ImageIcon("./img/menuBG.jpg");

	private final JLabel menuBackground = new JLabel(background);

	private final JButton loadButton;

	private final JButton delButton;

	private final JButton chanButton;

	private final JButton chanButton2;

	private String keyName;


	public LoadGame() throws IOException {
		setTitle("SHARD v0.5");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		dao = new JsonDao();
		font = new Font("Segoe Script", Font.BOLD, 12);

		players = new JLabel();
		players.setIcon(listLogo);
		players.setBounds(10, -210, 600, 500);
		panel.add(players);

		load = new JLabel();
		load.setIcon(loadImg);
		load.setBounds(460, -210, 600, 500);
		panel.add(load);

		key = new JLabel();
		key.setIcon(keyImg);
		key.setBounds(350, -150, 600, 500);
		panel.add(key);

		textField1 = new JTextField();
		textField1.setBounds(420, 83, 300, 30);
		textField1.setFont(font);
		panel.add(textField1);

		loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			//juntarEventos
			//dao para o Game

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField1.getText() != null) {
					try {
						Game.getInstance().setPlayer(dao.getPlayer(textField1.getText()));
						if (Game.getInstance().getPlayer() != null) {
							Game.getInstance().start();
						} else {
							log.setText("Invalid Player!");
						}
					} catch (final Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					;
				}

			}
		});
		loadButton.setBounds(510, 123, 114, 25);
		panel.add(loadButton);

		delete = new JLabel();
		delete.setIcon(delImg);
		delete.setBounds(435, -50, 600, 500);
		panel.add(delete);

		key2 = new JLabel();
		key2.setIcon(keyImg);
		key2.setBounds(350, 0, 600, 500);
		panel.add(key2);

		textField2 = new JTextField();
		textField2.setBounds(420, 235, 300, 30);
		textField2.setFont(font);
		panel.add(textField2);

		delButton = new JButton("Delete");
		delButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField2.getText() != null) {
						if (dao.deletePlayer(textField2.getText())) {
							jTextArea.setText(dao.playerListing());
							log.setText("Updated List.");
						} else {
							log.setText("Invalid User!");
						}
					}
				} catch (final IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		delButton.setBounds(510, 275, 114, 25);
		panel.add(delButton);

		change = new JLabel();
		change.setIcon(chanImg);
		change.setBounds(435, 100, 600, 500);
		panel.add(change);

		key3 = new JLabel();
		key3.setIcon(keyImg);
		key3.setBounds(350, 145, 600, 500);
		panel.add(key3);

		textField3 = new JTextField();
		textField3.setBounds(420, 380, 300, 30);
		textField3.setFont(font);
		panel.add(textField3);

		chanButton = new JButton("Change");
		chanButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField3.getText() != null) {
					keyName = textField3.getText();
					key3.setIcon(nameImg);
					key3.setBounds(340, 145, 600, 500);
					log.setText("Enter the new name!");
					textField3.setText("");
					panel.remove(chanButton);
					chanButton2.setEnabled(true);
				}
			}
		});
		chanButton.setBounds(510, 420, 114, 25);
		panel.add(chanButton);

		chanButton2 = new JButton("Change");
		chanButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField3.getText() != null) {
					if (dao.changeName(keyName, textField3.getText())) {
						try {
							jTextArea.setText(dao.playerListing());
						} catch (final IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						log.setText("Updated List.");
					} else {
						log.setText("Invalid User!");
						log.setForeground(Color.RED);
					}
				}
			}
		});
		chanButton2.setBounds(510, 420, 114, 25);
		chanButton2.setEnabled(false);
		panel.add(chanButton2);

		font2 = new Font("Segoe Script", Font.BOLD, 25);
		log = new JLabel();
		log.setBounds(430, 270, 600, 500);
		log.setText("");
		log.setFont(font2);
		log.setForeground(Color.ORANGE);
		panel.add(log);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 300, 450);
		panel.add(scrollPane);

		jTextArea = new JTextArea();
		scrollPane.setViewportView(jTextArea);
		jTextArea.setText(dao.playerListing());
		jTextArea.setEditable(false);
		jTextArea.setFont(font);

		add(menuBackground);
		menuBackground.setBounds(0, 0, 800, 600);

	}
}
