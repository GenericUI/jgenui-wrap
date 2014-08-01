/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.platform.Platform;

/**
 *
 * @author katelyn
 */
public abstract class WrappedPlatform<C> extends Platform<C> {

    protected WrappedPlatform(String name) {
        super(name);
    }

    
}
