package br.com.ep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.ep.factory.ConnectionFactory;
import br.com.ep.modelo.Doenca;

public class DoencaDAO {
	
	public void create(Doenca d) {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("INSERT INTO doenca (IDDOENCA, NOME) VALUES (?, ?)");
			stmt.setInt(1, d.getIdDoenca());
			stmt.setString(2, d.getNome());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar: " +  e);			
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}
	
	public List<Doenca> findAll() {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Doenca> listaDoencas = new ArrayList<>();
		
		try {
			
			stmt = con.prepareStatement("SELECT * FROM doenca");
					
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Doenca d = new Doenca();
				d.setIdDoenca(rs.getInt("iddoenca"));
				d.setNome(rs.getString("nome"));
				
				listaDoencas.add(d);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar: " +  e);			
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return listaDoencas;
		
	}
	
	public void update(Doenca d) {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("UPDATE doenca SET nome = ? WHERE iddoenca = ?");
			stmt.setString(1, d.getNome());
			stmt.setInt(2, d.getIdDoenca());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar: " +  e);			
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}
	
	public void delete(Doenca d) {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("DELETE FROM doenca WHERE iddoenca = ?");
			stmt.setInt(1, d.getIdDoenca());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Excluído com Sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir: " +  e);			
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}

}
