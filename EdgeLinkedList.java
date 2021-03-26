public class EdgeLinkedList
{
    private static final int NOT_IN_ARRAY = -1;

    /** Reference to edge head node (if applicable). */
    protected EdgeNode mEdgeHead;

    /** Length of list. */
    protected int mLength;

    /**
     * Default constructor.
     */
    public EdgeLinkedList()
    {
        mEdgeHead = null;
        mLength = 0;
    } // end of SimpleLinkedList()

    /**
     * Add a new edge to the start of the list.
     *
     * @param newEdge Value to add to list.
     */
    public void add(Edge newEdge)
    {
        EdgeNode newEdgeNode = new EdgeNode(newEdge);

        // If head is empty, then list is empty and head reference need to be initialised.
        if (mEdgeHead == null)
        {
            mEdgeHead = newEdgeNode;
        }
        // otherwise, add node to the head of list.
        else {
            newEdgeNode.setNext(mEdgeHead);
            mEdgeHead = newEdgeNode;
        }

        ++mLength;
    } // end of add()

    /**
     * Insert value (and corresponding node) at position 'index'.  Indices start at 0.
     *
     * @param index Position in list to add new value to.
     * @param newEdge Value to add to list.
     *
     * @throws IndexOutOfBoundsException Index are out of bounds.
     */
    public void insert(int index, Edge newEdge) throws IndexOutOfBoundsException
    {
        if (index >= mLength || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        EdgeNode newEdgeNode = new EdgeNode(newEdge);

        if (mEdgeHead == null)
        {
            mEdgeHead = newEdgeNode;
        }
        // list is not empty
        else {
            // if index = 0, we should replace mHead with newNode
            if (index == 0)
            {
                newEdgeNode.setNext(mEdgeHead);
                mEdgeHead = newEdgeNode;
            }
            else {
                EdgeNode currEdgeNode = mEdgeHead;
                for (int i = 0; i < index-1; ++i)
                {
                    currEdgeNode = currEdgeNode.getNext();
                }

                newEdgeNode.setNext(currEdgeNode.getNext());
                currEdgeNode.setNext(newEdgeNode);
            }
        }

        ++mLength;
    } // end of insert()


    /**
     * Returns the value stored in node at position 'index' of list.
     *
     * @param index Position in list to get new value for.
     * @return Value of element at specified position in list.
     *
     * @throws IndexOutOfBoundsException Index is out of bounds.
     */
    public Edge get(int index) throws IndexOutOfBoundsException
    {
        if (index >= mLength || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        EdgeNode currEdgeNode = mEdgeHead;
        for (int i = 0; i < index; ++i)
        {
            currEdgeNode = currEdgeNode.getNext();
        }

        return currEdgeNode.getEdge();
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
    public Edge search(String name)
    {
        EdgeNode currEdgeNode = mEdgeHead;
        for (int i = 0; i < mLength; ++i)
        {
            if (currEdgeNode.getEdge().toString().contentEquals(name))
            {
                return currEdgeNode.getEdge();
            }
            currEdgeNode = currEdgeNode.getNext();
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
        EdgeNode prevEdgeNode = null;
        EdgeNode currEdgeNode = mEdgeHead;

        for (int i = 0; i < mLength; ++i)
        {
            if (currEdgeNode.getEdge().toString().contentEquals(name))
            {
                if(prevEdgeNode == null)
                {
                    mEdgeHead = currEdgeNode.getNext();
                    --mLength;
                    return true;
                }

                else {
                    prevEdgeNode.setNext(currEdgeNode.getNext());
                    --mLength;
                    return true;
                }
            }
            else {
                prevEdgeNode = currEdgeNode;
                currEdgeNode = currEdgeNode.getNext();
            }
        }

        return false;
    } // end of remove()

    /**
     * @return String representation of the list.
     */
    public String ToString()
    {
        EdgeNode currEdgeNode = mEdgeHead;

        StringBuffer str = new StringBuffer();

        while (currEdgeNode != null)
        {
            str.append(currEdgeNode.getEdge().toString() + " ");
            currEdgeNode = currEdgeNode.getNext();
        }

        return str.toString();
    } // end of toString();
}
