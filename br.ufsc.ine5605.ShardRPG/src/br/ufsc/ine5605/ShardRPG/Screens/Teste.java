package br.ufsc.ine5605.ShardRPG.Screens;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Teste extends JFrame {

	private final JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					final Teste frame = new Teste();
					frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Teste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(201, 12, 128, 267);
		contentPane.add(scrollPane);

		final JTextArea jTextArea = new JTextArea();
		scrollPane.setViewportView(jTextArea);
		jTextArea.setText("O poeta é um fingidor.\n" + "Finge tão completamente\n" + "Que chega a fingir que é dor\n"
			+ "A dor que deveras sente.\n" + "\n" + "E os que lêem o que escreve,\n" + "Na dor lida sentem bem,\n"
			+ "Não as duas que ele teve,\n" + "Mas só a que eles não têm.\n" + "\n" + "E assim nas calhas da roda\n"
			+ "Gira, a entreter a razão,\n" + "Esse comboio de corda\n" + "Que se chama o coração.O poeta é um fingidor.\n"
			+ "Finge tão completamente\n" + "Que chega a fingir que é dor\n" + "A dor que deveras sente.\n" + "\n"
			+ "E os que lêem o que escreve,\n" + "Na dor lida sentem bem,\n" + "Não as duas que ele teve,\n"
			+ "Mas só a que eles não têm.\n" + "\n" + "E assim nas calhas da roda\n" + "Gira, a entreter a razão,\n"
			+ "Esse comboio de corda\n" + "Que se chama o coração.");

	}
}
