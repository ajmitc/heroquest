package heroquest.quest;

import heroquest.monster.Monster;
import heroquest.furniture.Furniture;
import heroquest.trap.Trap;

public class Cell
{
    // Direction / Index
    public static final int UP    = 0;
    public static final int RIGHT = 1;
    public static final int DOWN  = 2;
    public static final int LEFT  = 3;

    // Wall Type
    public static final int OPEN        = 0;
    public static final int CLOSED_DOOR = 1;
    public static final int OPEN_DOOR   = 2;
    public static final int WALL        = 3;

    // Room Number
    public static final int HALLWAY = 0;

    private int _x, _y;
    private Monster _monster;
    private Furniture _furniture;
    private Trap _trap;
    private boolean _solidRock;

    private int[] _walls;
    private int _room; // 0 = hallway
    private boolean _stairs;
    
    public Cell()
    {
        _x = _y = 0;
        _monster = null;
        _furniture = null;
        _trap = null;
        _solidRock = false;
        _walls = new int[]{ OPEN, OPEN, OPEN, OPEN };
        _room = HALLWAY;
        _stairs = false;
    }

    public Cell( int x, int y )
    {
        this();
        _x = x;
        _y = y;
    }

    public int getX(){ return _x; }
    public int getY(){ return _y; }
    public Monster getMonster(){ return _monster; }
    public Furniture getFurniture(){ return _furniture; }
    public Trap getTrap(){ return _trap; }
    public boolean isSolidRock(){ return _solidRock; }
    public int getWall( int index ){ return _walls[ index ]; }
    public int getRoom(){ return _room; }
    public boolean isStairs(){ return _stairs; }

    public void setLocation( int x, int y ){ _x = x; _y = y; }
    public void setMonster( Monster m ){ _monster = m; }
    public void setFurniture( Furniture f ){ _furniture = f; }
    public void setTrap( Trap t ){ _trap = t; }
    public void setSolidRock( boolean v ){ _solidRock = v; }
    public void setWall( int index, int v ){ _walls[ index ] = v; }
    public void setRoom( int num ){ _room = num; }
    public void setStairs( boolean v ){ _stairs = v; }
}

