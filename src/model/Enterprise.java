package model;

import java.util.ArrayList;
import java.util.Stack;

import datastructure.GraphA;
import datastructure.GraphB;
import datastructure.Node;

public class Enterprise {
	
	public static char ADYACENT_LIST='L';
	public static char MATRIX_LIST = 'M';
	
	private GraphB<String,Computer> graphB;
	private GraphA<String,Computer>  graphA;
	private Computer computerSelectedA;
	private Computer computerSelectedB;
	
	public Enterprise() {
		graphB = new GraphB<>(GraphB.SIMPLE_GRAPH);
		graphA = new GraphA<>();
		
	}
	
	public void addComputer(String serialNumber, String office, int floor) {
		Computer c = new Computer(serialNumber,office,floor);
		graphB.add(serialNumber, c);
		graphA.add(serialNumber, c);
	}
	
	public void addConection(String serialNumber1, String serialNumber2, double ping) {
		
		//NodeA
		
		//NodeB
		graphB.addEdge(serialNumber1, serialNumber2, ping);
	}
	
	public void removeComputer(String serialNumber) {
		graphB.removeVertex(serialNumber);
		graphA.removeVertex(serialNumber);
	}
	
	public void removeConection(String serialNumber1,String serialNumber2) {
		graphB.removeEdge(serialNumber1, serialNumber2);
		graphA.removeEdge(serialNumber1, serialNumber2);
	}
	
	public Computer searchComputer(String serialNumber) {
		computerSelectedA = graphB.search(serialNumber);
		computerSelectedB = graphB.search(serialNumber);
		return computerSelectedB;
	}
	
	public ArrayList<String> minimunPath(String serialNumber1, String serialNumber2,char type) {
		ArrayList<String> route = new ArrayList<>(); 
		
		if(type==ADYACENT_LIST) {
			
			 Double[] prevs = graphB.dijkstra(serialNumber1).get(1);
			 Stack<String> stack = graphB.buildRoute(prevs, serialNumber2);
			 while(!stack.isEmpty()) {
				 route.add(stack.pop());
			 }
		}else if(type==MATRIX_LIST) {
			// graphA.dijkstra(serialNumber1);
		}
		
		return route;
	}
	
	public void prim(char type) {
		if(type==ADYACENT_LIST) {
			 graphB.prim();
		}else if(type==MATRIX_LIST) {
			 graphA.prim();
		}
	}
}
