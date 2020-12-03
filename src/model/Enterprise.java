package model;

import datastructure.GraphA;
import datastructure.GraphB;

public class Enterprise {
	
	public static char ADYACENT_LIST='L';
	public static char MATRIX_LIST = 'M';
	
	private GraphB<String,Computer> graphB;
	private GraphA<String,Computer>  graphA;
	
	public Enterprise() {
		graphB = new GraphB(GraphB.SIMPLE_GRAPH);
		graphA = new GraphA();		
	}
	
	public void addComputer(String serialNumber, String office, int floor) {
		Computer c = new Computer(serialNumber,office,floor);
		graphB.add(serialNumber, c);
		graphA.add(serialNumber, c);
	}
	
	public void addConection(String serialNumber1, String serialNumber2) {
	
	}
	
	public void removeComputer(String serialNumber) {
		graphB.removeVertex(serialNumber);
		graphA.removeVertex(serialNumber);
	}
	
	public void removeConection(String serialNumber1,String serialNumber2) {
		graphB.removeEdge(serialNumber1, serialNumber2);
		graphA.removeEdge(serialNumber1, serialNumber2);
	}
	
	public void searchComputer(String serialNumber) {
		graphB.search(serialNumber);
		graphA.search(serialNumber);
	}
	
	public void minimunPath(String serialNumber1, String serialNumber2,char type) {
		if(type==ADYACENT_LIST) {
			 graphB.dijkstra(serialNumber1);
		}else if(type==MATRIX_LIST) {
			 graphA.dijkstra(serialNumber1);
		}
	}
	
	public void prim(char type) {
		if(type==ADYACENT_LIST) {
			 graphB.prim();
		}else if(type==MATRIX_LIST) {
			 graphA.prim();
		}
	}
}
