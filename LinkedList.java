public class LinkedList
{
    private static final int NOT_IN_ARRAY = -1;
    /** Reference to vertex head node (if applicable) */
    protected VertexNode mVertexHead;

    /** Reference to edge head node (if applicable). */
    protected EdgeNode mEdgeHead;

    /** Length of list. */
    protected int mLength;


    /**
     * Default constructor.
     */
    public LinkedList()
    {
        mVertexHead = null;
        mEdgeHead = null;
        mLength = 0;
    } // end of SimpleLinkedList()


    /**
     * Add a new vertex to the start of the list.
     *
     * @param newValue Value to add to list.
     */
    public void addVertex(Vertex newValue)
    {
        VertexNode newVertexNode = new VertexNode(newValue);

        // If head is empty, then list is empty and head reference need to be initialised.
        if (mVertexHead == null)
        {
            mVertexHead = newVertexNode;
        }
        // otherwise, add node to the head of list.
        else
        {
            newVertexNode.setNext(mVertexHead);
            mVertexHead = newVertexNode;
        }

        ++mLength;
    } // end of add()

    /**
     * Add a new edge to the start of the list.
     *
     * @param newEdge Value to add to list.
     */
    public void addEdge(Edge newEdge)
    {
        EdgeNode newEdgeNode = new EdgeNode(newEdge);

        // If head is empty, then list is empty and head reference need to be initialised.
        if (mEdgeHead == null)
        {
            mEdgeHead = newEdgeNode;
        }
        // otherwise, add node to the head of list.
        else
        {
            newEdgeNode.setNext(mEdgeHead);
            mEdgeHead = newEdgeNode;
        }

        ++mLength;
    } // end of add()


    /**
     * Insert value (and corresponding node) at position 'index'.  Indices start at 0.
     *
     * @param index Position in list to add new value to.
     * @param newVertex Value to add to list.
     *
     * @throws IndexOutOfBoundsException Index are out of bounds.
     */
    public void insertVertex(int index, Vertex newVertex) throws IndexOutOfBoundsException
    {
        if (index >= mLength || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        VertexNode newVertexNode = new VertexNode(newVertex);

        if (mVertexHead == null)
        {
            mVertexHead = newVertexNode;
        }
        // list is not empty
        else
        {
            // if index = 0, we should replace mHead with newNode
            if (index == 0)
            {
                newVertexNode.setNext(mVertexHead);
                mVertexHead = newVertexNode;
            }
            else
            {
                VertexNode currVertexNode = mVertexHead;
                for (int i = 0; i < index-1; ++i)
                {
                    currVertexNode = currVertexNode.getNext();
                }

                newVertexNode.setNext(currVertexNode.getNext());
                currVertexNode.setNext(newVertexNode);
            }
        }

        ++mLength;
    } // end of insert()

    /**
     * Insert value (and corresponding node) at position 'index'.  Indices start at 0.
     *
     * @param index Position in list to add new value to.
     * @param newEdge Value to add to list.
     *
     * @throws IndexOutOfBoundsException Index are out of bounds.
     */
    public void insertEdge(int index, Edge newEdge) throws IndexOutOfBoundsException
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
        else
        {
            // if index = 0, we should replace mHead with newNode
            if (index == 0)
            {
                newEdgeNode.setNext(mEdgeHead);
                mEdgeHead = newEdgeNode;
            }
            else
            {
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
    public Vertex getVertex(int index) throws IndexOutOfBoundsException
    {
        if (index >= mLength || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        VertexNode currVertexNode = mVertexHead;
        for (int i = 0; i < index; ++i)
        {
            currVertexNode = currVertexNode.getNext();
        }

        return currVertexNode.getVertex();
    } // end of get()

    /**
     * Returns the value stored in node at position 'index' of list.
     *
     * @param index Position in list to get new value for.
     * @return Value of element at specified position in list.
     *
     * @throws IndexOutOfBoundsException Index is out of bounds.
     */
    public Edge getEdge(int index) throws IndexOutOfBoundsException
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
    public Vertex searchVertex(String name)
    {
        VertexNode currVertexNode = mVertexHead;
        for (int i = 0; i < mLength; ++i)
        {
            if (currVertexNode.getVertex().getName().contentEquals(name))
            {
                return currVertexNode.getVertex();
            }
            currVertexNode = currVertexNode.getNext();
        }

        return null;
    } // end of search()

    /**
     * Searches for the index that contains value.  If value is not present,
     * method returns -1 (NOT_IN_ARRAY).
     * If there are multiple values that could be returned, return the one with
     * the smallest index.
     *
     * @param name Name of vertex to search for.
     * @return Index where value is located, otherwise returns -1 (NOT_IN_ARRAY).
     */
    public Edge searchEdge(String name)
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
    public boolean removeVertex(String name)
    {
        VertexNode prevVertexNode = null;
        VertexNode currVertexNode = mVertexHead;

        for (int i = 0; i < mLength; ++i)
        {
            if (currVertexNode.getVertex().getName().contentEquals(name))
            {
                if(prevVertexNode == null)
                {
                    mVertexHead = currVertexNode.getNext();
                    --mLength;
                    return true;
                }

                else
                {
                    prevVertexNode.setNext(currVertexNode.getNext());
                    --mLength;
                    return true;
                }
            }
            else
            {
                prevVertexNode = currVertexNode;
                currVertexNode = currVertexNode.getNext();
            }
        }

        return false;
    } // end of remove()

    /**
     * Delete given value from list (delete first instance found).
     *
     * @param name Name of vertex to remove.
     * @return True if deletion was successful, otherwise false.
     */
    public boolean removeEdge(String name)
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

                else
                {
                    prevEdgeNode.setNext(currEdgeNode.getNext());
                    --mLength;
                    return true;
                }
            }
            else
            {
                prevEdgeNode = currEdgeNode;
                currEdgeNode = currEdgeNode.getNext();
            }
        }

        return false;
    } // end of remove()

    /**
     * @return String representation of the list.
     */
    public String vertexesToString()
    {
        VertexNode currVertexNode = mVertexHead;

        StringBuffer str = new StringBuffer();

        while (currVertexNode != null)
        {
            str.append(currVertexNode.getVertex().getName() + " ");
            currVertexNode = currVertexNode.getNext();
        }

        return str.toString();
    } // end of toString();

    /**
     * @return String representation of the list.
     */
    public String edgesToString()
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

    /**
     * VertexNode type, inner private class.
     */
    private class VertexNode
    {
        /** Stored value of vertex. */
        protected Vertex vertex;
        /** Reference to next node. */
        protected VertexNode nextVertexNode;

        public VertexNode(Vertex vertex)
        {
            this.vertex = vertex;
            nextVertexNode = null;
        }

        public Vertex getVertex()
        {
            return vertex;
        }


        public VertexNode getNext()
        {
            return nextVertexNode;
        }

        public void setNext(VertexNode next)
        {
            nextVertexNode = next;
        }
    } // end of inner class Node

    /**
     * EdgeNode type, inner private class.
     */
    private class EdgeNode
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

        public Edge getEdge()
        {
            return edge;
        }


        public EdgeNode getNext()
        {
            return nextEdgeNode;
        }

        public void setNext(EdgeNode next)
        {
            nextEdgeNode = next;
        }
    } // end of inner class Node
}
