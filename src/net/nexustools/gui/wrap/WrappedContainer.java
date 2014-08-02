/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.concurrent.CacheProp;
import net.nexustools.concurrent.IfWriter;
import net.nexustools.concurrent.Prop;
import net.nexustools.concurrent.PropAccessor;
import net.nexustools.gui.Container;
import net.nexustools.gui.Widget;
import net.nexustools.gui.event.LayoutListener;
import net.nexustools.gui.geom.Size;
import net.nexustools.gui.layout.Layout;
import net.nexustools.gui.layout.SizeConstraints;
import net.nexustools.gui.platform.Platform;
import net.nexustools.utils.Creator;

/**
 *
 * @author katelyn
 */
public class WrappedContainer extends WrappedAbstractContainer implements Container {

    private static final SizeConstraints maxConstraints = new SizeConstraints(new Size(0, 0), new Size(Integer.MAX_VALUE, Integer.MAX_VALUE), new Size(0, 0));
    
    private final Prop<Layout> layout = new Prop();
    protected WrappedContainer(String tagName, Platform platform, NativeContainer nativeWidget) {
        super(tagName, platform, nativeWidget);
    }
    protected WrappedContainer(String tagName, Platform platform, Creator<? extends NativeContainer, ? extends WrappedContainer> nativeCreator) {
        super(tagName, platform, nativeCreator);
    }
    public WrappedContainer(Platform platform, NativeContainer nativeWidget) {
        this("Container", platform, nativeWidget);
    }
    public WrappedContainer(Platform platform, Creator<? extends NativeContainer, ? extends WrappedContainer> nativeCreator) {
        this("Container", platform, nativeCreator);
    }

    @Override
    public void addLayoutListener(LayoutListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeLayoutListener(LayoutListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLayout(final Layout layout) {
        this.layout.write(new IfWriter<PropAccessor<Layout>>() {
            @Override
            public boolean test(PropAccessor<Layout> against) {
                return layout != against.get();
            }
            @Override
            public void write(PropAccessor<Layout> data) {
                //layoutConstraints.clear();
                data.set(layout);
                
                //if(layout != null && isOnscreen())
                //    scheduleLayoutUpdate();
            }
        });
    }

    @Override
    public Layout layout() {
        return layout.get();
    }
    
}
