package heroquest.game;

import java.util.List;
import java.util.ArrayList;

import heroquest.quest.Quest;
import heroquest.quest.Cell;
import heroquest.hero.*;
import heroquest.util.Logger;

public class Game
{
    private Logger _logger = Logger.getLogger( Game.class );

    private Quest _quest;
    private List<Hero> _heroes;

    public Game()
    {
        _quest = null;
        _heroes = new ArrayList<>();

        _heroes.add( new Hero( HeroType.BARBARIAN, "Barbarian", new Broadsword() ) );
        _heroes.add( new Hero( HeroType.ELF, "Elf", new Shortsword() ) );
        _heroes.add( new Hero( HeroType.DWARF, "Dwarf", new Shortsword() ) );
        _heroes.add( new Hero( HeroType.WIZARD, "Wizard", new Dagger() ) );
    }

    public void placeHeroes()
    {
        List<Cell> stairs = _quest.getStairCells();
        if( stairs.size() >= 4 )
        {
            for( int i = 0; i < 4; ++i )
            {
                stairs.get( i ).setHero( _heroes.get( i ) );
            }
        }
        else
        {
            _logger.error( stairs.size() + " cells have stairs" );
        }
    }

    public Quest getQuest(){ return _quest; }
    public void setQuest( Quest q ){ _quest = q; }

    public List<Hero> getHeroes(){ return _heroes; }
}

