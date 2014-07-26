package com.blogspot.javanbswing.treetable.withroot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

public class TreeTableMainWithRoot extends JFrame {

    private JXTreeTable treeTable;
    
    public TreeTableMainWithRoot() {
        List<Department> departmentList = new ArrayList<Department>();

        List<Employee> empList = new ArrayList<Employee>();
        empList.add(new Employee(1, "Ram", Calendar.getInstance().getTime(), "emp1.jpg"));
        empList.add(new Employee(2, "Krishna", Calendar.getInstance().getTime(), "emp2.jpg"));
        departmentList.add(new Department(1, "Sales", empList));

        List<Employee> empList2 = new ArrayList<Employee>();
        empList2.add(new Employee(3, "Govind", Calendar.getInstance().getTime(), "emp1.jpg"));
        empList2.add(new Employee(4, "Kiran", Calendar.getInstance().getTime(), "emp2.jpg"));
        empList2.add(new Employee(5, "Karthik", Calendar.getInstance().getTime(), "emp1.jpg"));
        departmentList.add(new Department(2, "Marketing", empList2));
        
        Organization organization = new Organization("ABC XYZ Corporation", departmentList);
        
        MyTreeTableModel myTreeTableModel = new MyTreeTableModel(organization);
        treeTable = new JXTreeTable(myTreeTableModel);
        treeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        treeTable.setRootVisible(true);
        treeTable.getColumnModel().getColumn(3).setCellRenderer(new PhotoRenderer());
        treeTable.setRowHeight(50);

        add(new JScrollPane(treeTable));

        setTitle("JXTreeTable Example with Root");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TreeTableMainWithRoot();
            }
        });
    }

    
}