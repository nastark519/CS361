
/**
 * @author Nathan Stark
 * 
 * The following code was provided by Aakash Hasija.
 * From https://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
 *
 */
public class Lab4 {

    // A class to represent a weighted edge in graph
    class Edge {
        int src, dest, weight;      // Where its coming from, where its going, and how heavy. Respectively.
        Edge() {
            src = dest = weight = 0;
        }
    };
    
    //fields of Lab 4 in order to implement the Bellman-Ford Algorithm.
    int V, E;
    Edge edge[];
	
    /**
     * The constructed class, that initializes the values of how many edges
     * and how many Vertices are included on the graph.
     * 
     * @param v the amount of vertices that are included with the graph.
     * @param e the amount of edges connecting the vertices.
     */
    Lab4(int v, int e){
    	V=v;
    	E = e;
    	edge = new Edge[e];
    	for(int i=0; i<e; ++i){
    		edge[i]= new Edge();
    	}
    }
    
    public void bellmanFord(Lab4 graph, int src){
    	int i, j; 										// index variables.
    	int V = graph.V, E = graph.E;
    	int dist[] = new int[V];						// Distance array set to the length of how many vertices are on the graph.
    	
    	for(i=0;i<V;++i){								// Init. the distances to unreachable values.
    		dist[i] = Integer.MAX_VALUE;				// Note here that we are using the max possible value for integers instead of infinity, b/c infinity is not a number. 
    	}
    	dist[src] = 0;									// Starting distance will always be 0.
    	
    	for(i=0;i<V;++i){
    		for(j=0;j<E;++j){
    			int u = graph.edge[j].src;				// Set u to be the vertex that we are coming from.
    			int v = graph.edge[j].dest;				// Set v to be the vertex that we are going to.
    			int weight = graph.edge[j].weight;		// set the weight to be the weight between u and v.
    			if(dist[u]!=Integer.MAX_VALUE && dist[v]+weight<dist[v]){
    				dist[v]=dist[u]+weight;				// set the new weigh if the it passes the check above.
    			}
    		}
    	}
    	
    	for(j=0;j<E;++j){
    		int u = graph.edge[j].src;					// Set u to be the vertex that we are coming from.
			int v = graph.edge[j].dest;					// Set v to be the vertex that we are going to.
			int weight = graph.edge[j].weight;			// set the weight to be the weight between u and v.
			if (dist[u] != Integer.MAX_VALUE && dist[u]+weight < dist[v]){
				System.out.println("Graph contains negative weight cycle");//detect if there is a negative weight on the graph.
			}
    	}
    	printArray(dist,V);								// call the utility function to show the results.
    }
    
    /**
     * Utility method used to print the results.
     * 
     * @param dist the distance array.
     * @param V The Vertex.
     */
    private void printArray(int dist[], int V){
    	System.out.println("Vertex   Distance from Source");
    	for(int i=0; i<V;++i){
    		System.out.println(i+"\t\t"+dist[i]);
    	}
    }
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int V = 14;
		int E = 21;
		
		Lab4 graph = new Lab4(V,E);
		
		// a-d
		graph.edge[0].src = 0;
		graph.edge[0].dest = 3;
		graph.edge[0].weight = 3;
		//b-a
		graph.edge[1].src = 1;
		graph.edge[1].dest = 0;
		graph.edge[1].weight = -2;
		//c-b
		graph.edge[2].src = 2;
		graph.edge[2].dest = 1;
		graph.edge[2].weight = 1;
		//c-n
		graph.edge[3].src = 2;
		graph.edge[3].dest = 13;
		graph.edge[3].weight = -3;
		//c-m
		graph.edge[4].src = 2;
		graph.edge[4].dest = 12;
		graph.edge[4].weight = 3;
		//d-e
		graph.edge[5].src = 3;
		graph.edge[5].dest = 4;
		graph.edge[5].weight = 2;
		//d-f
		graph.edge[6].src = 3;
		graph.edge[6].dest = 5;
		graph.edge[6].weight = 6;
		//d-g
		graph.edge[7].src = 3;
		graph.edge[7].dest = 6;
		graph.edge[7].weight = -1;
		//d-n
		graph.edge[8].src = 3;
		graph.edge[8].dest = 13;
		graph.edge[8].weight = -1;
		//e-f
		graph.edge[9].src = 4;
		graph.edge[9].dest = 5;
		graph.edge[9].weight = 3;
		//f-h
		graph.edge[10].src = 5;
		graph.edge[10].dest = 7;
		graph.edge[10].weight = -2;
		//g-h
		graph.edge[11].src = 5;
		graph.edge[11].dest = 7;
		graph.edge[11].weight = 1;
		//g-j
		graph.edge[12].src = 5;
		graph.edge[12].dest = 9;
		graph.edge[12].weight = 3;
		//h-k
		graph.edge[13].src = 7;
		graph.edge[13].dest = 10;
		graph.edge[13].weight = -1;
		//i-h
		graph.edge[14].src = 8;
		graph.edge[14].dest = 7;
		graph.edge[14].weight = -4;
		//j-i
		graph.edge[15].src = 9;
		graph.edge[15].dest = 8;
		graph.edge[15].weight = 2;
		//j-k
		graph.edge[16].src = 9;
		graph.edge[16].dest = 10;
		graph.edge[16].weight = 3;
		//l-k
		graph.edge[17].src = 11;
		graph.edge[17].dest = 10;
		graph.edge[17].weight = 2;
		//m-l
		graph.edge[18].src = 12;
		graph.edge[18].dest = 11;
		graph.edge[18].weight = -4;
		//n-m
		graph.edge[19].src = 13;
		graph.edge[19].dest = 12;
		graph.edge[19].weight = 8;
		//n-c
		graph.edge[20].src = 13;
		graph.edge[20].dest = 2;
		graph.edge[20].weight = -3;
		
		graph.bellmanFord(graph, 0);
	}

}
