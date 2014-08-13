/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap.impl;

import net.nexustools.gui.wrap.WAbstractContainer;

/**
 *
 * @author katelyn
 */
public interface NAbstractContainer<W extends WAbstractContainer> extends NContentHolder<W> {
    
    public void nativeAdd(NWidget child);
    public void nativeInsert(NWidget child, int at);
    public void nativeSwap(NWidget child, NWidget other);
    public void nativeRemove(NWidget child);
    
}
