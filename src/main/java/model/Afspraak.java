package model;

public class Afspraak { //afspraak POJO
	private int afspraakNummer;
	private String email;
	private int werknemersNummer;
	private int tijdId;
	private String voornaam;
	private String achternaam;
	private int telefoonNummer;
	private String datum;
	private String beginTijd;
	
	public Afspraak(int afspraakNummer, String email, int werknemersNummer, int tijdId, String voornaam,
			String achternaam, int telefoonNummer, String datum, String beginTijd) {
		super();
		this.afspraakNummer = afspraakNummer;
		this.email = email;
		this.werknemersNummer = werknemersNummer;
		this.tijdId = tijdId;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.telefoonNummer = telefoonNummer;
		this.datum = datum;
		this.beginTijd = beginTijd;
	}
	
	public Afspraak(){
		
	}
	
	public int getAfspraakNummer() {
		return afspraakNummer;
	}

	public void setAfspraakNummer(int afspraakNummer) {
		this.afspraakNummer = afspraakNummer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getWerknemersNummer() {
		return werknemersNummer;
	}

	public void setWerknemersNummer(int werknemersNummer) {
		this.werknemersNummer = werknemersNummer;
	}

	public int getTijdId() {
		return tijdId;
	}

	public void setTijdId(int tijdId) {
		this.tijdId = tijdId;
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

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getBeginTijd() {
		return beginTijd;
	}

	public void setBeginTijd(String beginTijd) {
		this.beginTijd = beginTijd;
	}

}

