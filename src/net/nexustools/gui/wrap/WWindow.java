/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.impl.Menu;
import net.nexustools.gui.impl.Toolbar;
import net.nexustools.gui.impl.Window;
import net.nexustools.gui.style.StyleSheet;
import net.nexustools.gui.wrap.impl.NWindow;

/**
 *
 * @author katelyn
 */
public abstract class WWindow<N extends NWindow> extends WFrame<N> implements Window {

    public WWindow(String tag, WPlatform platform) {
        super(tag, platform);
        visible.set(false); // Windows start invisible
    }

    public Menu menuBar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setMenuBar(Menu menu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addToolbar(Toolbar toolbar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeToolbar(Toolbar toolbar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public StyleSheet styleSheet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setStyleSheet(StyleSheet styleSheet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVisible(boolean visible) {
        if(this.visible.update(visible))
            createAndUpdate(visibleUpdate);
    }
    
}
