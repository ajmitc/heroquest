package heroquest.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.List;
import java.util.ArrayList;

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
    public static final String MASK_IMAGE        = "mask.gif";

    private static final Color HIDDEN_COLOR = new Color( 0, 0, 0, 200 );

    private static final int XOFFSET = 18;
    private static final int YOFFSET = 10;
    private static final int CELL_SIZE = 50;
    private static final int WALL_SIZE = 5;
    private static final int BOARD_WIDTH  = 1358;
    private static final int BOARD_HEIGHT = 1089;
    private static final int HERO_PANEL_XOFFSET = BOARD_WIDTH;
    private static final int HERO_PANEL_YOFFSET = 0;
    private static final int HERO_PANEL_WIDTH = 200;
    private static final int HERO_PANEL_HEIGHT = 250;

    private Logger _logger = Logger.getLogger( GamePanel.class );
    private Model _model;
    private BufferedImage _boardImage;

    private List<HeroPanel> _heroPanels;

    private boolean _displayQuestDescription;

    public GamePanel( Model model )
    {
        super();
        _model = model;
        _boardImage = ImageStore.getInstance().get( BOARD_IMAGE );

        // Load images
        ImageStore.getInstance().get( CLOSED_DOOR_IMAGE );
        ImageStore.getInstance().get( OPEN_DOOR_IMAGE );
        ImageStore.getInstance().get( WALL_IMAGE );
        ImageStore.getInstance().get( MASK_IMAGE );

        _heroPanels = null;
        _displayQuestDescription = false;
    }


    public void setDisplayQuestDescription( boolean v )
    {
        _displayQuestDescription = v;
    }

    public boolean isDisplayQuestDescription()
    {
        return _displayQuestDescription;
    }

    public void paintComponent( Graphics gfx )
    {
        Game game = _model.getGame();
        if( game == null )
            return;

        Graphics2D g = (Graphics2D) gfx;

        // Draw main board
        g.drawImage( _boardImage, 0, 0, null );

        List<Hero> heroes = game.getHeroes();
        // Draw Hero Panels
        if( _heroPanels == null )
        {
            _heroPanels = new ArrayList<>();
            for( Hero hero: _model.getGame().getHeroes() )
            {
                _heroPanels.add( new HeroPanel( _model, hero ) );
            }
        }
        for( int i = 0; i < _heroPanels.size(); ++i )
        {
            HeroPanel heroPanel = _heroPanels.get( i );
            heroPanel.paintComponent( g, HERO_PANEL_XOFFSET, HERO_PANEL_YOFFSET + (i * HERO_PANEL_HEIGHT) );
        }
        
        Quest quest = game.getQuest();
        if( quest == null )
            return;
        // TODO Draw Quest Components

        List<Cell> board = quest.getBoard();
        for( Cell cell: board )
        {
            //int px = XOFFSET + (cell.getX() * CELL_SIZE) + (quest.countWallsHorizontal( cell.getX(), cell.getY() ) * WALL_SIZE);
            //int py = YOFFSET + (cell.getY() * CELL_SIZE) + (quest.countWallsVertical(   cell.getX(), cell.getY() ) * WALL_SIZE);
            int px = XOFFSET + (cell.getX() * CELL_SIZE) + cell.getX();
            int py = YOFFSET + (cell.getY() * CELL_SIZE) + cell.getY();

            if( cell.isHidden() )
            {
                g.setColor( HIDDEN_COLOR );
                g.fillRect( px, py, CELL_SIZE, CELL_SIZE );
            }
        }

        for( Cell cell: board )
        {
            if( cell.isHidden() )
                continue;

            int px = XOFFSET + (cell.getX() * CELL_SIZE) + cell.getX();
            int py = YOFFSET + (cell.getY() * CELL_SIZE) + cell.getY();

            if( cell.isSolidRock() ) //|| cell.isHidden() )
            {
                _logger.info( "Drawing solid rock at [" + px + ", " + py + "]" );
                //g.setColor( SOLID_ROCK_COLOR );
                //g.fillRect( px, py, CELL_SIZE, CELL_SIZE );
                BufferedImage img = ImageStore.getInstance().get( WALL_IMAGE );
                g.drawImage( img, px, py, null );
                //g.setColor( Color.WHITE );
                //g.drawString( "[" + cell.getX() + ", " + cell.getY() + "]", px, py + (CELL_SIZE / 2) );
                continue;
            }

            if( cell.getTrap() != null )
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

            if( cell.getFurniture() != null )
            {
                BufferedImage img = ImageStore.getInstance().get( cell.getFurniture().getType().getImgFilename() );
                g.drawImage( img, px, py, null );
            }

            if( cell.getMonster() != null )
            {
                BufferedImage img = ImageStore.getInstance().get( MASK_IMAGE );
                g.drawImage( img, px, py, null );
                img = ImageStore.getInstance().get( cell.getMonster().getIconName() );
                g.drawImage( img, px, py, null );
            }

            if( cell.getHero() != null )
            {
                BufferedImage img = ImageStore.getInstance().get( MASK_IMAGE );
                g.drawImage( img, px, py, null );
                img = ImageStore.getInstance().get( cell.getHero().getType().getIconName() );
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

        if( _displayQuestDescription )
        {
            QuestDescriptionDrawer.draw( quest, this, g );
        }
    }
}


