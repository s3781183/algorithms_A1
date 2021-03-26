public class EdgeNode
{
    /** Stored value of edge. */
    protected Edge edge;
    /** Reference to next node. */
    protected EdgeNode nextEdgeNode;

    public EdgeNode(Edge edge)
    {
        this.edge = edge;
        nextEdgeNode = null;
    }

    public Edge getEdge() { return edge; }


    public EdgeNode getNext() { return nextEdgeNode; }

    public void setNext(EdgeNode next)
        {
            nextEdgeNode = next;
        }
}
