package br.com.ep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.ep.factory.ConnectionFactory;
import br.com.ep.modelo.Medico;

public class MedicoDAO {

	public void create(Medico m) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("INSERT INTO medico (CRM, NOME, TELEFONE) VALUES (?, ?, ?)");
			stmt.setInt(1, m.getCrm());
			stmt.setString(2, m.getNome());
			stmt.setString(3, m.getTelefone());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

	}

	public List<Medico> findAll() {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Medico> listaMedicos = new ArrayList<>();

		try {

			stmt = con.prepareStatement("SELECT * FROM medico");

			rs = stmt.executeQuery();

			while (rs.next()) {
				Medico m = new Medico();
				m.setCrm(rs.getInt("crm"));
				m.setNome(rs.getString("nome"));
				m.setTelefone(rs.getString("telefone"));

				listaMedicos.add(m);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar: " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return listaMedicos;

	}

	public Medico find(int crm) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Medico medico = new Medico();

		try {

			stmt = con.prepareStatement("SELECT * FROM medico WHERE crm = ?");
			stmt.setInt(1, crm);
			rs = stmt.executeQuery();

			while (rs.next()) {
				medico.setCrm(rs.getInt("crm"));
				medico.setNome(rs.getString("nome"));
				medico.setTelefone(rs.getString("telefone"));
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar medico: " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return medico;

	}

	public void update(Medico m) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("UPDATE medico SET nome = ?, telefone = ? WHERE crm = ?");
			stmt.setString(1, m.getNome());
			stmt.setString(2, m.getTelefone());
			stmt.setInt(3, m.getCrm());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

	}

	public void delete(Medico m) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("DELETE FROM medico WHERE crm = ?");
			stmt.setInt(1, m.getCrm());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Excluído com Sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

	}
}
