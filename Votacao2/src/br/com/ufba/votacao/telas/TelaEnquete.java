package br.com.ufba.votacao.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.CardLayout;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class TelaEnquete  extends JFrame {

	private JFrame frame;
	private static JFrame frameTable;
	private static int indEnq;
	private static String dados[];
	private TipoEnquete enq = new TipoEnquete();
	private Scanner scanner;
	private File arqPass = null;
	FileWriter fileWriter;
	BufferedWriter bw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEnquete window = new TelaEnquete(indEnq, frameTable);
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
	public TelaEnquete(int indEnq, JFrame frameTable) {
		getContentPane().setLayout(null);
		
		Scanner s = null;
		try {
			s = new Scanner(new File("User" + TelaInicial.userID));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		String x = s.nextLine();
		
        TipoUser user = new TipoUser(x);
		
        if(!user.jaVotados.contains(indEnq)) {
		
			this.indEnq = indEnq;		
			this.frameTable = frameTable;
			
			try {
				scanner = new Scanner(new File("Enquete"+indEnq));
	
				String lineFromFile = scanner.nextLine();
	
				dados = lineFromFile.split(":");
				enq.id = dados[0];
				enq.titulo = dados[1];
				enq.nop = dados[2];
				enq.op1 = dados[3];
				enq.qtdOp1 = dados[4];
				enq.op2 = dados[5];
				enq.qtdOp2 = dados[6];
				enq.op3 = dados[7];
				enq.qtdOp3 = dados[8];
				enq.op4 = dados[9];
				enq.qtdOp4 = dados[10];
				enq.op5 = dados[11];
				enq.qtdOp5 = dados[12];
				enq.dtf = dados[13];
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			initialize();
		
        } else {
        	ResultadosEnquete obj = new ResultadosEnquete(indEnq,frameTable);
        }	
        
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(400,400);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel(enq.titulo);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(0, 0, 384, 43);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JRadioButton op1Check = new JRadioButton(enq.op1);
		op1Check.setBounds(44, 72, 285, 23);
		frame.getContentPane().add(op1Check);
		
		JRadioButton op2Check = new JRadioButton(enq.op2);
		op2Check.setBounds(44, 110, 285, 23);
		frame.getContentPane().add(op2Check);
		

		JRadioButton op3Check = new JRadioButton(enq.op3);
		op3Check.setBounds(44, 154, 285, 23);
		

		JRadioButton op4Check = new JRadioButton(enq.op4);
		op4Check.setBounds(44, 198, 285, 23);
		

		JRadioButton op5Check = new JRadioButton(enq.op5);
		op5Check.setBounds(44, 237, 285, 23);
		
		int numOP = Integer.parseInt(enq.nop);
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(op1Check);
		grupo.add(op2Check);
		if(numOP >= 3) {
			frame.getContentPane().add(op3Check);
			grupo.add(op3Check);
		}
		
		if(numOP >= 4) {
			frame.getContentPane().add(op4Check);
			grupo.add(op4Check);
		}
		
		if(numOP >= 5) {
			frame.getContentPane().add(op5Check);
			grupo.add(op5Check);
		}
		
		
		
		JButton btnVotar = new JButton("Votar");
		btnVotar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(op1Check.isSelected()) {
					int x = Integer.parseInt(enq.qtdOp1)+1;
					enq.qtdOp1 = Integer.toString(x);
					//System.out.println(enq.qtdOp1);
				}
				
				if(op2Check.isSelected()) {
					int x = Integer.parseInt(enq.qtdOp2)+1;
					enq.qtdOp2 = Integer.toString(x);
				}
				
				if(op3Check.isSelected() && numOP >= 3) {
					int x = Integer.parseInt(enq.qtdOp3)+1;
					enq.qtdOp3 = Integer.toString(x);
				}
				
				if(op4Check.isSelected() && numOP >= 4) {
					int x = Integer.parseInt(enq.qtdOp4)+1;
					enq.qtdOp4 = Integer.toString(x);
				}
				
				if(op5Check.isSelected() && numOP >= 5) {
					int x = Integer.parseInt(enq.qtdOp5)+1;
					enq.qtdOp5 = Integer.toString(x);
				}
			
				String x = new String("");
				x = enq.id + ":" + enq.titulo + ":" + enq.nop + ":" + enq.op1 + ":" + enq.qtdOp1 + ":" + enq.op2 + ":" + enq.qtdOp2 + ":" + enq.op3 + ":" + enq.qtdOp3 + ":" + enq.op4 + ":" + enq.qtdOp4 + ":" + enq.op5 + ":" + enq.qtdOp5 + ":" + enq.dtf;

				File fold=new File("Enquete"+indEnq);
				fold.delete();
				File fnew=new File("Enquete"+indEnq);
				
				try {
				    FileWriter f2 = new FileWriter(fnew, false);
				    f2.write(x);
				    f2.close();
				} catch (IOException e1) {
				    e1.printStackTrace();
				}     
				String z = "";
				
				try {
					scanner = new Scanner(new File("User" + TelaInicial.userID));
					x = scanner.nextLine();
					scanner.close();
					TipoUser y = new TipoUser(x);
					y.qtd = y.qtd+1;
					z += y.id + ":" + y.nome + ":" + y.senha + ":" + y.qtd + ":";
					for(int i = 0; i < y.jaVotados.size(); i++) {
						z += y.jaVotados.get(i) + ":";
					}
					z += indEnq; 
					
					fold=new File("User"+TelaInicial.userID);
					fold.delete();
					fnew=new File("User"+TelaInicial.userID);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
				    FileWriter f2 = new FileWriter(fnew, false);
				    f2.write(z);
				    f2.close();
				} catch (IOException e1) {
				    e1.printStackTrace();
				}  
				frame.setVisible(false);
				
				ResultadosEnquete ob = new ResultadosEnquete(indEnq, frameTable);
				
			}
		});
		
		
		btnVotar.setBounds(137, 306, 89, 23);
		frame.getContentPane().add(btnVotar);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
