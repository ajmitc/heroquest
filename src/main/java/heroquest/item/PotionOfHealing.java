package heroquest.item;

public class PotionOfHealing extends Item
{
    private int _bodyRestore;

    public PotionOfHealing( int bodyRestore )
    {
        super( ItemType.POTION_OF_HEALING );
        _bodyRestore = bodyRestore;
    }

    public int getBodyRestore(){ return _bodyRestore; }

    public String toString()
    {
        return _type.getName() + " (+" + _bodyRestore + ")";
    }
}

