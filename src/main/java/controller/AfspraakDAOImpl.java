package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Afspraak;

public class AfspraakDAOImpl extends ConDAO implements AfspraakDAO {
	private Connection conn;

	@Override
	public ArrayList<Afspraak> vindAfspraken() throws SQLException { // zoekt alle afspraken en geeft alle afspraken terug

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();

			ArrayList<Afspraak> afspraakLijst = new ArrayList<Afspraak>();
			String queryText = "SELECT * FROM afspraak";

			ResultSet rs = stmt.executeQuery(queryText);
			while (rs.next()) {

				Afspraak a = new Afspraak(rs.getInt("afspraaknummer"), rs.getString("email_adres"),
						rs.getInt("werknemersnummer"), rs.getInt("tijd_id"), rs.getString("voornaam"),
						rs.getString("achternaam"), rs.getInt("telefoonnummer"), rs.getString("datum"),
						rs.getString("begintijd"));
				afspraakLijst.add(a);
			}
			rs.close();
			stmt.close();
			con.close();
			return afspraakLijst;
		}
	}

	@Override
	public List<Afspraak> vindTijden(String date) throws SQLException { //vind de begintijd van alle afspraken met een specifieke datum

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();

			List<Afspraak> tijdLijst = new ArrayList<Afspraak>();
			String queryText = "SELECT begintijd FROM afspraak WHERE datum ="+date;

			ResultSet rs = stmt.executeQuery(queryText);
			while (rs.next()) {

				Afspraak a = new Afspraak();
				a.setBeginTijd(rs.getString("begintijd"));
				tijdLijst.add(a);
			}
			rs.close();
			stmt.close();
			con.close();
			return tijdLijst;
		}
	}
	
	@Override
	public Afspraak maakAfspraak(Afspraak afspraak) throws SQLException { //maak een nieuwe afspraak aan in de database
		Afspraak a = afspraak;
		conn = super.getConnection();
		Statement stmt;
		stmt = conn.createStatement();
		String strQuery = "INSERT INTO afspraak VALUES" + "(nextVal('afspraak_afspraaknummer_seq')" + ",'"
				+ afspraak.getEmail() + "'," + afspraak.getWerknemersNummer() + "," + afspraak.getTijdId() + ",'"
				+ afspraak.getVoornaam() + "','" + afspraak.getAchternaam() + "'," + afspraak.getTelefoonNummer()
				+ ",'" + afspraak.getDatum() + "','" + afspraak.getBeginTijd() + "')";
		stmt.executeUpdate(strQuery);
		stmt.close();
		conn.close();
		return a;
	}

	@Override
	public Boolean deleteAfspraak(Afspraak a) throws SQLException { //delete een afspraak uit de database
		Boolean check = false;
		Connection con = super.getConnection();
		Statement stmt = con.createStatement();
		try {

			String queryText = "DELETE FROM afspraak WHERE afspraak.afspraaknummer ='" + a.getAfspraakNummer() + "';";

			stmt.executeUpdate(queryText);

			check = true;
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;

	}

	@Override
	public Afspraak updateAfspraak(Afspraak a) throws SQLException { //update een afspraak in de database
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String strQuery = "update afspraak set afspraaknummer = "+a.getAfspraakNummer()+", email_adres ='" + a.getEmail()+"', werknemersnummer ="+ a.getWerknemersNummer() +", tijd_id ="+ a.getTijdId()+",voornaam ='"+a.getVoornaam()+"', achternaam ='" + a.getAchternaam()+"', telefoonnummer =" + a.getTelefoonNummer()+ ", datum ='" + a.getDatum() +"', begintijd ='" + a.getBeginTijd()+"' where afspraaknummer ="+a.getAfspraakNummer()+";";
			System.out.println(strQuery);
			stmt.executeUpdate(strQuery);
			stmt.close();
			con.close();
			return a;
		}
	}

	@Override
	public Afspraak getAfspraakByCode(String aNr) { //vind een specifieke afspraak op het afspraaknummer
		Afspraak a = null;
		conn = super.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String strQuery = "select * from afspraak where afspraaknummer ='" + aNr + "'";

			ResultSet rs = stmt.executeQuery(strQuery);
			System.out.println(strQuery);

			while (rs.next()) {
				a = new Afspraak(rs.getInt("afspraaknummer"), rs.getString("email_adres"),
						rs.getInt("werknemersnummer"), rs.getInt("tijd_id"), rs.getString("voornaam"),
						rs.getString("achternaam"), rs.getInt("telefoonnummer"), rs.getString("datum"),
						rs.getString("begintijd"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

}
