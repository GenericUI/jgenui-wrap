/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.concurrent.IfWriter;
import net.nexustools.concurrent.Prop;
import net.nexustools.concurrent.PropAccessor;
import net.nexustools.gui.Frame;
import net.nexustools.gui.Widget;
import net.nexustools.gui.platform.Platform;
import net.nexustools.utils.Creator;

/**
 *
 * @author katelyn
 */
public class WrappedFrame extends WrappedContainer implements Frame {

    private final Prop<String> title = new Prop();
    protected WrappedFrame(String tagName, Platform platform, NativeFrame nativeWidget) {
        super(tagName, platform, nativeWidget);
    }
    protected WrappedFrame(String tagName, Platform platform, Creator<? extends NativeFrame, ? extends WrappedFrame> nativeCreator) {
        super(tagName, platform, nativeCreator);
    }
    public WrappedFrame(Platform platform, NativeFrame nativeWidget) {
        this("Container", platform, nativeWidget);
    }
    public WrappedFrame(Platform platform, Creator<? extends NativeFrame, ? extends WrappedFrame> nativeCreator) {
        this("Container", platform, nativeCreator);
    }

    @Override
    public String title() {
        return title.get();
    }

    @Override
    public void setTitle(final String title) {
        this.title.write(new IfWriter<PropAccessor<String>>() {
            @Override
            public boolean test(PropAccessor<String> against) {
                return !title.equals(against.get());
            }
            @Override
            public void write(PropAccessor<String> data) {
                ((NativeFrame)_n()).nativeSetTitle(title);
                data.set(title);
            }
        });
    }

    @Override
    public boolean raisedBorder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRaisedBorder(boolean raised) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMainWidget(Widget mainWidget) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
