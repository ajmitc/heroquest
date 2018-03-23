package heroquest.util;

import java.util.Random;
import java.util.Date;

public class Dice
{
    public static final int SKULL        = 0;
    public static final int WHITE_SHIELD = 1;
    public static final int BLACK_SHIELD = 2;
    
    public static final String SKULL_IMAGE        = "dice-skull.gif";
    public static final String WHITE_SHIELD_IMAGE = "dice-wshield.gif";
    public static final String BLACK_SHIELD_IMAGE = "dice-bshield.gif";

    public static final String DICE1_IMAGE = "dice1.gif";
    public static final String DICE2_IMAGE = "dice2.gif";
    public static final String DICE3_IMAGE = "dice3.gif";
    public static final String DICE4_IMAGE = "dice4.gif";
    public static final String DICE5_IMAGE = "dice5.gif";
    public static final String DICE6_IMAGE = "dice6.gif";

    private static Random _gen = new Random( new Date().getTime() );

    public static int[] rollCombat( int count )
    {
        int[] ret = new int[ count ];
        for( int i = 0; i < count; ++i )
        {
            ret[ i ] = roll();
        }
        return ret;
    }

    public static int rollCombat()
    {
        int r = _gen.nextInt( 6 );
        if( r <= 2 )
            return SKULL;
        if( r <= 4 )
            return WHITE_SHIELD;
        return BLACK_SHIELD;
    }

    public static int roll()
    {
        return _gen.nextInt( 6 ) + 1;
    }
}

