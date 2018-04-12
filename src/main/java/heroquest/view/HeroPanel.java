package heroquest.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.image.BufferedImage;

import java.util.stream.*;

import heroquest.Model;
import heroquest.hero.Hero;
import heroquest.util.ImageStore;
import heroquest.util.Logger;

public class HeroPanel
{
    public static final String FONT_NAME = "Serif";
    public static final int NAME_FONT_SIZE = 20;
    public static final Font NAME_FONT = new Font( FONT_NAME, Font.PLAIN, NAME_FONT_SIZE );
    public static final int TYPE_FONT_SIZE = 16;
    public static final Font TYPE_FONT = new Font( FONT_NAME, Font.PLAIN, TYPE_FONT_SIZE );
    public static final int STAT_LABEL_FONT_SIZE = 16;
    public static final Font STAT_LABEL_FONT = new Font( FONT_NAME, Font.PLAIN, STAT_LABEL_FONT_SIZE );
    public static final int STAT_FONT_SIZE = 20;
    public static final Font STAT_FONT = new Font( FONT_NAME, Font.BOLD, STAT_FONT_SIZE );
    public static final int INV_LABEL_FONT_SIZE = 12;
    public static final Font INV_LABEL_FONT = new Font( FONT_NAME, Font.PLAIN, INV_LABEL_FONT_SIZE );
    public static final int INV_FONT_SIZE = 14;
    public static final Font INV_FONT = new Font( FONT_NAME, Font.PLAIN, INV_FONT_SIZE );

    public static final int BORDER_WIDTH = 10;
    public static final int STAT_WIDTH = 60;
    public static final int STAT_HEIGHT = STAT_FONT_SIZE + 10;

    public static final int NAME_XOFFSET = BORDER_WIDTH + 60;
    public static final int NAME_YOFFSET = BORDER_WIDTH + NAME_FONT_SIZE + 5;
    public static final int TYPE_XOFFSET = NAME_XOFFSET;
    public static final int TYPE_YOFFSET = NAME_YOFFSET + TYPE_FONT_SIZE + 5;

    public static final int ATTACK_LABEL_XOFFSET = TYPE_XOFFSET;
    public static final int ATTACK_LABEL_YOFFSET = TYPE_YOFFSET + STAT_LABEL_FONT_SIZE + 10;
    public static final int ATTACK_XOFFSET = ATTACK_LABEL_XOFFSET;
    public static final int ATTACK_YOFFSET = ATTACK_LABEL_YOFFSET + STAT_FONT_SIZE + 5;

    public static final int DEFEND_LABEL_XOFFSET = ATTACK_LABEL_XOFFSET + STAT_WIDTH + 10;
    public static final int DEFEND_LABEL_YOFFSET = ATTACK_LABEL_YOFFSET;
    public static final int DEFEND_XOFFSET = DEFEND_LABEL_XOFFSET;
    public static final int DEFEND_YOFFSET = DEFEND_LABEL_YOFFSET + STAT_FONT_SIZE + 5;

    public static final int BODY_LABEL_XOFFSET = DEFEND_LABEL_XOFFSET + STAT_WIDTH + 10;
    public static final int BODY_LABEL_YOFFSET = DEFEND_LABEL_YOFFSET;
    public static final int BODY_XOFFSET = BODY_LABEL_XOFFSET;
    public static final int BODY_YOFFSET = BODY_LABEL_YOFFSET + STAT_FONT_SIZE + 5;

    public static final int MIND_LABEL_XOFFSET = BODY_LABEL_XOFFSET + STAT_WIDTH + 10;
    public static final int MIND_LABEL_YOFFSET = BODY_LABEL_YOFFSET;
    public static final int MIND_XOFFSET = MIND_LABEL_XOFFSET;
    public static final int MIND_YOFFSET = MIND_LABEL_YOFFSET + STAT_FONT_SIZE + 5;

    public static final int WEAPON_LABEL_XOFFSET = ATTACK_LABEL_XOFFSET;
    public static final int WEAPON_LABEL_YOFFSET = ATTACK_LABEL_YOFFSET + STAT_HEIGHT + 20;
    public static final int WEAPON_XOFFSET = WEAPON_LABEL_XOFFSET + 70;
    public static final int WEAPON_YOFFSET = WEAPON_LABEL_YOFFSET;

    public static final int ARMOR_LABEL_XOFFSET = WEAPON_LABEL_XOFFSET;
    public static final int ARMOR_LABEL_YOFFSET = WEAPON_LABEL_YOFFSET + INV_FONT_SIZE + 15;
    public static final int ARMOR_XOFFSET = ARMOR_LABEL_XOFFSET + 70;
    public static final int ARMOR_YOFFSET = ARMOR_LABEL_YOFFSET;

