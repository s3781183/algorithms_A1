import java.io.PrintWriter;
/**
 * This class is an implementation for the Adjacency list.
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

    /** Contructs empty graph.*/
    public AdjacencyList()
    { }

    /**
     * This method adds a Vertex to the graph
     * @param vertLabel String label of Vertex to add.
     */
    public void addVertex(String vertLabel)
    {
        Vertex vertex = new Vertex(vertLabel);
        VertexLinkedList vertexList = new VertexLinkedList(vertex);
        array.add(vertexList);
    }

    /**
     * This method adds an edge between two vertexes in the graph
     * @param srcLabel Name of source vertex of the edge to be added.
     * @param tarLabel Name of target vertex of the edge to be added.
     */
    public void addEdge(String srcLabel, String tarLabel)
    {

        VertexLinkedList verts1 = array.search(srcLabel);
        VertexLinkedList verts2 = array.search(tarLabel);
        Vertex vertex1 = verts1.getmHead().getVertex();
        Vertex vertex2 = verts2.getmHead().getVertex();

        //If both vertexes exist then add an edge between them
        if (vertex1 != null && vertex2 != null)
        {
            Edge edge = new Edge(vertex1, vertex2);
            verts1.add(vertex2);
            verts2.add(vertex1);
            edgeList.add(edge);
        }
    }

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
    }

    /**
     * This method deletes an existing edge from the graph
     * @param srcLabel Source vertex of the edge that needs to be deleted.
     * @param tarLabel Target vertex of the edge that needs to be deleted.
     */
    public void deleteEdge(String srcLabel, String tarLabel)
    {
        /*
         * String form of the edge is created and then searched for also,
         * Linked List of adjacent vertexes for supplied vertexes are initialised
         */
        StringBuilder name = new StringBuilder();
        name.append(srcLabel);
        name.append(tarLabel);
        VertexLinkedList verts1 = array.search(srcLabel);
        VertexLinkedList verts2 = array.search(tarLabel);
        Edge edge = edgeList.search(name.toString());

        /*
         * Edge is removed and vertexes are removed from
         * the other vertexes Adjacency Lists respectively
         */
        if (edge != null)
        {
            verts1.remove(tarLabel);
            verts2.remove(srcLabel);
            edgeList.remove(edge.toString());
        }
    }

    /**
     * This method searches for and deletes an existing vertex from the graph
     * @param vertLabel Vertex to delete.
     */
    public void deleteVertex(String vertLabel)
    {
        VertexLinkedList vertexLinkedList = array.search(vertLabel);
        Vertex vertex = vertexLinkedList.getmHead().getVertex();
        /*
         *  If the vertex exists, delete it and remove
         *  all copies of it from any Adjacency List
         */
        if (vertex != null)
        {
            //Removes all edges that involve the Vertex to be deleted
            for(int i = 0; i < edgeList.getmLength(); ++i)
            {
                if (edgeList.get(i).getVert1().getName().matches(vertLabel)
                || edgeList.get(i).getVert2().getName().matches(vertLabel))
                {
                    edgeList.remove(edgeList.get(i).toString());
                    --i;
                }
            }

            array.remove(vertexLinkedList);
            for (int i = 0; i < array.getLength(); ++i)
            {
                if (array.search(vertLabel) != null)
                {
                    array.get(i).remove(vertLabel);
                }
            }
        }
    }

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
        String[] neighbours = new String[0];

        for (int i = 0; i < k; ++i)
        {
            //If there are no neighbours in the list
            if (i == 0)
            {
                for (int j = 0; j < edgeList.getmLength(); ++j)
                {
                    if (edgeList.get(j).getVert1().getName().matches(vertLabel))
                    {
                        neighbours = copyNeighbours(neighbours, j);
                    }
                }
            }

            //If there are neighbours in the list
            else
            {
                for (int m = 0; m < neighbours.length; ++m)
                {
                    for (int j = 0; j < edgeList.getmLength(); ++j)
                    {
                        if (edgeList.get(j).getVert1().getName().matches(neighbours[m]))
                        {
                            boolean present = false;
                            //Check if the vertex has already been added
                            for (int n = 0; n < neighbours.length; ++n)
                            {
                                if (neighbours[n].matches(edgeList.get(j).getVert2().getName()))
                                {
                                    present = true;
                                }
                            }
                            //If vertex is not present, then add it to the list
                            if (!present)
                            {
                                neighbours = copyNeighbours(neighbours, j);
                            }
                        }
                    }
                }
            }
        }

        return neighbours;
    }

    private String[] copyNeighbours(String[] neighbours, int j)
    {
        String[] tempNeighbours = new String[neighbours.length + 1];
        for (int l = 0; l < neighbours.length; ++l)
        {
            tempNeighbours[l] = neighbours[l];
        }
        tempNeighbours[tempNeighbours.length - 1] = edgeList.get(j).getVert2().getName();
        neighbours = tempNeighbours;
        return neighbours;
    }

    /**
     * This method prints all existing vertices in the graph
     * @param os PrinterWriter to print to.
     */
    public void printVertices(PrintWriter os)
    {
        os.print(array.print() + "\n");
        os.flush();
    }

    /**
     * This method prints all existing edges in the graph
     * @param os PrinterWriter to print to.
     */
    public void printEdges(PrintWriter os)
    {
        os.print(edgeList.toString() + "\n");
        os.flush();
    }

    @Override
    public SIRState currentState(String vertLabel)
    {
        for (int i = 0; i < array.getLength(); ++i)
        {
            Vertex vertex = array.get(i).getmHead().getVertex();
            if (vertex.getName().matches(vertLabel))
            {
                return vertex.getState();
            }
        }
        return null;
    }

    @Override
    public String[] listVertices()
    {
        String[] vertices = new String[array.getLength()];

        for (int i = 0; i < array.getLength(); ++i)
        {
            vertices[i] = array.get(i).getmHead().getVertex().getName();
        }
        return vertices;
    }
}