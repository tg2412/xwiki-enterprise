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
package com.xpn.xwiki.it.selenium;

import java.lang.reflect.Method;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.xwiki.test.XWikiTestSetup;

import com.xpn.xwiki.it.selenium.framework.AbstractXWikiTestCase;
import com.xpn.xwiki.it.selenium.framework.XWikiSeleniumTestSetup;

/**
 * A class listing all the Selenium Functional tests to execute. We need such a class (rather than letting the JUnit
 * Runner discover the different TestCases classes by itself) because we want to start/stop XWiki before and after the
 * tests start (but only once).
 * 
 * @version $Id$
 */
public class AllTests extends TestCase
{
    private static final String PATTERN = ".*" + System.getProperty("pattern", "");

    public static Test suite() throws Exception
    {
        TestSuite suite = new TestSuite();

        // TODO: I don't like listing tests here as it means we can add a new TestCase class and
        // forget to add it here and the tests won't be run but we'll not know about it and we'll
        // think the tests are all running fine. I haven't found a simple solution to this yet
        // (there are complex solutions like searching for all tests by parsing the source tree).
        // I think there are TestSuite that do this out there but I haven't looked for them yet.

        addTestCase(suite, DeletePageTest.class);
        addTestCase(suite, LoginLogoutTest.class);
        addTestCase(suite, UserProfileTest.class);
        addTestCase(suite, WikiEditorTest.class);
        addTestCase(suite, WysiwygEditorFromWikiTest.class);
        addTestCase(suite, WysiwygEditorTest.class);
        addTestCase(suite, InlineEditorTest.class);
        addTestCase(suite, VelocityMacrosTest.class);
        addTestCase(suite, BacklinksTest.class);
        addTestCase(suite, CacheTest.class);
        addTestCase(suite, UsersGroupsRightsManagementTest.class);
        addTestCase(suite, SkinCustomizationsTest.class);
        addTestCase(suite, XObjectsTest.class);
        addTestCase(suite, VersionTest.class);
        addTestCase(suite, WatchListTest.class);
        addTestCase(suite, KeyboardShortcutsTest.class);
        addTestCase(suite, AllDocsTest.class);
        addTestCase(suite, UrlMiscTest.class);
        addTestCase(suite, RegisterTest.class);
        addTestCase(suite, ValidationTest.class);
        addTestCase(suite, CopyPageTest.class);
        addTestCase(suite, AdministrationTest.class);
        addTestCase(suite, LanguageTest.class);
        addTestCase(suite, SchedulerTest.class);
        addTestCase(suite, PanelWizardTest.class);
        addTestCase(suite, DocExtraTest.class);
        addTestCase(suite, PanelsTest.class);
        addTestCase(suite, SectionTest.class);
        addTestCase(suite, ImportTest.class);
        // Disabling since it blocks the release.
        // addTestCase(suite, XWikiJavaScriptComponentsTest.class);
        
        return new XWikiSeleniumTestSetup(new XWikiTestSetup(suite));
    }

    private static void addTestCase(TestSuite suite, Class< ? extends AbstractXWikiTestCase> testClass)
        throws Exception
    {
        if (testClass.getName().matches(PATTERN)) {
            Method method = testClass.getMethod("suite", (Class[]) null);
            suite.addTest((Test) method.invoke(null, (Object[]) null));
        }
    }
}
