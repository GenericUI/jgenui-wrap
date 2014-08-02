/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.Menu;
import net.nexustools.gui.Toolbar;
import net.nexustools.gui.Window;
import net.nexustools.gui.platform.Platform;
import net.nexustools.gui.render.StyleSheet;
import net.nexustools.utils.Creator;

/**
 *
 * @author katelyn
 */
public class WrappedWindow extends WrappedFrame implements Window {

    protected WrappedWindow(String tagName, Platform platform, NativeWindow nativeWidget) {
        super(tagName, platform, nativeWidget);
        visible.set(false); // Windows start invisible
    }
    protected WrappedWindow(String tagName, Platform platform, Creator<? extends NativeWindow, ? extends WrappedWindow> nativeCreator) {
        super(tagName, platform, nativeCreator);
        visible.set(false); // Windows start invisible
    }

    @Override
    public Menu menu(String pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addMenu(String pos, Menu menu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addToolbar(Toolbar toolbar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeToolbar(Toolbar toolbar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StyleSheet styleSheet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStyleSheet(StyleSheet styleSheet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
