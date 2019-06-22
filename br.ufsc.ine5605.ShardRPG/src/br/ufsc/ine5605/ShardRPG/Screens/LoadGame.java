package br.ufsc.ine5605.ShardRPG.Screens;

import java.awt.Color;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import br.ufsc.ine5605.ShardRPG.Control.JsonDao;

public class LoadGame extends JFrame {

	private final JPanel panel;

	private final JsonDao dao;

	private ImageIcon listLogo;


	public LoadGame() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		dao = new JsonDao();

		final JLabel players = new JLabel();

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 300, 500);
		panel.add(scrollPane);

		final JTextArea jTextArea = new JTextArea();
		scrollPane.setViewportView(jTextArea);
		jTextArea.setText(dao.playerListing());
		jTextArea.setEditable(false);

	}


	public static void main(String[] args) throws IOException {
		final LoadGame game = new LoadGame();
	}
}
