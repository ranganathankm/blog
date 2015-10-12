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

import java.io.*;
import org.netbeans.api.diff.Difference;

/**
 * //Original text
 * This panel is to be used as a wrapper for diff visualizers.
 * @author  Martin Entlicher
 * 
 * Ranga:
 * This class has been extracted out of org.netbeans.modules.diff.builtin.DiffPresenter.
 * This was originally present as an inner class. Stuff related to TopComponent have also been removed.
 */

public abstract class Info extends Object
{

    private String name1;
    private String name2;
    private String title1;
    private String title2;
    private String mimeType;
 
    public Info(String name1, String name2, String title1, String title2, String mimeType)
    {
        this.name1 = name1;
        this.name2 = name2;
        this.title1 = title1;
        this.title2 = title2;
        this.mimeType = mimeType;
    }

    public String getName1()
    {
        return name1;
    }

    public String getName2()
    {
        return name2;
    }

    public String getTitle1()
    {
        return title1;
    }

    public String getTitle2()
    {
        return title2;
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public Difference[] getDifferences()
    {
        return null;
    }

    public Difference[] getInitialDifferences()
    {
        return null;
    }

    public abstract Reader createFirstReader()
            throws FileNotFoundException;

    public abstract Reader createSecondReader()
            throws FileNotFoundException;

}
