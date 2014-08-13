/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap.impl;

import net.nexustools.gui.geom.Vec4f;
import net.nexustools.gui.wrap.WContentHolder;

/**
 *
 * @author katelyn
 */
public interface NContentHolder<W extends WContentHolder> extends NWidget<W> {
    
    public Vec4f nativeInsets();
    public void nativeClearContent();
    
}
