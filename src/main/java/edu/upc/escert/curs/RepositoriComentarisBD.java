package edu.upc.escert.curs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RepositoriComentarisBD implements RepositoriComentaris {	

	static RepositoriComentaris instance;

	public static RepositoriComentaris getInstance() {
		if (instance == null) {
			instance = new RepositoriComentarisBD();
		}
		return instance;
	}

	DataSource ds;

	public RepositoriComentarisBD() {
		try {
			Context ctx = new InitialContext();
			Context envContext  = (Context)ctx.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/escert");
			crear();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void crear() {
		Connection conn=null;
		Statement stmt=null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			stmt.execute("create table comentaris (autor varchar(100),comentari varchar2(2000),data date)");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {stmt.close();} catch (Exception e2) {;}
		}
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
					+ "','" + c.getComentari() + "',CURRENT_TIMESTAMP())";
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {stmt.close();} catch (Exception e2) {;}
		}
	}

}
