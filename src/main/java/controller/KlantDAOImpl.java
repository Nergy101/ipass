package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Klant;

public class KlantDAOImpl extends ConDAO implements KlantDAO {
	private Connection conn;
	
	@Override
	public ArrayList<Klant> vindKlanten() throws SQLException {

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();

			ArrayList<Klant> klantLijst = new ArrayList<Klant>();

			String queryText = "SELECT * FROM Klant";

			ResultSet rs = stmt.executeQuery(queryText);
			System.out.println(rs);
			while (rs.next()) {

				Klant a = new Klant(rs.getString("email"), rs.getString("voornaam"), rs.getString("achternaam"),
						rs.getInt("telefoonNummer"));
				klantLijst.add(a);
			}
			rs.close();
			stmt.close();
			con.close();
			return klantLijst;
		}
	}

	@Override
	public Klant maakKlant(Klant klant) throws SQLException { // maak een klant aan in de database
		Klant k= klant;
		conn = super.getConnection();
		Statement stmt;
		stmt = conn.createStatement();
		String strQuery = "insert into Klant values"
				+ "(" +klant.getEmail()+ "','" +k.getTelefoonNummer()
				+ "','" 
				+klant.getAchternaam()+  "','"+k.getVoornaam()
				+"')";
		//System.out.println(strQuery);
		stmt.executeUpdate(strQuery);
		stmt.close();
		conn.close();
		return k;
	}
	
	@Override
	public void deleteKlant (Klant k) throws SQLException { // delete een klant uit de database
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			//System.out.println(c.getCode()+"DAOImpl");
			String queryText = "DELETE FROM klant WHERE Klant.E-mail_adres ='"+k.getEmail()+"';";

			stmt.executeUpdate(queryText);

			stmt.close();
			con.close();
		}
	}
	
	@Override
	public Klant updateKlant(Klant k) throws SQLException { // update een klant in de database
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String queryText = 
					"UPDATE Klant"+
					"SET email_adres = '"+k.getEmail()+"', telefoonnummer='"+k.getTelefoonNummer()+
					"',achternaam="+k.getAchternaam()+",voornaam="+k.getVoornaam()+"';";

			stmt.executeUpdate(queryText);

			stmt.close();
			con.close();
			return k;
		}
	}
	
	}
