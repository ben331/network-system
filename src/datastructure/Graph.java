package datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph<K extends Comparable<K>,V> implements IGraph<K,V>{
	
	//Constants
	public static final char SIMPLE_GRAPH ='S';
	public static final char MULTIGRAPH ='M';
	public static final char PSEUDOGRAPHER ='P';
	public static final char DIRECTED_GRAPH ='D';
	public static final char DIRECTED_MULTIGRAPH ='L';
	
	//Fields
	private int size;
	private char type;
	private ArrayList<Node<K,V>> nodes;

	//Contructor
	public Graph(char type) {
		nodes = new ArrayList<Node<K,V>>();
		this.type=type;
	}
	
	//Getters
	public ArrayList<Node<K,V>> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node<K,V>> nodes) {
		this.nodes = nodes;
	}
	
	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	//Analyzers
	
	@Override
	public void add(K key, V value, ArrayList<Edge> adjacency) {
		Node<K,V> newNode = new Node<>(key, value, adjacency);
		nodes.add(newNode);
		size++;
	}
	
	@Override
	public ArrayList<Node<K,V>> BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] isVisited = new boolean[nodes.size()];
		ArrayList<Node<K,V>> visitedNodes = new ArrayList<>();
		
		if(!this.isEmpty()) {
			
			queue.add(0);	
			int index;
			Node<K,V> current;
			while(!queue.isEmpty()) {
				
				index = queue.poll();
				current = nodes.get(index);
				
				if(!isVisited[index]) {
					visitedNodes.add(current);
					isVisited[index]=true;
				}
				
				for(int i=0; i<current.getAdjacents();i++) {
					int indexTemp = current.getNeiborgIndex(i);
					if(!isVisited[indexTemp])
						queue.add(indexTemp);
				}
			}
		}
		return nodes;
	}

	@Override
	public ArrayList<Node<K,V>> DFS() {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] isVisited = new boolean[size];
		ArrayList<Node<K,V>> visitedNodes = new ArrayList<>();
		
		if(!this.isEmpty()) {
			
			stack.push(0);	
			int index;
			Node<K,V> current;
			while(!stack.isEmpty()) {
				
				index = stack.pop();
				current = nodes.get(index);
				
				if(!isVisited[index]) {
					visitedNodes.add(current);
					isVisited[index]=true;
				}
				
				for(int i=current.getAdjacents()-1; i>=0;i--) {
					int indexTemp = current.getNeiborgIndex(i);
					if(!isVisited[indexTemp])
						stack.push(indexTemp);
				}
			}
		}
		return nodes;
	}
	
	private int searchIndex(K key) {
		int result=-1;
		for(int i=0; i<nodes.size();i++) {
			if(nodes.get(i).getKey().compareTo(key)==0) {
				result = i;
			}
		}
		return result;
	}

	@Override
	public ArrayList<Double[]> dijkstra(K principalKey) {
		ArrayList<Double[]> result = new ArrayList<>();
		Double[] prev = new Double[size];
		Priority[] minimunWeight = new Priority[size];
		Heap<Integer, Integer, Priority> priorityQ = new Heap<>(size);
		
		for(int i=0; i<size; i++) {
			Priority p = new Priority(Double.MAX_VALUE);
			minimunWeight[i] = p;
			prev[i] = new Double(-1);
			priorityQ.insert(i, i, p);
		}
		
		int currentIndex = searchIndex(principalKey);
		Node<K,V> currentNode = nodes.get(currentIndex);
		minimunWeight[currentIndex].setWeight(0);
		priorityQ.increaseKey(currentIndex);
		
		while(!priorityQ.isEmpty()) {
			currentIndex = priorityQ.extractMax();
			currentNode = nodes.get(currentIndex);
			
			for(int i=0; i<currentNode.getAdjacents();i++) {
				
				double weightCurrentNeigborg = currentNode.getNeiborgWeight(i);
				double temp = minimunWeight[currentIndex].getWeight() + weightCurrentNeigborg;
				
				if(temp < minimunWeight[currentNode.getNeiborgIndex(i)].getWeight()) {
					minimunWeight[currentNode.getNeiborgIndex(i)].setWeight(temp);
					prev[currentNode.getNeiborgIndex(i)]= new Double(currentIndex);
					priorityQ.increaseKey(currentNode.getNeiborgIndex(i));
				}
			}
		}
		
		Double[] weights = new Double[size];
		for(int i=0; i<minimunWeight.length; i++) {
			weights[i]=minimunWeight[i].getWeight();
		}
		result.add(weights);
		result.add(prev);
		return result;
	}

	@Override
	public double[][] floydWarshall() {
		double[][] matrix = new double[size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size;j++) {
				if(i!=j) {
					matrix[i][j]=Integer.MAX_VALUE;
				}else {
					matrix[i][j]=0;
				}
			}
		}
		
		for(int i=0; i<size; i++) {
			Node<K,V> startingNode = nodes.get(i);
			for(int j=0; j<startingNode.getAdjacents();j++) {
				if(i!=startingNode.getNeiborgIndex(j)) {
					matrix[i][startingNode.getNeiborgIndex(j)] = startingNode.getNeiborgWeight(j);
				}
			}
		}
		
		for(int k=0; k<size; k++) {
			for(int i=0; (i<size && matrix[i][k]<Double.MAX_VALUE) && i!=k; i++) {
				for(int j=0; (j<size && j!=k) && (i!=j && matrix[k][j]<Double.MAX_VALUE); j++) {
					
					double alt = matrix[i][k] + matrix[k][j];
					
					if(alt < matrix[i][j]) {
						matrix[i][j] = alt;
					}
				}
			}
		}
		
		return matrix;
	}

	@Override
	public double prim() {
		Double[] prev = new Double[size];
		Priority[] weights = new Priority[size];
		Heap<Integer, Integer, Priority> priorityQ = new Heap<>(size);
		boolean[] isVisited = new boolean[size];
		
		for(int i=0; i<size; i++) {
			Priority p = new Priority(Double.MAX_VALUE, i);
			weights[i] = p;
			prev[i] = new Double(-1);
			priorityQ.insert(i, i, p);
		}
		
		int nodeWithMinEdge = getNodeWithMinEdge();
		if(nodeWithMinEdge!=-1) {
			weights[nodeWithMinEdge].setWeight(0);
			priorityQ.increaseKey(nodeWithMinEdge);
		}
		
		while(!priorityQ.isEmpty()) {
			int currentIndex = priorityQ.extractMax();
			Node<K,V> currentNode = nodes.get(currentIndex);
			for(int i=0; i<currentNode.getAdjacents(); i++) {
				if(!isVisited[currentIndex] && currentNode.getNeiborgWeight(i) < weights[currentIndex].getWeight()) {
					weights[currentNode.getNeiborgIndex(i)].setWeight(currentNode.getNeiborgWeight(i));
					prev[currentNode.getNeiborgIndex(i)] = (double) currentIndex;
					priorityQ.increaseKey(currentNode.getNeiborgIndex(i));
				}
			}
			isVisited[currentIndex]=true;
		}
		
		int travelWeight = 0;
		for(int i=0; i<weights.length; i++) {
			travelWeight+=weights[i].getWeight();
		}
		return travelWeight;
	}
	
	private int  getNodeWithMinEdge() {
		int minNode=-1;
		double minWeight=Double.MAX_VALUE;
		
		for(int i=0; i<size; i++) {
			for(int j=0; j< nodes.get(i).getAdjacents();j++) {
				if(nodes.get(i).getNeiborgWeight(j)<minWeight){
					minWeight = nodes.get(i).getNeiborgWeight(j);
					minNode = nodes.get(i).getNeiborgIndex(j);
				}
			}
		}
		return minNode;
	}
}
