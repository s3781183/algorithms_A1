
public class VertexLinkedList
{
    private static final int NOT_IN_ARRAY = -1;
    /** Reference to head node */
    private VertexNode mHead;

    /** Reference to tail node */
    private VertexNode mTail;

    /** Length of list. */
    private int mLength;

    /**
     * Default constructor.
     */
    public VertexLinkedList(Vertex head)
    {
        VertexNode headNode = new VertexNode(head);
        mHead = headNode;
        mTail = headNode;
        mLength = 1;
    } // end of SimpleLinkedList()


    /**
     * Add a new vertex to the start of the list.
     *
     * @param newValue Value to add to list.
     */
    public void add(Vertex newValue)
    {
        VertexNode newNode = new VertexNode(newValue);
        mTail.setNext(newNode);
        mTail = newNode;
        ++mLength;
    } // end of add()

    /**
     * Returns the value stored in node at position 'index' of list.
     *
     * @param index Position in list to get new value for.
     * @return Value of element at specified position in list.
     *
     * @throws IndexOutOfBoundsException Index is out of bounds.
     */
    public VertexNode get(int index) throws IndexOutOfBoundsException
    {
        if (index >= mLength || index < 1)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        VertexNode currNode = mHead;
        for (int i = 1; i < index; ++i)
        {
            currNode = currNode.getNext();
        }

        return currNode;
    } // end of get()

    /**
     * Searches for the index that contains value.  If value is not present,
     * method returns -1 (NOT_IN_ARRAY).
     * If there are multiple values that could be returned, return the one with
     * the smallest index.
     *
     * @param name Name of vertex to search for.
     * @return Index where value is located, otherwise returns -1 (NOT_IN_ARRAY).
     */
    public Vertex search(String name)
    {
        VertexNode currNode = mHead;
        if (mLength > 1)
        {
            for (int i = 1; i < mLength; ++i)
            {
                if (currNode.getVertex().getName().contentEquals(name))
                {
                    return currNode.getVertex();
                }
                currNode = currNode.getNext();
            }
        }
        return null;
    } // end of search()

    /**
     * Delete given value from list (delete first instance found).
     *
     * @param name Name of vertex to remove.
     * @return True if deletion was successful, otherwise false.
     */
    public boolean remove(String name)
    {
        VertexNode prevNode = mHead;
        VertexNode currNode = get(1);

        for (int i = 1; i < mLength; ++i)
        {
            if (currNode.getVertex().getName().contentEquals(name))
            {
                prevNode.setNext(currNode.getNext());
                --mLength;
                return true;
            }
            else
            {
                prevNode = currNode;
                currNode = currNode.getNext();
            }
        }

        return false;
    } // end of remove()

    /**
     * @return String representation of the list.
     */
    public String ToString()
    {
        VertexNode currNode = mHead;

        StringBuffer str = new StringBuffer();

        while (currNode != null)
        {
            str.append(currNode.getVertex().getName() + " ");
            currNode = currNode.getNext();
        }

        return str.toString();
    } // end of toString();

    public VertexNode getmHead() { return mHead; }

    public VertexNode getmTail() { return mTail; }

    public int getmLength() { return mLength; }
}
