import java.util.TreeMap;

public class ArrayMap
{
    private TreeMap<Edge, Integer> collection = new TreeMap<Edge, Integer>();
    private Vertex vertex;

    public void addEdge(Edge edge)
    {
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

    public void deleteEdge(Edge edge)
    {
        if (edge.getVert1().getName().matches(vertex.getName()) ||
                edge.getVert2().getName().matches(vertex.getName()))
        {
            collection.remove(edge);
        }
    }

    public void setVertex(Vertex vertex)
    {
        this.vertex = vertex;
    }

    public Vertex getVertex()
    {
        return vertex;
    }
}
