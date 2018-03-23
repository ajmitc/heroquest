package heroquest.furniture;

import heroquest.util.Logger;

public class FurnitureFactory
{
    private static Logger _logger = Logger.getLogger( FurnitureFactory.class );


    public static Furniture create( FurnitureType type )
    {
        return create( type, 0 );
    }

    public static Furniture create( FurnitureType type, int orientation )
    {
        Furniture f = new Furniture( type );
        switch( type )
        {
            case STAIRS:
                f.setSize( 2, 2 );
                break;
            case BOOKCASE:
                f.setSize( 3, 1 );
                break;
            case CHEST:
                f.setSize( 1, 1 );
                break;
            case CUPBOARD:
                f.setSize( 3, 1 );
                break;
            case FIREPLACE:
                f.setSize( 3, 1 );
                break;
            case RACK:
                f.setSize( 3, 2 );
                break;
            case SORCERERS_TABLE:
                f.setSize( 3, 2 );
                break;
            case TABLE:
                f.setSize( 3, 2 );
                break;
            case THRONE:
                f.setSize( 1, 1 );
                break;
            case TOMB:
                f.setSize( 3, 2 );
                break;
            case WEAPONS_RACK:
                f.setSize( 3, 1 );
                break;
            default:
                _logger.error( "Unsupported FurnitureType: " + type );
                return null;
        }
        f.setOrientation( orientation );
        return f;
    }

    private FurnitureFactory(){}
}

