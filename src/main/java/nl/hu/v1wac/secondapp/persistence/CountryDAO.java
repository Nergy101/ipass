package nl.hu.v1wac.secondapp.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.secondapp.webservices.Country;

public interface CountryDAO {
	public Country save(Country country) throws SQLException;
	public ArrayList<Country> findALl() throws SQLException;
	public Country findByCode(String incode) throws SQLException;
	public List<Country> find10LargestPopulations() throws SQLException;
	public List<Country> find10LargestSurfaces() throws SQLException;
	public Country update(Country country) throws SQLException;
	public boolean delete(Country country) throws SQLException;
	
}