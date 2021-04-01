import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Incidence matrix implementation for the GraphInterface interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2021.
 */
public class IncidenceMatrix extends AbstractGraph
{
    private ArrayMap array[];
    private EdgeLinkedList edgeList;

    /**
     * Contructs empty graph.
     */
    public IncidenceMatrix()
    {
        array = null;
        edgeList = new EdgeLinkedList();
    } // end of IncidenceMatrix()


    public void addVertex(String vertLabel)
    {
        Vertex vertex = new Vertex(vertLabel);

        if (array == null)
        {
            // allocate array of size 1
            array = new ArrayMap[1];
            array[0].setVertex(vertex);
        }
        else
        {
            // increase size of array by one (not terribly efficient, but for this
            // lab we assume increase array size by one.
            ArrayMap newArray[] = new ArrayMap[array.length + 1];

            // copy all existing values of array to newArray
            for (int i = 0; i < array.length; i++)
            {
                newArray[i] = array[i];
            }

            // new entry, add to end of newArray
            newArray[array.length].setVertex(vertex);

            // update reference of array to point to newArray
            array = newArray;
        }
    } // end of addVertex()


    public void addEdge(String srcLabel, String tarLabel)
    {
        Vertex vert1 = null;
        Vertex vert2 = null;

        for(ArrayMap mapArray : array)
        {
            if (mapArray.getVertex().getName().matches(srcLabel))
            {
                vert1 = mapArray.getVertex();
            }
        }

        for(ArrayMap mapArray : array)
        {
            if (mapArray.getVertex().getName().matches(srcLabel))
            {
                vert2 = mapArray.getVertex();
            }
        }

        if (vert1 != null && vert2 != null)
        {
            Edge edge = new Edge(vert1,vert2);
            for (ArrayMap mapArray : array)
            {
                mapArray.addEdge(edge);
                edgeList.add(edge);
            }
        }
    } // end of addEdge()


    public void toggleVertexState(String vertLabel)
    {
        Vertex vert1 = null;

        for(ArrayMap mapArray : array)
        {
            if (mapArray.getVertex().getName().matches(vertLabel))
            {
                vert1 = mapArray.getVertex();
                vert1.toggleState();
            }
        }
    } // end of toggleVertexState()


    public void deleteEdge(String srcLabel, String tarLabel)
    {
        Edge delEdge = null;
        for(int i = 0; i < edgeList.getmLength(); ++i)
        {
            Edge edge = edgeList.get(i);
            if (edge.getVert1().getName().matches(srcLabel) &&
                    edge.getVert2().getName().matches(tarLabel))
            {
                delEdge = edge;
            }
        }

        for (ArrayMap mapArray : array)
        {
            mapArray.deleteEdge(delEdge);
            edgeList.remove(delEdge.toString());
        }
    } // end of deleteEdge()


    public void deleteVertex(String vertLabel)
    {
        ArrayMap[] newArray = new ArrayMap[array.length - 1];

        int i = 0;
        for (ArrayMap arrayMap: array)
        {
            if(!arrayMap.getVertex().getName().matches(vertLabel))
            {
                newArray[i] = arrayMap;
            }
            ++i;
        }

        array = newArray;

    } // end of deleteVertex()


    public String[] kHopNeighbours(int k, String vertLabel)
    {
        // Implement me!

        // please update!
        return null;
    } // end of kHopNeighbours()


    public void printVertices(PrintWriter os)
    {
        StringBuilder vertices = new StringBuilder();
        for (ArrayMap mapArray : array)
        {
            vertices.append(mapArray.getVertex().getName() + "");
        }

        os.print(vertices.toString());
    } // end of printVertices()


    public void printEdges(PrintWriter os)
    {
        os.print(edgeList.toString());
    } // end of printEdges()

    @Override
    public SIRState currentState(String vertLabel) {
        return null;
    }

    @Override
    public String[] listVertices() {
        return new String[0];
    }

} // end of class IncidenceMatrix