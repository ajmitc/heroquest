package heroquest.item;

public enum ItemType
{
    POTION_OF_HEALING( "Potion of Healing" );

    private String _name;

    ItemType( String n )
    {
        _name = n;
    }

    public String getName(){ return _name; }

    public String toString(){ return _name; }
}

