/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;


/**
 *
 * @author katelyn
 */
public interface NativeAbstractContainer extends NativeContentHolder {
    
    public void nativeAdd(NativeWidget nativeWidget);
    public void nativeRemove(NativeWidget nativeWidget);

    
}
