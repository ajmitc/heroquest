package heroquest;

import heroquest.game.Game;

public class Model
{
    private Game _game;

    public Model()
    {
        
    }

    public Game getGame(){ return _game; }
    public void setGame( Game g ){ _game = g; }
}

