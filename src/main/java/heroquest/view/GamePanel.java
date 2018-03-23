package heroquest.view;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import heroquest.Model;
import heroquest.util.ImageStore;

public class GamePanel extends JPanel
{
    public static final String BOARD_IMAGE = "HQBoard.jpg";
    public static final String ARMORY_IMAGE = "MGWArmorySheetFull.gif";

    private Model _model;
    private BufferedImage _boardImage;

    public GamePanel( Model model )
    {
        super();
        _model = model;
        _boardImage = ImageStore.getInstance().get( BOARD_IMAGE );
    }


    public void displayQuestDescription()
    {

    }

    public void paintComponent( Graphics gfx )
    {
        Graphics2D g = (Graphics2D) gfx;

        // Draw main board
        g.drawImage( _boardImage, 0, 0, null );
    }
}

