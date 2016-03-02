/*-----------------------------------------------------------
 * University of Nebraska at Kearney 
 * CSIT 441 - Artificial Intelligence. Spring 2016
 * 
 * AUTHOR: 96740976 - Igor Felipe Ferreira Ceridorio
 * LAST MODIFIED: 02/25/2016
 * ARCHIVE: VertexComparator.java
 * 
 * OBJECTIVE: 
 * This class defines a Vertex object comparator. It is
 * necessary since the search strategies applied uses
 * lexicographical order. A comparator is needed in order
 * to sort the array of vertices. This class creates the
 * rule used to sort the array of vertices of the graph. 			    
 *---------------------------------------------------------*/

import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {

	/* ----------------------------------------------------
	 * override method that creates a comparison rule
	 * for two Vertex objects based on their id
	 * -------------------------------------------------- */
	@Override
	public int compare(Vertex v1, Vertex v2) {
		
		if(v1.id > v2.id)
			return 1;
		else if(v1.id < v2.id)
			return -1;
		else
			return 0;
		
	}
	
}