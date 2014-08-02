/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import java.util.Iterator;
import net.nexustools.gui.ContentHolder;
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
public class WrappedContentHolder extends WrappedWidget implements ContentHolder<Widget> {
    
    protected WrappedContentHolder(String tagName, Platform platform, NativeContentHolder nativeWidget) {
        super(tagName, platform, nativeWidget);
    }
    protected WrappedContentHolder(String tagName, Platform platform, Creator<? extends NativeContentHolder, ? extends WrappedContentHolder> nativeCreator) {
        super(tagName, platform, nativeCreator);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    void replaceNative(NativeWidget what, NativeWidget with) {
        ((NativeContentHolder)_n()).nativeReplace(what, with);
    }
    
}
