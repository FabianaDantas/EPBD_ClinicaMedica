package br.com.ep.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ClinicaMedica extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private DoencasView viewDoencas;
	private PacienteView viewPaciente;
	private MedicoView viewMedico;
	private AgendaView viewAgenda;
	
	private JFrame frmClinicaMedica;
	private javax.swing.JDesktopPane jDesktopPane1;
	JMenuBar menuBar = new JMenuBar();
	JMenu mnPrincipal = new JMenu("Principal");
	JMenuItem mntmSair = new JMenuItem("Sair");
	JMenu mnDoencas = new JMenu("Doen\u00E7as");
	JMenu mnEspecialidades = new JMenu("Especialidades");
	JMenu mnMedicos = new JMenu("Medicos");
	JMenu mnPacientes = new JMenu("Pacientes");
	
	private final JMenuItem mntmAbrirDoencas = new JMenuItem("Abrir");
	private final JMenuItem mntmAbrirPacientes = new JMenuItem("Abrir");
	private final JMenuItem mntmAbrirMedicos = new JMenuItem("Abrir");
	private final JMenuItem mntmAgenda = new JMenuItem("Agenda");


	/**
	 * Create the application.
	 */
	public ClinicaMedica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		this.configFrame();

        jDesktopPane1 = new javax.swing.JDesktopPane();
		
		frmClinicaMedica.setJMenuBar(menuBar);
		frmClinicaMedica.setContentPane(jDesktopPane1);
		
		menuBar.add(mnPrincipal);		
			mnPrincipal.add(mntmSair);		
		menuBar.add(mnDoencas);
		
		mnDoencas.add(mntmAbrirDoencas);
		mntmAbrirDoencas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (viewDoencas == null) {
					viewDoencas = new DoencasView();
		        }
	            jDesktopPane1.add(viewDoencas);
				viewDoencas.setVisible(true);
				
			}
		});
		menuBar.add(mnEspecialidades);		
		menuBar.add(mnMedicos);	
		mnMedicos.add(mntmAbrirMedicos);
		mntmAbrirMedicos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (viewMedico == null) {
					viewMedico = new MedicoView();
		        }
	            jDesktopPane1.add(viewMedico);
	            viewMedico.setVisible(true);
				
			}
		});
		mnMedicos.add(mntmAgenda);
		mntmAgenda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (viewAgenda == null) {
					viewAgenda = new AgendaView();
		        }
	            jDesktopPane1.add(viewAgenda);
	            viewAgenda.setVisible(true);
				
			}
		});
		menuBar.add(mnPacientes);		
		mnPacientes.add(mntmAbrirPacientes);
		mntmAbrirPacientes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (viewPaciente == null) {
					viewPaciente = new PacienteView();
		        }
	            jDesktopPane1.add(viewPaciente);
				viewPaciente.setVisible(true);
				
			}
		});
		
	}
	

	private void configFrame() {
		frmClinicaMedica = new JFrame();
		frmClinicaMedica.setTitle("Clinica Medica");
		frmClinicaMedica.setBounds(100, 100, 704, 384);
		frmClinicaMedica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClinicaMedica.getContentPane().setLayout(null);
		frmClinicaMedica.setLocationRelativeTo(null);		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        	System.err.println(ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClinicaMedica window = new ClinicaMedica();
					window.frmClinicaMedica.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
