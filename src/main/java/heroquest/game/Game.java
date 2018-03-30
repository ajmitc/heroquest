package heroquest.game;

import java.util.List;
import java.util.ArrayList;

import heroquest.quest.Quest;
import heroquest.hero.*;

public class Game
{
    private Quest _quest;
    private List<Hero> _heros;

    public Game()
    {
        _quest = null;
        _heros = new ArrayList<>();

        _heros.add( new Hero( HeroType.BARBARIAN, "Barbarian", new Broadsword() ) );
        _heros.add( new Hero( HeroType.ELF, "Elf", new Shortsword() ) );
        _heros.add( new Hero( HeroType.DWARF, "Dwarf", new Shortsword() ) );
        _heros.add( new Hero( HeroType.WIZARD, "Wizard", new Dagger() ) );
    }

    public Quest getQuest(){ return _quest; }
    public void setQuest( Quest q ){ _quest = q; }

    public List<Hero> getHeroes(){ return _heros; }
}

