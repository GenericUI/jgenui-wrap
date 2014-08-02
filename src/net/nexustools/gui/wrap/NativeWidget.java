/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.Widget;
import net.nexustools.gui.geom.Point;
import net.nexustools.gui.geom.Shape;
import net.nexustools.gui.geom.Size;
import net.nexustools.gui.layout.SizeConstraints;

/**
 *
 * @author katelyn
 * @param <W>
 */
public interface NativeWidget {
    
    public void nativeSetTag(String tag);
    public void nativeSetVisible(boolean visible);
    
    public void nativeMove(Point pos);
    public void nativeResize(Size size);
    public void nativeReshape(Shape shape);
    
    public SizeConstraints nativeConstraints();
    
    public WrappedWidget _w();
    
}
