package nl.hu.v1wac.secondapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.secondapp.webservices.Country;

public class CountryDAOImpl extends BaseDAO implements CountryDAO {
	private Connection conn;

	@Override
	public Country save(Country country) throws SQLException {
		Country c = country;
		conn = super.getConnection();
		Statement stmt;
		stmt = conn.createStatement();
		String strQuery = "insert into country (code, code2, name, capital) values "
				+ "('" + country.getIso3Code() + "','"+country.getIso2Code()+ "','" + country.getName() + "','" + country.getCapital() + "')";
		System.out.println(strQuery);
		stmt.executeUpdate(strQuery);
		stmt.close();
		conn.close();
		return c;
	}

	@Override
	public ArrayList<Country> findALl() throws SQLException {
		ArrayList<Country> countries = new ArrayList<Country>();
		conn = super.getConnection();
		Statement stmt = conn.createStatement();
		String strQuery = "select * from country";
		ResultSet rs = stmt.executeQuery(strQuery);
		while (rs.next()) {
			Country a = new Country(rs.getString("name"), rs.getString("capital"), rs.getString("region"),
					rs.getDouble("surfacearea"), rs.getInt("population"), rs.getString("code2"));
			countries.add(a);
		}
		rs.close();
		stmt.close();
		conn.close();
		return countries;
	}

	@Override
	public Country findByCode(String incode) {
		Country a = null;
		conn = super.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String strQuery = "select * from country where code2 ='" + incode + "'";
			System.out.println(incode);

			ResultSet rs = stmt.executeQuery(strQuery);
			System.out.println(strQuery);

			while (rs.next()) {
				System.out.println(rs.getString("name"));
				a = new Country(rs.getString("name"), rs.getString("capital"), rs.getString("region"),
						rs.getDouble("surfacearea"), rs.getInt("population"), rs.getString("code2"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return a;
	}

	@Override
	public List<Country> find10LargestPopulations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Country> find10LargestSurfaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country update(Country country) throws SQLException {
		Country c = country;
		conn = super.getConnection();
		Statement stmt;
			stmt = conn.createStatement();		
//			String strQuery = "insert into country (code, name, capital) values ('" + country.getIso2Code() + "','" + country.getName() + "','" + country.getCapital() + "')";
			String strQuery = "update country set name = '"+c.getName()+"', surfacearea =" + c.getSurface()+", capital ='"+ c.getCapital() +"',population ="+ c.getPopulation()+",region ='"+c.getRegion()+"' where code2 ='"+c.getIso2Code()+"'";
			System.out.println(strQuery);
			stmt.executeUpdate(strQuery);
			stmt.close();
			conn.close();
		return c;
		
	}

	@Override
	public boolean delete(Country country) {
		Boolean b = false;
		Country c = country;
		conn = super.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String strQuery = "delete from country where code2 = '" + c.getIso2Code() + "'";
			System.out.println(strQuery);
			stmt.executeUpdate(strQuery);
			b = true;
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

}
