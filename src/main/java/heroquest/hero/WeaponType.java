package heroquest.hero;

public enum WeaponType
{
    DAGGER( "Dagger", 1, 1, 100 ),
    STAFF( "Staff", 1, 1, 1, true ),
    SHORTSWORD( "Shortsword", 2, 1, 1 ),
    BROADSWORD( "Broadsword", 3, 1, 1 ),
    LONGSWORD( "Longsword", 4, 1, 1, true ),
    BATTLEAXE( "Battleaxe", 5, 1, 1 ),
    CROSSBOW( "Crossbow", 3, 2, 100, true );

    private String _name;
    private int _attack;
    private int _minRange;
    private int _maxRange;
    private boolean _diagonalAttack;

    WeaponType( String n, int a, int minr, int maxr, boolean d )
    {
        _name = n;
        _attack = a;
        _minRange = minr;
        _maxRange = maxr;
        _diagonalAttack = d;
    }

    WeaponType( String n, int a, int minr, int maxr )
    {
        this( n, a, minr, maxr, false );
    }

    public String getName(){ return _name; }
    public int getAttack(){ return _attack; }
    public int getMinRange(){ return _minRange; }
    public int getMaxRange(){ return _maxRange; }
    public boolean allowDiagonalAttack(){ return _diagonalAttack; }

    public String toString(){ return _name + "[" + _attack + "/" + _minRange + "-" + _maxRange + (_diagonalAttack? "/diagonal": "") + "]"; }
}

