/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.event.ActionListener;
import net.nexustools.gui.impl.AbstractAction;
import net.nexustools.gui.impl.Button;
import net.nexustools.gui.impl.Image;
import net.nexustools.gui.impl.Shortcut;
import net.nexustools.gui.wrap.impl.NButton;

/**
 *
 * @author katelyn
 */
public abstract class WButton<N extends NButton> extends WLabel<N> implements Button {

    public WButton(String tag, WPlatform platform) {
        super(tag, platform);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeActionListener(ActionListener actionListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
