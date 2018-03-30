package heroquest.hero;

public enum HeroType
{
    //         Name         Defend Body Mind
    BARBARIAN( "Barbarian", 2,     8,   2,   "MGBHeroBarbarian.gif" ),
    WIZARD(    "Wizard",    2,     4,   6,   "MGBHeroWizard.gif" ),
    DWARF(     "Dwarf",     2,     7,   3,   "MGBHeroDwarf.gif" ),
    ELF(       "Elf",       2,     6,   4,   "MGBHeroElf.gif" );


    private String _name;
    private int _defend;
    private int _body;
    private int _mind;
    private String _iconName;

    HeroType( String n, int d, int b, int mind, String fn )
    {
        _name = n;
        _defend = d;
        _body = b;
        _mind = mind;
        _iconName = fn;
    }

    public String getName(){ return _name; }
    public int getDefend(){ return _defend; }
    public int getBody(){ return _body; }
    public int getMind(){ return _mind; }
    public String getIconName(){ return _iconName; }

    public String toString(){ return _name + "[" + _defend + "/" + _body + "/" + _mind + "]"; }
}

