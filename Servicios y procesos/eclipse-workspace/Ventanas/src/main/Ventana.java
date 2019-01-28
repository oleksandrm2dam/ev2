package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana {

	private JFrame frame;
	public static int NUM_FILOSOFOS = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 276, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnEmpezar = new JButton("Empezar a filosofear");
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
				GestorPalillos gp = new GestorPalillos(NUM_FILOSOFOS);
				
				for(int i = 0; i < NUM_FILOSOFOS; ++i) {
					filosofos[i] = new Filosofo(gp, i);
					new Thread(filosofos[i]).start();
				}
			}
		});
		btnEmpezar.setBounds(10, 11, 240, 23);
		frame.getContentPane().add(btnEmpezar);
		
		JLabel lblLabel = new JLabel("");
		lblLabel.setIcon(new ImageIcon("C:\\Users\\madrid\\Documents\\GitHub\\ev2\\Servicios y procesos\\eclipse-workspace\\Ventanas\\res\\filosofos.png"));
		lblLabel.setBounds(0, 0, 256, 285);
		frame.getContentPane().add(lblLabel);
	}
}
