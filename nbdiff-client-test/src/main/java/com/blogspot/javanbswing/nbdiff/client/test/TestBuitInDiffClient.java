package com.blogspot.javanbswing.nbdiff.client.test;

import java.io.*;
import java.util.logging.*;
import javax.swing.text.*;
import org.netbeans.api.diff.Difference;
import org.netbeans.modules.diff.builtin.provider.BuiltInDiffProvider;
import org.netbeans.modules.diff.util.*;
import org.netbeans.spi.diff.DiffProvider;

/**
 *
 * @author ranga
 */
public class TestBuitInDiffClient
{

    /**
     * Creates new form TestDiffClient
     */
    public TestBuitInDiffClient()
    {
    }
    
    void showDiff(String filePath1, String filePath2) throws FileNotFoundException, IOException, BadLocationException
    {
        Difference[] differences;
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        
        Reader r1 = new FileReader(file1);
        Reader r2 = new FileReader(file2);
        
        DiffProvider diff = new BuiltInDiffProvider(); //(DiffProvider) Lookup.getDefault().lookup(DiffProvider.class);        
        differences = diff.computeDiff(r1, r2);
        
        r1.close();
        r2.close();
        
        r1 = new FileReader(file1);
        r2 = new FileReader(file2);
        
        TextDiffInfo info = new TextDiffInfo(
                filePath1, filePath2, null, null, r1, r2, differences);
        info.setContextMode(true, 3);

        String norDiff = DiffUtil.differenceToNormalDiffText(info);
        System.out.println(norDiff);
    }

    void showUnifiedDiff(String filePath1, String filePath2) throws FileNotFoundException, IOException, BadLocationException
    {
        Difference[] differences;
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        
        Reader r1 = new FileReader(file1);
        Reader r2 = new FileReader(file2);
        
        DiffProvider diff = new BuiltInDiffProvider(); //(DiffProvider) Lookup.getDefault().lookup(DiffProvider.class);        
        differences = diff.computeDiff(r1, r2);
        
        r1.close();
        r2.close();

        r1 = new FileReader(file1);
        r2 = new FileReader(file2);
        
        TextDiffInfo info = new TextDiffInfo(
                filePath1, filePath2, null, null, r1, r2, differences);
        info.setContextMode(true, 3);
        final String diffText = DiffUtil.differenceToUnifiedDiffText(info);

        System.out.println(diffText);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException, FileNotFoundException, BadLocationException
    {

        final TestBuitInDiffClient testDiffClient = new TestBuitInDiffClient();
        //testDiffClient.showUnifiedDiff(TestBuitInDiffClient.class.getResource("/DiffTestFile1a.txt").getPath(), 
                                       //TestBuitInDiffClient.class.getResource("/DiffTestFile1b.txt").getPath());
        testDiffClient.showDiff(TestBuitInDiffClient.class.getResource("/DiffTestFile1a.txt").getPath(), 
                                       TestBuitInDiffClient.class.getResource("/DiffTestFile1b.txt").getPath());
    }
                 
}
