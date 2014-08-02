/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.platform.Platform;
import net.nexustools.gui.render.StyleSheet;

/**
 *
 * @author katelyn
 * @param <N>
 */
public abstract class WrappedPlatform<N> extends Platform {
    
    public static enum QueuePlacement {
        AtBack,
        ReplaceExisting,
        CancelExisting
    }
    
    protected WrappedPlatform(String name) {
        super(name);
        push(new Runnable() {
            @Override
            public void run() {
                invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        makeCurrent();
                    }
                }, QueuePlacement.AtBack);
            }
        });
        makeCurrent();
    }
    
    /**
     * Queues a Runnable to run when the internal event loop
     * of the wrapped toolkit becomes idle.
     * 
     * If this is not possible, invokeLater(run, QueuePlacement.CancelExisting) is called.
     * 
     * @param run 
     */
    public abstract void onIdle(final Runnable run);
    
    /**
     * Runs later on the internal event loop of this 
     * @param run 
     */
    public abstract void invokeLater(Runnable run, QueuePlacement placement);
    
    /**
     * Runs later on the internal event loop of this 
     * @param run 
     */
    public abstract void invokeAndWait(Runnable run);

    public final StyleSheet styleSheet() {
        return null;
    }

    public final void setStyleSheet(StyleSheet styleSheet) {
        // TODO: Implement
    }

}
