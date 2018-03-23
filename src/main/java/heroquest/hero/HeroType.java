package heroquest.hero;

public enum HeroType
{
    //         Name         Defend Body Mind
    BARBARIAN( "Barbarian", 2,     8,   2 ),
    WIZARD(    "Wizard",    2,     4,   6 ),
    DWARF(     "Dwarf",     2,     7,   3 ),
    ELF(       "Elf",       2,     6,   4 );

    private String _name;
    private int _defend;
    private int _body;
    private int _mind;

    HeroType( String n, int d, int b, int mind )
    {
        _name = n;
        _defend = d;
        _body = b;
        _mind = mind;
    }

    public String getName(){ return _name; }
    public int getDefend(){ return _defend; }
    public int getBody(){ return _body; }
    public int getMind(){ return _mind; }

    public String toString(){ return _name + "[" + _defend + "/" + _body + "/" + _mind + "]"; }
}

