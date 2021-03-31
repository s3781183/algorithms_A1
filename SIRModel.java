import java.io.PrintWriter;

/**
 * SIR model.
 *
 * @author Jeffrey Chan, 2021.
 */
public class SIRModel
{

    /**
     * Default constructor, modify as needed.
     */
    public SIRModel() {

    } // end of SIRModel()


    /**
     * Run the SIR epidemic model to completion, i.e., until no more changes to the states of the vertices for a whole iteration.
     *
     * @param graph Input contracts graph.
     * @param seedVertices Set of seed, infected vertices.
     * @param infectionProb Probability of infection.
     * @param recoverProb Probability that a vertex can become recovered.
     * @param sirModelOutWriter PrintWriter to output the necessary information per iteration (see specs for details).
     */
    public void runSimulation(ContactsGraph graph, String[] seedVertices,
        float infectionProb, float recoverProb, PrintWriter sirModelOutWriter) {

        int iterations = 1;

        for (String vertex : seedVertices) {
            graph.toggleVertexState(vertex);
        }
        boolean cont = true;

        while (cont) {

            iterations++;
        }

    }

    private String[] updateInfected(ContactsGraph graph, float infectProb){
        int index =0;
        String[] newInfected = new String[graph.listVertices().length];
        for(String vertex:graph.listVertices()){
            if(graph.currentState(vertex)==SIRState.I)
                for(String neighbour:graph.kHopNeighbours(1, vertex)){
                 double rand = Math.random();
                 if(graph.currentState(neighbour)==SIRState.S && rand<=infectProb){
                     newInfected[index]=neighbour;
                     index++;
                 }

        }}

        return newInfected;
    }


    private String[] updateRecovered(ContactsGraph graph, float recoverProb){
        int index =0;
        String[] newRecover = new String[graph.listVertices().length];
        for(String vertex:graph.listVertices()){
            if(graph.currentState(vertex)==SIRState.I){
                double rand = Math.random();
                if(rand<=recoverProb){
                    newRecover[index]=vertex;
                    index++;
                    }

                }}

        return newRecover;

    }

    private ContactsGraph updateGraph(String[] newInfect, String[] newRecover,ContactsGraph graph ){
        for(String infection:newInfect){
            for(String vertex:graph.listVertices()){
                if(infection.equals(vertex)){
                graph.toggleVertexState(vertex);
            }}}
        for(String recover:newRecover){
             for(String vertex:graph.listVertices()){
                  if(recover.equals(vertex)){
                   graph.toggleVertexState(vertex);
                        }

            }}
        return graph;
    }

    private boolean updateStop(String[] newInfect, String[] newRecover,String[] currentInfect ){
        



        return true;
    }



        //infectionProb - random number between 0 and 1
        //rand- random number between 0 and 1
        //check neighbours if I, then they all have random prob to infect target
        //they will be infected if rand < or equal to infectionProb
        //for each neighbour create a different rand value
        //if they become infected for next iteration change to I

        //RECOVERY
        //recoveryProb - random number between 0 and 1
        //rand- random number between 0 and 1
        //they will be recovered if rand < or equal to recoveryProb

        //set the seedVertices in graph to infected

        //updatedInfected method
        //newRecovered method
        //updateGraph
        //updateStop - newInfected, newRecovered, infected - if there is at least one newly
        //infected node/recrvered node
        //if there is no change but there is still infected then run ten more time
        // IMPLEMENT ME!
     // end of runSimulation()
} // end of class SIRModel
