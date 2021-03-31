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
    private ArrayLinkedList array;
    private EdgeLinkedList edgeList;

    /**
	 * Contructs empty graph.
	 */
    public AdjacencyList()
    {
    	 array = new ArrayLinkedList();
    	 edgeList = new EdgeLinkedList();
    } // end of AdjacencyList()


    public void addVertex(String vertLabel)
    {
        Vertex vertex = new Vertex(vertLabel);
        VertexLinkedList vertexList = new VertexLinkedList(vertex);
        array.add(vertexList);
    } // end of addVertex()


    public void addEdge(String srcLabel, String tarLabel)
    {
        VertexLinkedList verts1 = array.search(srcLabel);
        VertexLinkedList verts2 = array.search(tarLabel);
        Vertex vertex1 = verts1.search(srcLabel);
        Vertex vertex2 = verts2.search(srcLabel);

        if (vertex1 != null && vertex2 != null)
        {
            Edge edge = new Edge(vertex1, vertex2);
            verts1.add(vertex2);
            verts2.add(vertex1);
            edgeList.add(edge);
        }
    } // end of addEdge()


    public void toggleVertexState(String vertLabel)
    {
        Vertex vertex = array.search(vertLabel).getmHead().getVertex();

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
        VertexLinkedList verts1 = array.search(srcLabel);
        VertexLinkedList verts2 = array.search(tarLabel);
        Edge edge = edgeList.search(name.toString());

        if (edge != null)
        {
            verts1.remove(tarLabel);
            verts2.remove(srcLabel);
            edgeList.remove(name.toString());
        }
    } // end of deleteEdge()


    public void deleteVertex(String vertLabel)
    {
        Vertex vertex = array.search(vertLabel).getmHead().getVertex();
        VertexLinkedList verts1 = array.search(vertLabel);

        if (vertex != null)
        {
            array.remove(verts1);
            for (int i = 0; i < array.getLength(); ++i)
            {
                array.get(i).remove(vertLabel);
            }
        }
    } // end of deleteVertex()


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


    public void printVertices(PrintWriter os)
    {
        os.print(array.print());
    } // end of printVertices()


    public void printEdges(PrintWriter os)
    {
        os.print(edgeList.ToString());
    } // end of printEdges()

    public SIRState currentState(String vertLabel)
    {
        Vertex vertex = array.search(vertLabel).getmHead().getVertex();

        if (vertex != null) {
            return vertex.getState();
        }
        return null;
    }

    public String[] listVertices()
    {
        String[] vertices = new String[array.getLength()];
        int index =0;
        for(int i=0; i< array.getLength();i++){
            vertices[index] = array.get(i).getmHead().getVertex().getName();
            index++;

        }
        return vertices;
    }
} // end of class AdjacencyList
