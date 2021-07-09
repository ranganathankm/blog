package com.blogspot.ranganathankm.javafx.treetable;

import java.time.LocalDate;

/**
 *
 * @author ranga
 */

public class Employee {

    private int id;
    private String name;
    private LocalDate doj;
    
    public Employee(int id, String name, LocalDate doj) {
        super();
        this.id = id;
        this.name = name;
        this.doj = doj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

}
