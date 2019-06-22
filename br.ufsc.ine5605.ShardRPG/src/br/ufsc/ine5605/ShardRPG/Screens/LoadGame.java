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

import br.ufsc.ine5605.ShardRPG.Control.JsonDao;

public class LoadGame extends JFrame {

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

			@Override
			public void actionPerformed(ActionEvent e) {
				//////////////

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
				/////////////
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
				key3.setIcon(nameImg);
				key3.setBounds(340, 145, 600, 500);
				log.setText("Enter the new name!");
			}
		});
		chanButton.setBounds(510, 420, 114, 25);
		panel.add(chanButton);

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


	public static void main(String[] args) throws IOException {
		final LoadGame game = new LoadGame();
	}
}