    public static final int ITEMS_LABEL_XOFFSET = ARMOR_LABEL_XOFFSET;
    public static final int ITEMS_LABEL_YOFFSET = ARMOR_LABEL_YOFFSET + INV_FONT_SIZE + 15;
    public static final int ITEMS_XOFFSET = ITEMS_LABEL_XOFFSET + 70;
    public static final int ITEMS_YOFFSET = ITEMS_LABEL_YOFFSET;

    public static final int GOLD_LABEL_XOFFSET = ITEMS_LABEL_XOFFSET;
    public static final int GOLD_LABEL_YOFFSET = ITEMS_LABEL_YOFFSET + INV_FONT_SIZE + 15;
    public static final int GOLD_XOFFSET = GOLD_LABEL_XOFFSET + 70;
    public static final int GOLD_YOFFSET = GOLD_LABEL_YOFFSET;

    private Logger _logger = Logger.getLogger( HeroPanel.class );
    private Model _model;
    private Hero _hero;
    private BufferedImage _heroImage;
    private BufferedImage _heroInfoImage;

    public HeroPanel( Model model, Hero hero )
    {
        _model = model;
        _hero  = hero;
        _heroImage     = ImageStore.getInstance().get( _hero.getType().getIconName() );
        _heroInfoImage = ImageStore.getInstance().get( _hero.getType().getInfoIconName() );
    }

    public void paintComponent( Graphics2D g, int x, int y )
    {
        // Draw Picture
        g.drawImage( _heroImage, BORDER_WIDTH + x, BORDER_WIDTH + y, null );

        // Draw Hero Name and Type
        g.setFont( NAME_FONT );
        g.drawString( _hero.getName(), x + NAME_XOFFSET, y + NAME_YOFFSET );

        g.setFont( TYPE_FONT );
        g.drawString( _hero.getType().getName(), x + TYPE_XOFFSET, y + TYPE_YOFFSET );

        // Draw Stats
        g.setFont( STAT_LABEL_FONT );
        g.drawString( "Attack", x + ATTACK_LABEL_XOFFSET, y + ATTACK_LABEL_YOFFSET );
        g.drawString( "Defend", x + DEFEND_LABEL_XOFFSET, y + DEFEND_LABEL_YOFFSET );
        g.drawString( "Body",   x + BODY_LABEL_XOFFSET,   y + BODY_LABEL_YOFFSET   );
        g.drawString( "Mind",   x + MIND_LABEL_XOFFSET,   y + MIND_LABEL_YOFFSET   );
        g.setFont( STAT_FONT );
        g.drawString( "" + _hero.getAttack(), x + ATTACK_XOFFSET, y + ATTACK_YOFFSET );
        g.drawString( "" + _hero.getDefend(), x + DEFEND_XOFFSET, y + DEFEND_YOFFSET );
        g.drawString( _hero.getBody() + "/" + _hero.getMaxBody(), x + BODY_XOFFSET, y + BODY_YOFFSET );
        g.drawString( "" + _hero.getMind(), x + MIND_XOFFSET, y + MIND_YOFFSET );

        // Draw Inventory
        g.setFont( INV_LABEL_FONT );
        g.drawString( "Weapons:", x + WEAPON_LABEL_XOFFSET, y + WEAPON_LABEL_YOFFSET );
        g.drawString( "Armor:",   x + ARMOR_LABEL_XOFFSET,  y + ARMOR_LABEL_YOFFSET );
        g.drawString( "Items:",   x + ITEMS_LABEL_XOFFSET,  y + ITEMS_LABEL_YOFFSET );
        g.drawString( "Gold:",    x + GOLD_LABEL_XOFFSET,   y + GOLD_LABEL_YOFFSET );
        g.setFont( INV_FONT );
        g.drawString( _hero.getWeapons().stream().map( w -> w.toString() ).collect( Collectors.joining( ", " ) ), x + WEAPON_XOFFSET, y + WEAPON_YOFFSET );
        g.drawString( _hero.getArmor().stream().map( a -> a.toString() ).collect( Collectors.joining( ", " ) ),   x + ARMOR_XOFFSET,  y + ARMOR_YOFFSET );
        g.drawString( _hero.getItems().stream().map( i -> i.toString() ).collect( Collectors.joining( ", " ) ),   x + ITEMS_XOFFSET,  y + ITEMS_YOFFSET );
        g.drawString( "" + _hero.getGold(), x + GOLD_XOFFSET, y + GOLD_YOFFSET );
    }
}


