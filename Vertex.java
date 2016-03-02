/*-----------------------------------------------------------
 * University of Nebraska at Kearney 
 * CSIT 441 - Artificial Intelligence. Spring 2016
 * 
 * AUTHOR: 96740976 - Igor Felipe Ferreira Ceridorio
 * LAST MODIFIED: 02/25/2016
 * ARCHIVE: Vertex.java
 * 
 * OBJECTIVE: 
 * This class defines a Vertex object. A vertex is composed
 * by a letter id, a color, the distance it will have from
 * the start of the search until it, its predecessor vertex,
 * and an array list of its connections, that is with which
 * other vertices it is connected to. 			    
 *---------------------------------------------------------*/

import java.util.ArrayList;

public class Vertex{

	char id;
	int color; // 0 - white: not visited; 1 - gray: visited; 2 - black: explored
	float dist; // stores the distance of this graph from the start village 
	Vertex pred; // stores the predecessor vertex of this current one
	ArrayList<VertexConn> vertexConnList;
	
	/* ----------------------------------------------------
	 * class constructor
	 * -------------------------------------------------- */
	public Vertex(char id){
		this.id = id;
		this.color = 0;
		this.dist = -1;
		this.pred = null;
		this.vertexConnList = new ArrayList<VertexConn>();
	}
	
	/* ----------------------------------------------------
	 * method used to reset the values of the vertex to
	 * default
	 * -------------------------------------------------- */
	public void resetVertex() {
		this.color = 0;
		this.dist = -1;
		this.pred = null;
	}
	
}
