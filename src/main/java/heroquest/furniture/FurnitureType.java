package heroquest.furniture;

public enum FurnitureType
{
    STAIRS( "Stairs", "MGTileStairs.gif" ),
    BOOKCASE( "Bookcase", "furn-Bookcase.gif" ),
    CHEST( "Chest", "furn-Chest.gif" ),
    CUPBOARD( "Cupboard", "furn-Cupboard.gif" ),
    FIREPLACE( "Fireplace", "furn-FirePlace.gif" ),
    RACK( "Rack", "furn-Rack.gif" ),
    SORCERERS_TABLE( "Sorcerer's Table", "furn-SorcerersTable.gif" ),
    TABLE( "Table", "furn-Table.gif" ),
    THRONE( "Throne", "furn-Throne.gif" ),
    TOMB( "Tomb", "furn-Tomb.gif" ),
    WEAPONS_RACK( "Weapons Rack", "furn-WeaponsRack.gif" );

    private String _name;
    private String _imgFilename;

    FurnitureType( String n, String fn )
    {
        _name = n;
        _imgFilename = fn;
    }

    public String getName(){ return _name; }
    public String getImgFilename(){ return _imgFilename; }

    public String toString(){ return _name; }
}

