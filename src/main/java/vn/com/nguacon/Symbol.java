package vn.com.nguacon;

public class Symbol {
	private String code;
	private double floorPrice;
	private double ceilingPrice;
	
	public Symbol (String code, double floorPrice, double ceilingPrice) {
		this.code = code;
		this.floorPrice = floorPrice;
		this.ceilingPrice = ceilingPrice;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getFloorPrice() {
		return floorPrice;
	}
	public void setFloorPrice(double floorPrice) {
		this.floorPrice = floorPrice;
	}
	public double getCeilingPrice() {
		return ceilingPrice;
	}
	public void setCeilingPrice(double ceilingPrice) {
		this.ceilingPrice = ceilingPrice;
	}
	
}
