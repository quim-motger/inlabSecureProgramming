package edu.upc.escert.curs.repositori;

import java.sql.SQLException;
import java.util.List;

import edu.upc.escert.curs.Comentari;

public interface RepositoriComentaris {

	public abstract List<Comentari> getComentarisFromSQL(String sql) throws SQLException;

	public abstract List<Comentari> getComentaris() throws SQLException;

	public abstract List<Comentari> getComentarisPerAutor(String autor) throws SQLException;

	public abstract void afegirComentari(Comentari c) throws SQLException;

	public abstract void esborrarComentari(int id) throws SQLException;

}