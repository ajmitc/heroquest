package heroquest.hero;

public enum ArmorType
{
    DAGGER( "Dagger", 1 ),
    STAFF( "Staff", 1 ),
    SHORTSWORD( "Shortsword", 2 ),
    BROADSWORD( "Broadsword", 3 ),
    LONGSWORD( "Longsword", 4 ),
    BATTLEAXE( "Battleaxe", 5 ),
    CROSSBOW( "Crossbow", 3 );

    private String _name;
    private int _defend;

    ArmorType( String n, int d )
    {
        _name = n;
        _defend = d;
    }

    public String getName(){ return _name; }
    public int getDefend(){ return _defend; }

    public String toString(){ return _name + "[" + _defend + "]"; }
}

