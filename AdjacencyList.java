import java.io.PrintWriter;
// import java.util.*;

/**
 * Adjacency list implementation for the AssociationGraph interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class AdjacencyList extends AbstractGraph
{
    LinkedList vertexList = new LinkedList();
    LinkedList edgeList = new LinkedList();

    /**
	 * Contructs empty graph.
	 */
    public AdjacencyList()
    {
    	 // Implement me!

    } // end of AdjacencyList()


    public void addVertex(String vertLabel)
    {
        Vertex vertex = new Vertex(vertLabel);
        vertexList.addVertex(vertex);
    } // end of addVertex()


    public void addEdge(String srcLabel, String tarLabel)
    {
        Vertex vertex1 = vertexList.searchVertex(srcLabel);
        Vertex vertex2 = vertexList.searchVertex(tarLabel);

        if (vertex1 != null && vertex2 != null)
        {
            Edge edge = new Edge(vertex1, vertex2);
            edgeList.addEdge(edge);
        }
    } // end of addEdge()


    public void toggleVertexState(String vertLabel)
    {
        Vertex vertex = vertexList.searchVertex(vertLabel);

        if (vertex != null)
        {
            if (vertex.getState() == SIRState.S)
            {
                vertex.setState(SIRState.I);
            }
            else if (vertex.getState() == SIRState.I)
            {
                vertex.setState(SIRState.R);
            }
        }
    } // end of toggleVertexState()


    public void deleteEdge(String srcLabel, String tarLabel)
    {
        StringBuffer name = new StringBuffer();
        name.append(srcLabel);
        name.append(tarLabel);
        Edge edge = edgeList.searchEdge(name.toString());

        if (edge != null)
        {
            edgeList.removeEdge(name.toString());
        }
    } // end of deleteEdge()


    public void deleteVertex(String vertLabel)
    {
        Vertex vertex = vertexList.searchVertex(vertLabel);

        if (vertex != null)
        {
            edgeList.removeEdge(vertLabel);
        }
    } // end of deleteVertex()


    public String[] kHopNeighbours(int k, String vertLabel) {
        // Implement me!

        // please update!
        return null;
    } // end of kHopNeighbours()


    public void printVertices(PrintWriter os)
    {
        os.print(vertexList.vertexesToString());
    } // end of printVertices()


    public void printEdges(PrintWriter os)
    {
        os.print(edgeList.edgesToString());
    } // end of printEdges()

} // end of class AdjacencyList
