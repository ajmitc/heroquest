package heroquest.monster;

public enum MonsterType
{
    //             Name          Move Attack Defend Body Mind
    GOBLIN(        "Goblin",       10,  2,     1,     1,   1, "mini-goblin1.gif" ),
    SKELETON(      "Skeleton",      6,  2,     2,     1,   0, "mini-skeleton.gif" ),
    ZOMBIE(        "Zombie",        5,  2,     3,     1,   0, "mini-zombie.gif" ),
    ORC(           "Orc",           8,  3,     2,     1,   2, "mini-orc1.gif" ),
    FIMIR(         "Fimir",         6,  3,     3,     2,   3, "mini-fimir.gif" ),
    MUMMY(         "Mummy",         4,  3,     4,     2,   0, "mini-mummy.gif" ),
    CHAOS_WARRIOR( "Chaos Warrior", 7,  4,     4,     3,   3, "mini-chaoswarrior.gif" ),
    GARGOYLE(      "Gargoyle",      6,  4,     5,     3,   4, "mini-gargoyle.gif" );

    private String _name;
    private int _movement;
    private int _attack;
    private int _defend;
    private int _body;
    private int _mind;
    private String _icon;

    MonsterType( String n, int m, int a, int d, int b, int mind, String icon )
    {
        _name = n;
        _movement = m;
        _attack = a;
        _defend = d;
        _body = b;
        _mind = mind;
        _icon = icon;
    }

    public String getName(){ return _name; }
    public int getMovement(){ return _movement; }
    public int getAttack(){ return _attack; }
    public int getDefend(){ return _defend; }
    public int getBody(){ return _body; }
    public int getMind(){ return _mind; }
    public String getIconName(){ return _icon; }

    public String toString(){ return _name + "[" + _movement + "/" + _attack + "/" + _defend + "/" + _body + "/" + _mind + "]"; }
}

