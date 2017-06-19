package model;

public class BeschikbareTijd { // BeschikbareTijd POJO
	
	private int id;
	private String datum;
	private String beginTijd;
	private String eindTijd;
	
	public BeschikbareTijd(int id, String datum, String beginTijd, String eindTijd) {
		super();
		this.id = id;
		this.datum = datum;
		this.beginTijd = beginTijd;
		this.eindTijd = eindTijd;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getEindTijd() {
		return eindTijd;
	}
	public void setEindTijd(String eindTijd) {
		this.eindTijd = eindTijd;
	}
	
}
