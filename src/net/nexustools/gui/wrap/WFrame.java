/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.concurrent.Prop;
import net.nexustools.gui.impl.Frame;
import net.nexustools.gui.impl.Widget;
import net.nexustools.gui.wrap.impl.NFrame;


/**
 *
 * @author katelyn
 */
public abstract class WFrame<N extends NFrame> extends WContainer<N> implements Frame {

    final Prop<String> title = new Prop();
    final Prop<Boolean> raisedBorder = new Prop<Boolean>(false);
    public WFrame(String tag, WPlatform platform) {
        super(tag, platform);
    }

    public String title() {
        return title.get();
    }

    DelayedNativeUpdate titleUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
            nativeWidget.nativeSetTitle(title.get());
        }
    };
    public void setTitle(String title) {
        if(this.title.update(title))
            update(titleUpdate);
    }

    public boolean raisedBorder() {
        return raisedBorder.get();
    }  

    final DelayedNativeUpdate raisedBorderUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
            nativeWidget.nativeSetRaisedBorder(raisedBorder.get());
        }
    };
    public void setRaisedBorder(boolean raised) {
        if(raisedBorder.update(raised))
            update(raisedBorderUpdate);
    }

    public void setMainWidget(Widget mainWidget) {
        //clearContent();
    }

    @Override
    protected void initNative(N na) {
        raisedBorderUpdate.update(na);
        titleUpdate.update(na);
        super.initNative(na);
    }
    
}
