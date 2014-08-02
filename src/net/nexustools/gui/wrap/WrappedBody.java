/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.gui.Body;
import net.nexustools.gui.Container;
import net.nexustools.gui.Widget;
import net.nexustools.gui.platform.Platform;
import net.nexustools.utils.Creator;

/**
 *
 * @author katelyn
 */
public class WrappedBody extends WrappedWindow implements Body {

    public WrappedBody(Platform platform, NativeBody nativeWidget) {
        super("Body", platform, nativeWidget);
    }
    public WrappedBody(Platform platform, Creator<NativeBody, ? extends WrappedBody> nativeCreator) {
        super("Body", platform, nativeCreator);
    }

    @Override
    public void setMainWidget(Widget mainWidget) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMainWidget(Widget mainWidget, String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Container mainContainer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Widget mainWidget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
