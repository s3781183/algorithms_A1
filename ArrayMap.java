import java.util.TreeMap;

/**
 * This class represents an Array of TreeMaps data structure that will be used
 * to hold the values for the Incidence Matrix.
 */
public class ArrayMap
{
    //The Tree Map that will hold the values for the relevant Edge
    private TreeMap<Edge, Integer> collection = new TreeMap<Edge, Integer>();
    //The Vertex that the values held in the map are for
    private Vertex vertex;

    /**
     * This adds an edge to the map with the relevant value
     * @param edge The edge that will be added to the map
     */
    public void addEdge(Edge edge)
    {
        /**
         * If the Vertex the map is representative of is one of the vertexes
         * that is part of the set of vertices of the supplied edge, then
         * represent it in the Incidence Matrix
         */
        if(edge.getVert1().getName() != vertex.getName() ||
                edge.getVert2().getName() != vertex.getName())
        {
            collection.put(edge, 0);
        }

        else
        {
            collection.put(edge, 1);
        }
    }


    /**
     * This method removes the supplied edge from the map
     * @param edge The edge that is to be removed
     */
    public void deleteEdge(Edge edge)
    {
        if (edge.getVert1().getName().matches(vertex.getName()) ||
                edge.getVert2().getName().matches(vertex.getName()))
        {
            collection.remove(edge);
        }
    }

    /**
     * Setter for the Vertex that the values in the map will be representative of
     * @param vertex The Vertex that will be used
     */
    public void setVertex(Vertex vertex) { this.vertex = vertex; }

    /**
     * Getter for the Vertex that the values in the map will be representative of
     * @return The Vertex that has been set
     */
    public Vertex getVertex() { return vertex; }
}
