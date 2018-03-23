package heroquest.treasure;

public class Treasure
{
    private TreasureType _type;
    protected String _name;
    protected String _icon;
    
    public Treasure( TreasureType type )
    {
        _type = type;
    }

    public TreasureType getType(){ return _type; }

    public String getName(){ return _name != null? _name: _type.getName(); }
    public String getIconName(){ return _icon != null? _icon: _type.getIconName(); }

    public String toString(){ return _name != null? _name: _type.toString(); }
}

