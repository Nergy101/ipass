package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Afspraak;
import nl.hu.v1wac.secondapp.webservices.Country;

public interface AfspraakDAO {

	Boolean deleteAfspraak(Afspraak as) throws SQLException;

	Afspraak maakAfspraak(Afspraak afspraak) throws SQLException;

	ArrayList<Afspraak> vindAfspraken() throws SQLException;

	List<Afspraak> vindTijden(String datum) throws SQLException;

	Afspraak getAfspraakByCode(String aNr);

	Afspraak updateAfspraak(Afspraak a) throws SQLException;
	
}
