/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nexustools.gui.wrap;

import java.util.EventListener;
import net.nexustools.concurrent.ListAccessor;
import net.nexustools.concurrent.Lockable;
import net.nexustools.concurrent.Prop;
import net.nexustools.concurrent.PropAccessor;
import net.nexustools.concurrent.PropList;
import net.nexustools.concurrent.logic.Reader;
import net.nexustools.concurrent.logic.SoftWriteReader;
import net.nexustools.concurrent.logic.BaseReader;
import net.nexustools.concurrent.logic.BaseWriter;
import net.nexustools.concurrent.logic.IfReader;
import net.nexustools.data.Storage;
import net.nexustools.event.DefaultEventDispatcher;
import net.nexustools.event.Event;
import net.nexustools.event.EventDispatcher;
import net.nexustools.event.EventListenerRedirect;
import net.nexustools.event.FocusListener;
import net.nexustools.event.FocusListener.FocusEvent;
import net.nexustools.event.VisibilityListener;
import net.nexustools.event.VisibilityListener.VisibilityEvent;
import net.nexustools.gui.event.MoveListener;
import net.nexustools.gui.event.MoveListener.MoveEvent;
import net.nexustools.gui.event.OnscreenListener;
import net.nexustools.gui.event.OnscreenListener.OnscreenEvent;
import net.nexustools.gui.event.SizeListener;
import net.nexustools.gui.event.SizeListener.SizeEvent;
import net.nexustools.gui.geom.Point;
import net.nexustools.gui.geom.Rect;
import net.nexustools.gui.geom.Size;
import net.nexustools.gui.impl.AbstractMenu;
import net.nexustools.gui.impl.Body;
import net.nexustools.gui.impl.ContentHolder;
import net.nexustools.gui.impl.Menu;
import net.nexustools.gui.impl.Widget;
import net.nexustools.gui.impl.Window;
import net.nexustools.gui.layout.SizeConstraints;
import net.nexustools.gui.render.Painter;
import net.nexustools.gui.render.Renderer;
import net.nexustools.gui.style.Style;
import net.nexustools.gui.style.StyleSheet;
import net.nexustools.gui.wrap.impl.NWidget;
import net.nexustools.runtime.RunQueue;

/**
 *
 * @author katelyn
 */
public abstract class WWidget<N extends NWidget> implements Widget {

    final Prop<N> impl = new Prop();
    
    // Properties
    final Prop<String> tag;
    final Prop<String> id = new Prop();
    final Prop<Boolean> enabled = new Prop(true);
    final Prop<Boolean> visible = new Prop(true);
    final Prop<Boolean> focusable = new Prop(false);
    final Prop<Menu> contextMenu = new Prop();
    final WPlatform platform;
    
    final Prop<Size> size = new Prop();
    final Prop<Point> pos = new Prop(new Point(0, 0));
    
    final Prop<Painter.Instruction[]> paintInstructions = new Prop();
    
    final Prop<Size> minSize = new Prop();
    final Prop<Size> maxSize = new Prop();
    final Prop<Size> prefSize = new Prop();
    
    final Prop<ContentHolder> contentHolder = new Prop();
    
    final Prop<SizeConstraints> sizeConstraints = new Prop(new SizeConstraints());
    final PropList<String> classes = new PropList();
    
    // Events
    final EventDispatcher<WPlatform, MoveListener, MoveEvent> moveDispatcher = new DefaultEventDispatcher(platform());
    final EventDispatcher<WPlatform, SizeListener, SizeEvent> sizeDispatcher = new DefaultEventDispatcher(platform());
    final EventDispatcher<WPlatform, VisibilityListener, VisibilityEvent> visibilityDispatcher = new DefaultEventDispatcher(platform());
    final EventDispatcher<WPlatform, OnscreenListener, OnscreenEvent> onscreenDispatcher = new DefaultEventDispatcher(platform());
    final EventDispatcher<WPlatform, FocusListener, FocusEvent> focusDispatcher = new DefaultEventDispatcher(platform());

