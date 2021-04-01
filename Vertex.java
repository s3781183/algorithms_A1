public class Vertex
{
    private String name;
    private SIRState state;

    public Vertex(String name)
    {
        this.name = name;
        state = SIRState.S;
    }

    public String getName() { return name; }

    public void setState(SIRState state)
    {
        this.state = state;
    }

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

    public SIRState getState()
    {
        return state;
    }
}
