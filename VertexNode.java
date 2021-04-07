/**
 * This class represents a Node in the VertexLinkedList object
 * which will store an vertex and the next VertexNode in the list each
 */
public class VertexNode
{
    //Stored Edge Value
    private Vertex vertex;

    //Stored value to the next EdgeNode in the List
    private VertexNode nextNode;

    /**
     * Constructor for VertexNode object
     * @param vertex The Vertex object that will be stored in this node
     */
    public VertexNode(Vertex vertex)
    {
        this.vertex = vertex;
        nextNode = null;
    }

    /**
     * This is a getter method for the vertex value stored
     * @return Vertex that is stored
     */
    public Vertex getVertex() { return vertex; }

    /**
     * This is a getter method for the next VertexNode in the list
     * @return VertexNode that is the next value in the list
     */
    public VertexNode getNext() { return nextNode; }

    /**
     * This is a setter for the next value in the list
     * @param next VertexNode that is next in the list
     */
    public void setNext(VertexNode next) { nextNode = next; }
}