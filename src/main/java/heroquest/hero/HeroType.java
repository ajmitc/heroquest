package heroquest.hero;

public enum HeroType
{
    //         Name         Defend Body Mind Icon                  InfoCard
    BARBARIAN( "Barbarian", 2,     8,   2,   "mini-barbarian.gif", "MGBHeroBarbarian.gif" ),
    WIZARD(    "Wizard",    2,     4,   6,   "mini-wizard1.gif",   "MGBHeroWizard.gif" ),
    DWARF(     "Dwarf",     2,     7,   3,   "mini-dwarf.gif",     "MGBHeroDwarf.gif" ),
    ELF(       "Elf",       2,     6,   4,   "mini-elf.gif",       "MGBHeroElf.gif" );


    private String _name;
    private int _defend;
    private int _body;
    private int _mind;
    private String _iconName;
    private String _infoIconName;

    HeroType( String n, int d, int b, int mind, String fn, String infoFn )
    {
        _name = n;
        _defend = d;
        _body = b;
        _mind = mind;
        _iconName = fn;
        _infoIconName = infoFn;
    }

    public String getName(){ return _name; }
    public int getDefend(){ return _defend; }
    public int getBody(){ return _body; }
    public int getMind(){ return _mind; }
    public String getIconName(){ return _iconName; }
    public String getInfoIconName(){ return _infoIconName; }

    public String toString(){ return _name + "[" + _defend + "/" + _body + "/" + _mind + "]"; }
}

