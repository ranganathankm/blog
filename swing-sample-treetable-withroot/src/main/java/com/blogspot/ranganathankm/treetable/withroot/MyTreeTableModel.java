package com.blogspot.ranganathankm.treetable.withroot;

import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

public class MyTreeTableModel extends AbstractTreeTableModel {
    private final static String[] COLUMN_NAMES = {"Id", "Name", "Doj", "Photo"};

    public MyTreeTableModel(Organization organization) {
        super(organization);
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
    public Object getValueAt(Object node, int column) {
        if (node instanceof Organization) {
            if (1 == column) {
                return ((Organization) node).getName();
            }
        }
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
    public Object getChild(Object parent, int index) {
        if (parent instanceof Organization) {
            Organization org = (Organization) parent;
            return org.getDepartmentList().get(index);
        } else {
            Department dept = (Department) parent;
            return dept.getEmployeeList().get(index);
        }
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof Organization) {
            Organization org = (Organization) parent;
            return org.getDepartmentList().size();
        } else {
            Department dept = (Department) parent;
            return dept.getEmployeeList().size();
        }
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof Organization) {
            Organization org = (Organization) parent;
            Department dept = (Department) child;
            return org.getDepartmentList().indexOf(dept);
        } else {
            Department dept = (Department) parent;
            Employee emp = (Employee) child;
            return dept.getEmployeeList().indexOf(emp);
        }
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        if (node instanceof Organization && (0 == column || 2 == column)) {
            return false;
        }
        else if (node instanceof Department && column == 2) {
            return false;
        }

        return true;
    }

    @Override
    public void setValueAt(Object value, Object node, int column) {
        String strValue = (String) value;
        if (node instanceof Organization) {
            Organization org = (Organization) node;
            switch (column) {
                case 1:
                    org.setName(strValue);
                    break;
            }
        } else if (node instanceof Department) {
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
    
    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Employee;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return super.getColumnClass(column);
    }
}
