package com.blogspot.javanbswing.javafx.table;

import java.time.LocalDate;
import javafx.beans.property.*;

/**
 *
 * @author ranga
 */

public class Employee
{
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty salary;
    private SimpleBooleanProperty partTime;
    private SimpleObjectProperty<LocalDate> doj;

    public Employee(int id, String name, double salary, LocalDate doj, boolean partTime)
    {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.salary = new SimpleDoubleProperty(salary);
        this.doj = new SimpleObjectProperty<>(doj);        
        this.partTime = new SimpleBooleanProperty(partTime);
    }

    public int getId()
    {
        return id.get();
    }

    public void setId(int id)
    {
        this.id.set(id);
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public double getSalary()
    {
        return salary.get();
    }

    public void setSalary(double salary)
    {
        this.salary.set(salary);
    }

    public SimpleBooleanProperty partTimeProperty()
    {
        return partTime;
    }
    
    public boolean isPartTime()
    {
        return partTime.get();
    }

    public void setPartTime(boolean partTime)
    {
        this.partTime.set(partTime);
    }

    public LocalDate getDoj()
    {
        return doj.get();
    }

    public void setDoj(LocalDate doj)
    {
        this.doj.set(doj);
    }

}
