/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.ContentHolder;


/**
 *
 * @author katelyn
 */
public interface NativeContentHolder extends NativeWidget {
    
    public void nativeReplace(NativeWidget what, NativeWidget with);
    
}
