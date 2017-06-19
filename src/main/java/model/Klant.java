package model;

	public class Klant { //Klant POJO

		private String email;
		private String voornaam;
		private String achternaam;
		private int telefoonNummer;
		
		public Klant(String email, String voornaam, String achternaam, int telefoonNummer) {
			super();
			this.email = email;
			this.voornaam = voornaam;
			this.achternaam = achternaam;
			this.telefoonNummer = telefoonNummer;
		}
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getVoornaam() {
			return voornaam;
		}

		public void setVoornaam(String voornaam) {
			this.voornaam = voornaam;
		}

		public String getAchternaam() {
			return achternaam;
		}

		public void setAchternaam(String achternaam) {
			this.achternaam = achternaam;
		}

		public int getTelefoonNummer() {
			return telefoonNummer;
		}

		public void setTelefoonNummer(int telefoonNummer) {
			this.telefoonNummer = telefoonNummer;
		}


}
