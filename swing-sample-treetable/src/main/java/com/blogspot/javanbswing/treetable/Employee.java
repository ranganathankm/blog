package com.blogspot.javanbswing.treetable;

import java.util.Date;

/**
 *
 * @author ranga
 */

public class Employee {

    private int id;
    private String name;
    private Date doj;
    
    private String photo;

    public Employee(int id, String name, Date doj, String photo) {
        super();
        this.id = id;
        this.name = name;
        this.doj = doj;
        this.photo = photo;
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

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
