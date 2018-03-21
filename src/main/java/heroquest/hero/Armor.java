package heroquest.hero;

public abstract class Armor
{
    private ArmorType _type;

    public Armor( ArmorType type )
    {
        _type = type;
    }

    public ArmorType getType(){ return _type; }
}

