package heroquest.treasure;

public class Gold extends Treasure
{
    private int _amount;

    public Gold( int a )
    {
        this( TreasureType.GOLD, a );
    }

    public Gold( TreasureType type, int a )
    {
        super( type );
        _amount = a;
    }

    public int getAmount(){ return _amount; }
}

