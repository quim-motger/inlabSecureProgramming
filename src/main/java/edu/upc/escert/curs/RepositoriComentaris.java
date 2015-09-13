package edu.upc.escert.curs;

import java.util.List;

public interface RepositoriComentaris {

	public abstract List<Comentari> getComentaris();

	public abstract void addComentari(Comentari c);

}