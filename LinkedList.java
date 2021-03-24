public class LinkedList
{
    private static final int NOT_IN_ARRAY = -1;
    /** Reference to head node. */
    protected Node mHead;

    /** Length of list. */
    protected int mLength;


    /**
     * Default constructor.
     */
    public LinkedList()
    {
        mHead = null;
        mLength = 0;
    } // end of SimpleLinkedList()


    /**
     * Add a new value to the start of the list.
     *
     * @param newValue Value to add to list.
     */
    public void add(Vertex newValue)
    {
        Node newNode = new Node(newValue);

        // If head is empty, then list is empty and head reference need to be initialised.
        if (mHead == null)
        {
            mHead = newNode;
        }
        // otherwise, add node to the head of list.
        else
        {
            newNode.setNext(mHead);
            mHead = newNode;
        }

        mLength++;
    } // end of add()


    /**
     * Insert value (and corresponding node) at position 'index'.  Indices start at 0.
     *
     * @param index Position in list to add new value to.
     * @param newValue Value to add to list.
     *
     * @throws IndexOutOfBoundsException Index are out of bounds.
     */
    public void insert(int index, int newValue) throws IndexOutOfBoundsException
    {
        if (index >= mLength || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        Node newNode = new Node(newValue);

        if (mHead == null)
        {
            mHead = newNode;
        }
        // list is not empty
        else
        {
            // if index = 0, we should replace mHead with newNode
            if (index == 0)
            {
                newNode.setNext(mHead);
                mHead = newNode;
            }
            else
            {
                Node currNode = mHead;
                for (int i = 0; i < index-1; ++i)
                {
                    currNode = currNode.getNext();
                }

                newNode.setNext(currNode.getNext());
                currNode.setNext(newNode);
            }
        }

        mLength += 1;
    } // end of insert()


    /**
     * Returns the value stored in node at position 'index' of list.
     *
     * @param index Position in list to get new value for.
     * @return Value of element at specified position in list.
     *
     * @throws IndexOutOfBoundsException Index is out of bounds.
     */
    public int get(int index) throws IndexOutOfBoundsException
    {
        if (index >= mLength || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        Node currNode = mHead;
        for (int i = 0; i < index; ++i)
        {
            currNode = currNode.getNext();
        }

        return currNode.getVertex();
    } // end of get()


    /**
     * Searches for the index that contains value.  If value is not present,
     * method returns -1 (NOT_IN_ARRAY).
     * If there are multiple values that could be returned, return the one with
     * the smallest index.
     *
     * @param value Value to search for.
     * @return Index where value is located, otherwise returns -1 (NOT_IN_ARRAY).
     */
    public int search(int value)
    {
        Node currNode = mHead;
        for (int i = 0; i < mLength; ++i)
        {
            if (currNode.getVertex() == value)
            {
                return i;
            }
            currNode = currNode.getNext();
        }

        return NOT_IN_ARRAY;
    } // end of search()



    /**
     * Delete given value from list (delete first instance found).
     *
     * @param value Value to remove.
     * @return True if deletion was successful, otherwise false.
     */
    public boolean remove(int value)
    {
        Node prevNode = null;
        Node currNode = mHead;

        for (int i = 0; i < mLength; ++i)
        {
            if (currNode.getVertex() == value)
            {
                if(prevNode == null)
                {
                    mHead = currNode.getNext();
                    --mLength;
                    return true;
                }

                else
                {
                    prevNode.setNext(currNode.getNext());
                    --mLength;
                    return true;
                }
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
     * Delete value (and corresponding node) at position 'index'.  Indices start at 0.
     *
     * @param index Position in list to get new value for.
     * @param dummy Dummy variable, serves no use apart from distinguishing overloaded methods.
     * @return Value of node that was deleted.
     *
     * @throws IndexOutOfBoundsException Index is out of bounds.
     */
    public int remove(int index, boolean dummy) throws IndexOutOfBoundsException
    {
        if (index >= mLength || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        Node prevNode = null;
        Node currNode = mHead;

        for (int i = 0; i <= index; ++i)
        {
            if (i == index)
            {
                if(prevNode == null)
                {
                    mHead = currNode.getNext();
                    --mLength;
                    return 1;
                }

                else
                {
                    prevNode.setNext(currNode.getNext());
                    --mLength;
                    return 1;
                }
            }

            prevNode = currNode;
            currNode = currNode.getNext();
        }

        return NOT_IN_ARRAY;
    } // end of remove()


    /**
     * Returns the minimum value in the list.
     *
     * @return Minimum value in the list.
     *
     * @throws IndexOutOfBoundsException Index is out of bounds.
     */
    public int min() throws IllegalStateException
    {
        if (mLength == 0)
        {
            throw new IllegalStateException("Min is not defined for an empty list.");
        }

        // traverse list, looking for the minimum valued element
        int minValue = mHead.getVertex();
        Node currNode = mHead.getNext();

        while (currNode != null) {
            if (currNode.getVertex() < minValue)
            {
                minValue = currNode.getVertex();
            }
            currNode = currNode.getNext();
        }

        return minValue;
    } // end of min()


    /**
     * Returns the maximum value in the list.
     *
     * @return Maximum value in the list.
     *
     * @throws IndexOutOfBoundsException Index are out of bounds.
     */
    public int max() throws IndexOutOfBoundsException
    {
        if (mLength == 0)
        {
            throw new IllegalStateException("Max is not defined for an empty list.");
        }

        // traverse list, looking for the minimum valued element
        int maxValue = mHead.getVertex();
        Node currNode = mHead.getNext();

        while (currNode != null)
        {
            if (currNode.getVertex() > maxValue)
            {
                maxValue = currNode.getVertex();
            }
            currNode = currNode.getNext();
        }

        return maxValue;
    } // end of max()



    /**
     * Print the list in head to tail.
     */
    public void print()
    {
        System.out.println(toString());
    } // end of print()

    /**
     * @return String representation of the list.
     */
    public String toString()
    {
        Node currNode = mHead;

        StringBuffer str = new StringBuffer();

        while (currNode != null)
        {
            str.append(currNode.getVertex() + " ");
            currNode = currNode.getNext();
        }

        return str.toString();
    } // end of toString();

    /**
     * Node type, inner private class.
     */
    private class Node
    {
        /** Stored value of vertex. */
        protected Vertex vertex;
        /** Reference to next node. */
        protected Node nextNode;

        public Node(Vertex vertex)
        {
            this.vertex = vertex;
            nextNode = null;
        }

        public Vertex getVertex()
        {
            return vertex;
        }


        public Node getNext()
        {
            return nextNode;
        }

        public void setNext(Node next)
        {
            nextNode = next;
        }
    } // end of inner class Node
}
