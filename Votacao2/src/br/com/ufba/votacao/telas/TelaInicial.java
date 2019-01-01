package br.com.ufba.votacao.telas;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;

public class TelaInicial {
	
	private static String inputData;
	private static File arqPass;
	private static File arqUser;

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private Scanner scanner;
	private static boolean achei;
	private int qtdUser;
	public static int userID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
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
	public TelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("activeCaption"));
		
		frame.getContentPane().setEnabled(true);
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel_inicial = new JPanel();
		panel_inicial.setBounds(127, 64, 533, 231);
		frame.getContentPane().add(panel_inicial);
		panel_inicial.setLayout(null);
		
		JLabel lblVotao = new JLabel("Vota\u00E7\u00E3o");
		lblVotao.setBounds(161, 11, 172, 29);
		panel_inicial.add(lblVotao);
		lblVotao.setFont(new Font("Courier New", Font.BOLD, 23));
		lblVotao.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel aux = new JPanel();
		aux.setBounds(114, 40, 279, 140);
		panel_inicial.add(aux);
		aux.setLayout(null);
		
		JPanel ASD = new JPanel();
		ASD.setBackground(Color.LIGHT_GRAY);
		ASD.setBounds(10, 11, 259, 78);
		aux.add(ASD);
		ASD.setLayout(null);
		
		username = new JTextField();
		username.setBackground(Color.WHITE);
		username.setBounds(69, 11, 149, 20);
		ASD.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("USU\u00C1RIO : ");
		lblNewLabel.setBounds(0, 14, 70, 14);
		ASD.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSenha = new JLabel("SENHA :");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setBounds(0, 45, 70, 14);
		ASD.add(lblSenha);
		
		password = new JPasswordField();
		password.setBounds(69, 42, 149, 20);
		ASD.add(password);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(153, 100, 126, 29);
		aux.add(btnLogin);
		
		JButton btnCadastro = new JButton("CADASTRO");
		btnCadastro.setBounds(0, 100, 126, 29);
		aux.add(btnCadastro);
		
		qtdUser = 0;
		try {
			scanner = new Scanner(new File("pass"));
			
			while (scanner.hasNextLine()) {
				scanner.nextLine();
				qtdUser++;	
			}	
			//System.out.println("qtdUser = " + qtdUser);
		} catch (FileNotFoundException e1) {
			File arqPass = new File("pass");
			try {
				arqPass.createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		// Fazer login
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String user  = username.getText();
				String pass = password.getText();
				achei = false;
				int ind = 0, i = 0;
				
				if (user.equals("")){
					JOptionPane.showMessageDialog(panel_inicial,"Campo usuario em branco");
				}
				else if (pass.equals("")){
					JOptionPane.showMessageDialog(panel_inicial,"Campo senha em branco");
				}
				else {
					try {
						scanner = new Scanner(new File("pass"));

						while (scanner.hasNextLine()) {

							String lineFromFile = scanner.nextLine();

							String userAndPass[] = lineFromFile.split(":");

							String user1 = userAndPass[1];
							String pass1 = userAndPass[2];

							if (user.equals(user1) && pass1.equals(new String(pass))) {
								achei = true;
								ind = i;
							}
							i++;
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
					if (achei) {
						 JOptionPane.showMessageDialog(frame, "Login efetuado com sucesso!");
						 userID = ind;
						 frame.setVisible(false);
						 JTableRow obj = new JTableRow();
					} else {
						JOptionPane.showMessageDialog(panel_inicial,"Usuario ou Senha Incorretos");
					}
					
							
				}
			}
		});
		
		
		// Fazer Cadastro
		btnCadastro.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String user  = username.getText();
				String pass = password.getText();
				
				
				achei = false;
				if (user.equals("")){
					JOptionPane.showMessageDialog(panel_inicial,"Campo usuario em branco");
				}
				else if (pass.equals("")) {
					JOptionPane.showMessageDialog(panel_inicial,"Campo senha em branco");
				}
				else {
					inputData = user + ":" + pass;
					panel_inicial.setVisible(false);
					try {
						arqPass = new File("pass");
						if (!arqPass.exists()) {
							arqPass.createNewFile();
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					FileWriter fileWriter;
					try {
						
						try {
							scanner = new Scanner(new File("pass"));

							while (scanner.hasNextLine()) {

								String lineFromFile = scanner.nextLine();
								String userAndPass[] = lineFromFile.split(":");

								String user1 = userAndPass[1];
								String pass1 = userAndPass[2];
								if (user.equals(user1)) {
									
									achei = true;
									break;
								}
							}
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}						
						
						if (!achei) {
							fileWriter = new FileWriter(arqPass.getName(), true);
							BufferedWriter bw = new BufferedWriter(fileWriter);
							bw.write(qtdUser + ":" + inputData + "\n");
							bw.close();
							String aux = "User"+qtdUser;
							try {			            		
			        			arqUser = new File(aux);
			        			if (!arqUser.exists()) {
			        				arqUser.createNewFile();
			        			}
			        		} catch (IOException e1) {
			        			e1.printStackTrace();
			        		}
							fileWriter = new FileWriter(aux, true);
							bw = new BufferedWriter(fileWriter);
							bw.write(qtdUser + ":" + inputData + ":0" + "\n");
							bw.close();
							JOptionPane.showMessageDialog(panel_inicial,"Cadastro Efetuado com Sucesso");
							frame.setVisible(false);
							userID = qtdUser;
							JTableRow obj = new JTableRow();
						} else {
							JOptionPane.showMessageDialog(panel_inicial,"Usuário já cadastrado");
							frame.setVisible(false);
							TelaInicial obj = new TelaInicial();
						}
						
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
	}
}
