package heroquest.item;

public abstract class Item
{
    protected ItemType _type;
    protected String _name;

    public Item( ItemType type )
    {
        this( type, null );
    }

    public Item( ItemType type, String name )
    {
        _type = type;
        _name = name;
    }

    public ItemType getType(){ return _type; }
    public String getName(){ return _name != null? _name: _type.getName(); }
}

