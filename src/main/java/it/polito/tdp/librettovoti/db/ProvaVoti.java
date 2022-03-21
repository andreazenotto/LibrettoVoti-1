package it.polito.tdp.librettovoti.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvaVoti {
	
	public void aggiungiVoto(String nome, int punti) {
		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=rootpassword";
		try {
			Connection conn = DriverManager.getConnection(url);
			String sql = "INSERT INTO voti (`nome`, `punti`) VALUES (?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nome);
			st.setInt(2, punti);
			int res = st.executeUpdate();
			st.close();
			if(res==1) {
				System.out.println("Dato correttamente inserito");
			}
			conn.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		ProvaVoti provaVoti = new ProvaVoti();
		provaVoti.aggiungiVoto("Economia aziendale", 25);

		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=rootpassword";
		
		try {
			Connection conn = DriverManager.getConnection(url);			
			String sql = "SELECT * FROM voti";			
			PreparedStatement st = conn.prepareStatement(sql);			
			ResultSet res = st.executeQuery();
			while(res.next()) {
				String nome = res.getNString("nome");
				int punti = res.getInt("punti");
				System.out.println(nome+" "+punti);
			}			
			st.close();  
			conn.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
