import java.io.PrintWriter;


/**
 * Adjacency matrix implementation for the GraphInterface interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2021.
 */
public class AdjacencyMatrix extends AbstractGraph
{
    protected Array vertices;
    protected int noVertices;
    protected boolean matrix[][];
	/**
	 * Contructs empty graph.
	 */
    public AdjacencyMatrix() {
        noVertices =0;
        matrix = new boolean[noVertices][noVertices];

    	// Implement me!
    } // end of AdjacencyMatrix()


    public void addVertex(String vertLabel) {
        vertices.add(new Vertex(vertLabel));

        if (matrix == null) {
            // allocate array of size 1
            matrix[0][0] = false;
            noVertices++;
        }
        else {
            // increase size of array by one (not terribly efficient, but for this
            // lab we assume increase array size by one.
            noVertices++;
            boolean newMatrix[][] = new boolean[noVertices][noVertices];

            // copy all existing values of array to newArray
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    newMatrix[i][j] = matrix[i][j];
                }

                // new entry, add to end of newArray
                newMatrix[noVertices][noVertices] = false;

                // update reference of array to point to newArray
                matrix = newMatrix;
            }
        }
       }// end of addVertex()


    public void addEdge(String srcLabel, String tarLabel) {
        int srcIndex = vertices.search(srcLabel);
        int tarIndex = vertices.search(tarLabel);
        if(srcIndex > -1 && tarIndex > -1){
            matrix[srcIndex][tarIndex] = true;
            matrix[tarIndex][srcIndex] = true;
        }


        // Implement me!
    } // end of addEdge()

//PUT IN INTERFACE??
    public void toggleVertexState(String vertLabel) {
       int vertexIndex = vertices.search(vertLabel);
       if(vertices.get(vertexIndex).getState() == SIRState.S){
           vertices.get(vertexIndex).setState(SIRState.I);
       }
        if(vertices.get(vertexIndex).getState() == SIRState.I) {
            vertices.get(vertexIndex).setState(SIRState.R);
        }

    } // end of toggleVertexState()


    public void deleteEdge(String srcLabel, String tarLabel) {
        int srcIndex = vertices.search(srcLabel);
        int tarIndex = vertices.search(tarLabel);
        if(srcIndex > -1 && tarIndex > -1){
            matrix[srcIndex][tarIndex] = false;
            matrix[tarIndex][srcIndex] = false;
        }
    } // end of deleteEdge()


    public void deleteVertex(String vertLabel) {
        int vertexIndex = vertices.search(vertLabel);
        Array newVertices=null;
        noVertices--;
        for (int i = 0; i < vertexIndex; i++) {
            newVertices.add(vertices.get(i));
        }

        for (int i = vertexIndex+1; i < vertices.length(); i++) {
            newVertices.add(vertices.get(i));
        }

        vertices = newVertices;

        boolean newMatrix[][] = new boolean[noVertices][noVertices];

        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0,currColumnRow=0; j < matrix.length; j++)
            {
                if(j != vertexIndex && i !=  vertexIndex)
                {
                    newMatrix[currColumnRow++][currColumnRow++] = matrix[i][j];
                }
            }
        }

    } // end of deleteVertex()


    public String[] kHopNeighbours(int k, String vertLabel) {
       String neighbours[] = new String[matrix.length];
        int vertexIndex = vertices.search(vertLabel);
        neighbours[0]=vertLabel;
        int index =1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[vertexIndex][i] == true) {
                neighbours[index] = vertices.get(i).getName();
                index++;
            }
        }
        if(k>1) {
            int loop = 1;
            while (loop < k) {
                for (String neighbour : neighbours) {
                    int currentIndex = vertices.search(neighbour);
                    for (int i = 0; i < matrix.length; i++) {
                        if (matrix[currentIndex][i] == true) {
                            boolean exists=false;
                            for (int j=0; j< neighbours.length;j++) {
                                if(neighbours[j].equals(vertices.get(currentIndex).getName())){
                                    exists=true; }
                        }
                            if(exists==false){
                                neighbours[index]=vertices.get(currentIndex).getName();
                                index++;
                            }
                        }
                    }
                }
            }
        }

        return neighbours;
    } // end of kHopNeighbours()

    //PUT IN INTERFACE??
    public void printVertices(PrintWriter os) {
       for(int i=0; i<vertices.length(); i++){
           os.print("("+ vertices.get(i).getName() +"," + vertices.get(i).getState() +") ");
       }
    } // end of printVertices()


    public void printEdges(PrintWriter os) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
               if(matrix[i][j]==true){
                   os.println(vertices.get(i).getName() +vertices.get(j).getName());
               }
            }

        }
    } // end of printEdges()

    public SIRState currentState(String vertLabel){
        int vertexIndex = vertices.search(vertLabel);

        return vertices.get(vertexIndex).getState() ;
    }


    public String[] listVertices() {
        return vertices.allVertices();
    }

} // end of class AdjacencyMatrix
