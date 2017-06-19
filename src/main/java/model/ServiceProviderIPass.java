package model;

public class ServiceProviderIPass { // levert beide Servicen die gemaakt zijn

		private static AfspraakService afspraakService = new AfspraakService();
		private static KlantService klantService = new KlantService();
		
		public static AfspraakService getAfspraakService() {
			return afspraakService;
		}
		
		public static KlantService getKlantService(){
			return klantService;
		}
	}

