package heroquest.util;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Component;

public class GridBagLayoutUtil
{
    /*
    gridx, gridy
        Specify the row and column at the upper left of the component. The leftmost column has address gridx=0 and the top row has address gridy=0. Use GridBagConstraints.RELATIVE (the default value) to specify that the component be placed just to the right of (for gridx) or just below (for gridy) the component that was added to the container just before this component was added. We recommend specifying the gridx and gridy values for each component rather than just using GridBagConstraints.RELATIVE; this tends to result in more predictable layouts.

    gridwidth, gridheight
        Specify the number of columns (for gridwidth) or rows (for gridheight) in the component's display area. These constraints specify the number of cells the component uses, not the number of pixels it uses. The default value is 1. Use GridBagConstraints.REMAINDER to specify that the component be the last one in its row (for gridwidth) or column (for gridheight). Use GridBagConstraints.RELATIVE to specify that the component be the next to last one in its row (for gridwidth) or column (for gridheight). We recommend specifying the gridwidth and gridheight values for each component rather than just using GridBagConstraints.RELATIVE and GridBagConstraints.REMAINDER; this tends to result in more predictable layouts.

        Note: GridBagLayout does not allow components to span multiple rows unless the component is in the leftmost column or you have specified positive gridx and gridy values for the component.

    fill
        Used when the component's display area is larger than the component's requested size to determine whether and how to resize the component. Valid values (defined as GridBagConstraints constants) include NONE (the default), HORIZONTAL (make the component wide enough to fill its display area horizontally, but do not change its height), VERTICAL (make the component tall enough to fill its display area vertically, but do not change its width), and BOTH (make the component fill its display area entirely).

    ipadx, ipady
        Specifies the internal padding: how much to add to the size of the component. The default value is zero. The width of the component will be at least its minimum width plus ipadx*2 pixels, since the padding applies to both sides of the component. Similarly, the height of the component will be at least its minimum height plus ipady*2 pixels.

    insets
        Specifies the external padding of the component -- the minimum amount of space between the component and the edges of its display area. The value is specified as an Insets object. By default, each component has no external padding.

    anchor
        Used when the component is smaller than its display area to determine where (within the area) to place the component. Valid values (defined as GridBagConstraints constants) are CENTER (the default), PAGE_START, PAGE_END, LINE_START, LINE_END, FIRST_LINE_START, FIRST_LINE_END, LAST_LINE_END, and LAST_LINE_START.

        Here is a picture of how these values are interpreted in a container that has the default, left-to-right component orientation.
        FIRST_LINE_START    PAGE_START  FIRST_LINE_END
        LINE_START          CENTER      LINE_END
        LAST_LINE_START     PAGE_END    LAST_LINE_END
        Version note: The PAGE_* and *LINE_* constants were introduced in 1.4. Previous releases require values named after points of the compass. For example, NORTHEAST indicates the top-right part of the display area. We recommend that you use the new constants, instead, since they enable easier localization.

    weightx, weighty
        Specifying weights is an art that can have a significant impact on the appearance of the components a GridBagLayout controls. Weights are used to determine how to distribute space among columns (weightx) and among rows (weighty); this is important for specifying resizing behavior.

        Unless you specify at least one non-zero value for weightx or weighty, all the components clump together in the center of their container. This is because when the weight is 0.0 (the default), the GridBagLayout puts any extra space between its grid of cells and the edges of the container.

        Generally weights are specified with 0.0 and 1.0 as the extremes: the numbers in between are used as necessary. Larger numbers indicate that the component's row or column should get more space. For each column, the weight is related to the highest weightx specified for a component within that column, with each multicolumn component's weight being split somehow between the columns the component is in. Similarly, each row's weight is related to the highest weighty specified for a component within that row. Extra space tends to go toward the rightmost column and bottom row.
    */


    public static int gFill = GridBagConstraints.NONE;
    public static double gWeightx = 0.0;
    public static double gWeighty = 0.0;
    public static int gGridx = 0;
    public static int gGridy = 0;
    public static int gIpadx = 0;
    public static int gIpady = 0;
    public static int gAnchor = GridBagConstraints.CENTER;
    public static Insets gInsets = new Insets( 0, 0, 0, 0 );

    public static GridBagConstraintsHelper setLayout( Container container )
    {
        container.setLayout( new GridBagLayout() );
        return new GridBagConstraintsHelper();
    }

    public static class GridBagConstraintsHelper
    {
        public GridBagConstraints c = null;

        public GridBagConstraintsHelper()
        {
            c = new GridBagConstraints();
            reset();
        }

        public GridBagConstraintsHelper fill( int fill )
        {
            c.fill = fill;
            return this;
        }

        public GridBagConstraintsHelper fillHorizontal()
        {
            return fill( GridBagConstraints.HORIZONTAL );
        }

        public GridBagConstraintsHelper fillVertical()
        {
            return fill( GridBagConstraints.VERTICAL );
        }

        public GridBagConstraintsHelper fillBoth()
        {
            return fill( GridBagConstraints.BOTH );
        }

        public GridBagConstraintsHelper grid( int gridx, int gridy )
        {
            c.gridx = gridx;
            c.gridy = gridy;
            return this;
        }

        public GridBagConstraintsHelper ipad( int ipadx, int ipady )
        {
            c.ipadx = ipadx;
            c.ipady = ipady;
            return this;
        }

        public GridBagConstraintsHelper weight( double weightx, double weighty )
        {
            c.weightx = weightx;
            c.weighty = weighty;
            return this;
        }

        public GridBagConstraintsHelper anchor( int anchor )
        {
            c.anchor = anchor;
            return this;
        }

        public GridBagConstraintsHelper anchorCenter()
        {
            return anchor( GridBagConstraints.CENTER );
        }

        public GridBagConstraintsHelper anchorPageStart()
        {
            return anchor( GridBagConstraints.PAGE_START );
        }

        public GridBagConstraintsHelper anchorPageEnd()
        {
            return anchor( GridBagConstraints.PAGE_END );
        }

        public GridBagConstraintsHelper insets( int top, int left, int bottom, int right )
        {
            c.insets.set( top, left, bottom, right );
            return this;
        }

        public GridBagConstraintsHelper reset()
        {
            c.fill = gFill;
            c.weightx = gWeightx;
            c.weighty = gWeighty;
            c.gridx = gGridx;
            c.gridy = gGridy;
            c.ipadx = gIpadx;
            c.ipady = gIpady;
            c.anchor = gAnchor;
            c.insets = (Insets) gInsets.clone();
            return this;
        }

        public GridBagConstraintsHelper add( Container container, Component comp )
        {
            container.add( comp, c );
            return this;
        }

        public GridBagConstraints getConstraints()
        {
            return c;
        }
    }

    private GridBagLayoutUtil(){}
}

