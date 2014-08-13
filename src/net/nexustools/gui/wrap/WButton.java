/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.event.EventDispatcher;
import net.nexustools.gui.event.ActionListener;
import net.nexustools.gui.event.ActionListener.ActionEvent;
import net.nexustools.gui.impl.AbstractAction;
import net.nexustools.gui.impl.Button;
import net.nexustools.gui.impl.Shortcut;
import net.nexustools.gui.wrap.impl.NButton;

/**
 *
 * @author katelyn
 */
public abstract class WButton<N extends NButton> extends WLabel<N> implements Button {

    final Runnable activate = new Runnable() {
        public void run() {
            activateDispatcher.dispatch(new EventDispatcher.Processor<ActionListener, ActionEvent>() {
                public ActionEvent create() {
                    return new ActionEvent(WButton.this);
                }
                public void dispatch(ActionListener listener, ActionEvent event) {
                    listener.activated(event);
                }
            });
        }
    };
    final WrapEventDispatcher<ActionListener, ActionEvent> activateDispatcher = new WrapEventDispatcher<ActionListener, ActionEvent>() {
        @Override
        public void connect(N widget) {
            widget.attachActionListener(activate);
        }
        @Override
        public void disconnect(N widget) {
            widget.detachActionListener();
        }
    };
    public WButton(String tag, WPlatform platform) {
        super(tag, platform);
    }

    @Override
    protected void initNative(N na) {
        activateDispatcher.init(na);
        super.initNative(na);
    }

    public AbstractAction action() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Shortcut shortcut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setShortcut(Shortcut shortcut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addActionListener(ActionListener actionListener) {
        activateDispatcher.add(actionListener);
    }

    public void removeActionListener(ActionListener actionListener) {
        activateDispatcher.remove(actionListener);
    }

    public boolean selectable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSelected(boolean selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isSelected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void activate() {
        activate.run();
    }

    
}
