package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Klant;

public interface KlantDAO {

	ArrayList<Klant> vindKlanten() throws SQLException;

	Klant maakKlant(Klant Klant) throws SQLException;

	void deleteKlant(Klant a) throws SQLException;

	Klant updateKlant(Klant a) throws SQLException;
	
}
