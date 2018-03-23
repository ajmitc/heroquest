package heroquest.util;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

/**
 * This class is responsible for loading images into memory
 */
public class ImageStore
{
    public static final String DEFAULT_DIRECTORY = "images";

    private static ImageStore _instance = null;

    /**
     * Initialize the ImageStore with the given directory
     */
    public static ImageStore getInstance( String dir )
    {
        if( _instance == null )
            _instance = new ImageStore( dir );
        return _instance;
    }

    public static ImageStore getInstance()
    {
        if( _instance == null )
            _instance = new ImageStore( DEFAULT_DIRECTORY );
        return _instance;
    }

    private String _imageDir;
    //private String _ext;

    private Map<String, BufferedImage> _images;

    private ImageStore( String dir )
    {
        _imageDir = dir;
        _images = new HashMap<String, BufferedImage>();
    }

    public ImageIcon getIcon( String filename )
    {
        BufferedImage bi = get( filename );
        if( bi == null ) return null;
        return new ImageIcon( bi );
    }

    public BufferedImage get( String filename )
    {
        if( !_images.containsKey( filename ) )
        {
            try
            {
                //_images.put( filename, ImageIO.read( new File( _imageDir + "/" + filename ) ) );
                _images.put( filename, ImageIO.read( ClassLoader.getSystemResource( _imageDir + "/" + filename ) ) );
            }
            catch( Exception e )
            {
                System.err.println( "Unable to load image: '" + filename + "'" );
                e.printStackTrace();
                return null;
            }
        }
        return _images.get( filename );
    }

    public void setDirectory( String d ){ _imageDir = d; }
}

