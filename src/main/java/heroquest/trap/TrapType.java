package heroquest.trap;

public enum TrapType
{
    PIT( "Pit Trap", "MGTrapPitTrap.gif" ),
    SPEAR( "Spear Trap", null ),
    STALACTITE( "Stalactite Trap", null ),
    FALLING_BLOCK( "Falling Block Trap", "MGTileRubble.gif" );

    private String _name;
    private String _icon;

    TrapType( String n, String fn )
    {
        _name = n;
        _icon = fn;
    }

    public String getName(){ return _name; }
    public String getIconName(){ return _icon; }

    public String toString(){ return _name; }
}
