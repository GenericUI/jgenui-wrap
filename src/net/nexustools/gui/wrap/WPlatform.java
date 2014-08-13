/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.platform.GUIPlatform;
import net.nexustools.runtime.future.QueueFuture;
import net.nexustools.gui.style.StyleSheet;

/**
 *
 * @author katelyn
 */
public abstract class WPlatform extends GUIPlatform {
    
    protected WPlatform(String name) {
        super(name);
    }

    public QueueFuture invokeAndWait(Runnable run) {
        invokeAndWaitImpl(run);
        return null;
    }
    public QueueFuture invokeLater(Runnable run, QueuePlacement placement) {
        switch(placement) {
            case WaitOnIdleThread:
                invokeOnIdleImpl(run);
                break;
        }
        
        final QueueFuture future = wrap(run, QueueFuture.State.WaitingInQueue, placement);
        if(future.isDone())
            return null;
        invokeLaterImpl(new Runnable() {
            public void run() {
                future.execute();
            }
        });
        return future;
    }

    protected abstract void invokeAndWaitImpl(Runnable run);
    protected abstract void invokeOnIdleImpl(Runnable run);
    protected abstract void invokeLaterImpl(Runnable run);

    public StyleSheet styleSheet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setStyleSheet(StyleSheet styleSheet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
