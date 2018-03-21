package heroquest.monster;

public abstract class Monster
{
    private MonsterType _type;
    private String _name;

    public Monster( MonsterType type )
    {
        this( type, type.getName() );
    }

    public Monster( MonsterType type, String name )
    {
        _type = type;
        _name = name;
    }

    public Monster clone()
    {
        Monster m = new Monster( _type, _name );
        return m;
    }

    public MonsterType getType(){ return _type; }
    public String getName(){ return _name; }
}

