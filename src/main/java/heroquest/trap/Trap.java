package heroquest.trap;

public class Trap
{
    private TrapType _type;

    public Trap( TrapType type )
    {
        _type = type;
    }

    public TrapType getType(){ return _type; }
}

