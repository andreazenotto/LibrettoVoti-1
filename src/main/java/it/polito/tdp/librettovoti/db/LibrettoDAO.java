package it.polito.tdp.librettovoti.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.librettovoti.model.Voto;

public class LibrettoDAO {
	
	public boolean createVoto(Voto v) {		
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "INSERT INTO voti (`nome`, `punti`) VALUES (?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, v.getNome());
			st.setInt(2, v.getPunti());
			int res = st.executeUpdate();
			st.close();
			conn.close();
			return (res==1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Voto> readAllVoto() {
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "SELECT * FROM voti";			
			PreparedStatement st = conn.prepareStatement(sql);			
			ResultSet res = st.executeQuery();
			List<Voto> result = new LinkedList<>();
			while(res.next()) {
				String nome = res.getNString("nome");
				int punti = res.getInt("punti");
				result.add(new Voto(nome, punti));
			}			
			st.close();  
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Voto readVotoByName(String n) {
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "SELECT * FROM voti";			
			PreparedStatement st = conn.prepareStatement(sql);			
			ResultSet res = st.executeQuery();
			Voto voto = null;
			while(res.next()) {
				String nome = res.getNString("nome");
				if(nome.equals(n)) {
					int punti = res.getInt("punti");
					voto = new Voto(nome, punti);
				}
			}			
			st.close();  
			conn.close();
			return voto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
