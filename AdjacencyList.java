import java.io.PrintWriter;

/**
 * Adjacency list implementation for the AssociationGraph interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan 2019.
 * @author Shaunak Karuna 2021.
 */
public class AdjacencyList extends AbstractGraph
{
    /**
     * An instantiation of the ArrayLinkedList object to store a
     * list of incident vertexes for each vertex in the graph
     */
    private ArrayLinkedList array = new ArrayLinkedList();

    /**
     * An instantiation of the EdgeLinkedList object to store a
     * list of all existing edges in the graph
     */
    private EdgeLinkedList edgeList = new EdgeLinkedList();

    /**
	 * Contructs empty graph.
	 */
    public AdjacencyList()
    { } // end of AdjacencyList()

    /**
     * This method adds a Vertex to the graph
     * @param vertLabel String label of Vertex to add.
     */
    public void addVertex(String vertLabel)
    {
        Vertex vertex = new Vertex(vertLabel);
        VertexLinkedList vertexList = new VertexLinkedList(vertex);
        array.add(vertexList);
    } // end of addVertex()

    /**
     * This method adds an edge between two vertexes in the graph
     * @param srcLabel Name of source vertex of the edge to be added.
     * @param tarLabel Name of target vertex of the edge to be added.
     */
    public void addEdge(String srcLabel, String tarLabel)
    {

        VertexLinkedList verts1 = array.search(srcLabel);
        VertexLinkedList verts2 = array.search(tarLabel);
        Vertex vertex1 = verts1.search(srcLabel);
        Vertex vertex2 = verts2.search(srcLabel);

        //If both vertexes exist then add an edge between them
        if (vertex1 != null && vertex2 != null)
        {
            Edge edge = new Edge(vertex1, vertex2);
            verts1.add(vertex2);
            verts2.add(vertex1);
            edgeList.add(edge);
        }
    } // end of addEdge()

    /**
     * This method toggles the state of the Vertex passed in
     * @param vertLabel Vertex whose state needs to be toggled
     */
    public void toggleVertexState(String vertLabel)
    {
        Vertex vertex = array.search(vertLabel).getmHead().getVertex();

        if (vertex != null)
        {
            vertex.toggleState();
        }
    } // end of toggleVertexState()

    /**
     * This method deletes an existing edge from the graph
     * @param srcLabel Source vertex of the edge that needs to be deleted.
     * @param tarLabel Target vertex of the edge that needs to be deleted.
     */
    public void deleteEdge(String srcLabel, String tarLabel)
    {
        /**
         * String form of the edge is created and then searched for also,
         * Linked List of adjacent vertexes for supplied vertexes are initialised
         */
        StringBuffer name = new StringBuffer();
        name.append(srcLabel);
        name.append(tarLabel);
        VertexLinkedList verts1 = array.search(srcLabel);
        VertexLinkedList verts2 = array.search(tarLabel);
        Edge edge = edgeList.search(name.toString());

        /*
            Edge is removed and vertexes are removed from
            the other vertexes Adjacency Lists respectively
         */
        if (edge != null)
        {
            verts1.remove(tarLabel);
            verts2.remove(srcLabel);
            edgeList.remove(name.toString());
        }
    } // end of deleteEdge()

    /**
     * This method searches for and deletes an existing vertex from the graph
     * @param vertLabel Vertex to delete.
     */
    public void deleteVertex(String vertLabel)
    {
        Vertex vertex = array.search(vertLabel).getmHead().getVertex();
        VertexLinkedList vert = array.search(vertLabel);

        /*
            If the vertex exists, delete it and remove
            all copies of it from any Adjacency List
         */
        if (vertex != null)
        {
            array.remove(vert);
            for (int i = 0; i < array.getLength(); ++i)
            {
                array.get(i).remove(vertLabel);
            }
        }
    } // end of deleteVertex()

    /**
     * This method finds neighbours for all nodes that are of a max of k degree
     * away from the supplied starting vertex
     *
     * @param k the maximum degree away an incident vertex
     * can be from the starting vertex
     * @param vertLabel Vertex to find the k-hop neighbourhood for
     * @return String[] containing the names of all Vertexes at most k degree away
     */
    public String[] kHopNeighbours(int k, String vertLabel)
    {
        String[] neighbours = new String[array.getLength()];
        neighbours[0] = vertLabel;
        int index = 1;

        for (int i = 0; i < k; i++)
        {
            for (String neighbour: neighbours)
            {
                VertexLinkedList list = array.search(neighbour);
                for (int j = 0; j < list.getmLength(); ++j)
                {
                    for (String neighbourNode: neighbours)
                    {
                        Vertex vert = list.get(i).getVertex();
                        if (!vert.getName().contentEquals(neighbourNode))
                        {
                            neighbours[index] = vert.getName();
                            ++index;
                        }
                    }
                }
            }
        }

        return neighbours;
    } // end of kHopNeighbours()

    /**
     * This method prints all existing vertices in the graph
     * @param os PrinterWriter to print to.
     */
    public void printVertices(PrintWriter os)
    {
        os.print(array.print());
    } // end of printVertices()

    /**
     * This method prints all existing edges in the graph
     * @param os PrinterWriter to print to.
     */
    public void printEdges(PrintWriter os)
    {
        os.print(edgeList.ToString());
    } // end of printEdges()

    /**
     * This method returns the SIR state of the supplied Vertex
     * @param vertLabel Vertex to find the state of the vertex.
     * @return SIRState for the vertex in question
     */
    public SIRState currentState(String vertLabel)
    {
        Vertex vertex = array.search(vertLabel).getmHead().getVertex();

        if (vertex != null) { return vertex.getState(); }
        return null;
    }

    /**
     * This method returns a String array containing
     * the names of all the vertexes in the graph
     * @return String[] of names of all vertices
     */
    public String[] listVertices()
    {
        String[] vertices = new String[array.getLength()];
        int index = 0;

        //Iterate through add names of all vertexes
        for(int i=0; i< array.getLength();i++)
        {
            vertices[index] = array.get(i).getmHead().getVertex().getName();
            index++;
        }
        return vertices;
    }
} // end of class AdjacencyList
