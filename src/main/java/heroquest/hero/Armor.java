package heroquest.hero;

public abstract class Armor
{
    private ArmorType _type;
    protected String _name;
    protected Integer _defend;

    public Armor( ArmorType type )
    {
        this( type, null );
    }

    public Armor( ArmorType type, String name )
    {
        _type = type;
        _name = name;
        _defend = null;
    }

    public ArmorType getType(){ return _type; }

    public String getName(){ return _name != null? _name: _type.getName(); }
    public int getDefend(){ return _defend != null? _defend: _type.getDefend(); }

    public void setName( String n ){ _name = n; }
    public void setDefend( int d ){ _defend = d; }

    public String toString(){ return getName(); }
}

