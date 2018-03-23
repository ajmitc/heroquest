package heroquest.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import heroquest.util.ImageStore;
import heroquest.util.GridBagLayoutUtil;

public class MainMenuPanel extends JPanel
{
    public static final String COVER_IMAGE = "cover.jpg";

    private ImageIcon _coverImage;

    private JButton _btnPlay;
    private JButton _btnExit;

    public MainMenuPanel()
    {
        super();
        _coverImage = ImageStore.getInstance().getIcon( COVER_IMAGE );

        JPanel coverPanel = new JPanel( new BorderLayout() );
        coverPanel.setBounds( 0, 0, 1024, 647 );
        coverPanel.add( new JLabel( _coverImage ), BorderLayout.CENTER );

        _btnPlay = new JButton( "Play" );
        _btnExit = new JButton( "Exit" );

        JPanel buttonpanel = new JPanel();
        buttonpanel.setBounds( 0, 0, 1024, 647 );
        buttonpanel.setOpaque( false );
        GridBagLayoutUtil.setLayout( buttonpanel )
            .anchorPageEnd()
            .add( buttonpanel, _btnPlay )
            .reset()
            .grid( 0, 1 )
            .anchorPageStart()
            .add( buttonpanel, _btnExit );

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds( 0, 0, 1024, 647 );
        layeredPane.add( coverPanel, new Integer( 0 ), 0 );
        layeredPane.add( buttonpanel, new Integer( 1 ), 0 );

        setLayout( new BorderLayout() );
        add( layeredPane, BorderLayout.CENTER );
        //add( coverPanel, BorderLayout.WEST );
        //add( buttonpanel, BorderLayout.EAST );
    }

    /*
    public void paintComponent( Graphics gfx )
    {
        Graphics2D g = (Graphics2D) gfx;

        // Draw main board
        g.drawImage( _boardImage, 0, 0, null );
    }
    */

    public JButton getBtnPlay(){ return _btnPlay; }
    public JButton getBtnExit(){ return _btnExit; }
}

