package heroquest.view;

import java.awt.Graphics2D;
import java.awt.Color;

import heroquest.quest.Quest;
import heroquest.util.Util;

public class QuestDescriptionDrawer
{
    private static final Color QUEST_DESC_BACKGROUND_COLOR = Color.YELLOW;
    private static final Color QUEST_DESC_TITLE_COLOR  = Color.BLACK;
    private static final Color QUEST_DESC_DESC_COLOR   = Color.BLACK;
    private static final Color QUEST_DESC_BORDER_COLOR = Color.BLACK;

    private static final int QUEST_DESC_XOFFSET       = 100;
    private static final int QUEST_DESC_YOFFSET       = 100;
    private static final int QUEST_DESC_WIDTH         = 400;
    private static final int QUEST_DESC_HEIGHT        = 300;
    private static final int QUEST_DESC_INSET         = 10;
    private static final int QUEST_DESC_TITLE_YOFFSET = 50;
    private static final int QUEST_DESC_DESC_YOFFSET  = 100;

    public static void draw( Quest quest, GamePanel gamepanel, Graphics2D g )
    {
        // Draw background
        g.setColor( QUEST_DESC_BACKGROUND_COLOR );
        g.fillRect( QUEST_DESC_XOFFSET, QUEST_DESC_YOFFSET, QUEST_DESC_WIDTH, QUEST_DESC_HEIGHT );
        g.setColor( QUEST_DESC_BORDER_COLOR );
        g.drawRect( QUEST_DESC_XOFFSET, QUEST_DESC_YOFFSET, QUEST_DESC_WIDTH, QUEST_DESC_HEIGHT );
        // Draw text
        g.setColor( QUEST_DESC_TITLE_COLOR );
        Util.drawCenteredString( g, quest.getName(), QUEST_DESC_XOFFSET, QUEST_DESC_YOFFSET + QUEST_DESC_TITLE_YOFFSET );
        g.setColor( QUEST_DESC_DESC_COLOR );
        Util.drawWrappedString( quest.getDescription(), g, QUEST_DESC_XOFFSET + QUEST_DESC_INSET, QUEST_DESC_YOFFSET + QUEST_DESC_DESC_YOFFSET, QUEST_DESC_WIDTH - (QUEST_DESC_INSET * 2) );

    }

    private QuestDescriptionDrawer(){}
}

