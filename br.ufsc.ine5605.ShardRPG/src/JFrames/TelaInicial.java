package JFrames;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class TelaInicial extends JFrame{

	private static final long serialVersionUID = 1L;
	static JFrame telaTeste = new JFrame("SHARD v0.5");
	static JTextArea textoConsole = new JTextArea(20, 20);
	static PrintStream printStream = new PrintStream(new CustomOutputStream(textoConsole));
	//static JLabel imagem = new JLabel("ROOM/EVENT IMAGE GOES HERE");
	static FlowLayout flow = new FlowLayout();
	
	public static void inicializaTela() {
		System.setOut(printStream);
		System.setErr(printStream);
		telaTeste.setVisible(true);
		telaTeste.setSize(800,600);
		telaTeste.setBackground(Color.gray);
		telaTeste.setLayout(flow);
		flow.setAlignment(FlowLayout.CENTER);
		telaTeste.setLocation(0,0);
		telaTeste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaTeste.setResizable(true);
		//telaTeste.add(imagem);
		telaTeste.add(textoConsole);
		textoConsole.setBackground(Color.white);
	}
	public static void main(String[] args) {
		inicializaTela(); 
		System.out.println("oi");
	}
	
}