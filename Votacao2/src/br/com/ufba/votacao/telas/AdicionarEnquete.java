package br.com.ufba.votacao.telas;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;

public class AdicionarEnquete {

	private JFrame frame;
	private JTextField titulo;
	private static File arqEnq;
	private static int qtdEnq;
	private JTextField dataTxt;
	private DateValidator dateValidator;
	private static JFrame frameTable;
	private int selection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarEnquete window = new AdicionarEnquete(qtdEnq, frameTable);
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
	public AdicionarEnquete(int qtdEnq, JFrame frameTable) {
		this.frameTable = frameTable;
		this.qtdEnq = qtdEnq;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.selection = 2;
		
		frame = new JFrame();
		frame.setSize(500,450);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setLayout(null);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblAdicionarEnquete = new JLabel("Adicionar Enquete");
		lblAdicionarEnquete.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdicionarEnquete.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarEnquete.setBounds(175, 15, 143, 14);
		panel.add(lblAdicionarEnquete);
		
		JLabel label_1 = new JLabel("Titulo");
		label_1.setBounds(100, 76, 46, 14);
		panel.add(label_1);
		
		titulo = new JTextField();
		titulo.setColumns(10);
		titulo.setBounds(175, 73, 178, 20);
		panel.add(titulo);
		
		Label label_2 = new Label("Numero de opcoes");
		label_2.setBounds(30, 96, 131, 22);
		panel.add(label_2);
		
		Label label_3 = new Label("Opcao 1");
		label_3.setBounds(86, 124, 75, 22);
		panel.add(label_3);
		
		Label label_4 = new Label("Opcao  2");
		label_4.setBounds(86, 152, 75, 22);
		panel.add(label_4);
		
		Label label_5 = new Label("Opcao 3");
		label_5.setBounds(86, 181, 75, 22);
		panel.add(label_5);
		
		Label label_6 = new Label("Opcao 4");
		label_6.setBounds(86, 209, 75, 22);
		panel.add(label_6);
		
		TextField op1Text = new TextField();
		op1Text.setBounds(175, 124, 178, 22);
		panel.add(op1Text);
		
		TextField op2Text = new TextField();
		op2Text.setBounds(175, 152, 178, 22);
		panel.add(op2Text);
		
		TextField op3Text = new TextField();
		op3Text.setBounds(175, 181, 178, 22);
		panel.add(op3Text);
		
		
		TextField op4Text = new TextField();
		op4Text.setBounds(175, 209, 178, 22);
		panel.add(op4Text);
		
		Label label_7 = new Label("Opcao 5");
		label_7.setBounds(86, 237, 75, 22);
		panel.add(label_7);
		
		TextField op5Text = new TextField();
		op5Text.setBounds(175, 237, 178, 22);
		panel.add(op5Text);
		label_5.setVisible(false);
		op3Text.setVisible(false);
		label_6.setVisible(false);
		op4Text.setVisible(false);
		label_7.setVisible(false);
		op5Text.setVisible(false);	
		
		dataTxt = new JTextField();
		dataTxt.setToolTipText("");
		dataTxt.setBounds(261, 272, 178, 20);
		panel.add(dataTxt);
		dataTxt.setColumns(10);
		
		
		
		JButton btnAddEnq = new JButton("Adicionar");
        
        // button add row
		ButtonGroup grupo = new ButtonGroup();
        btnAddEnq.addActionListener(new ActionListener(){
        	
			@Override
            public void actionPerformed(ActionEvent e) {
            	dateValidator = new DateValidator();
            	if (titulo.getText().equals("")|| op1Text.getText().equals("")|| op2Text.getText().equals("")|| dataTxt.getText().equals("")) {
            		JOptionPane.showMessageDialog(frame, "Algum campo deixado em branco.");
            	} else if(dateValidator.isThisDateValid(dataTxt.getText(), "dd/MM/yyyy") == false){
            		JOptionPane.showMessageDialog(frame, "Data Invalida.");
            	} else {
            	String toWrite = Integer.toString(qtdEnq);
            	
            	toWrite = toWrite + ':' + titulo.getText() + ':' + selection + ':' + op1Text.getText() + ':' + '0' + ':' + op2Text.getText() + ':' + '0' + ':' + op3Text.getText() + ':' + '0' + ':' + op4Text.getText() + ':' + '0' + ':' + op5Text.getText() + ':' + '0' + ':' + dataTxt.getText();           	
            	
            	try {
            		String aux = "Enquete"+qtdEnq;
            		
        			arqEnq = new File(aux);
        			if (!arqEnq.exists()) {
        				arqEnq.createNewFile();
        			}

        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}

        		FileWriter fileWriter;
        		try {
        			fileWriter = new FileWriter(arqEnq.getName(), true);
        			BufferedWriter bw = new BufferedWriter(fileWriter);
        			bw.write(toWrite + "\n");
        			bw.close();
        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}
            	
                frameTable.setVisible(false);
                frame.setVisible(false);
				JTableRow obj = new JTableRow();
                
                
            }
            }
        });
        
        
		btnAddEnq.setBounds(202, 341, 89, 23);
		panel.add(btnAddEnq);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frameTable.setVisible(false);
				JTableRow obj = new JTableRow();
			}
		});
		btnVoltar.setBounds(23, 11, 89, 23);
		panel.add(btnVoltar);
		
		JLabel lblDigiteAData = new JLabel("Digite a data limite (Formato dd/MM/YYYY)");
		lblDigiteAData.setBounds(20, 275, 239, 14);
		panel.add(lblDigiteAData);		
		
		JLabel lblEx = new JLabel("Ex: 20/02/2018");
		lblEx.setBounds(306, 303, 111, 14);
		panel.add(lblEx);
		
		JRadioButton radioButton2 = new JRadioButton("2");
		
		radioButton2.setBounds(185, 96, 41, 23);
		panel.add(radioButton2);
		
		JRadioButton radioButton3 = new JRadioButton("3");
		
		radioButton3.setBounds(226, 96, 37, 23);
		panel.add(radioButton3);
		
		JRadioButton radioButton4 = new JRadioButton("4");
		
		radioButton4.setBounds(261, 96, 37, 23);
		panel.add(radioButton4);
		
		JRadioButton radioButton5 = new JRadioButton("5");
		
		radioButton5.setBounds(294, 96, 37, 23);
		panel.add(radioButton5);
		
		grupo.add(radioButton2);
		grupo.add(radioButton3);
		grupo.add(radioButton4);
		grupo.add(radioButton5);
		
		radioButton2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				selection = 2;
				label_5.setVisible(false);
				op3Text.setVisible(false);
				label_6.setVisible(false);
				op4Text.setVisible(false);
				label_7.setVisible(false);
				op5Text.setVisible(false);
			}
		});
		radioButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selection = 3;
				label_5.setVisible(true);
				op3Text.setVisible(true);
				label_6.setVisible(false);
				op4Text.setVisible(false);
				label_7.setVisible(false);
				op5Text.setVisible(false);
			}
		});
		radioButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selection = 4;
				label_5.setVisible(true);
				op3Text.setVisible(true);
				label_6.setVisible(true);
				op4Text.setVisible(true);
				label_7.setVisible(false);
				op5Text.setVisible(false);
			}
		});
		radioButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selection = 5;
				label_5.setVisible(true);
				op3Text.setVisible(true);
				label_6.setVisible(true);
				op4Text.setVisible(true);
				label_7.setVisible(true);
				op5Text.setVisible(true);
			}
		});
	}
}