    protected WWidget(String tag, WPlatform platform) {
        this.tag = new Prop(tag);
        this.platform = platform;
    }

    public String id() {
        return id.get();
    }

    public void setID(final String name) {
        id.set(name);
    }

    public String tag() {
        return tag.get();
    }

    final DelayedNativeUpdate tagUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
            nativeWidget.nativeSetTag(tag.get());
        }
    };
    public void setTag(final String name) {
        if(tag.update(name))
            update(tagUpdate);
    }

    public boolean isVisible() {
        return visible.get();
    }

    public boolean isOnscreen() {
        throw new UnsupportedOperationException();
    }

    DelayedNativeUpdate visibleUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
            nativeWidget.nativeSetVisible(visible.get());
        }
    };
    public void setVisible(final boolean visible) {
        if(this.visible.update(visible))
            update(visibleUpdate);
    }

    public Menu contextMenu() {
        return contextMenu.get();
    }

    DelayedNativeUpdate contextMenuUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
        }
    };
    public void setContextMenu(final Menu menu) {
        if(contextMenu.update(menu))
        update(contextMenuUpdate);
    }

    public void show() {
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }

    public void addClass(final String clazz) {
        classes.push(clazz);
    }

    public boolean hasClass(final String clazz) {
        return classes.contains(clazz);
    }

    public void removeClass(final String clazz) {
        classes.remove(clazz);
    }

    public void addMoveListener(final MoveListener<Widget> moveListener) {
        
    }

    public void addSizeListener(final SizeListener<Widget> sizeListener) {
        
    }

    public void addVisibilityListener(final VisibilityListener<Widget> visibilityListener) {
        
    }

    public void addOnscreenListener(final OnscreenListener<Widget> onscreenListener) {
        
    }

    public void addFocusListener(final FocusListener<Widget> focusListener) {
        
    }

    public void removeMoveListener(final MoveListener<Widget> moveListener) {
        
    }

    public void removeSizeListener(final SizeListener<Widget> sizeListener) {
        
    }

    public void removeVisibilityListener(final VisibilityListener<Widget> visibilityListener) {
        
    }

    public void removeOnscreenListener(final OnscreenListener<Widget> onscreenListener) {
        
    }

    public void removeFocusListener(final FocusListener<Widget> focusListener) {
        
    }

    public void uninstallRedirect(final EventListenerRedirect<Widget> redirect) {
        
    }

    public void installRedirect(final EventListenerRedirect<Widget> redirect) {
        
    }

    public Rect visibleBounds() {
        throw new UnsupportedOperationException();
    }

    public Rect topBounds() {
        throw new UnsupportedOperationException();
    }

    public void setMinimumSize(final Size size) {
    }

    public void setMaximumSize(final Size size) {
    }

    public void setPreferredSize(final Size prefSize) {
    }

    public ContentHolder container() {
        throw new UnsupportedOperationException();
    }

    public Window topLevel() {
        throw new UnsupportedOperationException();
    }

    public Body body() {
        throw new UnsupportedOperationException();
    }

    public Renderer renderer() {
        throw new UnsupportedOperationException();
    }

    public Renderer defaultRenderer() {
        throw new UnsupportedOperationException();
    }

    public void setRenderer(final Renderer renderer) {
    }

    public StyleSheet activeStyleSheet() {
        throw new UnsupportedOperationException();
    }

    public boolean enabled() {
        throw new UnsupportedOperationException();
    }

    final DelayedNativeUpdate enabledUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
            nativeWidget.nativeSetEnabled(enabled.get());
        }
    };
    public void setEnabled(final boolean enabled) {
        if(this.enabled.update(enabled))
            update(enabledUpdate);
    }

    public Style style() {
        throw new UnsupportedOperationException();
    }

    public WPlatform platform() {
        return platform;
    }

    public boolean hasFocus() {
        return false;
    }

    final DelayedNativeUpdate requestFocus = new DelayedNativeUpdate() {
        public void update(N nativeWidget) {
            nativeWidget.nativeRequestFocus();
        }
    };
    public void requestFocus() {
        update(requestFocus);
    }

    public boolean isFocusable() {
        return focusable.get();
    }

    final DelayedNativeUpdate focusableUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
            nativeWidget.nativeSetFocusable(focusable.get());
        }
    };
    public void setFocusable(final boolean focusable) {
        if(this.focusable.update(focusable))
            update(focusableUpdate);
    }

    public void showMenu(final AbstractMenu menu, final Point at) {
        if(!isOnscreen())
            return;
    }

    public Rect bounds() {
        return pos.read(new Reader<Rect, PropAccessor<Point>>() {
            @Override
            public Rect read(PropAccessor<Point> data) {
                return new Rect(data.get(), size());
            }
        });
        
    }

    final DelayedNativeUpdate redrawUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {}
    };
    public void pushRedraw(final Renderer renderer, final Painter.Instruction[] instructions) {
        update(redrawUpdate);
    }

    public Size size() {
        return size.read(new SoftWriteReader<Size, PropAccessor<Size>>() {
            @Override
            public Size soft(PropAccessor<Size> data) {
                return data.get();
            }
            @Override
            public Size read(PropAccessor<Size> data) {
                Size size = preferredSize();
                data.set(size);
                return size;
            }
        });
    }

    public Point pos() {
        return pos.get().clone();
    }

    public Size minimumSize() {
        return constraints().min;
    }

    public Size maximumSize() {
        return constraints().max;
    }

    public Size preferredSize() {
        return constraints().pref;
    }

    public SizeConstraints constraints() {
        return sizeConstraints.read(new SoftWriteReader<SizeConstraints, PropAccessor<SizeConstraints>>() {
            @Override
            public boolean test(PropAccessor<SizeConstraints> against) {
                SizeConstraints constraints = against.get();
                return constraints.max == null || constraints.min == null || constraints.pref == null;
            }
            @Override
            public SizeConstraints soft(PropAccessor<SizeConstraints> data) {
                return data.get();
            }
            @Override
            public SizeConstraints read(PropAccessor<SizeConstraints> data) {
                SizeConstraints constraints = data.get();
                if(constraints.min == null)
                    constraints.min = WWidget.this.read(new NativeReader<Size, N>() {
                        @Override
                        public Size read(N nativeWidget) {
                            return nativeWidget.nativeMinSize();
                        }
                    });
                if(constraints.max == null)
                    constraints.max = WWidget.this.read(new NativeReader<Size, N>() {
                        @Override
                        public Size read(N nativeWidget) {
                            return nativeWidget.nativeMaxSize();
                        }
                    });
                if(constraints.pref == null)
                    constraints.pref = WWidget.this.read(new NativeReader<Size, N>() {
                        @Override
                        public Size read(N nativeWidget) {
                            return nativeWidget.nativePrefSize();
                        }
                    });
                return constraints;
            }
        });
    }

    final DelayedNativeUpdate boundsUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
            Size size = size();
            Point pos = WWidget.this.pos.get();
            nativeWidget.nativeRebound((int)pos.x, (int)pos.y, (int)size.w, (int)size.h);
        }
    };
    public void updateBounds(final Rect rect) {
        this.pos.set(rect.topLeft);
        this.size.set(rect.size);
        update(boundsUpdate);
    }

    final DelayedNativeUpdate resizeUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
            Size size = WWidget.this.size.get();
            nativeWidget.nativeResize((int)size.w, (int)size.h);
        }
    };
    public void resize(final Size size) {
        this.size.set(size);
        update(resizeUpdate);
    }

    final DelayedNativeUpdate moveUpdate = new DelayedNativeUpdate() {
        @Override
        public void update(N nativeWidget) {
            Point pos = WWidget.this.pos.get();
            nativeWidget.nativeMove((int)pos.x, (int)pos.y);
        }
    };
    public void move(final Point pos) {
        this.pos.set(pos);
        update(moveUpdate);
    }

    public Storage storage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected abstract N createNative();
    protected void initNative(N na) {
        tagUpdate.update(na);
        moveUpdate.update(na);
        redrawUpdate.update(na);
        boundsUpdate.update(na);
        enabledUpdate.update(na);
        contextMenuUpdate.update(na);
        focusableUpdate.update(na);
        visibleUpdate.update(na);
    }
    
    private N getOrCreateNative(final PropAccessor<N> data, Lockable<PropAccessor<N>> lock) {
        N na = data.get();
        if(na == null) {
            if(!lock.tryFastUpgrade()) {
                lock.upgrade();
                na = data.get();
            }
            if(na == null) {
                na = createNative();
                data.set(na);
                initNative(na);
            }

            lock.downgrade();
        }
        return na;
    }

    public static interface NativeUpdate<N> {
        public void update(N nativeWidget);
    }
    protected abstract class DelayedNativeUpdate implements NativeUpdate<N>, Runnable {
        public final void run() {
            impl.read(new IfReader<Void, PropAccessor<N>>() {
                public Void read(final PropAccessor<N> data) {
                    update(data.get());
                    return null;
                }
            });
        }
    }
    public void update(final DelayedNativeUpdate updater) {
        impl.read(new IfReader<Void, PropAccessor<N>>() {
            public Void read(final PropAccessor<N> data) {
                platform().invokeLater(updater, RunQueue.QueuePlacement.ReplaceExisting);
                return null;
            }
        });
    }
    public void update(final NativeUpdate<N> updater) {
        impl.read(new IfReader<Void, PropAccessor<N>>() {
            public Void read(final PropAccessor<N> data) {
                platform().invokeAndWaitImpl(new Runnable() {
                    public void run() {
                        updater.update(data.get());
                    }
                });
                return null;
            }
        });
    }
    public void createAndUpdate(final NativeUpdate<N> updater) {
        impl.read(new BaseReader<Void, PropAccessor<N>>() {
            public Void read(final PropAccessor<N> data, final Lockable<PropAccessor<N>> lock) {
                lock.lock();
                try {
                    final N na = getOrCreateNative(data, lock);
                    platform().invokeAndWaitImpl(new Runnable() {
                        public void run() {
                            updater.update(na);
                        }
                    });
                } finally {
                    lock.unlock();
                }
                return null;
            }
        });
    }
    public static abstract class NativeReader<R, N extends NWidget> {
        protected R value;
        public abstract R read(N nativeWidget);
    }
    public <R> R read(final NativeReader<R, N> reader) {
        return impl.read(new BaseReader<R, PropAccessor<N>>() {
            public R read(final PropAccessor<N> data, Lockable<PropAccessor<N>> lock) {
                lock.lock();
                try {
                    final N na = getOrCreateNative(data, lock);
                    platform().invokeAndWaitImpl(new Runnable() {
                        public void run() {
                            reader.value = reader.read(na);
                        }
                    });
                    return reader.value;
                } finally {
                    lock.unlock();
                }
            }
        });
    }

    public Widget effective() {
        return this;
    }
    
    public abstract class WrapEventDispatcher<L extends EventListener, E extends Event> extends EventDispatcher<WPlatform, L, E> {

        public WrapEventDispatcher() {
            super(platform);
        }

        public void init(final N widget) {
            listeners.write(new BaseWriter<ListAccessor<L>>() {
                public void write(ListAccessor<L> data, Lockable lock) {
                    lock.lock();
                    try {
                        if(data.isTrue())
                            connect(widget);
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }

        public abstract void connect(N widget);
        public abstract void disconnect(N widget);

        @Override
        public final void connect() {
            update(new WWidget.NativeUpdate<N>() {
                public void update(N nativeWidget) {
                    connect(nativeWidget);
                }
            });
        }

        @Override
        public final void disconnect() {
            update(new WWidget.NativeUpdate<N>() {
                public void update(N nativeWidget) {
                    disconnect(nativeWidget);
                }
            });
        }
    }

    public String[] psuedoStates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void move(int x, int y) {
        move(new Point(x, y));
    }

    public void resize(int w, int h) {
        resize(new Size(w, h));
    }

}
