/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import java.util.Iterator;
import java.util.ListIterator;
import net.nexustools.concurrent.IfUpdateWriter;
import net.nexustools.concurrent.ListAccessor;
import net.nexustools.concurrent.Lockable;
import net.nexustools.concurrent.PropList;
import net.nexustools.concurrent.Reader;
import net.nexustools.gui.impl.AbstractContainer;
import net.nexustools.gui.impl.Widget;
import net.nexustools.gui.wrap.impl.NAbstractContainer;
import net.nexustools.gui.wrap.impl.NWidget;

/**
 *
 * @author katelyn
 */
public abstract class WAbstractContainer<N extends NAbstractContainer> extends WContentHolder<Widget, N> implements AbstractContainer {

    final PropList<WWidget> children = new PropList();
    protected WAbstractContainer(String tag, WPlatform platform) {
        super(tag, platform);
    }

    @Override
    protected void initNative(final N na) {
        iterate(new PropList.PropIterator<Widget>() {
            public void iterate(ListIterator<Widget> iterator, Lockable lock) {
                while(iterator.hasNext()) {
                    WWidget widget = (WWidget)iterator.next();
                    widget.createAndUpdate(new NativeUpdate<NWidget>() {
                        public void update(NWidget nativeWidget) {
                            na.nativeAdd(nativeWidget);
                        }
                    });
                }
            }
        });
                
        super.initNative(na);
    }

    public void add(Widget raw) {
        final WWidget widget = (WWidget)raw;
        children.write(new IfUpdateWriter<ListAccessor<WWidget>>() {
            @Override
            public boolean test(ListAccessor<WWidget> against) {
                return !against.contains(widget);
            }
            @Override
            public void write(ListAccessor<WWidget> data) {
                data.push(widget);
            }
            @Override
            public void update() {
                widget.contentHolder.set(WAbstractContainer.this);
                WAbstractContainer.this.update(new DelayedNativeUpdate() {
                    @Override
                    public void update(final N nativeContainer) {
                        widget.createAndUpdate(new NativeUpdate<NWidget>() {
                            @Override
                            public void update(NWidget nativeChild) {
                                nativeContainer.nativeAdd(nativeChild);
                            }
                        });
                    }
                });
            }
        });
    }

    public void insert(Widget widget, int at) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void remove(Widget widget) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Widget childAt(int pos) {
        return children.get(pos);
    }

    public int childCount() {
        return children.length();
    }

    public void iterate(PropList.PropIterator<Widget> iterator) {
        children.iterate((PropList.PropIterator)iterator);
    }

    public Iterator<Widget> iterator() {
        return (Iterator)children.iterator();
    }

}
