package nl.hu.v1wac.secondapp.webservices;

import nl.hu.v1wac.secondapp.webservices.WorldService;

public class ServiceProvider {
	private static WorldService worldService = new WorldService();

	public static WorldService getWorldService() {
		return worldService;
	}
}