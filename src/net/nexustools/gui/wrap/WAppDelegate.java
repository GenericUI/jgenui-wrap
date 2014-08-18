/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.nexustools.gui.wrap;

import static net.nexustools.AppDelegate.defaultName;
import static net.nexustools.AppDelegate.defaultOrganization;
import net.nexustools.gui.platform.GUIAppDelegate;
import net.nexustools.gui.platform.GUIPlatform;

/**
 *
 * @author kate
 */
public abstract class WAppDelegate<B extends WBody, P extends WPlatform> extends GUIAppDelegate<B, P> {

    protected WAppDelegate(String[] args, P platform) {
        this(args, defaultName(), defaultOrganization(), platform);
    }
    protected WAppDelegate(String[] args, String name, String organization) {
        this(args, name, organization, (P)GUIPlatform.findRichestImpl());
    }
    protected WAppDelegate(String[] args, String name, String organization, P platform) {
        super(args, name, organization, platform);
    }

}
