package br.com.ufba.votacao.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class JTableRow {
	
	 public JFrame frame = new JFrame();
     JTable table = new JTable(); 
     private int qtdEnq;
     private ArrayList<String> exp;
     
     public JTableRow() {
    	// System.out.println(TelaInicial.userID);
    	 initialize();
     }
     

    public static void main(String[] args){    
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTableRow window = new JTableRow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    	
    }	
    	
   private void initialize() {
	   frame.getContentPane().setBackground(UIManager.getColor("activeCaption"));
	   frame.setVisible(true);
	   frame.setSize(800,600);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
	   
        // create a table model and set a Column Identifiers to this model 
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
        /*JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(241, 94, 108, 44);
        btnDelete.setBounds(372, 94, 108, 44);*/
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 100, 772, 458);
        
        frame.getContentPane().setLayout(null);
        
        // colocando as rows na JTable
        ArrayList<TipoEnquete> validos = new ArrayList<TipoEnquete>();
	    ArrayList<TipoEnquete> expirados = new ArrayList<TipoEnquete>();
	    exp = new ArrayList<String>();
        
        // create an array of objects to set the row data
        Object[] row = new Object[4];
        try {
			
        	qtdEnq = 0;
        	
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    		LocalDate localDate = LocalDate.now();
    	   
        	
			while (true) {
				
				Scanner scanner = new Scanner(new File("Enquete" + qtdEnq));
				
				String lineFromFile = scanner.nextLine();
				TipoEnquete enq = new TipoEnquete();

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
				
				if(!localDate.isAfter(dateTime)) {
					//System.out.println(enq.titulo);
					validos.add(enq);					
				} else {
					expirados.add(enq);
					exp.add(enq.id);
				}
				
				qtdEnq++;
			}	
			
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
		}
        
       /* 
       for(int i = 0; i < validos.size()-1; i++) {
    	   for(int j = 0; j < validos.size()-i-1; j++) {
    		   if (!((validos.get(j+1).toLocDat()).isAfter(validos.get(j).toLocDat())))
    			   Collections.swap(validos, j, j+1);
    	   }
       }
       */
        
        Collections.sort(validos);
        
        for(int i = 0; i < validos.size(); i++) {
			
			TipoEnquete enq = validos.get(i);

			int qtdVotos = Integer.parseInt(enq.qtdOp1) + Integer.parseInt(enq.qtdOp2) + Integer.parseInt(enq.qtdOp3) + Integer.parseInt(enq.qtdOp4) + Integer.parseInt(enq.qtdOp5);
			
			String titulo = enq.titulo;		
			//System.out.println(titulo);
			
			Scanner s = null;
			try {
				s = new Scanner(new File("User" + TelaInicial.userID));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
			String x = s.nextLine();
			
	        TipoUser user = new TipoUser(x);
			
	        row[0] = enq.id;
	        if(user.jaVotados.contains(enq.id)) {
	        	row[1] = "(Votado) " + titulo;
	        } else {
	        	row[1] = titulo;
	        }
			row[2] = qtdVotos;
			row[3] = enq.dtf;
								
			model.addRow(row);
		
		}
		
		for(int i = 0; i < expirados.size(); i++) {
			
			TipoEnquete enq = expirados.get(i);

			int qtdVotos = Integer.parseInt(enq.qtdOp1) + Integer.parseInt(enq.qtdOp2) + Integer.parseInt(enq.qtdOp3) + Integer.parseInt(enq.qtdOp4) + Integer.parseInt(enq.qtdOp5);
			
			
			String titulo = enq.titulo;	
			Scanner s = null;
			try {
				s = new Scanner(new File("User" + TelaInicial.userID));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
			String x = s.nextLine();
			
	        TipoUser user = new TipoUser(x);
			
	        row[0] = enq.id;
	        if(user.jaVotados.contains(enq.id)) {
	        	row[1] = "(Votado) " + titulo;
	        } else {
	        	row[1] = titulo;
	        }
			row[2] = qtdVotos;
			row[3] = "Expirado";
								
			model.addRow(row);
		
		}
             
        // Definindo tamanho das colunas
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(125);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        // centralizando coluna do meio
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
     
        
        frame.getContentPane().add(pane);
        /*frame.getContentPane().add(btnDelete);
        frame.getContentPane().add(btnUpdate);*/
        
        JPanel panel = new JPanel();
        panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(192, 192, 192)));
        panel.setBackground(new Color(30, 144, 255));
        panel.setBounds(367, -11, 440, 112);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JButton btnNewButton_1 = new JButton("Logout");
        btnNewButton_1.setBounds(240, 36, 157, 51);
        panel.add(btnNewButton_1);
        
        JButton btnNewButton = new JButton("Informa\u00E7\u00F5es do Usu\u00E1rio");
        btnNewButton.setBounds(30, 36, 177, 51);
        panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Historico hist = new Historico(frame);
        	}
        });
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.setVisible(false);
        		TelaInicial obj = new TelaInicial();
        	}
        });
        
        //BOTAO ADDENQUETE
        JButton btnAdicionarEnquete = new JButton("Adicionar Enquete");
        btnAdicionarEnquete.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdicionarEnquete.setBounds(129, 23, 208, 51);
        frame.getContentPane().add(btnAdicionarEnquete);
        
        JButton btnNewButton_2 = new JButton("Atualizar");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		frame.setVisible(false);
        		JTableRow ob3 = new JTableRow();
        	}
        });
        btnNewButton_2.setBounds(10, 24, 91, 51);
        frame.getContentPane().add(btnNewButton_2);
        btnAdicionarEnquete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// panel_1.setVisible(false);
        		// panel.setVisible(true);
        		// frame.setVisible(false);
				AdicionarEnquete obj2 = new AdicionarEnquete(qtdEnq, frame);
        	}
        });
        
        
        
        // button remove row
        /*btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });*/
        
        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
        	
        	if (e.getClickCount() == 2) {  
        		// i = the index of the selected row
                //int i = table.getSelectedRow();
        		String value = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
        	
    			//System.out.println(titulo);
    			
    			Scanner s = null;
    			try {
    				s = new Scanner(new File("User" + TelaInicial.userID));
    			} catch (FileNotFoundException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    	        
    			String x = s.nextLine();
    			
    	        TipoUser user = new TipoUser(x);
        		
    	        if(user.jaVotados.contains(value) || exp.contains(value)) {
    	        	ResultadosEnquete obj = new ResultadosEnquete(Integer.parseInt(value), frame);
    	        } else {
    	        	TelaEnquete enq = new TelaEnquete((Integer.parseInt(value)), frame);
    	        }
                //System.out.println("Linha " + table.getSelectedRow());
            }            
        }
        });
        
        
        
    	
        
    }
}