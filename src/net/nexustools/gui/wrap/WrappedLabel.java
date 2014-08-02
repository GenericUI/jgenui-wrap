/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import net.nexustools.concurrent.IfWriter;
import net.nexustools.concurrent.Prop;
import net.nexustools.concurrent.PropAccessor;
import net.nexustools.gui.Label;
import net.nexustools.gui.platform.Platform;
import net.nexustools.utils.Creator;

/**
 *
 * @author katelyn
 */
public class WrappedLabel extends WrappedWidget implements Label {

    private final Prop<String> text = new Prop();

    public WrappedLabel(Platform platform, NativeLabel nativeLabel) {
        super("Label", platform, nativeLabel);
    }
    public WrappedLabel(Platform platform, Creator<NativeLabel, WrappedLabel> nativeCreator) {
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
                ((NativeLabel)_n()).nativeSetText(value);
            }
        });
    }
    
}
