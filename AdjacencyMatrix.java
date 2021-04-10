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
    protected Array vertices = new Array();
    protected int noVertices;
    protected boolean matrix[][];

    /**
	 * Contructs empty 2D graph with no vertices.
	 */
    public AdjacencyMatrix() {
        this.noVertices =0;
        this.matrix = new boolean[1][1];

    }


    /**
     * Adds new string vertex to matrix and vertex array.
     * @param vertLabel String vertex label to add to the end of matrix and array
     */
    public void addVertex(String vertLabel) {
        if(vertices.search(vertLabel) >-1){
        vertices.add(new Vertex(vertLabel));

        if (matrix[0].length ==1) {

            matrix[0][0] = false;
            noVertices++;
        }
        else {

            noVertices++;
            boolean newMatrix[][] = new boolean[noVertices][noVertices];

            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    newMatrix[i][j] = matrix[i][j];
                }


                for (int j = 0; j < noVertices; i++) {
                    newMatrix[noVertices - 1][j] = false;
                }

                for (int j = 0; j < noVertices; i++) {
                    newMatrix[i][noVertices - 1] = false;
                }

            }

            matrix = newMatrix;
        }
       }}

    /**
     * Adds new edge (changes element value in matrix to 1) between two vertices.
     * @param srcLabel The first source vertex value
     * @param tarLabel The second target vertex value
     */
    public void addEdge(String srcLabel, String tarLabel) {
        int srcIndex = vertices.search(srcLabel);
        int tarIndex = vertices.search(tarLabel);
        if(srcIndex > -1 && tarIndex > -1){
            matrix[srcIndex][tarIndex] = true;
            matrix[tarIndex][srcIndex] = true;
        }

    }

    /**
     * Changes the state of the vertex to the new stage
     * @param vertLabel The vertex that is going to change state
     */
    public void toggleVertexState(String vertLabel) {
       int vertexIndex = vertices.search(vertLabel);
       if(vertices.get(vertexIndex).getState() == SIRState.S){
           vertices.get(vertexIndex).setState(SIRState.I);
       }
        if(vertices.get(vertexIndex).getState() == SIRState.I) {
            vertices.get(vertexIndex).setState(SIRState.R);
        }

    }

    /**
     * Deletes edge (changes element value in matrix to 0) between two vertices.
     * @param srcLabel The first source vertex value
     * @param tarLabel The second target vertex value
     */
    public void deleteEdge(String srcLabel, String tarLabel) {
        int srcIndex = vertices.search(srcLabel);
        int tarIndex = vertices.search(tarLabel);
        if(srcIndex > -1 && tarIndex > -1){
            matrix[srcIndex][tarIndex] = false;
            matrix[tarIndex][srcIndex] = false;
        }
    }

    /**
     * Deletes vertex from matrix and vertex array.
     * @param vertLabel String vertex label to be deleted from matrix and vertex array
     */
    public void deleteVertex(String vertLabel) {
        int vertexIndex = vertices.search(vertLabel);
        Array newVertices= new Array();
        noVertices--;
        if(vertexIndex>-1){
        for (int i = 0; i < vertexIndex; i++) {
            newVertices.add(vertices.get(i));
        }

        for (int i = vertexIndex+1; i < vertices.length(); i++) {
            newVertices.add(vertices.get(i));
        }

        vertices = newVertices;

        //skipping row/column
        boolean newMatrix[][] = new boolean[noVertices][noVertices];

        for(int i = 0,currColumn=0; i < matrix[0].length; i++)
        {
            for(int j = 0,rowColumn=0; j < matrix[0].length; j++)
            {
                    if(j != vertexIndex && i !=  vertexIndex)
                    {
                        newMatrix[currColumn][rowColumn] = matrix[i][j];
                        currColumn++;
                        rowColumn++;
                    }

        }

    }}}

    /**
     * Finds the vertices that are neighbours with the source vertex (k-hop away)
     * @param k stands for how many k-hop away
     * @param vertLabel the target vertex
     */
    public String[] kHopNeighbours(int k, String vertLabel) {
       Array neighboursVect = new Array();
        int vertexIndex = vertices.search(vertLabel);
        neighboursVect.add(new Vertex(vertLabel));
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[vertexIndex][i] == true) {
                neighboursVect.add(vertices.get(i));
            }
        }
        if(k>0) {
            int loop = 0;
            while (loop < k) {
                for (String neighbour : neighboursVect.allVertices()) {
                    int currentIndex = vertices.search(neighbour);
                    for (int i = 0; i < matrix[0].length; i++) {
                        if (matrix[currentIndex][i] == true) {
                            boolean exists = false;
                            for (int j = 0; j < neighboursVect.length(); j++) {
                                if (neighboursVect.get(i).equals(vertices.get(i))) {
                                    exists = true;
                                }
                            }
                            if (exists == false) {
                            }
                        }
                    }
                }
                loop++;
            }
        }
        String[] neighbours = new String[neighboursVect.length()];
        for(int i =0; i< neighboursVect.length() ; i++){
            neighbours[i] = neighboursVect.get(i).getName();
        }
        return neighbours;
    } // end of kHopNeighbours()

    /**
     * Prints the vertices currently in vertex array
     */
    public void printVertices(PrintWriter os) {
       for(int i=0; i<vertices.length(); i++){
           os.print("");
           os.print("("+ vertices.get(i).getName() +"," + vertices.get(i).getState() +") ");
       }
    } // end of printVertices()

    /**
     * Prints the edges (value 1) currently in matrix
     */
    public void printEdges(PrintWriter os) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
               if(matrix[i][j]==true){
                  // os.println();
                   os.print(vertices.get(i).getName() +" " +vertices.get(j).getName());
               }
            }

        }
    } // end of printEdges()
    /**
     * Prints the current state of a particular vertex
     * @param vertLabel the string vertex that needs its state
     */
    public SIRState currentState(String vertLabel){
        int vertexIndex = vertices.search(vertLabel);

        return vertices.get(vertexIndex).getState() ;
    }

    /**
     * Returns the string array of all the current vertices
     */
    public String[] listVertices() {
        return vertices.allVertices();
    }

} // end of class AdjacencyMatrix
