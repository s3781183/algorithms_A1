
public class Array
{
    private static final int NOT_IN_ARRAY = -1;
    /** Reference to the memory of array. */
    protected Vertex array[];


    /**
     * Constructor.  Ensure you understand what the logic of it is doing,
     * will help wih understanding what arraySize means.
     */
    public Array() {
        // no memory allocated to array, so we set the reference to null and
        // only allocate memory when we add an element.
        array = null;
    } // end of DynamicArray()


    /**
     * Sets/replaces the value at index.  Indices start at 0.
     *
     * @param index Position in array to set/replace value.
     *
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
//    public void set(int index, Vertex newValue) throws IndexOutOfBoundsException {
//        if (index >= array.length || index < 0) {
//            throw new IndexOutOfBoundsException("Supplied index is invalid.");
//        }
//
//        array[index] = newValue;
//    } // end of set()


    /**
     * Gets/retrieves the value at index.  Indices start at 0.
     *
     * @param index Position in array to retrieve value from.
     * @return value of array at specified index.
     *
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public Vertex get(int index) throws IndexOutOfBoundsException {
        if (index >= array.length || index < 0) {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        return array[index];
    } // end of get()


    /**
     * Add value to end of array.
     *
     * @param newValue Value to add to array.
     *
     */
    public void add(Vertex newValue) {
        // check if we need to allocate memory
        if (array == null) {
            // allocate array of size 1
            array = new Vertex[1];
            array[0] = newValue;
        }
        else {
            // increase size of array by one (not terribly efficient, but for this
            // lab we assume increase array size by one.
            Vertex newArray[] = new Vertex[array.length + 1];

            // copy all existing values of array to newArray
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }

            // new entry, add to end of newArray
            newArray[array.length] = newValue;

            // update reference of array to point to newArray
            array = newArray;
        }
    } // end of add()

//REMOVED INSERT (?)


    /**
     * Searches for the index that contains value.  If value is not present,
     * method returns -1 (NOT_IN_ARRAY).
     * If there are multiple values that could be returned, return the one with
     * the smallest index.
     *
     * @param value Value to search for.
     * @return Index where value is located, otherwise returns -1 (NOT_IN_ARRAY).
     */
    public int search(String value) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                //??????
                if (array[i].getName().equals(value)) {
                    return i;
                }
            }
        }

        return NOT_IN_ARRAY;
    } // end of search()


    /**
     * Print the array from front to end (index 0 to end).
     */
    public String[] allVertices() {
        String[] vertices = new String[array.length];
        int index =0;
//        for(Vertex vertex: array){
//            vertices[index] = vertex.getName();
//            index++;
//        }
        for (int i = 0; i < array.length; ++i) {
            //??????
           vertices [i] = array[i].getName();
            }
        return vertices;
    } // end of print()

    public int length(){
        if (array==null){
            return 0;
        }
            return array.length;
    }



} // end of class DynamicArrayMinimal

