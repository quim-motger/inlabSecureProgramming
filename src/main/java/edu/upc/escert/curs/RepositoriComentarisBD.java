package edu.upc.escert.curs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RepositoriComentarisBD implements RepositoriComentaris {

	static RepositoriComentaris instance;

	public static RepositoriComentaris getInstance() throws NamingException {
		if (instance == null) {
			instance = new RepositoriComentarisBD();
		}
		return instance;
	}

	DataSource ds;

	public RepositoriComentarisBD() throws NamingException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("jdbc/escert");
	}

	@Override
	public List<Comentari> getComentaris() {
		List<Comentari> comentaris=new ArrayList<Comentari>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM COMENTARIS");
			while (rs.next()) {
				Comentari c=new Comentari();
				c.setAutor(rs.getString("autor"));
				c.setComentari(rs.getString("comentari"));
				c.setData(rs.getDate("data"));
				comentaris.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {stmt.close();} catch (Exception e2) {;}
			try {rs.close();} catch (Exception e3) {;}
		}
		return comentaris;
	}

	@Override
	public void addComentari(Comentari c) {
		Connection conn=null;
		Statement stmt=null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO COMENTARIS VALUES ('" + c.getAutor()
					+ "','" + c.getComentari() + "','" + c.getData() + "')";
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {stmt.close();} catch (Exception e2) {;}
		}
	}

}
