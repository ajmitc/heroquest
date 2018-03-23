package heroquest.furniture;

public class Furniture
{
    private FurnitureType _type;
    private int _width;
    private int _height;

    // Default is 0 degrees, which matches the orientation of the image
    // Each degree will rotate the image counter-clockwise by that amount
    private int _orientation;

    public Furniture( FurnitureType type )
    {
        _type = type;
        _width = 1;
        _height = 1;
        _orientation = 0;
    }

    public FurnitureType getType(){ return _type; }
    public int getWidth(){ return _width; }
    public int getHeight(){ return _height; }
    public int getOrientation(){ return _orientation; }

    public void setType( FurnitureType type ){ _type = type; }
    public void setWidth( int w ){ _width = w; }
    public void setHeight( int h ){ _height = h; }
    public void setSize( int w, int h ){ _width = w; _height = h; }
    public void setOrientation( int o ){ _orientation = o; }
}

