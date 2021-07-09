package com.blogspot.ranganathankm.treetable;

import java.util.List;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

/**
 *
 * @author ranga
 */

public class NoRootTreeTableModel extends AbstractTreeTableModel {
    private final static String[] COLUMN_NAMES = {"Id", "Name", "Doj", "Photo"};
    
    private List<Department> departmentList;

    public NoRootTreeTableModel(List<Department> departmentList) {
        super(new Object());
        this.departmentList = departmentList;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
    @Override
    public boolean isCellEditable(Object node, int column) {
        if (node instanceof Department && column == 2) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Employee;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof Department) {
            Department dept = (Department) parent;
            return dept.getEmployeeList().size();
        }
        return departmentList.size();
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof Department) {
            Department dept = (Department) parent;
            return dept.getEmployeeList().get(index);
        }
        return departmentList.get(index);
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Department dept = (Department) parent;
        Employee emp = (Employee) child;
        return dept.getEmployeeList().indexOf(emp);
    }

    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof Department) {
            Department dept = (Department) node;
            switch (column) {
                case 0:
                    return dept.getId();
                case 1:
                    return dept.getName();
            }
        } else if (node instanceof Employee) {
            Employee emp = (Employee) node;
            switch (column) {
                case 0:
                    return emp.getId();
                case 1:
                    return emp.getName();
                case 2:
                    return emp.getDoj();
                case 3:
                    return emp.getPhoto();
            }
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, Object node, int column) {
        String strValue = (String) value;
        if (node instanceof Department) {
            Department dept = (Department) node;
            switch (column) {
                case 0:
                    dept.setId(Integer.valueOf(strValue));
                    break;
                case 1:
                    dept.setName(strValue);
                    break;
            }
        } else if (node instanceof Employee) {
            Employee emp = (Employee) node;
            switch (column) {
                case 0:
                    emp.setId(Integer.valueOf(strValue));
                    break;
                case 1:
                    emp.setName(strValue);
                    break;
            }
        }
    }
}
