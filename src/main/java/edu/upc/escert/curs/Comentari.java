package edu.upc.escert.curs;

import java.util.Date;

public class Comentari {

	private int id;
	private String titol;
	private String comentari;
	private Date data;
	private String autor;

	public Comentari () {
	}

	public Comentari (String autor,String titol,String comentari) {
		setAutor(autor);
		setTitol(titol);
		setComentari(comentari);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitol() {
		return titol;
	}
	public void setTitol(String titol) {
		this.titol = titol;
	}
	public String getComentari() {
		return comentari;
	}
	public void setComentari(String comentari) {
		this.comentari = comentari;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}

}
