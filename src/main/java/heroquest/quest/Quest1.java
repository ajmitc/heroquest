package heroquest.quest;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import heroquest.monster.*;
import heroquest.furniture.*;
import heroquest.treasure.*;

public class Quest1 extends Quest
{
    public Quest1()
    {
        super( 1, "The Trial" );

        _description = "You have learnt well, my friends!  Now has come time of your first trial.  You are to travel east and enter the catacombs of Verag, a foul gargoyle.  The catacombs guard the tomb of Fellmarg.  The trial is not easy and some of you may not return.  Those who do survive will continue their training.  This is the first step on the road to becoming heroes ... tread carefully, my friends.";
        _wanderingMonster = new Orc();

        // Populate Rooms

        int r = 1; // room number
        setClosedDoorAt( r, 3, 1, Cell.RIGHT );
        setClosedDoorAt( r, 2, 2, Cell.DOWN );
        setMonsterAt( r, 1, 1, new Monster( MonsterType.SKELETON ) );
        setMonsterAt( r, 2, 1, new Monster( MonsterType.SKELETON ) );

        r = 2;
        Monster mummy = new Monster( MonsterType.MUMMY, "Guardian" );
        mummy.setDefend( MonsterType.MUMMY.getDefend() + 1 );
        setMonsterAt( r, 1, 0, new Monster( MonsterType.ZOMBIE ) );
        setMonsterAt( r, 1, 1, mummy );
        setMonsterAt( r, 1, 2, new Monster( MonsterType.ZOMBIE ) );
        setClosedDoorAt( r, 3, 1, Cell.RIGHT );

        r = 3;
        setMonsterAt( r, 0, 0, new Monster( MonsterType.SKELETON ) );
        setMonsterAt( r, 0, 2, new Monster( MonsterType.MUMMY ) );
        setMonsterAt( r, 0, 3, new Monster( MonsterType.SKELETON ) );
        setFurnitureAt( r, 1, 0, FurnitureFactory.create( FurnitureType.TOMB, 270 ) );
        setFurnitureAt( r, 1, 4, FurnitureFactory.create( FurnitureType.CHEST, 270 ) );
        getTreasure( r ).add( new Gold( 84 ) );

        r = 7;
        setMonsterAt( r, 3, 1, new Monster( MonsterType.ORC ) );
        setMonsterAt( r, 2, 2, new Monster( MonsterType.GOBLIN ) );
        setFurnitureAt( r, 0, 1, FurnitureFactory.create( FurnitureType.SORCERERS_TABLE ) );
        setClosedDoorAt( r, 2, 4, Cell.DOWN );

        r = 17;
        setStairsAt( r, 0, 0 );
        setClosedDoorAt( r, 2, 3, Cell.DOWN );

        setSolidRock( 0, 1, 0, 1 );
        setSolidRock( 0, 0, BOARD_WIDTH - 1, 0 );
        setSolidRock( 13, 1, BOARD_WIDTH - 1, 4 );
        setSolidRock( 15, 5, BOARD_WIDTH - 1, 5 );
        setSolidRock( 18, 6, BOARD_WIDTH - 1, 12 );
        setSolidRock( 19, 13, BOARD_WIDTH - 1, 17 );
        setSolidRock( 15, BOARD_HEIGHT - 1, BOARD_WIDTH - 1, BOARD_HEIGHT - 1 );

        setSolidRockRoom( 13 );
        setSolidRockRoom( 14 );


        Note noteA = new Note();
        noteA.setDescription( "The weapons on the Weapons Rack are rusty and chipped.  There is nothing a Hero would want." );
        _notes.put( 19, noteA );

        Note noteC = new Note();
        noteC.setDescription( "This Mummy is the guardian of Fellmarg's tomb and was once a mighty warrior.  It rolls an extra combat die in defense." );
        _notes.put( 2, noteC );

        //_endGameRewards = new ArrayList<>();
    }
}

