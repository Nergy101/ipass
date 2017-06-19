package nl.hu.v1wac.secondapp.webservices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.v1wac.secondapp.persistence.CountryDAO;
import nl.hu.v1wac.secondapp.persistence.CountryDAOImpl;

public class WorldService {
	CountryDAO cdao = new CountryDAOImpl();

	public WorldService() {

	}
	
	public Country save(Country country) throws SQLException {
		Country c = cdao.save(country);
		System.out.println("WorldService Connected");
		return c;
	}
	
	public boolean delete(Country country) throws SQLException {
		Boolean b = cdao.delete(country);
		return b;
	}

	public List<Country> getAllCountries() throws SQLException {
		ArrayList<Country> allCountries = cdao.findALl();
		return allCountries;
	}

	public List<Country> get10LargestPopulations() throws SQLException {
		ArrayList<Country> allCountries = cdao.findALl();
		Collections.sort(allCountries, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return c2.getPopulation() - c1.getPopulation();
			};
		});

		return allCountries.subList(0, 10);
	}

	public List<Country> get10LargestSurfaces() throws SQLException {
		ArrayList<Country> allCountries = cdao.findALl();
		Collections.sort(allCountries, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return (int) (c2.getSurface() - c1.getSurface());
			};
		});

		return allCountries.subList(0, 10);
	}

	public Country getCountryByCode(String code) throws SQLException {
		Country result = cdao.findByCode(code);
		return result;

	}
	public Country update(Country country) throws SQLException{
		country = cdao.update(country);
		return country;
	}
}
