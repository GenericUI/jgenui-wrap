/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.concurrent.BaseAccessor;
import net.nexustools.concurrent.BaseWriter;
import net.nexustools.concurrent.CacheProp;
import net.nexustools.concurrent.IfReader;
import net.nexustools.concurrent.IfWriter;
import net.nexustools.concurrent.Lockable;
import net.nexustools.concurrent.Prop;
import net.nexustools.concurrent.PropAccessor;
import net.nexustools.event.EventListenerRedirect;
import net.nexustools.event.FocusListener;
import net.nexustools.event.VisibilityListener;
import net.nexustools.gui.AbstractMenu;
import net.nexustools.gui.Body;
import net.nexustools.gui.Container;
import net.nexustools.gui.ContentHolder;
import net.nexustools.gui.Menu;
import net.nexustools.gui.Widget;
import net.nexustools.gui.Window;
import net.nexustools.gui.event.MoveListener;
import net.nexustools.gui.event.OnscreenListener;
import net.nexustools.gui.event.SizeListener;
import net.nexustools.gui.geom.Point;
import net.nexustools.gui.geom.Rect;
import net.nexustools.gui.geom.Shape;
import net.nexustools.gui.geom.Size;
import net.nexustools.gui.platform.Platform;
import net.nexustools.gui.render.Painter;
import net.nexustools.gui.render.Renderer;
import net.nexustools.gui.render.Style;
import net.nexustools.gui.render.StyleSheet;
import net.nexustools.utils.Creator;
import net.nexustools.utils.SimpleCreator;
import net.nexustools.utils.Testable;

/**
 *
 * @author katelyn
 * @param <N>
 */
public class WrappedWidget implements Widget {
    
    public abstract class NativeWriter<T> implements BaseWriter<PropAccessor<T>>, Testable<T> {
        private final T set;
        public NativeWriter(T set) {
            this.set = set;
        }
        @Override
        public final void write(PropAccessor<T> data, Lockable lock) {
            lock.lock();
            try {
                if(test(data.get())) {
                    if(!lock.tryFastUpgrade()) {
                        lock.upgrade();
                        if(!test(data.get())) {
                            lock.downgrade();
                            return;
                        }
                    }
                    data.set(set);
                } else
                    return;
            }finally {
                lock.unlock();
            }
            ((WrappedPlatform)platform()).invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    finish(set);
                }
            });
        }
        @Override
        public boolean test(T against) {
            return !set.equals(against);
        }
        public abstract void finish(T value);
    }
    
    // Constants
    private final Platform platform;
    
    // Caches
    private final CacheProp<NativeWidget, WrappedWidget> nativeWidget;
    
    // Properties
    private final Prop<String> tag;
    protected final Prop<Boolean> visible = new Prop(true);
    private final Prop<WrappedContentHolder> container = new Prop();
    protected final CacheProp<Rect, NativeWidget> visibleBounds = new CacheProp<Rect, NativeWidget>(new SimpleCreator<Rect, NativeWidget>() {
        @Override
        public Rect create() {
            return new Rect(0, 0, 0, 0);
        }
    });
    protected final CacheProp<Rect, NativeWidget> onscreenBounds = new CacheProp<Rect, NativeWidget>(new SimpleCreator<Rect, NativeWidget>() {
        @Override
        public Rect create() {
            return new Rect(0, 0, 0, 0);
        }
    });
    protected final CacheProp<Boolean, NativeWidget> fastAct = new CacheProp<Boolean, NativeWidget>(new SimpleCreator<Boolean, NativeWidget>() {
        @Override
        public Boolean create() {
            return onscreenBounds.get().isCollapsed();
        }
    });
    protected WrappedWidget(String tagName, Platform platform, final NativeWidget nativeWidget) {
        this(tagName, platform, new Creator<NativeWidget, WrappedWidget>() {
            @Override
            public NativeWidget create(WrappedWidget using) {
                return nativeWidget;
            }
        });
    }
    protected WrappedWidget(String tagName, Platform platform, Creator<? extends NativeWidget, ? extends WrappedWidget> nativeCreator) {
        nativeWidget = new CacheProp(nativeCreator, this);
        this.platform = platform;
        tag = new Prop(tagName);
    }
    
    /**
     * Act upon this widget within the platform's event queue.
     * 
     * @param run 
     */
    public void act(Runnable run) {
        if(fastAct.get())
            run.run();
        else
            ((WrappedPlatform)platform()).invokeAndWait(run);
    }
    
    /** 
     * Resets the internal implementation.
     */
    protected final void updateNative() {
        nativeWidget.write(new IfWriter<PropAccessor<NativeWidget>>() {
            @Override
            public void write(final PropAccessor<NativeWidget> nativeAccessor) {
                container.read(new IfReader<Void, PropAccessor<WrappedContentHolder>>() {
                    @Override
                    public Void read(PropAccessor<WrappedContentHolder> containerAccessor) {
                        NativeWidget nativeWidget = nativeAccessor.get();
                        nativeAccessor.clear();
                        containerAccessor.get().replaceNative(nativeWidget, nativeAccessor.get());
                        return null;
                    }
                });
            }
        });
    }

    @Override
    public String tag() {
        return tag.get();
    }

    @Override
    public void setTag(String name) {
        tag.write(new NativeWriter<String>(name) {
            @Override
            public void finish(String value) {
                _n().nativeSetTag(value);
            }
        });
    }

    @Override
    public boolean isVisible() {
        return visible.get();
    }

    @Override
    public boolean isOnscreen() {
        return !onscreenBounds.get().isCollapsed();
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible.write(new NativeWriter<Boolean>(visible) {
            @Override
            public void finish(Boolean value) {
                _n().nativeSetVisible(value);
            }
        });
    }

    @Override
    public Menu contextMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setContextMenu(Menu menu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void show() {
        setVisible(true);
    }

    @Override
    public void hide() {
        setVisible(false);
    }

    @Override
    public void addClass(String clazz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasClass(String clazz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeClass(String clazz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addMoveListener(MoveListener<Widget> moveListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSizeListener(SizeListener<Widget> sizeListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addVisibilityListener(VisibilityListener<Widget> visibilityListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addFocusListener(FocusListener<Widget> focusListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeMoveListener(MoveListener<Widget> moveListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSizeListener(SizeListener<Widget> sizeListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeVisibilityListener(VisibilityListener<Widget> visibilityListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeFocusListener(FocusListener<Widget> focusListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void uninstallRedirect(EventListenerRedirect<Widget> redirect) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void installRedirect(EventListenerRedirect<Widget> redirect) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point pos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Size size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape shape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rect bounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rect visibleBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rect topBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void move(Point pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resize(Size size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBounds(Rect geom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Size minimumSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMinimumSize(Size size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Size maximumSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMaximumSize(Size size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContentHolder container() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Window topLevel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Renderer renderer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Renderer defaultRenderer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRenderer(Renderer renderer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StyleSheet activeStyleSheet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean enabled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPreferredSize(Size prefSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Size preferredSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Style style() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Platform platform() {
        return platform;
    }

    @Override
    public boolean hasFocus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void requestFocus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFocusable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFocusable(boolean focusable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showMenu(AbstractMenu menu, Point at) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pushRedraw(Renderer renderer, Painter.Instruction[] instructions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public WrappedContentHolder _c() {
        return container.get();
    }

    public <N extends NativeWidget> N _n() {
        return (N)nativeWidget.get();
    }

    @Override
    public void addOnscreenListener(OnscreenListener<Widget> onscreenListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeOnscreenListener(OnscreenListener<Widget> onscreenListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Body body() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
