/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import java.util.Iterator;
import java.util.ListIterator;
import net.nexustools.gui.geom.Point;
import net.nexustools.gui.geom.Rect;
import net.nexustools.gui.geom.Size;
import net.nexustools.gui.impl.ContentHolder;
import net.nexustools.gui.impl.Widget;
import net.nexustools.gui.style.ID;
import net.nexustools.gui.wrap.impl.NAbstractContainer;

/**
 *
 * @author katelyn
 */
public abstract class WContentHolder<T, N extends NAbstractContainer> extends WWidget<N> implements ContentHolder<T> {

    public WContentHolder(String tag, WPlatform platform) {
        super(tag, platform);
    }

    public Rect contentBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Point contentOffset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Size contentSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Widget findByID(ID id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Widget> searchByID(ID id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Widget> search(ID.Path path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void clearContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
