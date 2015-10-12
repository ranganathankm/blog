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
 * The textual visualizer of diffs.
 *
 * @author  Martin Entlicher
 *
 * Ranga:
 * This class has been extracted out of org.netbeans.modules.diff.builtin.visualizer.TextDiffVisualizer.
 * Only stuff related to diff have been used. Others have been neglected.
 */
public class DiffUtil
{
    static InputStream differenceToLineDiffText(Difference[] diffs) {
        StringBuffer content = new StringBuffer();
        int n1, n2, n3, n4;
        for (int i = 0; i < diffs.length; i++) {
            Difference diff = diffs[i];
            switch (diff.getType()) {
                case Difference.ADD:
                    n3 = diff.getSecondStart();
                    n4 = diff.getSecondEnd();
                    if (n3 == n4) {
                        content.append(diff.getFirstStart()+"a"+n3+"\n");
                    } else {
                        content.append(diff.getFirstStart()+"a"+n3+","+n4+"\n");
                    }
                    appendText(content, "> ", diff.getSecondText());
                    break;
                case Difference.DELETE:
                    n1 = diff.getFirstStart();
                    n2 = diff.getFirstEnd();
                    if (n1 == n2) {
                        content.append(n1+"d"+diff.getSecondStart()+"\n");
                    } else {
                        content.append(n1+","+n2+"d"+diff.getSecondStart()+"\n");
                    }
                    appendText(content, "< ", diff.getFirstText());
                    break;
                case Difference.CHANGE:
                    n1 = diff.getFirstStart();
                    n2 = diff.getFirstEnd();
                    n3 = diff.getSecondStart();
                    n4 = diff.getSecondEnd();
                    if (n1 == n2 && n3 == n4) {
                        content.append(n1+"c"+n3+"\n");
                    } else if (n1 == n2) {
                        content.append(n1+"c"+n3+","+n4+"\n");
                    } else if (n3 == n4) {
                        content.append(n1+","+n2+"c"+n3+"\n");
                    } else {
                        content.append(n1+","+n2+"c"+n3+","+n4+"\n");
                    }
                    appendText(content, "< ", diff.getFirstText());
                    content.append("---\n");
                    appendText(content, "> ", diff.getSecondText());
                    break;
            }
        }
        return new ByteArrayInputStream(content.toString().getBytes());
    }
    
    private static void appendText(StringBuffer buff, String prefix, String text) {
        if (text == null) return ;
        int startLine = 0;
        do {
            int endLine = text.indexOf('\n', startLine);
            if (endLine < 0) endLine = text.length();
            buff.append(prefix + text.substring(startLine, endLine) + "\n");
            startLine = endLine + 1;
        } while (startLine < text.length());
    }
    
    /**
     * Produces textual diff output in unified format.
     * 
     * @param diffInfo encapsulates information needed to produce the diff
     * @return String textual diff output in unified format (unidiff)
     */
    public static String differenceToUnifiedDiffText(TextDiffInfo diffInfo) throws IOException {
        UnifiedDiff ud = new UnifiedDiff(diffInfo);
        return ud.computeDiff();
    }

    /**
     * Produces textual diff output in normal format.
     * 
     * @return String textual diff output in normal diff format
     */
    public static String differenceToNormalDiffText(TextDiffInfo diffInfo) throws IOException {
        InputStream is = differenceToLineDiffText(diffInfo.diffs);
        StringWriter sw = new StringWriter();
        copyStreamsCloseAll(sw, new InputStreamReader(is));
        return sw.toString();
    }
    
    private static void copyStreamsCloseAll(Writer writer, Reader reader) throws IOException {
        char [] buffer = new char[4096];
        int n;
        while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
        }
        writer.close();
        reader.close();
    }
        
}
