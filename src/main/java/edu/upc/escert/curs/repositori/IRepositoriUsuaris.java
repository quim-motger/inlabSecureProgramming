package edu.upc.escert.curs.repositori;

public interface IRepositoriUsuaris {
	public abstract boolean autenticar(String username, String password);
	public abstract void afegirUsuari(String username, String password, String rol);
}