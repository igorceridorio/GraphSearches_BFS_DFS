/*-----------------------------------------------------------
 * University of Nebraska at Kearney 
 * CSIT 441 - Artificial Intelligence. Spring 2016
 * 
 * AUTHOR: 96740976 - Igor Felipe Ferreira Ceridorio
 * LAST MODIFIED: 02/25/2016
 * ARCHIVE: VertexConnComparator.java
 * 
 * OBJECTIVE: 
 * This class defines a VertexConn object comparator. It is
 * necessary since the search strategies applied uses
 * lexicographical order. A comparator is needed in order
 * to sort the array of a vertex's connections. This class 
 * creates the rule used to sort these arrays. 			    
 *---------------------------------------------------------*/

import java.util.Comparator;

public class VertexConnComparator implements Comparator<VertexConn> {

	/* ----------------------------------------------------
	 * override method that creates a comparison rule
	 * for two VertexConn objects based on their id
	 * -------------------------------------------------- */
	@Override
	public int compare(VertexConn vc1, VertexConn vc2) {
		
		if(vc1.id > vc2.id)
			return 1;
		else if(vc1.id < vc2.id)
			return -1;
		else
			return 0;
		
	}
	
}