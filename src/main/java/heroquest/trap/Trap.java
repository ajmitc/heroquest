package heroquest.trap;

public class Trap
{
    private TrapType _type;
    private boolean _hidden;
    private boolean _warned;

    public Trap( TrapType type )
    {
        _type = type;
        _hidden = true;
        _warned = false;
    }

    public TrapType getType(){ return _type; }
    public boolean isHidden(){ return _hidden; }
    public boolean isWarned(){ return _warned; }

    public void setHidden( boolean v ){ _hidden = v; }
    public void setWarned( boolean v ){ _warned = v; }
}

