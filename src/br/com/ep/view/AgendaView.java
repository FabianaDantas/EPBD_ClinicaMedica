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

import br.com.ep.dao.AgendaDAO;
import br.com.ep.dao.MedicoDAO;
import br.com.ep.modelo.Agenda;
import br.com.ep.modelo.Medico;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

public class AgendaView extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	private JTable table;
	private MaskFormatter mascaraHora;

	private final String[] diasDaSemana = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado", "Domingo" };

	// lbl
	private JLabel lblIdDoenca;
	private JLabel lblDiaDaSemana;
	private JLabel lblMedico;
	private JLabel lblHoraInicio;
	private JLabel lblHoraFim;

	// txt
	private JTextField txtIDAgenda;
	private JFormattedTextField txtHoraFim;
	private JFormattedTextField txtHoraInicio;

	// btn
	private JButton btnNovo;
	private JButton btnCadastrar;
	private JButton btnAtualizar;
	private JButton btnDeletar;

	// cbx
	private JComboBox<Medico> cbMedico;
	private JComboBox<String> cbDiaDaSemana;

	private AgendaDAO dao = new AgendaDAO();
	private MedicoDAO medDao = new MedicoDAO();

	/**
	 * Create the frame.
	 */
	public AgendaView() {
		initComponents();
		initButtons();
		initComboBox();
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		table.setRowSorter(new TableRowSorter<>(modelo));

		readJTable();
	}

	@SuppressWarnings("serial")
	private void initComponents() {
		try {
			mascaraHora = new MaskFormatter("##:##:##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jPanel1 = new JPanel();
		jScrollPane1 = new JScrollPane();
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		setClosable(true);
		setMaximizable(false);
		setResizable(true);
		setTitle("Agenda dos Médicos");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

		jPanel2 = new JPanel();

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 183, Short.MAX_VALUE)));

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID_AGENDA", "DIA DA SEMANA", "HORA INICIO", "HORA FIM", "MEDICO" }) {
			boolean[] canEdit = new boolean[] { false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (table.getSelectedRow() != -1) {
					txtIDAgenda.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					cbDiaDaSemana.setSelectedItem(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtHoraInicio.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					txtHoraFim.setText(table.getValueAt(table.getSelectedRow(), 3).toString().replace(":", ""));
					cbMedico.setSelectedItem(table.getValueAt(table.getSelectedRow(), 4));
					
					txtIDAgenda.setEnabled(false);
					cbDiaDaSemana.setEnabled(true);
					cbMedico.setEnabled(true);
					txtHoraInicio.setEnabled(true);
					txtHoraFim.setEnabled(true);
					
					btnCadastrar.setEnabled(false);
					btnAtualizar.setEnabled(true);
					btnDeletar.setEnabled(true);
				}
			}
		});
		table.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				if (table.getSelectedRow() != -1) {
					txtIDAgenda.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					cbDiaDaSemana.setSelectedItem(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtHoraInicio.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					txtHoraFim.setText(table.getValueAt(table.getSelectedRow(), 3).toString().replace(":", ""));
					cbMedico.setSelectedItem(table.getValueAt(table.getSelectedRow(), 4));

					txtIDAgenda.setEnabled(false);
					cbDiaDaSemana.setEnabled(true);
					cbMedico.setEnabled(true);
					txtHoraInicio.setEnabled(true);
					txtHoraFim.setEnabled(true);
					
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

		/* ID */
		lblIdDoenca = new JLabel("ID");
		lblIdDoenca.setBounds(62, 11, 46, 14);
		jPanel1.add(lblIdDoenca);
		txtIDAgenda = new JTextField();
		txtIDAgenda.setEnabled(false);
		txtIDAgenda.setBounds(62, 32, 86, 29);
		jPanel1.add(txtIDAgenda);
		txtIDAgenda.setColumns(10);
		// Tratamento para aceitar somente numeros
		txtIDAgenda.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				String caracteres = "0987654321";
				if (!caracteres.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
			}

		});

		/* DIA DA SEMANA */
		lblDiaDaSemana = new JLabel("Dia da Semana");
		lblDiaDaSemana.setBounds(171, 11, 147, 14);
		jPanel1.add(lblDiaDaSemana);
		cbDiaDaSemana = new JComboBox<String>();
		cbDiaDaSemana.setEnabled(false);
		cbDiaDaSemana.setBounds(171, 32, 155, 29);
		jPanel1.add(cbDiaDaSemana);

		/* MEDICO */
		lblMedico = new JLabel("Medico");
		lblMedico.setBounds(349, 11, 46, 14);
		jPanel1.add(lblMedico);
		cbMedico = new JComboBox<Medico>();
		cbMedico.setEnabled(false);
		cbMedico.setBounds(349, 32, 173, 29);
		jPanel1.add(cbMedico);

		/* HORA INICIO */
		lblHoraInicio = new JLabel("Hora Inicio");
		lblHoraInicio.setBounds(187, 70, 86, 14);
		jPanel1.add(lblHoraInicio);
		txtHoraInicio = new JFormattedTextField(mascaraHora);
		txtHoraInicio.setEnabled(false);
		txtHoraInicio.setText("000000");
		txtHoraInicio.setBounds(187, 87, 107, 29);
		jPanel1.add(txtHoraInicio);

		/* HORA FIM */
		lblHoraFim = new JLabel("Hora Fim");
		lblHoraFim.setBounds(322, 70, 86, 14);
		jPanel1.add(lblHoraFim);
		txtHoraFim = new JFormattedTextField(mascaraHora);
		txtHoraFim.setEnabled(false);
		txtHoraFim.setText("000000");
		txtHoraFim.setBounds(322, 87, 107, 29);
		jPanel1.add(txtHoraFim);

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

				txtIDAgenda.setEnabled(true);
				cbDiaDaSemana.setEnabled(true);
				cbMedico.setEnabled(true);
				txtHoraInicio.setEnabled(true);
				txtHoraFim.setEnabled(true);

				btnCadastrar.setEnabled(true);
				btnAtualizar.setEnabled(false);
				btnDeletar.setEnabled(false);
			}
		});

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setEnabled(false);
		btnCadastrar.setBounds(134, 140, 89, 23);
		jPanel1.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Agenda a = new Agenda();

				if (txtIDAgenda.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira os dados");
				} else {
					a.setIdagenda(Integer.parseInt(txtIDAgenda.getText()));
					a.setDiaDaSemana(cbDiaDaSemana.getSelectedItem().toString());
					a.setHoraInicio(txtHoraInicio.getText());
					a.setHoraFim(txtHoraFim.getText());
					a.setMedico((Medico) cbMedico.getSelectedItem());
					dao.create(a);
					limparCampos();
					readJTable();

					txtIDAgenda.setEnabled(false);
					cbDiaDaSemana.setEnabled(false);
					cbMedico.setEnabled(false);
					txtHoraInicio.setEnabled(false);
					txtHoraFim.setEnabled(false);
					btnCadastrar.setEnabled(false);
				}
			}
		});

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setEnabled(false);
		btnAtualizar.setBounds(268, 140, 89, 23);
		jPanel1.add(btnAtualizar);
		btnAtualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {

					Agenda a = new Agenda();

					a.setIdagenda(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					a.setDiaDaSemana(cbDiaDaSemana.getSelectedItem().toString());
					a.setHoraInicio(txtHoraInicio.getText());
					a.setHoraFim(txtHoraFim.getText());
					a.setMedico((Medico) cbMedico.getSelectedItem());
					dao.update(a);
					limparCampos();
					readJTable();

					txtIDAgenda.setEnabled(false);
					cbDiaDaSemana.setEnabled(false);
					cbMedico.setEnabled(false);
					txtHoraInicio.setEnabled(false);
					txtHoraFim.setEnabled(false);

					btnAtualizar.setEnabled(false);
					btnDeletar.setEnabled(false);
				}
			}
		});

		btnDeletar = new JButton("Deletar");
		btnDeletar.setEnabled(false);
		btnDeletar.setBounds(396, 140, 89, 23);
		jPanel1.add(btnDeletar);
		btnDeletar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					Agenda a = new Agenda();

					a.setIdagenda(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					dao.delete(a);
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

			for (Agenda a : dao.findAll()) {
				model.addRow(new Object[] { a.getIdagenda(), a.getDiaDaSemana(), a.getHoraInicio(), a.getHoraFim(),
						a.getMedico()});
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro de conexão com o Banco de Dados");
		}
	}

	public void initComboBox() {
		for (Medico m : medDao.findAll()) {
			cbMedico.addItem(m);
		}
		for (String d : diasDaSemana) {
			cbDiaDaSemana.addItem(d);
		}
	}

	public void limparCampos() {
		txtIDAgenda.setText("");
		txtHoraFim.setText("000000");
		txtHoraInicio.setText("000000");
		cbDiaDaSemana.setSelectedIndex(0);
		cbMedico.setSelectedIndex(0);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendaView frame = new AgendaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
