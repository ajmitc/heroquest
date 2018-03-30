package heroquest.view;

import heroquest.Model;
import heroquest.util.Util;

import javax.swing.JFrame;

import java.awt.CardLayout;

public class View
{
    public static final String MAINMENU  = "MainMenu";
    public static final String GAMEPANEL = "GamePanel";

    private Model _model;
    private JFrame _frame;

    private MainMenuPanel _mainMenuPanel;
    private GamePanel _gamePanel;

    public View( Model model, JFrame frame )
    {
        _model = model;
        _frame = frame;

        _mainMenuPanel = new MainMenuPanel();
        _gamePanel = new GamePanel( _model );

        _frame.getContentPane().setLayout( new CardLayout() );
        _frame.getContentPane().add( _mainMenuPanel, MAINMENU );
        _frame.getContentPane().add( _gamePanel, GAMEPANEL );
    }

    public void showMainMenu()
    {
        _frame.setSize( 1024, 647 );
        showCard( MAINMENU );
    }

    public void showGame()
    {
        //_frame.setSize( Util.getScreenSize() );
        showCard( GAMEPANEL );
    }

    private void showCard( String name )
    {
        CardLayout cl = (CardLayout) _frame.getContentPane().getLayout();
        cl.show( _frame.getContentPane(), name );
    }

    public Model getModel(){ return _model; }
    public JFrame getFrame(){ return _frame; }
    public MainMenuPanel getMainMenuPanel(){ return _mainMenuPanel; }
    public GamePanel getGamePanel(){ return _gamePanel; }
}

