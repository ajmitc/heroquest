package heroquest.hero;

public abstract class Weapon
{
    private WeaponType _type;

    public Weapon( WeaponType type )
    {
        _type = type;
    }

    public WeaponType getType(){ return _type; }

    public String getName(){ return _type.getName(); }
    public int getAttack(){ return _type.getAttack(); }
    public int getMinRange(){ return _type.getMinRange(); }
    public int getMaxRange(){ return _type.getMaxRange(); }
    public boolean allowDiagonalAttack(){ return _type.allowDiagonalAttack(); }

    public String toString(){ return _type.toString(); }
}

