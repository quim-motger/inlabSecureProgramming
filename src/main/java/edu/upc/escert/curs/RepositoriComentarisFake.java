package edu.upc.escert.curs;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class RepositoriComentarisFake implements RepositoriComentaris {

	static RepositoriComentaris instance;

	public static RepositoriComentaris getInstance() {
		if (instance==null) {
			instance=new RepositoriComentarisFake();
		}
		return instance;
	}

	List<Comentari> comentaris=new ArrayList<Comentari>();

	private RepositoriComentarisFake () {
		Comentari c=new Comentari();
		c.setAutor("Jaume");
		c.setComentari("Tot va be");
		c.setData(new Date());
		comentaris.add(c);
	}

	/* (non-Javadoc)
	 * @see edu.upc.escert.curs.RepositoriComentaris#getComentaris()
	 */
	@Override
	public List<Comentari> getComentaris() {
		return comentaris;
	}

	/* (non-Javadoc)
	 * @see edu.upc.escert.curs.RepositoriComentaris#addComentari(edu.upc.escert.curs.Comentari)
	 */
	@Override
	public void addComentari(Comentari c) {
		comentaris.add(c);
	}


}
