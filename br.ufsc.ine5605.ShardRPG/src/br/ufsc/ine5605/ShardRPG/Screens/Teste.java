package br.ufsc.ine5605.ShardRPG.Screens;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

		final JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(208, 124, 114, 25);
		contentPane.add(btnNewButton);

		final JLabel lblBomDia = new JLabel("bom dia");
		lblBomDia.setBackground(Color.GREEN);
		lblBomDia.setForeground(Color.LIGHT_GRAY);
		lblBomDia.setBounds(163, 56, 96, 31);
		contentPane.add(lblBomDia);

	}
}
