/**
 * ArrayLinkedList implementation for an array that contains
 * all LinkedLists for each Vertex in the graph
 * @author Jeffrey Chan 2019.
 * @author Shaunak Karuna 2021.
 */
public class ArrayLinkedList
{
    private static final int NOT_IN_ARRAY = -1;
    /** Reference to the memory of array. */
    protected VertexLinkedList array[];


    /**
     * Constructor to instantiate an Array Linked List Object
     */
    public ArrayLinkedList()
    {
        // no memory allocated to array, so we set the reference to null and
        // only allocate memory when we add an element.
        array = null;
    } // end of DynamicArray()


    /**
     * Sets/replaces the value at the supplied index.
     * @param index Position in array to whose value needs to be set/replaced
     * @param newValue The VertexLinkedList that needs to be added to the array
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public void set(int index, VertexLinkedList newValue) throws IndexOutOfBoundsException
    {
        if (index >= array.length || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        array[index] = newValue;
    } // end of set()


    /**
     * This method retrieves the VertexLinkedList at the supplied index
     *
     * @param index Position in array to retrieve VertexLinkedList from.
     * @return VertexLinkedList at specified index.
     *
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public VertexLinkedList get(int index) throws IndexOutOfBoundsException
    {
        if (index >= array.length || index < 0)
        {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }
        return array[index];
    } // end of get()


    /**
     * Adds a VertexLinkedList to the end of the array.
     * @param newValue VertexLinkedList to add to the array.
     */
    public void add(VertexLinkedList newValue)
    {
        // If the array is empty, allocate enough memory to store a single element
        if (array == null)
        {
            // allocate array of size 1
            array = new VertexLinkedList[1];
            array[0] = newValue;
        }
        else {
            /**
             * Increase the size of the array by one by copying
             * all previous elements into a newly created array
             */
            VertexLinkedList newArray[] = new VertexLinkedList[array.length + 1];
            for (int i = 0; i < array.length; i++)
            {
                newArray[i] = array[i];
            }
            newArray[array.length] = newValue;
            array = newArray;
        }
    } // end of add()

    /**
     * Searches for the VertexLinkedList based on the name of the Vertex
     * that the LinkedList's data represents
     * @param nodeName Value to search for.
     * @return Index where value is located, otherwise returns -1 (NOT_IN_ARRAY).
     */
    public VertexLinkedList search(String nodeName)
    {
        if (array != null)
        {
            for (int i = 0; i < array.length; ++i)
            {
                if (array[i].getmHead().getVertex().getName().matches(nodeName))
                {
                    return array[i];
                }
            }
        }

        return null;
    } // end of search()

    /**
     * This method gives the length of the array
     * @return Int The length of the array
     */
    public int getLength(){return array.length;}

    /**
     * This method removes a LinkedList from the Array
     * @param list The VertexLinkedList to be removed
     */
    public void remove(VertexLinkedList list)
    {
        VertexLinkedList[] newArray = new VertexLinkedList[array.length - 1];
        for (int i = 0; i < array.length; ++i)
        {
            int index = array.length;
            if (array[i].getmHead() != list.getmHead() && index > i)
            {
                newArray[i] = array[i];
            }

            else if(array[i].getmHead() != list.getmHead() && index <= i)
            {
                newArray[i - 1] = array[i];
            }
        }
    }


    /**
     * This method prints the contents of array from front-to-back
     */
    public String print()
    {
        StringBuffer sB = new StringBuffer();
        if (array != null)
        {
            for (int i = 0; i < array.length; i++)
            {
                sB.append(array[i].ToString() + " ");
            }
        }

        return sB.toString();
    } // end of print()
}
