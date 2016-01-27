package edu.upc.escert.curs.repositori.parametric;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.upc.escert.curs.Comentari;
import edu.upc.escert.curs.repositori.IRepositoriComentaris;
import edu.upc.escert.curs.repositori.Repositori;

public class RepositoriComentaris extends Repositori implements IRepositoriComentaris {
	
	@Override
	protected void crear() {
		executaSQL("create table IF NOT EXISTS comentaris ("+
				"id int auto_increment, "+
				"autor varchar(100), "+
				"titol varchar(200), "+
				"comentari varchar2(2000), "+
				"data date default CURRENT_TIMESTAMP )");
		if (getComentaris().isEmpty()) {
			afegirComentari(new Comentari(
					"jaume","Benvinguts, comenteu!",
					"Aquesta aplicació està feta <b>expressament</b> amb vulnerabilitats."+
					"La idea es que al final del curs tinguem una versió <i>millorada</i>."
			));
		}
	}
	
	private List<Comentari> getComentarisFromSQL(String sql, Pair[] params) {
		List<Comentari> comentaris=new ArrayList<Comentari>();
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; ++i) {
				switch(params[i].first) {
					case "Integer":
						stmt.setInt(i+1, Integer.valueOf(params[i].second));
						break;
					case "String":
						stmt.setString(i+1, params[i].second);
					default:
						break;
				}
			}
			rs=stmt.executeQuery();
			while (rs.next()) {
				Comentari c=new Comentari();
				c.setId(rs.getInt("id"));
				c.setAutor(rs.getString("autor"));
				c.setTitol(rs.getString("titol"));
				c.setComentari(rs.getString("comentari"));
				c.setData(rs.getDate("data"));
				comentaris.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {stmt.close();} catch (Exception e2) {;}
			try {rs.close();} catch (Exception e3) {;}
		}
		return comentaris;
	}
	
	/**
	 * Parametric
	 */
	@Override
	public List<Comentari> getComentaris() {
		Pair[] params = {};
		return getComentarisFromSQL("SELECT * FROM COMENTARIS", params);
	}

	/**
	 * Parametric
	 */
	@Override
	public Comentari getComentariPerId(int id) {
		Pair[] params = {new Pair("Integer", String.valueOf(id))};
		return getComentarisFromSQL("SELECT * FROM COMENTARIS WHERE ID=?", params).get(0);
	}

	/**
	 * Parametric
	 */
	@Override
	public List<Comentari> getComentarisPerAutor(String autor) {
		Pair[] params = {new Pair("String", autor)};
		return getComentarisFromSQL("SELECT * FROM COMENTARIS WHERE AUTOR=?", params);
	}

	@Override
	public void afegirComentari(Comentari c) {
		executaSQL("INSERT INTO COMENTARIS (autor,titol,comentari,data) VALUES ('" +
				c.getAutor() + "','" + c.getTitol() + "','" + c.getComentari() + "',CURRENT_TIMESTAMP())");
	}

	@Override
	public void esborrarComentari(int id) {
		executaSQL("DELETE COMENTARIS WHERE ID="+id);
	}
	
	public class Pair {
		
		String first;
		String second;
		
		public Pair(String first, String second) {
			this.first = first;
			this.second = second;
		}
		
	}

}
