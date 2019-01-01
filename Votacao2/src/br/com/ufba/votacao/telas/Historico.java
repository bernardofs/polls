package br.com.ufba.votacao.telas;

import java.awt.EventQueue;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.List;

import javax.swing.JTable;
import java.awt.SystemColor;

public class Historico extends JFrame {

	private JFrame frame;
	private static JFrame frameTable;
	private JTable table;
	private TipoUser user;
	private TipoEnquete enq = new TipoEnquete();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Historico window = new Historico(frameTable);
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
	public Historico(JFrame table) {
		this.frameTable = table;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(600,400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(48, 175, 437, 147);
		panel.add(panel_1);
		
		table = new JTable();
		table.setBounds(0, 0, 437, 147);
		panel_1.add(table);
		
		Object[] columns = {"ID", "Nome da Enquete","Votos","Data Limite"};
        DefaultTableModel model = new DefaultTableModel() {
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
       
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        Scanner s = null;
		try {
			s = new Scanner(new File("User" + TelaInicial.userID));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		String x = s.nextLine();
		
        user = new TipoUser(x);
        
        Object[] row = new Object[4];
        for(int i = user.jaVotados.size()-1, j = 0; i >= 0 && j < 5; j++, i--) {
        	
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    		LocalDate localDate = LocalDate.now();
    		
				Scanner scanner;
				try {
					scanner = new Scanner(new File("Enquete" + user.jaVotados.get(i)));
				
				
					String lineFromFile = scanner.nextLine();
	
					String arr[] = lineFromFile.split(":");
					enq.id = arr[0];
					enq.titulo = arr[1];
					enq.nop = arr[2];
					enq.op1 = arr[3];
					enq.qtdOp1 = arr[4];
					enq.op2 = arr[5];
					enq.qtdOp2 = arr[6];
					enq.op3 = arr[7];
					enq.qtdOp3 = arr[8];
					enq.op4 = arr[9];
					enq.qtdOp4 = arr[10];
					enq.op5 = arr[11];
					enq.qtdOp5 = arr[12];
					enq.dtf = arr[13];
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate dateTime = LocalDate.parse(enq.dtf, formatter);
	
					int qtdVotos = Integer.parseInt(enq.qtdOp1) + Integer.parseInt(enq.qtdOp2) + Integer.parseInt(enq.qtdOp3) + Integer.parseInt(enq.qtdOp4) + Integer.parseInt(enq.qtdOp5);
					
					String titulo = enq.titulo;		
					row[0] = enq.id;
					row[1] = titulo;
					row[2] = qtdVotos;
					row[3] = enq.dtf;
										
					model.addRow(row);
				
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
				}
        }
		s.close();
		JLabel lblInformaesDoUsurio = new JLabel("Informa\u00E7\u00F5es do Usu\u00E1rio");
		lblInformaesDoUsurio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInformaesDoUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformaesDoUsurio.setBounds(189, 11, 188, 27);
		panel.add(lblInformaesDoUsurio);
		
		TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(250);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        // centralizando coluna do meio
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		
		JLabel label_5 = new JLabel("Usuario:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_5.setBounds(48, 59, 121, 37);
		panel.add(label_5);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				//JTableRow obj = new JTableRow();
			}
		});
		btnVoltar.setBounds(493, 11, 97, 50);
		panel.add(btnVoltar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(48, 130, 87, 27);
		panel.add(panel_2);
		
		JLabel label_7 = new JLabel("Historico");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(label_7);
		
		JLabel lblNome = new JLabel(user.nome);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(132, 59, 274, 37);
		panel.add(lblNome);
		
		JLabel lblNewLabel = new JLabel("Qtd de enquetes votadas:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(48, 92, 208, 27);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(Integer.toString(user.jaVotados.size()));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(266, 92, 152, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("                            Nome da Enquete                            Votos     Data de Expira\u00E7\u00E3o");
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(48, 156, 437, 19);
		panel.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(48, 156, 437, 19);
		panel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(48, 59, 437, 263);
		panel.add(panel_4);
		
		table.addMouseListener(new MouseAdapter(){
		@Override
        public void mouseClicked (MouseEvent e) {
        	
        	if (e.getClickCount() == 2) {  
        		String value = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
        		frame.setVisible(false);
                ResultadosEnquete rEnq = new ResultadosEnquete(Integer.parseInt(value), frameTable);
            }            
        }
		
		});
		
	}
}
