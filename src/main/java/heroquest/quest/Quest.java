package heroquest.quest;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import heroquest.util.Util;
import heroquest.monster.Monster;

public abstract class Quest
{
    public static final int BOARD_WIDTH = 26;
    public static final int BOARD_HEIGHT = 19;

    public static final int MIN_ROOM = 1;
    public static final int MAX_ROOM = 22;

    protected int _number;
    protected String _name;
    protected String _description;

    protected List<Cell> _board;

    protected Monster _wanderingMonster;

    protected Map<Integer, Note> _notes;
    
    protected List<Reward> _endGameRewards;

    private static Map<Integer, Integer[]> _roomBoundaries = new HashMap<>();

    static {
        //                   room             minX, maxX, minY, maxY
        _roomBoundaries.put(  1, new Integer[]{  1,  4,  1,  3 } );
        _roomBoundaries.put(  2, new Integer[]{  5,  8,  1,  3 } );
        _roomBoundaries.put(  3, new Integer[]{  9, 11,  1,  5 } );
        _roomBoundaries.put(  4, new Integer[]{ 14, 16,  1,  5 } );
        _roomBoundaries.put(  5, new Integer[]{ 17, 20,  1,  4 } );
        _roomBoundaries.put(  6, new Integer[]{ 21, 24,  1,  4 } );
        _roomBoundaries.put(  7, new Integer[]{  1,  4,  4,  8 } );
        _roomBoundaries.put(  8, new Integer[]{  5,  8,  4,  8 } );
        _roomBoundaries.put(  9, new Integer[]{ 17, 20,  5,  8 } );
        _roomBoundaries.put( 10, new Integer[]{ 21, 24,  5,  8 } );
        _roomBoundaries.put( 11, new Integer[]{ 10, 15,  7, 11 } );
        _roomBoundaries.put( 12, new Integer[]{  1,  4, 10, 13 } );
        _roomBoundaries.put( 13, new Integer[]{  5,  6, 10, 12 } );
        _roomBoundaries.put( 14, new Integer[]{  7,  8, 10, 12 } );
        _roomBoundaries.put( 15, new Integer[]{ 17, 20, 10, 13 } );
        _roomBoundaries.put( 16, new Integer[]{ 21, 24, 10, 13 } );
        _roomBoundaries.put( 17, new Integer[]{  1,  4, 14, 17 } );
        _roomBoundaries.put( 18, new Integer[]{  5,  8, 13, 17 } );
        _roomBoundaries.put( 19, new Integer[]{  9, 11, 13, 17 } );
        _roomBoundaries.put( 20, new Integer[]{ 14, 17, 13, 17 } );
        _roomBoundaries.put( 21, new Integer[]{ 18, 20, 14, 17 } );
        _roomBoundaries.put( 22, new Integer[]{ 21, 24, 14, 17 } );
    }

    public Quest()
    {
        _number = 0;
        _name = "";
        _description = "";
        _board = new ArrayList<>();
        _wanderingMonster = null;
        _notes = new HashMap<>();
        _endGameRewards = new ArrayList<>();

        initBoard();
    }

    /**
     * Load the default board with nothing on it.
     */
    private void initBoard()
    {
        // Create Cells and assign rooms
        for( int y = 0; y < BOARD_HEIGHT; ++y )
        {
            for( int x = 0; x < BOARD_WIDTH; ++x )
            {
                Cell cell = new Cell( x, y );

                for( int room: _roomBoundaries.keySet() )
                {
                    int minX = _roomBoundaries.get( room )[ 0 ];
                    int maxX = _roomBoundaries.get( room )[ 1 ];
                    int minY = _roomBoundaries.get( room )[ 2 ];
                    int maxY = _roomBoundaries.get( room )[ 3 ];

                    if( x >= minX && x <= maxX && y >= minY && y <= maxY )
                    {
                        cell.setRoom( room );
                        break;
                    }
                }

                if( x == 17 && y == 13 )
                {
                    cell.setRoom( 20 );
                }

                _board.add( cell );
            }
        }

        // Create walls
        for( int y = 0; y < BOARD_HEIGHT; ++y )
        {
            for( int x = 0; x < BOARD_WIDTH; ++x )
            {
                Cell cell = getCell( x, y );

                for( int room: _roomBoundaries.keySet() )
                {
                    int minX = _roomBoundaries.get( room )[ 0 ];
                    int maxX = _roomBoundaries.get( room )[ 1 ];
                    int minY = _roomBoundaries.get( room )[ 2 ];
                    int maxY = _roomBoundaries.get( room )[ 3 ];

                    if( cell.getRoom() == room )
                    {
                        // Left wall
                        if( x == minX )
                        {
                            setWall( cell, Cell.LEFT );
                        }

                        // Right wall
                        if( x == maxX )
                        {
                            setWall( cell, Cell.RIGHT );
                        }

                        // Top wall
                        if( y == minY )
                        {
                            setWall( cell, Cell.UP );
                        }

                        // Bottom wall
                        if( y == maxY )
                        {
                            setWall( cell, Cell.DOWN );
                        }

                        break;
                    }
                }

                // Left hallway outside wall
                if( x == 0 )
                {
                    setWall( cell, Cell.LEFT );
                }

                // Right hallway outside wall
                if( x == BOARD_WIDTH - 1 ) 
                {
                    setWall( cell, Cell.RIGHT );
                }

                // Top hallway outside wall
                if( y == 0 )
                {
                    setWall( cell, Cell.UP );
                }

                // Bottom hallway outside wall
                if( y == BOARD_HEIGHT - 1 )
                {
                    setWall( cell, Cell.DOWN );
                }
            }
        }
    }

