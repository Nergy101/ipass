package model;

import java.sql.SQLException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/afspraken")
public class AfspraakResource {

	@GET
	@Produces("application/json")
	public String getAfspraken() { //returned alle afspraken naar de browser
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Afspraak> afspraken;
		try {
			afspraken = ServiceProviderIPass.getAfspraakService().getAlleAfspraken();
			JsonObjectBuilder job = Json.createObjectBuilder();
			for (Afspraak a : afspraken) {
				job.add("afspraaknummer", a.getAfspraakNummer());
				job.add("email_adres", a.getEmail());
				job.add("werknemersnummer", a.getWerknemersNummer());
				job.add("tijd_id", a.getTijdId());
				job.add("voornaam", a.getVoornaam());
				job.add("achternaam", a.getAchternaam());
				job.add("telefoonnummer", a.getTelefoonNummer());
				job.add("datum", a.getDatum());
				job.add("begintijd", a.getBeginTijd());
				jab.add(job);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return jab.build().toString();
	}
	
	@PUT
	@Path("{aNr}")
	@Produces("application/json")
	public String updateAfspraak(@PathParam("aNr") int aNr, @FormParam("uE-mail_adres") String e_a, @FormParam("uWerknemersnummer") int wnnr, // update een afspraak in de database
	@FormParam("tijd_id") int t_id, @FormParam("uVoornaam") String vn, @FormParam("uAchternaam") String an,
	@FormParam("uTelefoonnummer") int tnr, @FormParam("uDatum") String datum, @FormParam("begintijd") String bt) throws SQLException {
		JsonObjectBuilder job = Json.createObjectBuilder();
		List<Afspraak> afspraken;
		afspraken = ServiceProviderIPass.getAfspraakService().getAlleAfspraken();
		for (Afspraak a : afspraken) {
			if (a.getAfspraakNummer()==(aNr)) {
				a.setEmail(e_a);
				a.setWerknemersNummer(wnnr);
				a.setTijdId(t_id);
				a.setVoornaam(vn);
				a.setAchternaam(an);
				a.setTelefoonNummer(tnr);
				a.setDatum(datum);
				a.setBeginTijd(bt);
				ServiceProviderIPass.getAfspraakService().updateAfspraak(a);
				job.add("email_adres", a.getEmail());
				job.add("werknemersnummer", a.getWerknemersNummer());
				job.add("tijd_id", a.getTijdId());
				job.add("voornaam", a.getVoornaam());
				job.add("achternaam", a.getAchternaam());
				job.add("telefoonnummer", a.getTelefoonNummer());
				job.add("datum", a.getDatum());
				job.add("begintijd", a.getBeginTijd());
				break;

			}
		}
		return job.build().toString();
	}

	@DELETE
	@Path("{id}")
	public Response deleteAfspraak(@PathParam("id") int id) throws SQLException { //delete een afspraak uit de database
		Afspraak found = null;
		for (Afspraak a : ServiceProviderIPass.getAfspraakService().getAlleAfspraken()) {
			if (a.getAfspraakNummer() == (id)) {
				found = a;
				ServiceProviderIPass.getAfspraakService().deleteAfspraak(found);
				break;
			}
		}

		if (found == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok().build();
		}
	}

	@POST
	@Produces("application/json")
	public String maakAfspraak(@FormParam("email_adres") String e_a, @FormParam("werknemersnummer") int wnnr, // maakt een afspraak in de database
			@FormParam("tijd_id") int t_id, @FormParam("voornaam") String vn, @FormParam("achternaam") String an,
			@FormParam("telefoonnummer") int tnr, @FormParam("datum") String datum, @FormParam("begintijd") String bt) {
		Afspraak a = new Afspraak();

		a.setEmail(e_a);
		a.setWerknemersNummer(wnnr);
		a.setTijdId(t_id);
		a.setVoornaam(vn);
		a.setAchternaam(an);
		a.setTelefoonNummer(tnr);
		a.setDatum(datum);
		a.setBeginTijd(bt);
		try {
			ServiceProviderIPass.getAfspraakService().maakAfspraak(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("email_adres", a.getEmail());
		job.add("werknemersnummer", a.getWerknemersNummer());
		job.add("tijd_id", a.getTijdId());
		job.add("voornaam", a.getVoornaam());
		job.add("achternaam", a.getAchternaam());
		job.add("telefoonnummer", a.getTelefoonNummer());
		job.add("datum", a.getDatum());
		job.add("begintijd", a.getBeginTijd());
		return job.build().toString();
	}
	
	@GET
	@Path("{afspraakNummer}")
	@Produces("application/json")
	public String getAfspraakByCode(@PathParam("afspraakNummer") String aNr) { //returned een specifieke afspraak naar de browser op afspraaknummer
		Afspraak a;
		JsonObjectBuilder job = Json.createObjectBuilder();
		try {
			a = ServiceProviderIPass.getAfspraakService().getAfspraakByCode(aNr);

			if (a == null) {
				throw new WebApplicationException("Er is geen afspraak!");
			}
			job.add("afspraaknummer", a.getAfspraakNummer());
			job.add("email_adres", a.getEmail());
			job.add("werknemersnummer", a.getWerknemersNummer());
			job.add("tijd_id", a.getTijdId());
			job.add("voornaam", a.getVoornaam());
			job.add("achternaam", a.getAchternaam());
			job.add("telefoonnummer", a.getTelefoonNummer());
			job.add("datum", a.getDatum());
			job.add("begintijd", a.getBeginTijd());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return job.build().toString();
	}

}
