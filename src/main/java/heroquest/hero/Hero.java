package heroquest.hero;

import java.util.List;
import java.util.ArrayList;

import heroquest.item.Item;

public class Hero
{
    public static final String BARBARIAN_INFO_IMAGE = "MGBHeroBarbarian.gif";
    public static final String WIZARD_INFO_IMAGE    = "MGBHeroWizard.gif";
    public static final String DWARF_INFO_IMAGE     = "MGBHeroDwarf.gif";
    public static final String ELF_INFO_IMAGE       = "MGBHeroElf.gif";

    private HeroType _type;
    private String _name;
    private int _attackModifier;
    private int _defendModifier;
    private int _maxBody;
    private int _mind;
    private List<Weapon> _weapons;
    private List<Armor> _armor;
    private int _gold;
    private List<Item> _items;

    // Current body points
    private int _body;

    public Hero( HeroType type, String name, Weapon weapon )
    {
        _type = type;
        _name = name;
        _attackModifier = 0;
        _defendModifier = 0;
        _maxBody = _type.getBody();
        _body = _maxBody;
        _mind = _type.getMind();
        _weapons = new ArrayList<>();
        _weapons.add( weapon );
        _armor = new ArrayList<>();
        _gold = 0;
        _items = new ArrayList<>();
    }

    public HeroType getType(){ return _type; }

    public String getName(){ return _name; }
    public void setName( String n ){ _name = n; }

    public int getAttack()
    { 
        return getAttack( 1 );
    }

    public int getAttack( int range )
    { 
        int maxAttack = 0;
        for( Weapon weapon: _weapons )
        {
            if( range >= weapon.getMinRange() && range <= weapon.getMaxRange() && weapon.getAttack() > maxAttack )
            {
                maxAttack = weapon.getAttack();
            }
        }
        return maxAttack + _attackModifier; 
    }

    public void setAttackModifier( int att ){ _attackModifier = att; }

    public int getDefend()
    { 
        int defend = _type.getDefend();
        for( Armor armor: _armor )
        {
            defend += armor.getDefend();
        }
        return defend + _defendModifier; 
    }

    public void setDefendModifier( int d ){ _defendModifier = d; }

    public int getMaxBody(){ return _type.getBody(); }

    public int getBody(){ return _body; }
    public void setBody( int b ){ _body = b; }
    public void adjBody( int b )
    { 
        _body += b; 
        if( _body < 0 )
            _body = 0;
        if( _body > _type.getBody() )
            _body = _type.getBody();
    }

    public int getMind(){ return _type.getMind(); }

    public List<Weapon> getWeapons(){ return _weapons; }
    public List<Armor> getArmor(){ return _armor; }

    public int getGold(){ return _gold; }
    public void setGold( int g ){ _gold = g; }
    public void adjGold( int g ){ _gold += g; }

    public List<Item> getItems(){ return _items; }
}