    public int getNumber(){ return _number; }
    public String getName(){ return _name; }
    public String getDescription(){ return _description; }

    public List<Cell> getBoard(){ return _board; }

    public Cell getCell( int x, int y )
    {
        for( Cell cell: _board )
        {
            if( cell.getX() == x && cell.getY() == y )
            {
                return cell;
            }
        }
        return null;
    }

    public List<Cell> getCells( int minX, int maxX, int minY, int maxY )
    {
        List<Cell> cells = new ArrayList<>();
        for( Cell cell: _board )
        {
            if( cell.getX() >= minX && cell.getX() <= maxY && cell.getY() >= minY && cell.getY() <= maxY )
            {
                cells.add( cell );
            }
        }
        return cells;
    }

    public List<Cell> getRoomCells( int room )
    {
        List<Cell> cells = new ArrayList<>();
        for( Cell cell: _board )
        {
            if( cell.getRoom() == room )
            {
                cells.add( cell );
            }
        }
        return cells;
    }


    public Cell getCellAtOffsetInRoom( int room, int dx, int dy )
    {
        Integer[] bounds = _roomBoundaries.get( room );
        int x = bounds[ 0 ] + dx;
        int y = bounds[ 2 ] + dy;
        List<Cell> cells = getRoomCells( room );
        for( Cell cell: cells )
        {
            if( cell.getX() == x && cell.getY() == y )
            {
                return cell;
            }
        }
        return null;
    }

    public List<Cell> getCellsAtOffsetInRoom( int room, int minDx, int maxDx, int minDy, int maxDy )
    {
        List<Cell> cells = new ArrayList<>();
        for( int dy = minDy; dy <= maxDy; ++dy )
        {
            for( int dx = minDx; dx <= maxDx; ++dx )
            {
                Cell cell = getCellAtOffsetInRoom( room, dx, dy );
                cells.add( cell );
            }
        }
        return cells;
    }


    public void setStairsAt( int room, int dx, int dy )
    {
        Cell c = getCellAtOffsetInRoom( room, dx, dy );
        c.setFurniture( new Stairs() );
        for( c: getCellsAtOffsetInRoom( room, dx, dx + 1, dy, dy + 1 ) )
            c.setStairs( true );
    }



    public void setWall( Cell cell, int dir )
    {
        setWallType( cell, dir, Cell.WALL );
    }

    public void setClosedDoorAt( int room, int dx, int dy, int dir )
    {
        Cell c = getCellAtOffsetInRoom( room, dx, dy );
        setWallType( c, dir, Cell.CLOSED_DOOR );
    }


    public void setWallType( Cell cell, int dir, int type )
    {
        cell.setWall( dir, type );
        // get the opposite cell and set the wall there too
        int oppdir = Util.oppositeDir( dir );
        Cell opp = getCell( oppdir );
        if( opp != null )
            opp.setWall( oppdir, type );
    }


    public Monster getWanderingMonster(){ return _wanderingMonster; }

    public Map<Integer, Note> getNotes(){ return _notes; }
    public Note getNote( int room ){ return _notes.get( room ); }

    public List<Reward> getEndGameRewards(){ return _endGameRewards; }
}

