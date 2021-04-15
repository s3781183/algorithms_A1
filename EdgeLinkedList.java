/**
 * This class represents a Linked List that stores edges
 */
public class EdgeLinkedList
{
    /** The head of the list*/
    private EdgeNode mEdgeHead;

    /** The length of list. */
    private int mLength;

    /**
     * Default constructor.
     */
    public EdgeLinkedList()
    {
        mEdgeHead = null;
        mLength = 0;
    }

    /**
     * This method adds a new edge to the end of the list.
     * @param newEdge Value to add to list.
     */
    public void add(Edge newEdge)
    {
        EdgeNode newEdgeNode = new EdgeNode(newEdge);

        // If head is empty, then list is empty and head reference need to be initialised.
        if (mEdgeHead != null)
        {
            newEdgeNode.setNext(mEdgeHead);
        }
        mEdgeHead = newEdgeNode;
        ++mLength;
    }

    /**
     * This metod returns the value stored
     * in node at position 'index' of list.
     * @param index position in list to get value for.
     * @return edge at specified position in list.
     * @throws IndexOutOfBoundsException Index is out of bounds.
     */
    public Edge get(int index) throws IndexOutOfBoundsException
    {
        if (index > mLength || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        EdgeNode currEdgeNode = mEdgeHead;
        if (mLength != 0)
        {
            for (int i = 0; i < index; ++i)
            {
                currEdgeNode = currEdgeNode.getNext();
            }
            return currEdgeNode.getEdge();
        }
        return null;
    }

    /**
     * This method searches for the index that contains value.
     * If value is not present, method returns null.
     * @param name Name of edge to search for.
     * @return Index where value is located.
     */
    public Edge search(String name)
    {
        EdgeNode currEdgeNode = mEdgeHead;
        for (int i = 0; i < mLength; ++i)
        {
            if (currEdgeNode.getEdge().toString().matches(name))
            {
                return currEdgeNode.getEdge();
            }
            currEdgeNode = currEdgeNode.getNext();
        }

        return null;
    }

    /**
     * This method deletes the first found
     * instance of a given value from the list
     * @param name Name of edge to remove.
     * @return True if deletion was successful, otherwise false.
     */
    public boolean remove(String name)
    {
        EdgeNode prevEdgeNode = null;
        EdgeNode currEdgeNode = mEdgeHead;

        while(currEdgeNode != null)
        {
            if (currEdgeNode.getEdge().toString().contentEquals(name))
            {
                if(prevEdgeNode == null)
                {
                    mEdgeHead = null;
                }
                else
                {
                    prevEdgeNode.setNext(currEdgeNode.getNext());
                }
                --mLength;
                return true;
            }
            else
            {
                prevEdgeNode = currEdgeNode;
                currEdgeNode = currEdgeNode.getNext();
            }
        }
        return false;
    }

    /**
     * This method provides a String that is
     * useful for operations such as printing.
     * @return String representation of the list.
     */
    @Override
    public String toString()
    {
        EdgeNode currEdgeNode = mEdgeHead;

        StringBuffer str = new StringBuffer();

        while (currEdgeNode != null)
        {
            str.append(currEdgeNode.getEdge().toString() + " ");
            currEdgeNode = currEdgeNode.getNext();
        }
        return str.toString();
    }

    /** This method is a getter for the length of the list */
    public int getmLength() { return mLength; }
}