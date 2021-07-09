package com.blogspot.ranganathankm.treetable.withroot;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXTreeTable;

public class TreeTableMainWithRoot extends JFrame {

    private JXTreeTable treeTable;
    
    public TreeTableMainWithRoot() {
        //sample doj
        final Date doj = Calendar.getInstance().getTime();

        List<Department> departmentList = new ArrayList<Department>();

        List<Employee> empList1 = new ArrayList<Employee>();
        empList1.add(new Employee(1, "Kiran", doj, "emp1.jpg"));
        empList1.add(new Employee(2, "Prabhu", doj, "emp2.jpg"));
        empList1.add(new Employee(3, "Murugavel", doj, "emp1.jpg"));        
        departmentList.add(new Department(1, "Sales", empList1));

        List<Employee> empList2 = new ArrayList<Employee>();
        empList2.add(new Employee(4, "Deiveegan", doj, "emp2.jpg"));
        empList2.add(new Employee(5, "Saravanan", doj, "emp1.jpg"));
        departmentList.add(new Department(2, "Production", empList2));
        
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