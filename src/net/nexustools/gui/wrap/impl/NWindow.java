/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap.impl;

import net.nexustools.gui.wrap.WWindow;

/**
 *
 * @author katelyn
 */
public interface NWindow<W extends WWindow> extends NFrame<W> {
    
    public void nativeAttachMenu();
    
}
