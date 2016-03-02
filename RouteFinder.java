/*-----------------------------------------------------------
 * University of Nebraska at Kearney 
 * CSIT 441 - Artificial Intelligence. Spring 2016
 * 
 * AUTHOR: 96740976 - Igor Felipe Ferreira Ceridorio
 * LAST MODIFIED: 02/25/2016
 * ARCHIVE: RouteFinder.java
 * 
 * OBJECTIVE: 
 * This class is responsible for managing the program menu
 * flow, creating the graph given an input file provided by 
 * the user, implementing BFS and DFS algorithms, as well as
 * implementing a method responsible for calculating the 
 * distance between every two villages present in the input
 * graph.
 * 
 * - Search strategy: Both BFS and DFS uses lexicographical
 * order in its strategies to choose the next vertex to be
 * explored. After the graph is read from the input, the
 * arrays containing the vertices and its connections are 
 * sorted throughout implemented comparison rules based on
 * the vertices identifications.
 * 
 * - Both searches do not guarantee the minimal path (when
 * it exists) for two vertices		    
 *---------------------------------------------------------*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class RouteFinder {
	
	Graph graphObj;

	/* ----------------------------------------------------
	 * class constructor
	 * -------------------------------------------------- */
	public RouteFinder() {
		this.graphObj = new Graph();
		genMainMenu();
	}

	/* ----------------------------------------------------
	 * main method
	 * -------------------------------------------------- */
	public static void main(String[] args) {
		new RouteFinder();
	}
	
	/* ----------------------------------------------------
	 * method responsible for generating the program main
	 * menu
	 * -------------------------------------------------- */
	public void genMainMenu() {

		System.out.println("************************************************************");
		System.out.println("* BFS and DFS search algorithms                            *");
		System.out.println("************************************************************");
		System.out.println("");
		System.out.print("Type the graph map file path and name (Ex: C:\\file.csv): ");

		// calls the method responsible for reading the file and creating the graph
		createGraph();

	}

	/* ----------------------------------------------------
	 * method responsible for generating the program sub
	 * menu
	 * -------------------------------------------------- */
	@SuppressWarnings("resource")
	public void genSubMenu() {

		int menuOption = 0;
		Scanner s = new Scanner(System.in);

		while (menuOption <= 0 || menuOption > 5) {

			System.out.println("\n====================");
			System.out.println("= Menu             =");
			System.out.println("====================");
			System.out.println("\nType your number of choice :");
			System.out.println("\t [1] - Execute Breadth First Search (BFS)");
			System.out.println("\t [2] - Execute Depth First Search (DFS)");
			System.out.println("\t [3] - Show the distances between each village");
			System.out.println("\t [4] - Enter a new graph map file path and name");
			System.out.println("\t [5] - Exit program");

			System.out.println("");
			System.out.print("-> ");

			try {
				menuOption = s.nextInt();
				s.nextLine();
			} catch (Exception e) {
				System.out.println("Option enter error: " + e);
				System.out.println("\nATTENTION: Please, enter only numbers");
				genSubMenu();
			}

			// executes the required option
			if (menuOption <= 0 || menuOption > 5) {
				System.out.println("\nATTENTION: Please, choose a valid option");
			} else {
				if(menuOption == 1) {
					
					Scanner sSVillage = new Scanner(System.in);
					Scanner sDVillage = new Scanner(System.in);
					char cSVillage = '\0';
					char cDVillage = '\0';
					boolean sValid = false;
					boolean dValid = false;
					
					// check if both informed vertices exist in the graph
					while(!sValid || !dValid) {
						
						System.out.print("\nInform the start village: ");
						cSVillage = sSVillage.next().charAt(0);
						cSVillage = Character.toUpperCase(cSVillage);
						System.out.print("Inform the end village: ");
						cDVillage = sDVillage.next().charAt(0);
						cDVillage = Character.toUpperCase(cDVillage);
						
						for(int i= 0; i < graphObj.vertexList.size(); i++) {
							if(graphObj.vertexList.get(i).id == cSVillage)
								sValid = true;
							if(graphObj.vertexList.get(i).id == cDVillage)
								dValid = true;
							
						}
						
						if(!sValid || !dValid) {
							System.out.println("Start, end or both villages are not present in this graph...");
							System.out.println("Please, enter these information again");
						}
						
					}
					
					// calls the BFS function
					bfs(graphObj, cSVillage, cDVillage, true);
					
					// calls the sub menu generator again
					genSubMenu();
					
				} else if(menuOption == 2) { 
				
					Scanner sSVillage = new Scanner(System.in);
					Scanner sDVillage = new Scanner(System.in);
					char cSVillage = '\0';
					char cDVillage = '\0';
					boolean sValid = false;
					boolean dValid = false;
					
					// check if both informed vertices exist in the graph
					while(!sValid || !dValid) {
						
						System.out.print("\nInform the start village: ");
						cSVillage = sSVillage.next().charAt(0);
						cSVillage = Character.toUpperCase(cSVillage);
						System.out.print("Inform the end village: ");
						cDVillage = sDVillage.next().charAt(0);
						cDVillage = Character.toUpperCase(cDVillage);
						
						for(int i= 0; i < graphObj.vertexList.size(); i++) {
							if(graphObj.vertexList.get(i).id == cSVillage)
								sValid = true;
							if(graphObj.vertexList.get(i).id == cDVillage)
								dValid = true;
							
						}
						
						if(!sValid || !dValid) {
							System.out.println("Start, end or both villages are not present in this graph...");
							System.out.println("Please, enter these information again");
						}
						
					}
					
					// calls the DFS function
					dfs(graphObj, cSVillage, cDVillage, true);
					
					// calls the sub menu generator again
					genSubMenu();
				
				} else if (menuOption == 3) {
					
					int iMethod = 0;
					Scanner sMethod = new Scanner(System.in);
					
					while (iMethod <= 0 || iMethod > 2) {
					
						System.out.print("\nInform the method of your choice:\n");
						System.out.println("\t [1] - Breadth First Search (BFS)");
						System.out.println("\t [2] - Depth First Search (DFS)");
						
						System.out.println("");
						System.out.print("-> ");
						
						try {
							iMethod = sMethod.nextInt();
							sMethod.nextLine();
						} catch (Exception e) {
							System.out.println("Option enter error: " + e);
							iMethod = 0;
							sMethod.nextLine();
						}
						
						// calls the matrix generator method with an integer flag 
						// representing which search strategy will be used
						if(iMethod != 1 && iMethod != 2) {
							System.out.println("\nATTENTION: Please, choose a valid option");
						} if(iMethod == 1) {
							genVillageArray(graphObj, 1);
						} else if (iMethod == 2) {
							genVillageArray(graphObj, 2);
						}
						
					}
				
				} else if (menuOption == 4) {
					
					System.out.println("");
					System.out.print("Type the graph map file path and name (Ex: C:\\file.csv): ");
					
					// if the user wants to enter another graph the previous one must be cleared
					this.graphObj.clearGraph();
					createGraph();
					
				} else if (menuOption == 5) {
					
					System.out.println("\nProgram closed.");
					System.exit(0);
					
				}
			}

		}
	
	}

	/* ----------------------------------------------------
	 * method responsible for reading the file and creating
	 * the graph object
	 * -------------------------------------------------- */
	public void createGraph() {

		String graphPath = null;

		// gets the path input location provided by the user
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			graphPath = br.readLine();
		} catch (IOException e) {
			System.out.println("Path location error: " + e);
		}
		
		// loads a graph file given a valid path
		Boolean file = false;

		while (!file) {
			file = true;
			BufferedReader br = null;

			try {
				String sLine;
				br = new BufferedReader(new FileReader(graphPath));

				// getting the total of vertex in this graph
				sLine = br.readLine();
				graphObj.n = Integer.parseInt(sLine);

				// first step: build an ArrayList containing all the vertex of the given graph
				while ((sLine = br.readLine()) != null) {

					// breaks the string when it has spaces and stores the information
					StringTokenizer st = new StringTokenizer(sLine);
					char firstToken = st.nextToken().toString().charAt(0);
					char secondToken = st.nextToken().toString().charAt(0);
					st.nextToken();

					boolean isPresent;

					if (graphObj.vertexList.size() != 0) {

						// adds the first vertex to the list if not present yet
						isPresent = false;
						for (int i = 0; i < graphObj.vertexList.size() && !isPresent; i++) {
							if (graphObj.vertexList.get(i).id == firstToken)
								isPresent = true;
						}

						if (!isPresent) {
							Vertex v = new Vertex(firstToken);
							graphObj.vertexList.add(v);
						}

						// adds the second vertex to the list if not present yet
						isPresent = false;
						for (int i = 0; i < graphObj.vertexList.size() && !isPresent; i++) {
							if (graphObj.vertexList.get(i).id == secondToken)
								isPresent = true;
						}

						if (!isPresent) {
							Vertex v = new Vertex(secondToken);
							graphObj.vertexList.add(v);
						}

					} else {

						Vertex v1 = new Vertex(firstToken);
						graphObj.vertexList.add(v1);
						Vertex v2 = new Vertex(secondToken);
						graphObj.vertexList.add(v2);

					}

				}

				br.close();
				br = new BufferedReader(new FileReader(graphPath));
				sLine = br.readLine();

				// second step: add the relations and weight to the adjacency lists
				while ((sLine = br.readLine()) != null) {

					// breaks the string when it has spaces and stores the information
					StringTokenizer st = new StringTokenizer(sLine);
					char firstToken = st.nextToken().toString().charAt(0);
					char secondToken = st.nextToken().toString().charAt(0);
					float thirdToken = Float.parseFloat(st.nextToken().toString());

					for (int i = 0; i < graphObj.vertexList.size(); i++) {
						if (graphObj.vertexList.get(i).id == firstToken) {
							VertexConn vc = new VertexConn(secondToken, thirdToken);
							graphObj.vertexList.get(i).vertexConnList.add(vc);
						} else if (graphObj.vertexList.get(i).id == secondToken) {
							VertexConn vc = new VertexConn(firstToken, thirdToken);
							graphObj.vertexList.get(i).vertexConnList.add(vc);
						}
					}

				}
				
				br.close();

			} catch (IOException e) {
				System.out.println("Error reading the file: " + e);
				System.out.print("Please inform a valid path: ");
				file = false;

				// reads the new file path location
				try {
					BufferedReader brPath = new BufferedReader(new InputStreamReader(System.in));
					graphPath = brPath.readLine();
				} catch (IOException ex) {
					System.out.println("Path location fatal error: " + ex);
					System.out.println("Program closed.");
					System.exit(0);
				}

			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException e) {
					System.out.println("Error closing the file: " + e);
					System.out.println("Program closed.");
					System.exit(0);
				}
			}
		}
		
		// sorts the array list of vertices by id using the comparator created
		Collections.sort(graphObj.vertexList, new VertexComparator());
		
		// sorts the array list of each vertex connections by id using the comparator created
		for(int i = 0; i < graphObj.vertexList.size(); i++) {
			Collections.sort(graphObj.vertexList.get(i).vertexConnList, new VertexConnComparator());
		}
		
		System.out.println("");
		graphObj.printGraph();
		
		// calls the method responsible for generating the sub menu
		genSubMenu();

	}
	
	/* ----------------------------------------------------
	 * method responsible for executing a BFS to the given
	 * input graph. The boolean showText is true when
	 * it is needed that BFS prints information in the
	 * screen and returns a solution path (if any)
	 * -------------------------------------------------- */
	public float bfs(Graph graph, char startVillage, char destVillage, boolean showText) {
		
		if(showText) {
			System.out.println("\n**************************************************");
			System.out.println("* BFS - Breadth First Search                     *");
			System.out.println("**************************************************");
			System.out.println("\nStart village: " + Character.toUpperCase(startVillage));
			System.out.println("Destination village: " + Character.toUpperCase(destVillage));
			System.out.println("");
		}
		
		Queue<Vertex> queue = new LinkedList<Vertex>();
		ArrayList<Character> explored = new ArrayList<Character>();
		ArrayList<Character> path = new ArrayList<Character>();	
		float distPath = 0;
		boolean foundPath = false;
		
		// the startVillage is now visited and added to the queue
		for(int i = 0; i < graph.vertexList.size(); i++) {
			if(graph.vertexList.get(i).id == startVillage) {
				graph.vertexList.get(i).dist = 0;
				graph.vertexList.get(i).color = 1;
				queue.add(graph.vertexList.get(i));
			}
		}
		
		// while queue is not empty nor the destination village was found proceeds with the search
		int stepCounter = 0;
		
		while(!queue.isEmpty()) {
			
			// remove the head of the queue and iterates through its neighbors
			Vertex currentVertex = queue.remove();
			for(int i = 0; i < graph.vertexList.size(); i++) {
				if(graph.vertexList.get(i).id == currentVertex.id) {
					for(int j = 0; j < graph.vertexList.get(i).vertexConnList.size(); j++) {
						for(int k = 0; k < graph.vertexList.size(); k++) {
							if(graph.vertexList.get(k).id == graph.vertexList.get(i).vertexConnList.get(j).id) {
								if(graph.vertexList.get(k).color == 0) {
									graph.vertexList.get(k).dist = graph.vertexList.get(i).dist + graph.vertexList.get(i).vertexConnList.get(j).weight;
									graph.vertexList.get(k).pred = graph.vertexList.get(i);
									graph.vertexList.get(k).color = 1;
									queue.add(graph.vertexList.get(k));
								}
							}
						}
					}
				}
			}
			// now this vertex is tagged as explored
			for(int i = 0; i < graph.vertexList.size(); i++) {
				if(graph.vertexList.get(i).id == currentVertex.id) {
					// checks to see if this is the goal
					if(graph.vertexList.get(i).id != destVillage) {
						graph.vertexList.get(i).color = 2;
						explored.add(graph.vertexList.get(i).id);
					} else {
						// this is the goal vertex, hence it contains the distance to the start
						distPath = graph.vertexList.get(i).dist;
						
						// retrieves the found path
						Vertex vPath = graph.vertexList.get(i); 
						char c = vPath.id;
						path.add(c);
						
						while(c != startVillage) {
							vPath = vPath.pred;
							c = vPath.id;
							path.add(c);
							for(int j = 0; j < graph.vertexList.size(); j++) {
								if(graph.vertexList.get(j).id == vPath.id)
									vPath = graph.vertexList.get(j);
							}
						}
						foundPath = true;
					}
				}
			}
			
			// prints the actual step, the frontier and the explored vertices
			stepCounter++;
			if(showText) {	
				System.out.println("Step " + stepCounter + ". Current node: " + queue.peek().id + "\n");
				System.out.print("\tFrontier: ");
				for(Vertex v : queue) {
					System.out.print(v.id + " ");
				}
				System.out.print("\n\tExplored: ");
				for(char c: explored) {
					System.out.print(c + " ");
				}
				System.out.println("\n");
			}
			
			// if found a path clear/reset all the structures and print the path
			if(foundPath) {
				if(showText) {
					System.out.print("PATH FOUND: ");
					for(int i = (path.size() - 1); i >= 0; i--) {
						System.out.print(path.get(i) + " ");
					}
					System.out.println("");
				}
				queue.clear();
				explored.clear();
				graph.resetGraph();
				if(showText)
					System.out.println("DISTANCE FROM THE START TO THE DESTINATION: " + distPath);
				return distPath;
			}
			
		}
		
		if(!foundPath && showText)
			System.out.print("THERE IS NO PATH BETWEEN THESE TWO VILLAGES\n");
		
		// clean all the used data structures and resets the graph vertices
		queue.clear();
		explored.clear();
		graph.resetGraph();
		
		if(showText)
			System.out.println("");
		return -1;
		
	}
	
	/* ----------------------------------------------------
	 * method responsible for executing a DFS to the given
	 * input graph. The boolean showText is true when
	 * it is needed that DFS prints information in the
	 * screen and returns a solution path (if any)
	 * -------------------------------------------------- */
	public float dfs(Graph graph, char startVillage, char destVillage, boolean showText) {
		
		if(showText) {
			System.out.println("\n**************************************************");
			System.out.println("* DFS - Depth First Search                       *");
			System.out.println("**************************************************");
			System.out.println("\nStart village: " + Character.toUpperCase(startVillage));
			System.out.println("Destination village: " + Character.toUpperCase(destVillage));
			System.out.println("");
		}
		
		Stack<Vertex> stack = new Stack<Vertex>();
		ArrayList<Character> explored = new ArrayList<Character>();
		ArrayList<Character> path = new ArrayList<Character>();	
		float distPath = 0;
		boolean foundPath = false;
		
		// the startVillage is now visited and added to the stack
		for(int i = 0; i < graph.vertexList.size(); i++) {
			if(graph.vertexList.get(i).id == startVillage) {
				graph.vertexList.get(i).dist = 0;
				stack.push(graph.vertexList.get(i));
			}
		}
		
		// while stack is not empty nor the destination village was found proceeds with the search
		int stepCounter = 0;
		
		while(!stack.isEmpty()) {
			
			// pops the stack and starts deepening the three
			Vertex currentVertex = stack.pop();
			
			for(int i = 0; i < graph.vertexList.size(); i++) {
				if(graph.vertexList.get(i).id == currentVertex.id) {
					if(graph.vertexList.get(i).color == 0) {
						graph.vertexList.get(i).color = 1;
						for(int j = 0; j < graph.vertexList.get(i).vertexConnList.size(); j++) {
							for(int k = 0; k < graph.vertexList.size(); k++) {
								if(graph.vertexList.get(k).id == graph.vertexList.get(i).vertexConnList.get(j).id) {
									if(graph.vertexList.get(k).color == 0) {
										graph.vertexList.get(k).dist = graph.vertexList.get(i).dist + graph.vertexList.get(i).vertexConnList.get(j).weight;
										graph.vertexList.get(k).pred = graph.vertexList.get(i);
										stack.push(graph.vertexList.get(k));
									}
								}
							}
						}
					}
				}
			}
			
			// now this vertex is tagged as explored
			for(int i = 0; i < graph.vertexList.size(); i++) {
				if(graph.vertexList.get(i).id == currentVertex.id) {
					// checks to see if this is the goal
					if(graph.vertexList.get(i).id != destVillage) {
						graph.vertexList.get(i).color = 2;
						explored.add(graph.vertexList.get(i).id);
					} else {
						// this is the goal vertex, hence it contains the distance to the start
						distPath = graph.vertexList.get(i).dist;
						
						// retrieves the found path
						Vertex vPath = graph.vertexList.get(i); 
						char c = vPath.id;
						path.add(c);
						
						while(c != startVillage) {
							vPath = vPath.pred;
							c = vPath.id;
							path.add(c);
							for(int j = 0; j < graph.vertexList.size(); j++) {
								if(graph.vertexList.get(j).id == vPath.id)
									vPath = graph.vertexList.get(j);
							}
						}
						foundPath = true;
					}
				}
			}
			
			// prints the actual step, the frontier and the explored vertices
			stepCounter++;
			if(showText) {
				System.out.println("Step " + stepCounter + ". Current node: " + stack.peek().id + "\n");
				System.out.print("\tFrontier: ");
				for(Vertex v : stack) {
					System.out.print(v.id + " ");
				}
				System.out.print("\n\tExplored: ");
				for(char c: explored) {
					System.out.print(c + " ");
				}
				System.out.println("\n");
			}
			
			// if found a path clear/reset all the structures and print the path
			if(foundPath) {
				if(showText) {
					System.out.print("PATH FOUND: ");
					for(int i = (path.size() - 1); i >= 0; i--) {
						System.out.print(path.get(i) + " ");
					}
					System.out.println("");
				}
				stack.clear();
				explored.clear();
				graph.resetGraph();
				if(showText)
					System.out.println("DISTANCE FROM THE START TO THE DESTINATION: " + distPath);
				return distPath;
			}
			
		}
		
		if(!foundPath && showText)
			System.out.print("THERE IS NO PATH BETWEEN THESE TWO VILLAGES\n");
		
		// clean all the used data structures and resets the graph vertex
		stack.clear();
		explored.clear();
		graph.resetGraph();
		
		System.out.println("");
		return -1;
			
	}
	
	/* ----------------------------------------------------
	 * method responsible for generating the matrix showing 
	 * the distances between each village
	 * -------------------------------------------------- */
	public void genVillageArray(Graph graph, int searchMethod) {
		
		// declares the array that will be filled with the distances
		float [][] matrix = new float[graph.n][graph.n];
		
		// initialize all the values of the matrix
		for(int i = 0; i < graph.n; i++) {
			for(int j = 0; j < graph.n; j++) {
				matrix[i][j] = 0;
			}
		}
		
		// given the search method executes either BFS or DFS for every 2 pairs of villages
		// and stores the value in the correspondent matrix spot

		System.out.println("\nFirst column letters represent: START (ST) village");
		System.out.println("First line letters represent: DESTINATION (DT)  village\n");
		
		// BFS case
		if(searchMethod == 1) {
			
			int row = graph.n;
			int col = graph.n; 
			
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					matrix[i][j] = bfs(graph, graph.vertexList.get(i).id, graph.vertexList.get(j).id, false);
				}
			}
			
			col = graph.n;
			System.out.println("");
			
			System.out.print("ST/DT\t");
			for(int i = 0; i < graph.vertexList.size(); i++){
				System.out.print(graph.vertexList.get(i).id + "\t");
			}
			System.out.println("");
			
			for(int i = 0; i < row; i++) {
				System.out.print(graph.vertexList.get(i).id + "\t");
				for(int j = 0; j < col; j++) {
					System.out.print(matrix[i][j] + "\t");
				}
				System.out.println("");
			}
		
		// DFS case
		} else if (searchMethod == 2) {
			
			int row = graph.n;
			int col = graph.n; 
			
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					matrix[i][j] = dfs(graph, graph.vertexList.get(i).id, graph.vertexList.get(j).id, false);
				}
			}
			
			col = graph.n;
			System.out.println("");
			
			System.out.print("\t");
			for(int i = 0; i < graph.vertexList.size(); i++){
				System.out.print(graph.vertexList.get(i).id + "\t");
			}
			System.out.println("");
			
			for(int i = 0; i < row; i++) {
				System.out.print(graph.vertexList.get(i).id + "\t");
				for(int j = 0; j < col; j++) {
					System.out.print(matrix[i][j] + "\t");
				}
				System.out.println("");
			}
			
		}
		
		// calls the method responsible for generating the sub menu
		genSubMenu();
		
	}
	
}
