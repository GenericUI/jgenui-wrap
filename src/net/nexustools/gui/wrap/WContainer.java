/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.event.LayoutListener;
import net.nexustools.gui.impl.Container;
import net.nexustools.gui.layout.Layout;
import net.nexustools.gui.wrap.impl.NContainer;

/**
 *
 * @author katelyn
 */
public abstract class WContainer<N extends NContainer> extends WAbstractContainer<N> implements Container {

    public WContainer(String tag, WPlatform platform) {
        super(tag, platform);
    }

    public void addLayoutListener(LayoutListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeLayoutListener(LayoutListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setLayout(Layout layout) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Layout layout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
