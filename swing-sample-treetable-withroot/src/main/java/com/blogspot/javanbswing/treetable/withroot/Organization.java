package com.blogspot.javanbswing.treetable.withroot;

import java.util.List;

public class Organization {

    private String name;
    private List<Department> departmentList;

    public Organization(String name, List<Department> departmentList) {
        this.name = name;
        this.departmentList = departmentList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
