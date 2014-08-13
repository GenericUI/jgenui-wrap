/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.concurrent.Prop;
import net.nexustools.gui.err.UnsupportedPlatformOperation;
import net.nexustools.gui.impl.Action;
import net.nexustools.gui.impl.Body;
import net.nexustools.gui.impl.Frame;
import net.nexustools.gui.impl.Label;
import net.nexustools.gui.impl.Widget;
import net.nexustools.gui.wrap.impl.NBody;

/**
 *
 * @author katelyn
 */
public abstract class WBody<N extends NBody> extends WWindow<N> implements Body {

    final Prop<Label> titleWidget = new Prop();
    final Prop<Widget> mainWidget = new Prop();
    final Prop<Widget> glassWidget = new Prop();
    public WBody(String tag, WPlatform platform) {
        super(tag, platform);
    }

    public void addAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean hasAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void insertAction(Action action, int at) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int indexOfAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Widget mainWidget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Label titleWidget() {
        return titleWidget.get();
    }

    public void setTitleWidget(Label widget) throws UnsupportedPlatformOperation {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Widget glassWidget() {
        return glassWidget.get();
    }

    final DelayedNativeUpdate glassWidgetUpdate = new DelayedNativeUpdate() {
        public void update(N nativeWidget) {
        }
    };
    public void setGlassWidget(Widget widget) {
        if(glassWidget.update(widget))
            update(glassWidgetUpdate);
    }

    public void insertAction(Action action, Action after) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void clearActions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Action nextAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setNextAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Frame pop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Frame peek() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void push(Frame frame) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Frame current() {
        return this;
    }
    
}
