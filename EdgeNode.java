/**
 * This class represents a Node in the EdgeLinkedList object
 * which will store an edge and the next EdgeNode in the list each
 */
public class EdgeNode
{
    //Stored Edge Value
    private Edge edge;

    //Stored value to the next EdgeNode in the List
    private EdgeNode nextEdgeNode;

    /**
     * Constructor for EdgeNode object
     * @param edge The edge object that will be stored in this node
     */
    public EdgeNode(Edge edge)
    {
        this.edge = edge;
        nextEdgeNode = null;
    }

    /**
     * This is a getter method for the edge value stored
     * @return Edge that is stored
     */
    public Edge getEdge() { return edge; }

    /**
     * This is a getter method for the next EdgeNode in the list
     * @return EdgeNode that is the next value in the list
     */
    public EdgeNode getNext() { return nextEdgeNode; }

    /**
     * This is a setter for the next value in the list
     * @param next EdgeNode that is next in the list
     */
    public void setNext(EdgeNode next) { nextEdgeNode = next;}
}