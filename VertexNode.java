public class VertexNode
{

    /** Stored value of vertex. */
    protected Vertex vertex;
    /** Reference to next node. */
    protected VertexNode nextNode;

    public VertexNode(Vertex vertex)
    {
        this.vertex = vertex;
        nextNode = null;
    }

    public Vertex getVertex()
        {

            return vertex;
        }
        public VertexNode getNext()
        {

            return nextNode;
        }
        public void setNext(VertexNode next)
        {

            nextNode = next;
        }
}
