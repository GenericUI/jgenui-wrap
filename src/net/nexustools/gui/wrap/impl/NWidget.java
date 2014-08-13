/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap.impl;

import net.nexustools.gui.geom.Size;
import net.nexustools.gui.impl.Menu;
import net.nexustools.gui.wrap.WWidget;

/**
 *
 * @author katelyn
 */
public interface NWidget<W extends WWidget> {
    
    public void nativeSetTag(String tag);
    public void nativeSetVisible(boolean visible);
    public void nativeSetFocusable(boolean focusable);
    public void nativeSetEnabled(boolean enabled);

    public void nativeMove(int x, int y);
    public void nativeResize(int w, int h);
    public void nativeRebound(int x, int y, int w, int h);
    
    public void nativeBindContextMenu(Menu contextMenu);
    public void nativeRequestFocus();

    public Size nativeMinSize();
    public Size nativeMaxSize();
    public Size nativePrefSize();
    
}
