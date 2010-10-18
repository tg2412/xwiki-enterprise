/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.test.selenium;

import junit.framework.Test;

import org.xwiki.test.selenium.framework.AbstractXWikiTestCase;
import org.xwiki.test.selenium.framework.ColibriSkinExecutor;
import org.xwiki.test.selenium.framework.XWikiTestSuite;

/**
 * Verify the keyboard shortcuts feature of XWiki.
 * 
 * @version $Id$
 */
public class KeyboardShortcutsTest extends AbstractXWikiTestCase
{
    private static String mainHtmlElement = "xwikimaincontainer";

    public static Test suite()
    {
        XWikiTestSuite suite = new XWikiTestSuite("Verify the keyboard shortcuts feature of XWiki");
        suite.addTestSuite(KeyboardShortcutsTest.class, ColibriSkinExecutor.class);
        return suite;
    }

    protected void testShortcutFromResultingTitle(String origURL, String shortcut, String expectedTitle)
        throws InterruptedException
    {
        testShortcutFromResultingTitle(origURL, shortcut, expectedTitle, false, false, false);
    }

    protected void testShortcutFromResultingTitle(String origURL, String shortcut, String expectedTitle,
        boolean withCtrlModifier, boolean withAltModifier, boolean withShiftModifier) throws InterruptedException
    {
        open(origURL);
        pressKeyboardShortcut(shortcut, withCtrlModifier, withAltModifier, withShiftModifier);
        waitPage();
        assertTitle(expectedTitle);
    }

    protected void testShortcutFromTextPresent(String origURL, String shortcut, String text)
        throws InterruptedException
    {
        testShortcutFromTextPresent(origURL, shortcut, text, false, false, false);
    }

    protected void testShortcutFromTextPresent(String origURL, String shortcut, String expectedText,
        boolean withCtrlModifier, boolean withAltModifier, boolean withShiftModifier) throws InterruptedException
    {
        open(origURL);
        pressKeyboardShortcut(shortcut, withCtrlModifier, withAltModifier, withShiftModifier);
        waitPage();
        assertTextPresent(expectedText);
    }

    public void testAllKeyboardShortcuts() throws InterruptedException
    {
        loginAsAdmin();

        String viewURL = "/xwiki/bin/view/Sandbox/WebHome";

        // e : default edit wysiswyg
        testShortcutFromResultingTitle(viewURL, "e", "Editing wysiwyg for Sandbox");
        // k : edit wiki
        testShortcutFromResultingTitle(viewURL, "k", "Editing Wiki for Sandbox");
        // g : edit wysiwyg
        testShortcutFromResultingTitle(viewURL, "g", "Editing wysiwyg for Sandbox");
        // f : edit inline
        testShortcutFromTextPresent(viewURL, "f", "Is minor edit");
        // r : edit rights
        testShortcutFromTextPresent(viewURL, "r", "Editing rights of");
        // o : edit objects
        testShortcutFromTextPresent(viewURL, "o", "Editing objects of");
        // s : edit class
        testShortcutFromTextPresent(viewURL, "s", "Editing class");
        // d : code
        // TODO: put this test back when 'view source code' will be available again.
        // testShortcutFromTextPresent(viewURL, "d", "Wiki source code of Sandbox");
        // Delete : delete
        testShortcutFromTextPresent(viewURL, "\\46", "Are you sure you wish to move this document to the recycle bin");
        // F2 : rename
        testShortcutFromTextPresent(viewURL, "\\113", "Renaming Sandbox.WebHome");

        // Alt+C : cancel edit
        // open(editURL);
        // testShortcutFromResultingTitle(editURL, "c", "XWiki - Main - WebHome", false, true, false);
        // This test and is commented (and there should be a bunch of others similar to this one)
        // since Alt+key combination seems to be buggy in selenium
        // http://jira.openqa.org/browse/SEL-437
        // The feature has been manualy tested with Firefox 2, IE6 and IE7
    }
}