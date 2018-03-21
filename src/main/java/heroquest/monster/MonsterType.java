package heroquest.monster;

public enum MonsterType
{
    //      Name      Move Attack Defend Body Mind
    GOBLIN( "Goblin", 10,  2,     1,     1,   1 ),
    ORC(    "Orc",     8,  2,     2,     1,   1 );

    private String _name;
    private int _movement;
    private int _attack;
    private int _defend;
    private int _body;
    private int _mind;

    MonsterType( String n, int m, int a, int d, int b, int mind )
    {
        _name = n;
        _movement = m;
        _attack = a;
        _defend = d;
        _body = b;
        _mind = mind;
    }

    public String getName(){ return _name; }
    public int getMovement(){ return _movement; }
    public int getAttack(){ return _attack; }
    public int getDefend(){ return _defend; }
    public int getBody(){ return _body; }
    public int getMind(){ return _mind; }

    public String toString(){ return _name + "[" + _movement + "/" + _attack + "/" + _defend + "/" + _body + "/" + _mind + "]"; }
}

