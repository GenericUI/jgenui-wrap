/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.err.UnsupportedPlatformOperation;
import net.nexustools.gui.impl.Action;
import net.nexustools.gui.impl.Body;
import net.nexustools.gui.impl.Widget;
import net.nexustools.gui.wrap.impl.NBody;

/**
 *
 * @author katelyn
 */
public abstract class WBody<N extends NBody> extends WWindow<N> implements Body {

    public WBody(String tag, WPlatform platform) {
        super(tag, platform);
    }

    public Widget titleWidget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTitleWidget(Widget widget) throws UnsupportedPlatformOperation {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean hasAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void insertAction(Action action, int at) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int indexOfAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
