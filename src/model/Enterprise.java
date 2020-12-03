package model;

import datastructure.GraphA;
import datastructure.GraphB;

public class Enterprise {
	
	public static char ADYACENT_LIST='L';
	public static char MATRIX_LIST = 'M';
	
	private GraphB<String,Computer> graphB;
	private GraphA<String,Computer>  graphA;
	
	public Enterprise() {}
	
	public void addComputer(String serialNumber, String office, int floor) {
		
	}
	
	public void removeComputer(String serialNumber) {
		
	}
	
	public void searchComputer(String serialNumber) {
		
	}
	
	public void minimunPath(String serialNumber1, String serialNumber2,char type) {
		
	}
	
	public void prim(char type) {
		
	}
}
