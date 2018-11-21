package br.com.ep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.ep.factory.ConnectionFactory;
import br.com.ep.modelo.Agenda;

public class AgendaDAO {

	public void create(Agenda a) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("INSERT INTO agenda (idagenda, diasemana, horainicio, horafim, idmedico) VALUES (?, ?, ?, ?, ?)");
			stmt.setInt(1, a.getIdagenda());
			stmt.setString(2, a.getDiaDaSemana());
			stmt.setString(3, a.getHoraInicio());
			stmt.setString(4, a.getHoraFim());
			stmt.setInt(5, a.getMedico().getCrm());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

	}
	
	
	public List<Agenda> findAll() {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		MedicoDAO daomed = new MedicoDAO();
		
		List<Agenda> lista = new ArrayList<>();

		try {

			stmt = con.prepareStatement("SELECT * FROM agenda");

			rs = stmt.executeQuery();

			while (rs.next()) {
				Agenda m = new Agenda();
				m.setIdagenda(rs.getInt("idagenda"));
				m.setDiaDaSemana(rs.getString("diasemana"));
				m.setHoraInicio(rs.getString("horainicio"));
				m.setHoraFim(rs.getString("horafim"));
				m.setMedico(daomed.find(rs.getInt("idmedico")));

				lista.add(m);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar: " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return lista;

	}

	public void update(Agenda a) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("UPDATE agenda SET diasemana = ?, horainicio = ?, horafim = ?, idmedico = ? WHERE idagenda = ?");
			stmt.setString(1, a.getDiaDaSemana());
			stmt.setString(2, a.getHoraInicio());
			stmt.setString(3, a.getHoraFim());
			stmt.setInt(4, a.getMedico().getCrm());
			stmt.setInt(5, a.getIdagenda());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

	}
	public void delete(Agenda a) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("DELETE FROM agenda WHERE idagenda = ?");
			stmt.setInt(1, a.getIdagenda());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Excluído com Sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

	}

}
