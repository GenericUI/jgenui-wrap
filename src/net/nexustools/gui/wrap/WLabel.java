/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.concurrent.Prop;
import net.nexustools.gui.event.ActionListener;
import net.nexustools.gui.impl.AbstractAction;
import net.nexustools.gui.impl.Button;
import net.nexustools.gui.impl.Image;
import net.nexustools.gui.impl.Label;
import net.nexustools.gui.impl.Shortcut;
import net.nexustools.gui.wrap.impl.NButton;
import net.nexustools.gui.wrap.impl.NLabel;

/**
 *
 * @author katelyn
 */
public abstract class WLabel<N extends NLabel> extends WWidget<N> implements Label {

    final Prop<String> text = new Prop();
    public WLabel(String tag, WPlatform platform) {
        super(tag, platform);
    }

    @Override
    protected void initNative(N na) {
        textUpdate.update(na);
        super.initNative(na);
    }

    public Image icon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String text() {
        return text.get();
    }

    DelayedNativeUpdate textUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
            nativeWidget.nativeSetText(text.get());
        }
    };
    public void setText(String text) {
        if(this.text.update(text))
            update(textUpdate);
    }

    public void setIcon(Image image) {}

    
}
