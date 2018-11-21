package br.com.ep.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.text.ParseException;

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
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import br.com.ep.dao.MedicoDAO;
import br.com.ep.modelo.Medico;
import javax.swing.JFormattedTextField;

public class MedicoView extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	private JTable table;
	private MaskFormatter mascaraTelefone;

	// lbl
	private JLabel lblCRM;
	private JLabel lblNome;
	private JLabel lblTelefone;

	// txt
	private JTextField txtCRM;
	private JTextField txtNome;
	private JFormattedTextField txtTelefone;

	// btn
	private JButton btnNovo;
	private JButton btnCadastrar;
	private JButton btnAtualizar;
	private JButton btnDeletar;
	
	
	private MedicoDAO dao = new MedicoDAO();

	/**
	 * Create the frame.
	 */
	public MedicoView() {
		initComponents();
		initButtons();
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		table.setRowSorter(new TableRowSorter<>(modelo));

		readJTable();

	}

	@SuppressWarnings("serial")
	private void initComponents() {

		try {
			mascaraTelefone = new MaskFormatter("(##)#####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		jPanel1 = new JPanel();
		jScrollPane1 = new JScrollPane();
		table = new JTable();

		setClosable(true);
		setMaximizable(false);
		setResizable(true);
		setTitle("Medico");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

		jPanel2 = new JPanel();

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)));

		table.setModel(new DefaultTableModel(new Object[][] {}, 
				new String[] { "CRM", "NOME", "TELEFONE" }) {
			boolean[] canEdit = new boolean[] { false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (table.getSelectedRow() != -1) {
					String telefone = table.getValueAt(table.getSelectedRow(), 2).toString();
					telefone = telefone.replace("(", "");
					telefone = telefone.replace(")", "");
					telefone = telefone.replace("-", "");
					txtCRM.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					txtNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtTelefone.setText(telefone);
					txtCRM.setEnabled(false);
					txtNome.setEnabled(true);
					txtTelefone.setEnabled(true);
					btnCadastrar.setEnabled(false);
					btnAtualizar.setEnabled(true);
					btnDeletar.setEnabled(true);
				}
			}
		});
		table.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				if (table.getSelectedRow() != -1) {
					String telefone = table.getValueAt(table.getSelectedRow(), 2).toString();
					telefone = telefone.replace("(", "");
					telefone = telefone.replace(")", "");
					telefone = telefone.replace("-", "");
					txtCRM.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					txtNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtTelefone.setText(telefone);
					txtCRM.setEnabled(false);
					txtNome.setEnabled(true);
					txtTelefone.setEnabled(true);
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
		lblCRM = new JLabel("CRM");
		lblCRM.setBounds(39, 11, 46, 14);
		jPanel1.add(lblCRM);
		txtCRM = new JTextField();
		txtCRM.setEnabled(false);
		txtCRM.setBounds(39, 32, 86, 29);
		jPanel1.add(txtCRM);
		txtCRM.setColumns(10);
		// Tratamento para aceitar somente numeros
		txtCRM.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				String caracteres = "0987654321";
				if (!caracteres.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
			}

		});

		/* NOME DOENCA */
		lblNome = new JLabel("Nome Medico");
		lblNome.setBounds(159, 11, 147, 14);
		jPanel1.add(lblNome);
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		txtNome.setBounds(159, 32, 201, 29);
		jPanel1.add(txtNome);
		

		/* TELEFONE */
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(375, 11, 147, 14);
		jPanel1.add(lblTelefone);
		
		txtTelefone = new JFormattedTextField(mascaraTelefone);
		txtTelefone.setEnabled(false);
		txtTelefone.setBounds(385, 32, 152, 29);
		jPanel1.add(txtTelefone);
		

		getContentPane().setLayout(layout);
		pack();
	}

	/*
	 * BOTÕES E AÇÕES
	 */
	public void initButtons() {

		btnNovo = new JButton("+");
		btnNovo.setBounds(565, 7, 46, 40);
		jPanel1.add(btnNovo);
		btnNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				txtCRM.setEnabled(true);
				txtNome.setEnabled(true);
				txtTelefone.setEnabled(true);
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
				Medico m = new Medico();
				
				if (txtCRM.getText().equals("") || txtNome.getText().equals("") || txtTelefone.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira os dados");
				} else {
					m.setCrm(Integer.parseInt(txtCRM.getText()));
					m.setNome(txtNome.getText());
					m.setTelefone(txtTelefone.getText());
					dao.create(m);
					limparCampos();
					readJTable();

					txtCRM.setEnabled(false);
					txtNome.setEnabled(false);
					txtTelefone.setEnabled(false);
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
				if (table.getSelectedRow() != -1) {

					Medico m = new Medico();

					m.setCrm(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					m.setNome(txtNome.getText());
					m.setTelefone(txtTelefone.getText());
					dao.update(m);
					limparCampos();
					readJTable();
					txtCRM.setEnabled(false);
					txtNome.setEnabled(false);
					txtTelefone.setEnabled(false);
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
				if (table.getSelectedRow() != -1) {
					Medico m = new Medico();

					m.setCrm(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					dao.delete(m);
					limparCampos();
					readJTable();
					btnAtualizar.setEnabled(false);
					btnDeletar.setEnabled(false);
					btnAtualizar.setEnabled(false);
					btnDeletar.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um produto para Excluir");
				}
			}
		});
	}

	public void readJTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		try {
			for (Medico m : dao.findAll()) {
				model.addRow(new Object[] { m.getCrm(), m.getNome(), m.getTelefone()});
			}
		} catch(Exception e){
 			JOptionPane.showMessageDialog(null, "Erro de conexão com o Banco de Dados");
 		}
	}

	public void limparCampos() {
		txtCRM.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicoView frame = new MedicoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
