package com.blogspot.ranganathankm.treetable.withroot;

import java.util.List;

public class Department {

    private int id;
    private String name;
    private List<Employee> employeeList;

    public Department(int id, String name, List<Employee> empList) {
        super();
        this.id = id;        
        this.name = name;
        this.employeeList = empList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}