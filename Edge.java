/**
 * This class will represent an Edge which simply represents a connection between
 * two vertexes that exist in the graph
 */
public class Edge
{
    //First vertex that is connected through this edge
    private Vertex vert1;
    //Second Vertex that is connected through this edge
    private Vertex vert2;

    /**
     * Constructor for instantiating an Edge object
     * @param vert1 Vertex that will be connected using this Edge
     * @param vert2 Vertex that will be connected using this Edge
     */
    public Edge(Vertex vert1, Vertex vert2)
    {
        this.vert1 = vert1;
        this.vert2 = vert2;
    }

    /**
     * Getter that gives the first Vertex connected through this edge
     * @return Vertex connected
     */
    public Vertex getVert1() { return vert1; }

    /**
     * Getter that gives the second Vertex connected through this edge
     * @return Vertex connected
     */
    public Vertex getVert2() { return vert2; }

    /**
     * This method returns a String that will be used to aid with printing operations
     * @return Appropriately formatted String to be utilised
     */
    public String toString()
    {
        StringBuffer string = new StringBuffer();
        string.append(vert1.getName());
        string.append(vert2.getName());
        return string.toString();
    }
}
