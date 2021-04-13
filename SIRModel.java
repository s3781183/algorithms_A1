import java.io.PrintWriter;

/**
 * SIR model.
 *
 * @author Jeffrey Chan, 2021.
 */
public class SIRModel
{
    private int reiteration =10;

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

        //changing to negative
        for (String vertex : seedVertices) {
            graph.toggleVertexState(vertex);
        }
        //can include null
        String[] infected = new String[graph.listVertices().length];
        int index = 0;
        for(String vertex : graph.listVertices()){
            if(graph.currentState(vertex)==SIRState.I){
                infected[index]=vertex;
                index++;
            }
        }

        boolean cont = true;

        while (cont) {
            String[] newInfected = updateInfected(graph, infectionProb);
            String[] newRecovered = updateRecovered(graph, recoverProb);
            String infect = "";
            String recover = "";
            for(int i=0; i<newInfected.length; i++){
                if(newInfected[i]!=null){
                     infect = newInfected[i] + " "+ infect;}
            }
//            for(String vertex : graph.listVertices()){
//                sirModelOutWriter.print(graph.currentState(vertex) +"\n");
//                sirModelOutWriter.flush();
//                }
        for(int i=0; i<newRecovered.length; i++){
            if(newRecovered[i]!=null){
                recover = newRecovered[i]  + " "+ recover;}
        }
            graph = updateGraph(newInfected, newRecovered,graph);
            cont = updateStop(newInfected, newRecovered, infected);
            infected = newInfected;

        sirModelOutWriter.print(iterations +": ["+infect +"]"+": ["+ recover+"]\n");
        sirModelOutWriter.flush();
            iterations++;
            }


        }


    /**
     *Loops through the infected vertices and infect particular neighbours based on probability
     * @param graph Input contracts graph.
     * @param infectProb Set of seed, infected vertices.
     */
    private String[] updateInfected(ContactsGraph graph, float infectProb){
        int index =0;
        String[] newInfected = new String[graph.listVertices().length];

//        String[] newInfected = {Integer.toString(graph.listVertices().length)};
        for(String vertex:graph.listVertices()){
            if(graph.currentState(vertex).equals(SIRState.I))
                for(String neighbour:graph.kHopNeighbours(0, vertex)){
                 double rand = Math.random();
                 if(graph.currentState(neighbour).equals(SIRState.S) && rand<=infectProb){
                     newInfected[index]=neighbour;
                     index++;
                 }

        }}

        return newInfected;
    }

    /**
     *Loops through the infected vertices recovers particular ones based on probability
     * @param graph Input contracts graph.
     * @param recoverProb Probability that a vertex can become recovered.
     */
    private String[] updateRecovered(ContactsGraph graph, float recoverProb){
        int index =0;
        String[] newRecover = new String[graph.listVertices().length];
        for(String vertex:graph.listVertices()){
            if(graph.currentState(vertex).equals(SIRState.I)){
                double rand = Math.random();
                if(rand<=recoverProb){
                    newRecover[index]=vertex;
                    index++;
                    }

                }}

        return newRecover;

    }

    /**
     *Changes the vertices states in the graph, based on the new recovered and infected arrays.
     * @param newRecover Array of the newly recovered vertices based on new iteration
     * @param newInfect Array of the newly infected vertices based on new iteration
     * @param graph Input contracts graph.
     */
    private ContactsGraph updateGraph(String[] newInfect, String[] newRecover,ContactsGraph graph ){
        if(newInfect.length>1){
        for(String infection:newInfect){
            if(infection!=null){
            for(String vertex:graph.listVertices()){
                if(infection.equals(vertex)){
                graph.toggleVertexState(vertex);
            }
            }
        }}
        }
        if(newRecover.length>1){
        for(String recover:newRecover){
            if(recover!=null){
             for(String vertex:graph.listVertices()){
                  if(recover.equals(vertex)){
                   graph.toggleVertexState(vertex);
                        }

            }}}}
        return graph;
    }

    /**
     *Returns boolean value depending on whether iterations should stop or continue
     * @param newInfect Array of the newly infected vertices based on previous iteration
     * @param newRecover Array of the newly recovered vertices based on previous iteration
     * @param currentInfect Array  infected vertices from current iteration
     */
    private boolean updateStop(String[] newInfect, String[] newRecover,String[] currentInfect ){
        for(int i=0; i< newInfect.length;i++){
            boolean changeInfect = true;
            for(int j=0; j< currentInfect.length; j++){
                if(newInfect[i]!=null){
                if(newInfect[i].equals(currentInfect[j])){
                    changeInfect = false;
                }
                if(j==currentInfect.length-1 && changeInfect==true){
                    return true;
                }
            }
        }}

        for(int i=0; i< newRecover.length;i++){
            for(int j=0; j< currentInfect.length; j++){
                if(newRecover[i]!=null){
                if(newRecover[i].equals(currentInfect[j])){
                    return true;
                }
            }}
        }

        if(newInfect.length >0){
            if(reiteration > 0){
                reiteration--;
                return true;
            }
        }
        return false;
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
