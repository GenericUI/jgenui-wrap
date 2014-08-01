/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import java.awt.Point;
import java.awt.Shape;
import net.nexustools.gui.Widget;
import net.nexustools.gui.geom.Size;

/**
 *
 * @author katelyn
 */
public interface NativeWidgetWrap<W extends Widget> {
    
    public void nativeMove(Point pos);
    public void nativeResize(Size size);
    public void nativeReshape(Shape Shape);
    
    public Point nativePos();
    public Size nativeSize();
    public Shape nativeShape();
    
    public W genUI();
    
}
