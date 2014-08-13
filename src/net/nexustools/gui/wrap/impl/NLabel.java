/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap.impl;

import net.nexustools.gui.impl.Image;
import net.nexustools.gui.wrap.WLabel;

/**
 *
 * @author katelyn
 */
public interface NLabel<W extends WLabel> extends NWidget<W> {
    
    public void nativeSetText(String text);
    public void nativeSetIcon(Image icon);
    
}
