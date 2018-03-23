package heroquest.monster;

public class Monster
{
    private MonsterType _type;
    private String _name;

    private Integer _movement;
    private Integer _attack;
    private Integer _defend;
    private Integer _body;
    private Integer _mind;
    private String _icon;

    public Monster( MonsterType type )
    {
        this( type, null );
    }

    public Monster( MonsterType type, String name )
    {
        _type = type;
        _name = name;
        _movement = null;
        _attack = null;
        _defend = null;
        _body = null;
        _mind = null;
        _icon = null;
    }

    public Monster clone()
    {
        Monster m = new Monster( _type, _name );
        m._movement = _movement;
        m._attack = _attack;
        m._defend = _defend;
        m._body = _body;
        m._mind = _mind;
        m._icon = _icon;
        return m;
    }

    public MonsterType getType(){ return _type; }
    public String getName(){ return _name != null? _name: _type.getName(); }

    public int getMovement(){ return _movement != null? _movement: _type.getMovement(); }
    public int getAttack(){ return _attack != null? _attack: _type.getAttack(); }
    public int getDefend(){ return _defend != null? _defend: _type.getDefend(); }
    public int getBody(){ return _body != null? _body: _type.getBody(); }
    public int getMind(){ return _mind != null? _mind: _type.getMind(); }
    public String getIconName(){ return _icon != null? _icon: _type.getIconName(); }

    public void setMovement( int m ){ _movement = m; }
    public void setAttack( int a ){ _attack = a; }
    public void setDefend( int d ){ _defend = d; }
    public void setBody( int b ){ _body = b; }
    public void setMind( int m ){ _mind = m; }
    public void setIconName( String i ){ _icon = i; }
}

