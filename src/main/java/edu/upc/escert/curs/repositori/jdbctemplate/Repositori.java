package edu.upc.escert.curs.repositori.jdbctemplate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class Repositori {

	protected JdbcTemplate jdbcTemplate;

	public Repositori() {
		try {
			Context ctx = new InitialContext();
			Context envContext  = (Context)ctx.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/escert");
			jdbcTemplate=new JdbcTemplate(ds);
			crear();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected abstract void crear();

}