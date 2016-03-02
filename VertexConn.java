/*-----------------------------------------------------------
 * University of Nebraska at Kearney 
 * CSIT 441 - Artificial Intelligence. Spring 2016
 * 
 * AUTHOR: 96740976 - Igor Felipe Ferreira Ceridorio
 * LAST MODIFIED: 02/25/2016
 * ARCHIVE: VertexConn.java
 * 
 * OBJECTIVE: 
 * This class defines a VertexConn object. This object 
 * contains an id, which identify the vertex that it is 
 * connected to, as well as the weight of the edge that is 
 * linking these two vertices.	    
 *---------------------------------------------------------*/

public class VertexConn {
	
	char id;
	float weight;

	/* ----------------------------------------------------
	 * class constructor
	 * -------------------------------------------------- */
	public VertexConn(char id, float weight) {
		this.id = id;
		this.weight = weight;
	}
	
}
