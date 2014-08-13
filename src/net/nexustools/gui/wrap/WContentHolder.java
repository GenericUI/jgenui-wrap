/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.concurrent.Prop;
import net.nexustools.gui.geom.Point;
import net.nexustools.gui.geom.Rect;
import net.nexustools.gui.geom.Size;
import net.nexustools.gui.geom.Vec4f;
import net.nexustools.gui.impl.ContentHolder;
import net.nexustools.gui.impl.Widget;
import net.nexustools.gui.style.ID;
import net.nexustools.gui.wrap.impl.NAbstractContainer;

/**
 *
 * @author katelyn
 */
public abstract class WContentHolder<T, N extends NAbstractContainer> extends WWidget<N> implements ContentHolder<T> {

    final Prop<Vec4f> insets = new Prop();
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

    public Widget findByID(final String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Widget> searchByID(final String id) {
        return search(new ID.Path() {
            {
                add(new ID(id));
            }
        });
    }

    public Iterable<Widget> search(ID.Path path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void clearContent() {
        update(new NativeUpdate<N>() {
            public void update(N nativeWidget) {
                nativeWidget.nativeClearContent();
            }
        });
    }
    
}
