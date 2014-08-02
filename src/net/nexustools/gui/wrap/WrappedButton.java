/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.concurrent.Prop;
import net.nexustools.gui.AbstractAction;
import net.nexustools.gui.Button;
import net.nexustools.gui.Shortcut;
import net.nexustools.gui.event.ActionListener;
import net.nexustools.gui.platform.Platform;
import net.nexustools.utils.Creator;

/**
 *
 * @author katelyn
 */
public class WrappedButton extends WrappedWidget implements Button {

    private final Prop<String> text = new Prop();
    public WrappedButton(Platform platform, NativeButton nativeLabel) {
        super("Label", platform, nativeLabel);
    }
    public WrappedButton(Platform platform, Creator<? extends NativeButton, ? extends WrappedButton> nativeCreator) {
        super("Label", platform, nativeCreator);
    }
    
    @Override
    public String text() {
        return text.get();
    }

    @Override
    public void setText(String text) {
        this.text.write(new UpdateWriter<String>(text) {
            @Override
            public void finish(String value) {
                ((NativeButton)_n()).nativeSetText(value);
            }
        });
    }

    @Override
    public AbstractAction action() {
        return new AbstractAction() {
            @Override
            public String text() {
                return WrappedButton.this.text();
            }
            @Override
            public boolean selectable() {
                return WrappedButton.this.selectable();
            }
            @Override
            public boolean isSelected() {
                return WrappedButton.this.isSelected();
            }
            @Override
            public void activate() {
                WrappedButton.this.activate();
            }
        };
    }

    @Override
    public Shortcut shortcut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setShortcut(Shortcut shortcut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addActionListener(ActionListener actionListener) {
    }

    @Override
    public void removeActionListener(ActionListener actionListener) {
    }

    @Override
    public boolean selectable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSelected(boolean selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSelected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void activate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
