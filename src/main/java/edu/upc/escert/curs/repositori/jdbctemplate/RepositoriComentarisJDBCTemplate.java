package edu.upc.escert.curs.repositori.jdbctemplate;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import edu.upc.escert.curs.Comentari;

public class RepositoriComentarisJDBCTemplate extends Repositori {

	static RepositoriComentarisJDBCTemplate instance;

	public static RepositoriComentarisJDBCTemplate getInstance() {
		if (instance == null) {
			instance = new RepositoriComentarisJDBCTemplate();
		}
		return instance;
	}

	protected void crear() {
		jdbcTemplate.execute("create table IF NOT EXISTS comentaris ("+
				"id int auto_increment, "+
				"autor varchar(100), "+
				"titol varchar(200), "+
				"comentari varchar2(2000), "+
				"data date default CURRENT_TIMESTAMP )");
		if (getComentaris().isEmpty()) {
			afegirComentari(new Comentari(
					"jaume","Benvinguts, comenteu!",
					"Aquesta aplicació està feta <b>expressament</b> amb vulnerabilitats."+
					"La idea es que al final del curs tinguem una versió <i>millorada</i>."
			));
		}
	}

	BeanPropertyRowMapper<Comentari> rowMapper=new BeanPropertyRowMapper<Comentari>(Comentari.class);

	public List<Comentari> getComentaris() {
		return jdbcTemplate.query(
				"SELECT * FROM COMENTARIS",rowMapper);
	}

	public List<Comentari> getComentarisPerAutor (String autor) {
		return jdbcTemplate.query(
				"SELECT * FROM COMENTARIS WHERE AUTOR=?",new Object[]{autor},rowMapper);
	}

	public void afegirComentari(Comentari c) {
		jdbcTemplate.update(
				"INSERT INTO COMENTARIS (autor,titol,comentari,data) VALUES (?,?,?,?,CURRENT_TIMESTAMP())",
				new Object[]{c.getAutor(),c.getTitol(),c.getComentari()},rowMapper);
	}

	public void esborrarComentari(int id) {
		jdbcTemplate.update(
				"DELETE COMENTARIS WHERE ID=?",new Object[]{id},rowMapper);
	}

}
