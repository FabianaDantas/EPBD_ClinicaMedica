package br.com.ep.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import br.com.ep.dao.DoencaDAO;
import br.com.ep.modelo.Doenca;

public class DoencasView extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel1;
	private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JTable table;
    
    //lbl
    private JLabel lblIdDoenca;
    private JLabel lblNomeDoenca;
    
    //txt
    private JTextField txtIDDoenca;
    private JTextField txtNomeDoenca;    
    
    //btn
    private JButton btnNovo;
    private JButton btnCadastrar;
    private JButton btnAtualizar;
    private JButton btnDeletar;

	private DoencaDAO dao = new DoencaDAO();

	/**
	 * Create the frame.
	 */
	public DoencasView() {
		 initComponents();
		 initButtons();
		 DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		 table.setRowSorter(new TableRowSorter<>(modelo));

	     readJTable();

	}
	 @SuppressWarnings("serial")
	private void initComponents() {

	        jPanel1 = new JPanel();
	        jScrollPane1 = new JScrollPane();
	        table = new JTable();

	        setClosable(true);
	        setMaximizable(false);
	        setResizable(true);
	        setTitle("Doenças");
			setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	        
	        jPanel2 = new JPanel();

	        GroupLayout layout = new GroupLayout(getContentPane());
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
	        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
	        );
	        
	        table.setModel(new DefaultTableModel(
	        	new Object[][] {
	        	},
	        	new String[] {
	        		"ID_DOENCA", "NOME"
	        	}
	        ){
	            boolean[] canEdit = new boolean [] {
	                    false, false, false
	                };

	                public boolean isCellEditable(int rowIndex, int columnIndex) {
	                    return canEdit [columnIndex];
	                }
	            });
	        
	        table.addMouseListener(new MouseAdapter() {
	        	public void mouseClicked(java.awt.event.MouseEvent evt) {
	            	  if(table.getSelectedRow() != -1){
	                      txtIDDoenca.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
	                      txtNomeDoenca.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
	  					  txtIDDoenca.setEnabled(false);
						  txtNomeDoenca.setEnabled(true);
						  btnCadastrar.setEnabled(false);
						  btnAtualizar.setEnabled(true);
						  btnDeletar.setEnabled(true);
	                  }
	            }
			});
	        table.addKeyListener(new KeyAdapter() {
	        	public void keyReleased(java.awt.event.KeyEvent evt) {
	            	if(table.getSelectedRow() != -1){
	                      txtIDDoenca.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
	                      txtNomeDoenca.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
	  					  txtIDDoenca.setEnabled(false);
						  txtNomeDoenca.setEnabled(true);
						  btnCadastrar.setEnabled(false);
						  btnAtualizar.setEnabled(true);
						  btnDeletar.setEnabled(true);
	                  }
	            }
			});

	        jScrollPane1.setViewportView(table);
	        
	        table.setBackground(Color.LIGHT_GRAY);
	        jPanel2.add(jScrollPane1);
	        jPanel1.setLayout(null);
	        
	        
	        /* ID DOENCA */
	        lblIdDoenca = new JLabel("ID");
	        lblIdDoenca.setBounds(141, 11, 46, 14);
	        jPanel1.add(lblIdDoenca);	        
	        txtIDDoenca = new JTextField();
	        txtIDDoenca.setEnabled(false);
	        txtIDDoenca.setBounds(141, 32, 86, 29);
	        jPanel1.add(txtIDDoenca);
	        txtIDDoenca.setColumns(10);
	        // Tratamento para aceitar somente numeros
	        txtIDDoenca.addKeyListener(new KeyAdapter() {
	        	public void keyTyped(KeyEvent evt) {
	        		String caracteres="0987654321";
	    	        if(!caracteres.contains(evt.getKeyChar()+"")){
	    	        evt.consume();
	    	        }
	            }
	        	
			});
	        
	        /* NOME DOENCA */
	        lblNomeDoenca = new JLabel("Nome Doen\u00E7a");
	        lblNomeDoenca.setBounds(297, 11, 147, 14);
	        jPanel1.add(lblNomeDoenca);	        
	        txtNomeDoenca = new JTextField();
	        txtNomeDoenca.setEnabled(false);
	        txtNomeDoenca.setColumns(10);
	        txtNomeDoenca.setBounds(297, 32, 201, 29);
	        jPanel1.add(txtNomeDoenca);

	        

	        getContentPane().setLayout(layout);
	        pack();
	    }

	 
     /*
      *  BOTÕES E AÇÕES 
      */	      
	 	public void initButtons() {
       
	        btnNovo = new JButton("+");
	        btnNovo.setBounds(565, 7, 46, 40);
	        jPanel1.add(btnNovo);
	        btnNovo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					limparCampos();
					txtIDDoenca.setEnabled(true);
					txtNomeDoenca.setEnabled(true);
					btnCadastrar.setEnabled(true);
					btnAtualizar.setEnabled(false);
					btnDeletar.setEnabled(false);
				}
			});
	 		
	 		btnCadastrar = new JButton("Cadastrar");
	 		btnCadastrar.setEnabled(false);
	        btnCadastrar.setBounds(138, 63, 89, 23);
	        jPanel1.add(btnCadastrar);
	        btnCadastrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Doenca d = new Doenca();
					if(txtIDDoenca.getText().equals("") || txtNomeDoenca.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Insira os dados");
					}else {
						d.setIdDoenca(Integer.parseInt(txtIDDoenca.getText()));
						d.setNome(txtNomeDoenca.getText());
						dao.create(d);
						limparCampos();
						readJTable();
						
						txtIDDoenca.setEnabled(false);
						txtNomeDoenca.setEnabled(false);
						btnCadastrar.setEnabled(false);
					}
				}
			});
	        
	        btnAtualizar = new JButton("Atualizar");
	        btnAtualizar.setEnabled(false);
	        btnAtualizar.setBounds(272, 63, 89, 23);
	        jPanel1.add(btnAtualizar);
	        btnAtualizar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(table.getSelectedRow() != -1){
						
						Doenca d = new Doenca();
						
						d.setIdDoenca(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
						d.setNome(txtNomeDoenca.getText());
						dao.update(d);
						limparCampos();
						readJTable();
						txtIDDoenca.setEnabled(false);
						txtNomeDoenca.setEnabled(false);
						btnAtualizar.setEnabled(false);
						btnDeletar.setEnabled(false);
		            }					
				}
			});
	        
	        btnDeletar = new JButton("Deletar");
	        btnDeletar.setEnabled(false);
	        btnDeletar.setBounds(400, 63, 89, 23);
	        jPanel1.add(btnDeletar);
	        btnDeletar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(table.getSelectedRow() != -1){
						Doenca d = new Doenca();
						
						d.setIdDoenca(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
						dao.delete(d);
						limparCampos();
						readJTable();
						btnAtualizar.setEnabled(false);
						btnDeletar.setEnabled(false);
						btnAtualizar.setEnabled(false);
						btnDeletar.setEnabled(false);
	                }else{
	                    JOptionPane.showMessageDialog(null,"Selecione um produto para Excluir");
	                }			
				}
			});
	 	}

	 	public void readJTable() {
	 		DefaultTableModel model = (DefaultTableModel) table.getModel();
	 		model.setNumRows(0);
	 		try {
		 		for(Doenca d: dao.findAll()) {
		 			model.addRow(new Object[] {
		 					d.getIdDoenca(),
		 					d.getNome()
		 			});
		 		}
	 		} catch(Exception e){
	 			JOptionPane.showMessageDialog(null, "Erro de conexão com o Banco de Dados");
	 		}
	 	}
	 	
	 	public void limparCampos() {
	 		txtIDDoenca.setText("");
	 		txtNomeDoenca.setText("");
	 	}
	 
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						DoencasView frame = new DoencasView();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}


	    
}
