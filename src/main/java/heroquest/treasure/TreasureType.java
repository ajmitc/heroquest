package heroquest.treasure;

public enum TreasureType
{
    GOLD( "Gold", "gold.gif" ),
    GOLD15( "Gold (15)", "MGTSGold15.gif" ),
    GOLD25( "Gold (25)", "MGTSGold25.gif" ),
    GEM( "Gem (35)", "MGTSGem.gif" ),
    HAZARD_ARROW( "Hazard Arrow", "MGTSHazardArrowA.gif" ),
    HAZARD_FALL( "Hazard Fall", "MGTSHazardFallA.gif" ),
    HEROIC_BREW( "Heroic Brew", "MGTSHeroicBrew.gif" ),
    JEWELS( "Jewels", "MGTSJewels.gif" ),
    POTION_OF_DEFENSE( "Potion of Defense", "MGTSPotionDefense.gif" ),
    POTION_OF_HEALING( "Potion of Healing", "MGTSPotionHealingA.gif" ),
    POTION_OF_STRENGTH( "Potion of Strength", "MGTSPotionStrength.gif" ),
    WANDERING_MONSTER( "Wandering Monster", "MGTSWanderingMonster.gif" );

    private String _name;
    private String _icon;

    TreasureType( String n, String i )
    {
        _name = n;
        _icon = i;
    }

    public String getName(){ return _name; }
    public String getIconName(){ return _icon; }

    public boolean isGold()
    {
        return (this == GOLD || this == GOLD15 || this == GOLD25 || this == GEM);
    }

    public String toString(){ return _name; }
}

