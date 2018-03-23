package heroquest;

import heroquest.game.Game;
import heroquest.view.View;
import heroquest.quest.QuestFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
                _view.showGame();
                _view.getGamePanel().displayQuestDescription();
            }
        });

        _view.getMainMenuPanel().getBtnExit().addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e )
            {
                System.exit( 0 );
            }
        });
    }
}

