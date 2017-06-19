package nl.hu.v1wac.secondapp.webservices;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/countries")
public class WorldResource {

	@GET
	  @Produces("application/json")
	  public String getCountries() {
	    JsonArrayBuilder jab = Json.createArrayBuilder();
	    List<Country> countries;
	    try {
	      countries = ServiceProvider.getWorldService().getAllCountries();
	      JsonObjectBuilder job = Json.createObjectBuilder();
	      for (Country cn : countries) {
	        job.add("name", cn.getName());
	        job.add("capital", cn.getCapital());
	        job.add("region", cn.getRegion());
	        job.add("surface", cn.getSurface());
	        job.add("peasants", cn.getPopulation());
	        // job.add("latitude", cn.getLatitude());
	        // job.add("longitude", cn.getLongitude());
	        job.add("code", cn.getIso2Code());
	        jab.add(job);
	      }
	    } catch (SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }

	    return jab.build().toString();
	  }

	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getCountriesByCode(@PathParam("code") String code) {
		Country countries;
		JsonArrayBuilder jab = Json.createArrayBuilder();
		try {
			countries = ServiceProvider.getWorldService().getCountryByCode(code);
			JsonObjectBuilder job = Json.createObjectBuilder();

			if (countries == null) {
				throw new WebApplicationException("No such order!");
			}
			job.add("name", countries.getName());
			job.add("capital", countries.getCapital());
			job.add("region", countries.getRegion());
			job.add("surface", countries.getSurface());
			job.add("peasants", countries.getPopulation());
			// job.add("latitude", countries.getLatitude());
			// job.add("longitude", countries.getLongitude());
			job.add("code", countries.getIso2Code());
			jab.add(job);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jab.build().toString();
	}

	@DELETE
	@Path("{code}")
	public Response deleteCustomer(@PathParam("code") String code) {
		System.out.println("deleted: " + code);
		Country found = null;
		try {
			for (Country c : ServiceProvider.getWorldService().getAllCountries()) {
				System.out.println(c.getIso3Code() + " + " + code);
				if (c.getIso3Code().equals(code)) {
					found = c;
					ServiceProvider.getWorldService().delete(c);
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (found == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok().build();
		}
	}

	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getCountriesBySurfaces() {
		List<Country> countries;
		JsonObjectBuilder job = Json.createObjectBuilder();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		try {
			countries = ServiceProvider.getWorldService().get10LargestSurfaces();
			for (Country cn : countries) {
				cn.getSurface();
				job = job.add("name", cn.getName());
				jab.add(job);
				// System.out.println(cn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jab.build().toString();
	}

	@GET
	@Path("/largestpopulation")
	@RolesAllowed("admin")
	@Produces("application/json")
	public String getCountriesByPopulation(@Context SecurityContext sc) {
//		  String name = sc.getUserPrincipal().getName();

		List<Country> countries;
		JsonObjectBuilder job = Json.createObjectBuilder();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		try {
			countries = ServiceProvider.getWorldService().get10LargestPopulations();
			for (Country cn : countries) {
				cn.getSurface();
				job = job.add("name", cn.getName());
				jab.add(job);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jab.build().toString();
	}

	@POST
	@Produces("application/json")
	public String createCountry(@FormParam("code") String cd, @FormParam("name") String nm,
			@FormParam("capital") String cap) {
		Country country = new Country();
		country.setIso3Code(cd);
		country.setIso2Code(cd);
		country.setName(nm);
		country.setCapital(cap);
		System.out.println("POST connected");
		try {
			ServiceProvider.getWorldService().save(country);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("name", country.getName());
		job.add("capital", country.getCapital());
		return job.build().toString();
	}

	@PUT
	@Path("{code}")
	@Produces("application/json")
	public String updateCustomer(@PathParam("code") String code, @FormParam("land") String land,
			@FormParam("hoofdstad") String cap, @FormParam("oppervlakte") double opp, @FormParam("inwoners") int inw,
			@FormParam("regio") String regio) throws SQLException {
		JsonObjectBuilder job = Json.createObjectBuilder();
		List<Country> countries;
		countries = ServiceProvider.getWorldService().getAllCountries();
		for (Country c : countries) {
			System.out.println(c.getIso2Code() + " " + code);
			if (c.getIso2Code().contains(code)) {
				c.setCapital(cap);
				c.setName(land);
				c.setPopulation(inw);
				c.setSurface(opp);
				c.setRegion(regio);
				ServiceProvider.getWorldService().update(c);
				job.add("land", land);
				job.add("capital", cap);
				job.add("code", code);
				job.add("inwoners", inw);
				job.add("regio", regio);
				job.add("oppervlakte", opp);
				System.out.println("in de for loop" + c.getName());
				break;

			}
			// throw new WebApplicationException("Customer not found!");
		}
		System.out.println(job.build().toString() + " build");
		return job.build().toString();
	}

}