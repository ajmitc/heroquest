package heroquest.quest;

import heroquest.util.Logger;

public class QuestFactory
{
    private static Logger _logger = Logger.getLogger( QuestFactory.class );

    public static Quest createQuest( int number )
    {
        if( number == 1 )
        {
            return new Quest1();
        }
        _logger.error( "That Quest is not yet supported" );
        return null;
    }

    private QuestFactory(){}
}

