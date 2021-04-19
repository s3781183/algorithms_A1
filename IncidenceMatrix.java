import java.io.PrintWriter;

/**
 * This class is an implementation of the Incidence Matrix
 * @author Shaunak Karuna, 2021
 * @author Jeffrey Chan, 2021.
 */
public class IncidenceMatrix extends AbstractGraph
{
    private ArrayMap[] array;
    private EdgeLinkedList edgeList;

    /** Contructs empty graph. */
    public IncidenceMatrix()
    {
        array = null;
        edgeList = new EdgeLinkedList();
    }


    public void addVertex(String vertLabel)
    {
        Vertex vertex = new Vertex(vertLabel);

        if (array == null)
        {
            // allocate array of size 1
            array = new ArrayMap[1];
            array[0] = new ArrayMap();
            array[0].setVertex(vertex);
        }
        else
        {
            // increase size of array by one (not terribly efficient, but for this
            // lab we assume increase array size by one.
            ArrayMap[] newArray = new ArrayMap[array.length + 1];

            // copy all existing values of array to newArray
            for (int i = 0; i < array.length; i++)
            {
                newArray[i] = array[i];
            }

            // new entry, add to end of newArray
            newArray[array.length] = new ArrayMap();
            newArray[array.length].setVertex(vertex);

            // update reference of array to point to newArray
            array = newArray;
        }
    }


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
            if (mapArray.getVertex().getName().matches(tarLabel))
            {
                vert2 = mapArray.getVertex();
            }
        }

        if (vert1 != null && vert2 != null)
        {
            Edge edge = new Edge(vert1,vert2);
            ArrayMap srcMap = null;
            ArrayMap tarMap = null;

            for (ArrayMap mapArray : array)
            {
                if(mapArray.getVertex().getName().matches(srcLabel))
                {
                    srcMap = mapArray;
                }

                else if(mapArray.getVertex().getName().matches(tarLabel))
                {
                    tarMap = mapArray;
                }
            }
            srcMap.addEdge(edge);
            tarMap.addEdge(edge);
            edgeList.add(edge);
        }
    }


    public void toggleVertexState(String vertLabel)
    {
        for(ArrayMap mapArray : array)
        {
            if (mapArray.getVertex().getName().matches(vertLabel))
            {
                Vertex vert1 = mapArray.getVertex();
                vert1.toggleState();
            }
        }
    }


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
            if (delEdge != null)
            {
                mapArray.deleteEdge(delEdge);
                edgeList.remove(delEdge.toString());
            }
        }
    }


    public void deleteVertex(String vertLabel)
    {
        ArrayMap[] newArray = new ArrayMap[array.length - 1];

        //Removes all edges that involve the Vertex to be deleted
        for(int i = 0; i < edgeList.getmLength(); ++i)
        {
            if (edgeList.get(i).getVert1().getName().matches(vertLabel)
                    || edgeList.get(i).getVert2().getName().matches(vertLabel))
            {
                edgeList.remove(edgeList.get(i).toString());
                --i;
            }
        }

        int i = 0;
        for (ArrayMap arrayMap: array)
        {
            if(!arrayMap.getVertex().getName().matches(vertLabel))
            {
                newArray[i] = arrayMap;
                ++i;
            }
        }

        array = newArray;
    }


    /**
     * This method finds neighbours for all nodes that are of a max of k degree
     * away from the supplied starting vertex
     *
     * @param k the maximum degree away an incident vertex
     * can be from the starting vertex
     * @param vertLabel Vertex to find the k-hop neighbourhood for
     * @return String[] containing the names of all Vertexes at most k degree away
     */
    public String[] kHopNeighbours(int k, String vertLabel)
    {
        String[] neighbours = new String[0];

        for (int i = 0; i < k; ++i)
        {
            //If there are no neighbours in the list
            if (i == 0)
            {
                for (int j = 0; j < edgeList.getmLength(); ++j)
                {
                    if (edgeList.get(j).getVert1().getName().matches(vertLabel))
                    {
                        neighbours = copyNeighbours(neighbours, j);
                    }
                }
            }

            //If there are neighbours in the list
            else
            {
                for (int m = 0; m < neighbours.length; ++m)
                {
                    for (int j = 0; j < edgeList.getmLength(); ++j)
                    {
                        if (edgeList.get(j).getVert1().getName().matches(neighbours[m]))
                        {
                            boolean present = false;
                            //Check if the vertex has already been added
                            for (int n = 0; n < neighbours.length; ++n)
                            {
                                if (neighbours[n].matches(edgeList.get(j).getVert2().getName()))
                                {
                                    present = true;
                                }
                            }
                            //If vertex is not present, then add it to the list
                            if (!present)
                            {
                                neighbours = copyNeighbours(neighbours, j);
                            }
                        }
                    }
                }
            }
        }

        return neighbours;
    }

    private String[] copyNeighbours(String[] neighbours, int j)
    {
        String[] tempNeighbours = new String[neighbours.length + 1];
        for (int l = 0; l < neighbours.length; ++l)
        {
            tempNeighbours[l] = neighbours[l];
        }
        tempNeighbours[tempNeighbours.length - 1] = edgeList.get(j).getVert2().getName();
        neighbours = tempNeighbours;
        return neighbours;
    }


    public void printVertices(PrintWriter os)
    {
        StringBuilder vertices = new StringBuilder();
        for (ArrayMap mapArray : array)
        {
            vertices.append(mapArray.getVertex().getName());
        }

        os.print(vertices + "\n");
        os.flush();
    }

    public void printEdges(PrintWriter os)
    {
        os.print(edgeList.toString() + "\n");
        os.flush();
    }

    @Override
    public SIRState currentState(String vertLabel)
    {
        for (ArrayMap arrayMap: array)
        {
            if (arrayMap.getVertex().getName().matches(vertLabel))
            {
                return arrayMap.getVertex().getState();
            }
        }
        return null;
    }

    @Override
    public String[] listVertices()
    {
        String[] list = new String[array.length];
        int index = 0;
        for (ArrayMap arrayMap : array)
        {
            list[index] = arrayMap.getVertex().getName();
            ++index;
        }
        return list;
    }
}