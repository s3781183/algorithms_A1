/**
 * This class represents a Vertex that has been added to the graph
 */
public class Vertex
{
    //This String represents the label of the Vertex
    private String name;
    //This State represents the current state of the Vertex
    private SIRState state;

    /**
     * This is the Constructor for a Vertex,
     * note: The default SIRState for a new Vertex is 'Susceptible'
     * @param name The name of the Vertex
     */
    public Vertex(String name)
    {
        this.name = name;
        state = SIRState.S;
    }

    /**
     * This method returns the name of this Vertex
     * @return String representing the name
     */
    public String getName() { return name; }

    /**
     * This method sets the state of the node
     * @param state SIRState that will be set as the state of this Vertex
     */
    public void setState(SIRState state)
    {
        this.state = state;
    }

    /**
     * This method changes the state of this vertex based on the current
     * state already set as provided in the assignment specification
     */
    public void toggleState()
    {
        if(state == SIRState.S)
        {
            state = SIRState.I;
        }
        else if(state == SIRState.I)
        {
            state = SIRState.R;
        }
    }

    /**
     * This is a getter method for the state of the vertex
     * @return SIRState of the Vertex
     */
    public SIRState getState() { return state; }
}