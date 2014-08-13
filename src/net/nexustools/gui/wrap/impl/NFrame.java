/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap.impl;

import net.nexustools.gui.wrap.WFrame;

/**
 *
 * @author katelyn
 */
public interface NFrame<W extends WFrame> extends NContainer<W> {
    
    public void nativeSetTitle(String title);
    public void nativeSetRaisedBorder(boolean raisedBorder);
    
}
