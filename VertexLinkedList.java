/**
 * This class reprsents a LinkedList object that stores Vertexes
 * to be used to assist with implementations of the Adjacency List
 */
public class VertexLinkedList
{
    //The Node that is at the head of this linked list
    private VertexNode mHead;

    //The Node that is at the tail of this linked list
    private VertexNode mTail;

    //The length of the linked list
    private int mLength;

    /**
     * Default constructor for instantiating a VertexLinkedList object
     * @param head A Vertex that will be used as the head of the list
     */
    public VertexLinkedList(Vertex head)
    {
        VertexNode headNode = new VertexNode(head);
        mHead = headNode;
        mTail = headNode;
        mLength = 1;
    }


    /**
     * This method adds a new vertex to the end of the list
     * @param newValue Vertex to add to list.
     */
    public void add(Vertex newValue)
    {
        VertexNode newNode = new VertexNode(newValue);
        mTail.setNext(newNode);
        mTail = newNode;
        ++mLength;
    }

    /**
     * This method returns the value stored in node at position 'index' of list
     * @param index Position in list to get value of
     * @return Value of element at the specified position in list.
     * @throws IndexOutOfBoundsException if index is out of bounds.
     */
    public VertexNode get(int index) throws IndexOutOfBoundsException
    {
        if (index > mLength || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        VertexNode currVertexNode = mHead;
        if (mLength != 0)
        {
            for (int i = 0; i < index; ++i)
            {
                currVertexNode = currVertexNode.getNext();
            }
            return currVertexNode;
        }
        return null;
    }

    /**
     * This method deletes first instance
     * found of a given value from the list
     *
     * @param name Name of vertex to remove.
     * @return True if deletion was successful, otherwise false.
     */
    public boolean remove(String name)
    {
        VertexNode prevNode = null;
        VertexNode currNode = mHead;

        while (currNode != null)
        {
            if (currNode.getVertex().getName().contentEquals(name))
            {
                if (mLength > 0)
                {
                    prevNode.setNext(currNode.getNext());
                    --mLength;
                }
                else
                {
                    mHead = null;
                }
                return true;
            }
            else
            {
                prevNode = currNode;
                currNode = currNode.getNext();
            }
        }
        return false;
    }

    /**
     * This method gives a string representation of the list
     * to help for printing purposes
     * @return String representation of the list.
     */
    @Override
    public String toString()
    {
        VertexNode currNode = mHead;

        StringBuilder str = new StringBuilder();

        while (currNode != null)
        {
            str.append(currNode.getVertex().getName() + " ");
            currNode = currNode.getNext();
        }

        return str.toString();
    }

    //This method returns the Head Node
    public VertexNode getmHead() { return mHead; }

    //This method returns the Tail Node
    public VertexNode getmTail() { return mTail; }

    //This method reutrns the length of the list
    public int getmLength() { return mLength; }
}
