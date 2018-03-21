package heroquest.quest;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import heroquest.monster.*;

public class Quest1 extends Quest
{
    public Quest1()
    {
        _number = 1;
        _name = "The Trial";
        _description = "You have learnt well, my friends!  Now has come time of your first trial.";
        _wanderingMonster = new Orc();

        for( int r = MIN_ROOM; r < MAX_ROOM; ++r )
        {
            if( r == 17 )
            {
                setStairs( 17, 0, 0 );
                setClosedDoorAt( 17, 2, 3, Cell.DOWN );
            }
        }


        Note noteA = new Note();
        noteA.setDescription( "The weapons on the Weapons Rack are rusty and chipped.  There is nothing a Hero would want." );
        _notes.put( 19, noteA );

        //_endGameRewards = new ArrayList<>();
    }
}

