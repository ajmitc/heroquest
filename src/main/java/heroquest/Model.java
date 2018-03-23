package heroquest;

import heroquest.game.Game;
import heroquest.util.ImageStore;
import heroquest.util.Dice;

public class Model
{
    private Game _game;

    public Model()
    {
        _game = null;

        // Load some images
        ImageStore.getInstance().get( Dice.BLACK_SHIELD_IMAGE );
        ImageStore.getInstance().get( Dice.WHITE_SHIELD_IMAGE );
        ImageStore.getInstance().get( Dice.SKULL_IMAGE );
        ImageStore.getInstance().get( Dice.DICE1_IMAGE );
        ImageStore.getInstance().get( Dice.DICE2_IMAGE );
        ImageStore.getInstance().get( Dice.DICE3_IMAGE );
        ImageStore.getInstance().get( Dice.DICE4_IMAGE );
        ImageStore.getInstance().get( Dice.DICE5_IMAGE );
        ImageStore.getInstance().get( Dice.DICE6_IMAGE );
    }

    public Game getGame(){ return _game; }
    public void setGame( Game g ){ _game = g; }
}

