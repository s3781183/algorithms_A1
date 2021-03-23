import java.io.PrintWriter;

/**
 * Graph interface used to implement the graphs representations.
 *
 * Note, you should not need to modify this, but if you do, make sure the
 * changes compile.
 *
 * @author Jeffrey Chan, 2021.
 */
public interface ContactsGraph
{

	/**
	 * Adds a vertex to the graph.  If the vertex already exists in the graph, no changes are made.
	 *
	 * @param vertLabel Vertex to add.
	 */
	public abstract void addVertex(String vertLabel);


	/**
	 * Adds an edge to the graph.  If the edge already exists in the graph, no changes are made.  If one of the vertices doesn't exist, a warning to System.err should be issued
	 * and no edge or vertices should be added.
	 *
	 * @param srcLabel Source vertex of edge to add.
	 * @param tarLabel Target vertex of edge to add.
	 */
	public abstract void addEdge(String srcLabel, String tarLabel);


	/**
	 * Toggles SIR state of vertex.  If vertex doesn't exist, then a warning to System.err should be issued.
	 *
	 * @param vertLabel Vertex to add.
	 */
	public abstract void toggleVertexState(String vertLabel);


	/**
	 * Deletes an edge from the graph.  If the edge doesn't exists in the graph, no changes are made, but a warning to System.err should be issued.
	 *
	 * @param srcLabel Source vertex of edge to delete.
	 * @param tarLabel Target vertex of edge to delete.
	 */
	public abstract void deleteEdge(String srcLabel, String tarLabel);


	/**
	 * Deletes a vertex from the graph.  If the vertex doesn't exists in the graph, no changes are made, but a warning to System.err should be issued.
	 *
	 * @param vertLabel Vertex to delete.
	 */
	public abstract void deleteVertex(String vertLabel);


	/**
     * Returns the set of in-neighbours for a vertex.  If vertex doesn't exist, then a warning to System.err should be issued.
     *
     * @param vertLabel Vertex to find the k-hop neighbourhood for.
     *
     * @returns An array of all k-hop neighbours of vertex 'vertLabel'.  There should be no duplicated edges in the returned array.
     */
    public abstract String[] kHopNeighbours(int k, String vertLabel);



	/**
	 * Prints the list of vertices to PrintWriter 'os'.
	 *
	 * @param os PrinterWriter to print to.
	 */
	public abstract void printVertices(PrintWriter os);


	/**
	 * Prints the list of edges to PrintWriter 'os'.
	 *
	 * @param os PrinterWriter to print to.
	 */
	public abstract void printEdges(PrintWriter os);

} // end of interface ContactsGraph
