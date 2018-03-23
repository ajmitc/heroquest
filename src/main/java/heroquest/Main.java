package heroquest;

import javax.swing.JFrame;

import heroquest.view.View;
import heroquest.util.Util;

public class Main
{
    public static void main( String ... args )
    {
        JFrame frame = new JFrame();
        frame.setTitle( "HeroQuest" );
        frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        frame.setSize( 1024, 647 );
        Util.center( frame );

        Model model = new Model();
        View view = new View( model, frame );
        new Controller( model, view );

        frame.setVisible( true );
    }
}

