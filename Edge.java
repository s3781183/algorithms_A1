public class Edge
{
    private Vertex vert1;
    private Vertex vert2;

    public Edge(Vertex vert1, Vertex vert2)
    {
        this.vert1 = vert1;
        this.vert2 = vert2;
    }

    public Vertex getVert1() { return vert1; }

    public Vertex getVert2() { return vert2; }
}