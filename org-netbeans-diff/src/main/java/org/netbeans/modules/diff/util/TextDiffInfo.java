/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.netbeans.modules.diff.util;

import java.io.Reader;
import org.netbeans.api.diff.Difference;

/**
 * //Original text
 * The textual visualizer of diffs.
 *
 * @author  Martin Entlicher
 *
 * Ranga:
 * This class has been extracted out of org.netbeans.modules.diff.builtin.visualizer.TextDiffVisualizer.
 * This was originally present as an inner class. Stuff related to TopComponent have also been removed.
 */
public class TextDiffInfo extends Info
{

    private Reader r1;
    private Reader r2;
    Difference[] diffs;
    private boolean contextMode;
    private int contextNumLines;

    public TextDiffInfo(String name1, String name2, String title1, String title2,
                        Reader r1, Reader r2, Difference[] diffs)
    {
        super(name1, name2, title1, title2, null);
        this.r1 = r1;
        this.r2 = r2;
        this.diffs = diffs;
    }

    public String getName()
    {
        String componentName = getName1();
        String name2 = getName2();
        if (name2 != null && name2.length() > 0) {
            componentName += " <> " + name2;
        }
        return componentName;
    }

    public String getTitle()
    {
        return getTitle1() + " <> " + getTitle2();
    }

    public Reader createFirstReader()
    {
        return r1;
    }

    public Reader createSecondReader()
    {
        return r2;
    }

    public Difference[] getDifferences()
    {
        return diffs;
    }

    /**
     * Setter for property contextMode.
     *
     * @param contextMode New value of property contextMode.
     */
    public void setContextMode(boolean contextMode, int contextNumLines)
    {
        this.contextMode = contextMode;
        this.contextNumLines = contextNumLines;
    }

    /**
     * Getter for property contextMode.
     *
     * @return Value of property contextMode.
     */
    public boolean isContextMode()
    {
        return contextMode;
    }

    public int getContextNumLines()
    {
        return contextNumLines;
    }

}
