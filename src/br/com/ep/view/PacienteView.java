package br.com.ep.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;

public class PacienteView extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel jPanel1;
	private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JTable table;
    
    //lbl
    private JLabel lblID;
    private JLabel lblCPF;
    private JLabel lblNome;
    private JLabel lblTelefone;
    private JLabel lblEndereco;
    private JLabel lblIdade;
    private JLabel lblSexo;
    
    // txt
    private JTextField txtID;
    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextField txtSexo;
    private JTextField txtEndereco;
    private JFormattedTextField txtCPF;
    private JFormattedTextField txtTelefone;
    
    //bt
    private JButton btnCadastrar;
    private JButton btnAtualizar;
    private JButton btnDeletar;
    
	/**
	 * Create the frame.
	 */
	public PacienteView() {
		 initComponents();

	}
	 @SuppressWarnings("serial")
	private void initComponents() {

	        jPanel1 = new javax.swing.JPanel();
	        jScrollPane1 = new JScrollPane();
	        table = new JTable();
	        jPanel2 = new JPanel();

	        setClosable(true);
	        setMaximizable(false);
	        setResizable(true);
	        setTitle("Paciente");
			setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	       
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.TRAILING)
	        		.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
	        		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
	        			.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
	        			.addContainerGap())
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
	        );
	        
	        
	        /*
	         *  TABELA E AÇÕES 
	         */	        
	        table.setModel(new DefaultTableModel(
	        	new Object[][] {
	        	},
	        	new String[] {
	        		"ID_PACIENTE", "CPF", "NOME", "TELEFONE", "ENDERECO", "IDADE", "SEXO"
	        	}
	        ){
	            boolean[] canEdit = new boolean [] {
	                    false, false, false
	                };

	                public boolean isCellEditable(int rowIndex, int columnIndex) {
	                    return canEdit [columnIndex];
	                }
	            });
	        
	        table.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	            	  if(table.getSelectedRow() != -1){
	                      txtID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
	                      txtCPF.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
	                      txtNome.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
	                      txtTelefone.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
	                      txtEndereco.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
	                      txtIdade.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
	                      txtSexo.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
	                  }
	            }
	        });
	        table.addKeyListener(new java.awt.event.KeyAdapter() {
	            public void keyReleased(java.awt.event.KeyEvent evt) {
	            	if(table.getSelectedRow() != -1){
	            		txtID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
	                      txtCPF.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
	                      txtNome.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
	                      txtTelefone.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
	                      txtEndereco.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
	                      txtIdade.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
	                      txtSexo.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
	                  }
	            }
	        });

	        jScrollPane1.setViewportView(table);
	        
	        table.setBackground(Color.LIGHT_GRAY);
	        jPanel2.add(jScrollPane1);
	        jPanel1.setLayout(null);
	        
	        
	        /* ID */
	        lblID = new JLabel("ID*");
	        lblID.setBounds(34, 15, 46, 14);
	        jPanel1.add(lblID);
	        
	        txtID = new JTextField();
	        txtID.setBounds(33, 36, 86, 29);
	        jPanel1.add(txtID);
	        txtID.setColumns(10);
	        
	        /* CPF */	        
	        lblCPF = new JLabel("CPF*");
	        lblCPF.setBounds(135, 15, 147, 14);
	        jPanel1.add(lblCPF);
	        
	        txtCPF = new JFormattedTextField();
	        txtCPF.setBounds(129, 36, 140, 29);
	        jPanel1.add(txtCPF);
	        
	        /* NOME DO PACIENTE */
	        lblNome = new JLabel("Nome do Paciente*");
	        lblNome.setBounds(279, 15, 147, 14);
	        jPanel1.add(lblNome);
	        
	        txtNome = new JTextField();
	        txtNome.setColumns(10);
	        txtNome.setBounds(279, 36, 171, 29);
	        jPanel1.add(txtNome);	        
	        
	        /* TELEFONE */
	        
	        txtTelefone = new JFormattedTextField();
	        txtTelefone.setBounds(460, 36, 140, 29);
	        jPanel1.add(txtTelefone);
	        
	        lblTelefone = new JLabel("Telefone*");
	        lblTelefone.setBounds(460, 15, 147, 14);
	        jPanel1.add(lblTelefone);
	        
	        /* ENDEREÇO */
	        lblEndereco = new JLabel("Endere\u00E7o");
	        lblEndereco.setBounds(73, 86, 147, 14);
	        jPanel1.add(lblEndereco);
	        
	        txtEndereco = new JTextField();
	        txtEndereco.setBounds(73, 107, 140, 29);
	        jPanel1.add(txtEndereco);
	        
	        
	        /* IDADE */
	        lblIdade = new JLabel("Idade");
	        lblIdade.setBounds(223, 86, 147, 14);
	        jPanel1.add(lblIdade);

	        txtIdade = new JTextField();
	        txtIdade.setColumns(10);
	        txtIdade.setBounds(223, 107, 171, 29);
	        jPanel1.add(txtIdade);
	        
	        /* SEXO */
	        lblSexo = new JLabel("Sexo");
	        lblSexo.setBounds(404, 86, 147, 14);
	        jPanel1.add(lblSexo);
	        
	        txtSexo = new JTextField();
	        txtSexo.setBounds(404, 107, 140, 29);
	        jPanel1.add(txtSexo);
	        
	        
	        
	        
	        
	        /*
	         *  BOTÕES E AÇÕES 
	         */	        
	        btnCadastrar = new JButton("Cadastrar");
	        btnCadastrar.setBounds(146, 185, 89, 23);
	        jPanel1.add(btnCadastrar);
	        btnCadastrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					DefaultTableModel dtmProdutos = (DefaultTableModel) table.getModel();
	                Object[] dados = {txtID.getText(),txtCPF.getText(), txtNome.getText(), txtTelefone.getText(), txtEndereco.getText(),
	                		txtIdade.getText(),	txtSexo.getText()};
	                dtmProdutos.addRow(dados);		
	                limparCampos();
				}
			});
	        
	        btnAtualizar = new JButton("Atualizar");
	        btnAtualizar.setBounds(280, 185, 89, 23);
	        jPanel1.add(btnAtualizar);
	        btnAtualizar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(table.getSelectedRow() != -1){
		        		table.setValueAt(txtID.getText(), table.getSelectedRow(), 0);
		        		table.setValueAt(txtCPF.getText(), table.getSelectedRow(), 1);	
		        		table.setValueAt(txtNome.getText(), table.getSelectedRow(), 2);	
		        		table.setValueAt(txtTelefone.getText(), table.getSelectedRow(), 3);	
		        		table.setValueAt(txtEndereco.getText(), table.getSelectedRow(), 4);	
		        		table.setValueAt(txtIdade.getText(), table.getSelectedRow(), 5);		
		        		table.setValueAt(txtSexo.getText(), table.getSelectedRow(), 5);		
		                limparCampos();
		            }					
				}
			});
	        
	        btnDeletar = new JButton("Deletar");
	        btnDeletar.setBounds(408, 185, 89, 23);
	        jPanel1.add(btnDeletar);
	        btnDeletar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(table.getSelectedRow() != -1){
	                    DefaultTableModel dtmProdutos = (DefaultTableModel) table.getModel();
	                    dtmProdutos.removeRow(table.getSelectedRow());		
		                limparCampos();
	                }else{
	                    JOptionPane.showMessageDialog(null,"Selecione um produto para Excluir");
	                }			
				}
			});

	        getContentPane().setLayout(layout);
	        pack();
	    }

	 private void limparCampos() {
		 txtID.setText("");
		 txtCPF.setText("");
		 txtNome.setText("");
		 txtTelefone.setText("");
		 txtEndereco.setText("");
		 txtIdade.setText("");
		 txtSexo.setText("");
	 }
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacienteView frame = new PacienteView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
