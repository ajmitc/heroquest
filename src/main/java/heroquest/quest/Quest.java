package heroquest.quest;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.*;

import heroquest.util.Util;
import heroquest.util.Logger;
import heroquest.monster.Monster;
import heroquest.treasure.Treasure;
import heroquest.furniture.Furniture;
import heroquest.furniture.FurnitureFactory;
import heroquest.furniture.FurnitureType;

public abstract class Quest
{
    public static final int BOARD_WIDTH = 26;
    public static final int BOARD_HEIGHT = 19;

    public static final int MIN_ROOM = 1;
    public static final int MAX_ROOM = 22;

    private Logger _logger = Logger.getLogger( Quest.class );

    protected int _number;
    protected String _name;
    protected String _description;

    protected List<Cell> _board;

    protected Monster _wanderingMonster;

    protected Map<Integer, Note> _notes;
    protected Map<Integer, List<Treasure>> _treasures; // Map<Room, List<Treasure>>
    
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

    public Quest( int num, String name )
    {
        _number = num;
        _name = name;
        _description = "";
        _board = new ArrayList<>();
        _wanderingMonster = null;
        _notes = new HashMap<>();
        _treasures = new HashMap<>();
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
            if( cell.getX() >= minX && cell.getX() <= maxX && cell.getY() >= minY && cell.getY() <= maxY )
            {
                cells.add( cell );
            }
        }
        return cells;
    }

    public Cell getCellInDir( Cell fromCell, int dir )
    {
        int x = fromCell.getX();
        int y = fromCell.getY();
        switch( dir )
        {
            case Cell.UP:
                y -= 1;
                break;
            case Cell.RIGHT:
                x += 1;
                break;
            case Cell.DOWN:
                y += 1;
                break;
            case Cell.LEFT:
                x -= 1;
                break;
        }
        return getCell( x, y );
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


    public int countWallsHorizontal( int x, int y )
    {
        List<Cell> cells = getCells( 0, x, y, y );
        Collections.sort( cells, new Comparator<Cell>(){
            public int compare( Cell c1, Cell c2 )
            {
                return (c1.getY() < c2.getY()? -1: (c1.getY() > c2.getY()? 1: 0));
            }

            public boolean equals( Object o )
            {
                return false;
            }
        });
        int count = 0;
        int lastRoom = 0;
        for( Cell cell: cells )
        {
            if( cell.getRoom() != lastRoom )
            {
                ++count;
                lastRoom = cell.getRoom();
            }
        }
        return count;
    }


    public int countWallsVertical( int x, int y )
    {
        List<Cell> cells = getCells( x, x, 0, y );
        Collections.sort( cells, new Comparator<Cell>(){
            public int compare( Cell c1, Cell c2 )
            {
                return (c1.getX() < c2.getX()? -1: (c1.getX() > c2.getX()? 1: 0));
            }

            public boolean equals( Object o )
            {
                return false;
            }
        });
        int count = 0;
        int lastRoom = 0;
        for( Cell cell: cells )
        {
            if( cell.getRoom() != lastRoom )
            {
                ++count;
                lastRoom = cell.getRoom();
            }
        }
        return count;
    }


    /**
     * Unhide the cells in the given room
     */
    public void showRoom( int room )
    {
        getRoomCells( room ).stream().forEach( cell -> cell.setHidden( false ) );
    }


    /**
     * Unhide the cells in the given room
     */
    public void showCells( int minX, int maxX, int minY, int maxY )
    {
        getCells( minX, maxX, minY, maxY ).stream().forEach( cell -> cell.setHidden( false ) );
    }


    public List<Cell> getStairCells()
    {
        return getBoard().stream().filter( cell -> cell.isStairs() ).collect( Collectors.toList() );
    }

    public void setStairsAt( int room, int dx, int dy )
    {
        Cell c = getCellAtOffsetInRoom( room, dx, dy );
        c.setFurniture( FurnitureFactory.create( FurnitureType.STAIRS ) );
        for( Cell cell: getCellsAtOffsetInRoom( room, dx, dx + 1, dy, dy + 1 ) )
            cell.setStairs( true );
        showRoom( room );
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
        Cell opp = getCellInDir( cell, dir );
        if( opp != null )
        {
            opp.setWall( oppdir, type );
        }
    }


    public void setMonsterAt( int room, int dx, int dy, Monster monster )
    {
        Cell c = getCellAtOffsetInRoom( room, dx, dy );
        c.setMonster( monster );
    }

    public void setFurnitureAt( int room, int dx, int dy, Furniture furniture )
    {
        Cell c = getCellAtOffsetInRoom( room, dx, dy );
        c.setFurniture( furniture );
    }


    public Monster getWanderingMonster(){ return _wanderingMonster; }

    public Map<Integer, Note> getNotes(){ return _notes; }
    public Note getNote( int room ){ return _notes.get( room ); }

    public Map<Integer, List<Treasure>> getTreasures(){ return _treasures; }
    public List<Treasure> getTreasure( int room )
    { 
        if( !_treasures.containsKey( room ) )
            _treasures.put( room, new ArrayList<Treasure>() );
        return _treasures.get( room );
    }

    public void setSolidRock( int x, int y )
    {
        getCell( x, y ).setSolidRock( true );
    }

    public void setSolidRock( int x0, int y0, int x1, int y1 )
    {
        getCells( x0, x1, y0, y1 ).stream().filter( cell -> cell != null ).forEach( cell -> cell.setSolidRock( true ) );
    }

    public void setSolidRockRoom( int room )
    {
        getRoomCells( room ).stream().forEach( cell -> cell.setSolidRock( true ) );
    }

    public List<Reward> getEndGameRewards(){ return _endGameRewards; }
}

