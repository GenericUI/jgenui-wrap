/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import java.util.Iterator;
import net.nexustools.concurrent.IfWriter;
import net.nexustools.concurrent.ListAccessor;
import net.nexustools.concurrent.PropList;
import net.nexustools.gui.AbstractContainer;
import net.nexustools.gui.Widget;
import net.nexustools.gui.geom.Point;
import net.nexustools.gui.geom.Rect;
import net.nexustools.gui.geom.Size;
import net.nexustools.gui.platform.Platform;
import net.nexustools.utils.Creator;

/**
 *
 * @author katelyn
 */
public class WrappedAbstractContainer extends WrappedContentHolder implements AbstractContainer {

    private final PropList<WrappedWidget> children = new PropList();
    protected WrappedAbstractContainer(String tagName, Platform platform, NativeAbstractContainer nativeWidget) {
        super(tagName, platform, nativeWidget);
    }
    protected WrappedAbstractContainer(String tagName, Platform platform, Creator<? extends NativeAbstractContainer, ? extends WrappedAbstractContainer> nativeCreator) {
        super(tagName, platform, nativeCreator);
    }
    
    void removeNative(NativeWidget nativeWidget) {
        ((NativeAbstractContainer)_n()).nativeRemove(nativeWidget);
    }
    void addNative(NativeWidget nativeWidget) {
        ((NativeAbstractContainer)_n()).nativeAdd(nativeWidget);
    }
    
    @Override
    public void add(final Widget widget) {
        children.write(new IfWriter<ListAccessor<WrappedWidget>>() {
            @Override
            public boolean test(ListAccessor<WrappedWidget> against) {
                return !against.contains((WrappedWidget)widget);
            }
            @Override
            public void write(ListAccessor<WrappedWidget> data) {
                addNative(((WrappedWidget)widget)._n());
                data.push((WrappedWidget)widget);
            }
        });
    }

    @Override
    public void remove(final Widget widget) {
        children.write(new IfWriter<ListAccessor<WrappedWidget>>() {
            @Override
            public boolean test(ListAccessor<WrappedWidget> against) {
                return against.contains((WrappedWidget)widget);
            }
            @Override
            public void write(ListAccessor<WrappedWidget> data) {
                removeNative(((WrappedWidget)widget)._n());
            }
        });
    }

    @Override
    public int childCount() {
        return children.length();
    }

    @Override
    public void iterate(ContentIterator<Widget> it) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rect contentBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point contentOffset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Size contentSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<Widget> iterator() {
        return new Iterator<Widget>() {
            Iterator<WrappedWidget> it = children.iterator();
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }
            @Override
            public Widget next() {
                return it.next();
            }
            @Override
            public void remove() {
            	throw new UnsupportedOperationException("PropList Iterators are not concurrent, use the iterate method for more complex iterations.");
            }
        };
    }
    
}
