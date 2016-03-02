/*-----------------------------------------------------------
 * University of Nebraska at Kearney 
 * CSIT 441 - Artificial Intelligence. Spring 2016
 * 
 * AUTHOR: 96740976 - Igor Felipe Ferreira Ceridorio
 * LAST MODIFIED: 02/25/2016
 * ARCHIVE: Graph.java
 * 
 * OBJECTIVE: 
 * This class defines a Graph object. A graph is composed
 * by an array list of vertex and an integer n that stores
 * the total of vertex present that are present in the graph. 			    
 *---------------------------------------------------------*/

import java.util.ArrayList;

public class Graph {
	
	ArrayList<Vertex> vertexList;
	int n; // stores the total of vertices for this graph
	
	
	/* ----------------------------------------------------
	 * class constructor
	 * -------------------------------------------------- */
	public Graph() {
		this.vertexList = new ArrayList<Vertex>();
	}
	
	/* ----------------------------------------------------
	 * method responsible for printing a graph with all 
	 * its vertices and connections
	 * -------------------------------------------------- */
	public void printGraph() {
		
		System.out.println("INPUT GRAPH:\n");
		
		for(int i = 0; i < this.vertexList.size(); i++){
			System.out.println("- Vertex " + this.vertexList.get(i).id + ":");
			System.out.print("\t");
			for(int j=0; j < this.vertexList.get(i).vertexConnList.size(); j++) {
				System.out.print(this.vertexList.get(i).vertexConnList.get(j).id + 
						"(" + this.vertexList.get(i).vertexConnList.get(j).weight + ")" + " ");
			}
			System.out.println("");
		}
			
	}
	
	/* ----------------------------------------------------
	 * method used to reset the vertex of the graph to 
	 * their default values
	 * -------------------------------------------------- */
	public void resetGraph() {
		for(int i = 0; i < vertexList.size(); i++) {
			vertexList.get(i).resetVertex();
		}
	}
	
	/* ----------------------------------------------------
	 * method responsible for clearing the vertex list of
	 * the graph
	 * -------------------------------------------------- */
	public void clearGraph() {
		this.vertexList.clear();
	}
	
}
