package br.com.ufba.votacao.telas;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;

public class ResultadosEnquete {

	private JFrame frame;
	private static JFrame frameTable;
	private Scanner scanner;
	private static int indEnq;
	private TipoEnquete enq = new TipoEnquete();
	private static String dados[];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultadosEnquete window = new ResultadosEnquete(indEnq, frameTable);
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
	public ResultadosEnquete(int n, JFrame f) {
		indEnq = n;
		frameTable=f;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(400,400);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		
		JLabel op1 = new JLabel(enq.op1+": "+enq.qtdOp1+ " votos");
		op1.setBounds(52, 68, 348, 14);
		frame.getContentPane().add(op1);
		
		JLabel op2 = new JLabel(enq.op2+": "+enq.qtdOp2+ " votos");
		op2.setBounds(52, 103, 348, 14);
		frame.getContentPane().add(op2);
		
		int numop = Integer.parseInt(enq.nop);
		
		if (numop>=3) {
			JLabel op3 = new JLabel(enq.op3+": "+enq.qtdOp3+ " votos");
			op3.setBounds(52, 142, 348, 14);
			frame.getContentPane().add(op3);
		}
		
		if (numop>=4) {
			JLabel op4 = new JLabel(enq.op4+": "+enq.qtdOp4+ " votos");
			op4.setBounds(52, 179, 348, 14);
			frame.getContentPane().add(op4);
		}
		
		if (numop>=5) {
			JLabel op5 = new JLabel(enq.op5+": "+enq.qtdOp5+ " votos");
			op5.setBounds(52, 216, 348, 14);
			frame.getContentPane().add(op5);
		}
		
		JButton btnVoltar = new JButton("Tela principal");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frameTable.setVisible(false);
				JTableRow obj = new JTableRow();
			}
		});
		btnVoltar.setBounds(134, 277, 133, 23);
		frame.getContentPane().add(btnVoltar);
		
		JButton btnMeuHistrico = new JButton("Meu Hist\u00F3rico");
		btnMeuHistrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Historico hist = new Historico(frameTable);
			}
		});
		btnMeuHistrico.setBounds(134, 313, 133, 23);
		frame.getContentPane().add(btnMeuHistrico);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 0, 400, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JLabel lblResultadosDaEnquete = new JLabel("\t\t\t\t\t\t\t\tResultados da Enquete \""+enq.titulo+"\":");
		lblResultadosDaEnquete.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblResultadosDaEnquete.setBounds(0, 27, 384, 14);
		panel.add(lblResultadosDaEnquete);
		lblResultadosDaEnquete.setHorizontalAlignment(SwingConstants.CENTER);
		frame.setVisible(true);
		
	}
}
