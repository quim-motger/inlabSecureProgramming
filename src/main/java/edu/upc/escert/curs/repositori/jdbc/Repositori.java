package edu.upc.escert.curs.repositori.jdbc;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class Repositori {

	protected DataSource ds;
	protected JdbcTemplate jdbcTemplate;

	public Repositori() {
		try {
			Context ctx = new InitialContext();
			Context envContext  = (Context)ctx.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/escert");
			jdbcTemplate=new JdbcTemplate(ds);
			crear();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void executaSQL(String sql) {
		Connection conn=null;
		Statement stmt=null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			System.out.println("Executo SQL:"+sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {stmt.close();} catch (Exception e2) {;}
		}
	}

	protected abstract void crear();

}