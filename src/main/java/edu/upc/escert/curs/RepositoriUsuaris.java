package edu.upc.escert.curs;

public class RepositoriUsuaris {

	static RepositoriUsuaris instance;

	public static RepositoriUsuaris getInstance() {
		if (instance==null) {
			instance=new RepositoriUsuaris();
		}
		return instance;
	}
	public boolean autenticar (String username, String password) {
		return username.equals(password);
	}

}
