package heroquest.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.List;

import heroquest.Model;
import heroquest.game.*;
import heroquest.quest.*;
import heroquest.hero.Hero;
import heroquest.util.ImageStore;
import heroquest.util.Logger;

public class GamePanel extends JPanel
{
    public static final String BOARD_IMAGE       = "HQBoard.jpg";
    public static final String ARMORY_IMAGE      = "MGWArmorySheetFull.gif";
    public static final String CLOSED_DOOR_IMAGE = "MGFurDoor.gif";
    public static final String OPEN_DOOR_IMAGE   = "MGFurOpenDoor.gif";
    public static final String WALL_IMAGE        = "MGTileWallSingle.gif";

    private static final Color SOLID_ROCK_COLOR = Color.BLACK;

    private static final int XOFFSET = 17;
    private static final int YOFFSET = 10;
    private static final int CELL_SIZE = 50;

    private Logger _logger = Logger.getLogger( GamePanel.class );
    private Model _model;
    private BufferedImage _boardImage;

    public GamePanel( Model model )
    {
        super();
        _model = model;
        _boardImage = ImageStore.getInstance().get( BOARD_IMAGE );

        // Load images
        ImageStore.getInstance().get( CLOSED_DOOR_IMAGE );
        ImageStore.getInstance().get( OPEN_DOOR_IMAGE );
        ImageStore.getInstance().get( WALL_IMAGE );
    }


    public void displayQuestDescription()
    {

    }

    public void paintComponent( Graphics gfx )
    {
        Graphics2D g = (Graphics2D) gfx;

        // Draw main board
        g.drawImage( _boardImage, 0, 0, null );

        Game game = _model.getGame();
        if( game == null )
            return;

        List<Hero> heroes = game.getHeroes();
        // TODO Draw Hero Panels
        
        Quest quest = game.getQuest();
        if( quest == null )
            return;
        // TODO Draw Quest Components

        List<Cell> board = quest.getBoard();
        for( Cell cell: board )
        {
            int px = XOFFSET + (cell.getX() * CELL_SIZE);
            int py = YOFFSET + (cell.getY() * CELL_SIZE);

            if( cell.isSolidRock() ) //|| cell.isHidden() )
            {
                _logger.info( "Drawing solid rock at [" + px + ", " + py + "]" );
                //g.setColor( SOLID_ROCK_COLOR );
                //g.fillRect( px, py, CELL_SIZE, CELL_SIZE );
                BufferedImage img = ImageStore.getInstance().get( WALL_IMAGE );
                g.drawImage( img, px, py, null );
                continue;
            }

            if( cell.getMonster() != null )
            {
                BufferedImage img = ImageStore.getInstance().get( cell.getMonster().getIconName() );
                g.drawImage( img, px, py, null );
            }
            else if( cell.getFurniture() != null )
            {
                BufferedImage img = ImageStore.getInstance().get( cell.getFurniture().getType().getImgFilename() );
                g.drawImage( img, px, py, null );
            }
            else if( cell.getTrap() != null )
            {
                if( !cell.getTrap().isHidden() )
                {
                    BufferedImage img = ImageStore.getInstance().get( cell.getTrap().getType().getIconName() );
                    g.drawImage( img, px, py, null );
                }
                else if( cell.getTrap().isWarned() )
                {
                    // What to do?
                }
            }
            else if( cell.getHero() != null )
            {
                BufferedImage img = ImageStore.getInstance().get( cell.getHero().getType().getIconName() );
                g.drawImage( img, px, py, null );
            }

            // Draw doors
            for( int i = 0; i < 4; ++i )
            {
                int w = cell.getWall( i );
                BufferedImage img = null;
                if( w == Cell.CLOSED_DOOR )
                {
                    img = ImageStore.getInstance().get( CLOSED_DOOR_IMAGE );
                }
                else if( w == Cell.OPEN_DOOR )
                {
                    
                    img = ImageStore.getInstance().get( OPEN_DOOR_IMAGE );
                }

                if( img != null )
                {
                    int dpx = px;
                    int dpy = py;
                    switch( i )
                    {
                        case Cell.UP:
                            break;
                        case Cell.RIGHT:
                            // TODO Rotate 90 degrees
                            dpx += CELL_SIZE;
                            break;
                        case Cell.DOWN:
                            dpy += CELL_SIZE;
                            break;
                        case Cell.LEFT:
                            // TODO Rotate 90 degrees
                            break;
                    }
                    g.drawImage( img, dpx, dpy, null );
                }
            }
        }
    }
}


