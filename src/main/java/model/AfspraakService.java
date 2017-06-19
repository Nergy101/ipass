package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.AfspraakDAO;
import controller.AfspraakDAOImpl;

public class AfspraakService {
	AfspraakDAO adao = new AfspraakDAOImpl();

	public AfspraakService() {
	}
	
	public List<Afspraak> getAlleAfspraken() throws SQLException {
		ArrayList<Afspraak> alleAfspraken = adao.vindAfspraken();
		return alleAfspraken;
	}
	
	public boolean deleteAfspraak(Afspraak afspraak) throws SQLException {
		Boolean b = adao.deleteAfspraak(afspraak);
		return b;
	}
	
	public Afspraak maakAfspraak(Afspraak afspraak) throws SQLException {
		Afspraak nA = adao.maakAfspraak(afspraak);
		return nA;
	}
	
	public List<Afspraak> getAlleTijden(String datum) throws SQLException {
		List<Afspraak> alleAfspraken = adao.vindTijden(datum);
		return alleAfspraken;
	}
	
	public Afspraak getAfspraakByCode(String aNr) throws SQLException{
		Afspraak a = adao.getAfspraakByCode(aNr);
		return a;
		
	}
	
	public Afspraak updateAfspraak(Afspraak a) throws SQLException{
		Afspraak b = adao.updateAfspraak(a);
		return b;
		
	}
}
