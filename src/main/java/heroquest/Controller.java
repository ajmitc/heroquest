package heroquest;

import heroquest.game.Game;
import heroquest.view.View;
import heroquest.quest.QuestFactory;
import heroquest.quest.TestQuest;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller
{
    private Model _model;
    private View _view;

    public Controller( Model model, View view )
    {
        _model = model;
        _view = view;

        _view.getMainMenuPanel().getBtnPlay().addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e )
            {
                Game game = new Game();
                game.setQuest( QuestFactory.createQuest( 1 ) );
                _model.setGame( game );
                game.placeHeroes();
                _view.showGame();
                _view.getGamePanel().setDisplayQuestDescription( true );
            }
        });

        _view.getMainMenuPanel().getBtnExit().addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e )
            {
                System.exit( 0 );
            }
        });

        _view.getGamePanel().addMouseListener( new MouseAdapter(){
            public void mouseClicked( MouseEvent e )
            {
                if( _view.getGamePanel().isDisplayQuestDescription() )
                {
                    _view.getGamePanel().setDisplayQuestDescription( false );
                    _view.refresh();
                }
            }
        });
    }
}

