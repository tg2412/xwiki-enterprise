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
package org.xwiki.it.ui.framework;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.xwiki.test.XWikiExecutor;

import org.xwiki.it.ui.elements.BasePage;

/**
 * This is a container for holding all of the information which should persist throughout all of the tests.
 *
 * @version $Id$
 * @since TODO
 */
public class PersistentTestContext
{
    /** This starts and stops the wiki engine. */
    private XWikiExecutor executor;

    private WebDriver driver;

    public PersistentTestContext() throws Exception
    {
        this.executor = new XWikiExecutor(0);
        executor.start();
        this.driver = new FirefoxDriver();
    }

    public PersistentTestContext(PersistentTestContext toClone)
    {
        this.executor = toClone.executor;
        this.driver = toClone.driver;
    }

    public void shutdown() throws Exception
    {
        driver.close();
        executor.stop();
    }

    public WebDriver getDriver()
    {
        return this.driver;
    }

    public PersistentTestContext getUnstoppable()
    {
        return new PersistentTestContext(this)
        {
            public void shutdown()
            {
                // Do nothing, that's why it's unstoppable.
            }
        };
    }
}