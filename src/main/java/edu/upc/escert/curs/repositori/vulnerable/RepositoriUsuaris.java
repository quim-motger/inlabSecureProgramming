package edu.upc.escert.curs.repositori.vulnerable;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.upc.escert.curs.repositori.*;

public class RepositoriUsuaris extends Repositori implements IRepositoriUsuaris {
	
	private String generarHash(String password) {
	    String hash=null;
	    try {
	      MessageDigest md = 
	        MessageDigest.getInstance("SHA-256");
	      md.update(password.getBytes("UTF-8"));
	      byte[] digest = md.digest();
	      hash=String.format("%064x", 
	        new java.math.BigInteger(1, digest));
	    } catch (Exception e) {
	      throw new RuntimeException(e.getMessage());
	    }
	    return hash;
	  }

	protected void crear() {
		executaSQL("CREATE TABLE IF NOT EXISTS USUARIS (USERNAME VARCHAR(100),PASSWORD VARCHAR(100),rol varchar2(10))");
		afegirUsuari("jaume",generarHash("trustno1"),"admin");
		afegirUsuari("scott",generarHash("tiger"),"usuari");
		afegirUsuari("ton",generarHash("secret"),"usuari");
	}

	@Override
	public boolean autenticar (String username, String password) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			String sql="SELECT count(*) FROM USUARIS WHERE USERNAME='"+username +"' AND PASSWORD='"+generarHash(password)+"'";
			System.out.println("Executo consulta SQL:"+sql);
			rs=stmt.executeQuery(sql);
			rs.next();
			int n=rs.getInt(1);
			System.out.println("Resultat:"+n);
			return (n>0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {stmt.close();} catch (Exception e2) {;}
			try {rs.close();} catch (Exception e3) {;}
		}
	}

	@Override
	public void afegirUsuari (String username, String password, String rol) {
		executaSQL("INSERT INTO USUARIS VALUES ('" + username + "','" + password + "','" + rol+ "')");
	}


}
