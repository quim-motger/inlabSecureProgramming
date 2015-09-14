package edu.upc.escert.curs;

import java.util.Date;

public class Comentari {

	private String comentari;
	private Date data;
	private String autor;

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
