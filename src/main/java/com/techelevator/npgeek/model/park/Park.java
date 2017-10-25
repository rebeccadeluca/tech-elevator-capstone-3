package com.techelevator.npgeek.model.park;

public class Park {

	private String imageName;
	private String name;
	private String location;
	private String summary;
	private String code;
	private int acreage;
	private int elevation;
	private float milesOfTrail;
	private int campsites;
	private String climate;
	private int yearFounded;
	private int visitors;
	private String quote;
	private String quoteSource;
	private int fee;
	private int species;
	
	public int getAcreage() {
		return acreage;
	}
	public void setAcreage(int acreage) {
		this.acreage = acreage;
	}
	public int getElevation() {
		return elevation;
	}
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	public float getMilesOfTrail() {
		return milesOfTrail;
	}
	public void setMilesOfTrail(float milesOfTrail) {
		this.milesOfTrail = milesOfTrail;
	}
	public int getCampsites() {
		return campsites;
	}
	public void setCampsites(int campsites) {
		this.campsites = campsites;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public int getYearFounded() {
		return yearFounded;
	}
	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}
	public int getVisitors() {
		return visitors;
	}
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getQuoteSource() {
		return quoteSource;
	}
	public void setQuoteSource(String quoteSource) {
		this.quoteSource = quoteSource;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getSpecies() {
		return species;
	}
	public void setSpecies(int species) {
		this.species = species;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName.toLowerCase() + ".jpg";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Park) {
			Park park = (Park) obj;
			if (park.getName().equals(this.getName()) && park.getImageName().equals(this.getImageName())
					&& park.getLocation().equals(this.getLocation()) && park.getSummary().equals(this.getSummary())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
