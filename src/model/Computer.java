package model;

public class Computer {
	
	private String serialNumber;
	private String office;
	private int floor;
	public Computer(String serialNumber, String office, int floor) {
		super();
		this.serialNumber = serialNumber;
		this.office = office;
		this.floor = floor;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	
	
}
