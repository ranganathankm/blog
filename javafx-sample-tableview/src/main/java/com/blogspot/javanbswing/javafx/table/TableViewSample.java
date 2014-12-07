package com.blogspot.javanbswing.javafx.table;

import java.time.LocalDate;
import javafx.application.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.converter.*;

/**
 *
 * @author ranga
 */

public class TableViewSample extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //define sample Employee objects        
        final Employee emp1 = new Employee(1, "Ram", 23123.23, LocalDate.now(), false);
        final Employee emp2 = new Employee(2, "Krishna", 32398.76, LocalDate.now(), true);

        final ObservableList<Employee> data
                = FXCollections.observableArrayList(
                        emp1, emp2);
         
        //initialise the TableView
        TableView<Employee> tableView = new TableView<>();        

        //define the columns in the table        
        TableColumn<Employee, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        
        TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
        nameCol.setPrefWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Employee, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setPrefWidth(100);
        salaryCol.setCellValueFactory(
                new PropertyValueFactory<>("salary"));
        salaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<Employee, Boolean> partTimeCol = new TableColumn<>("Part Time?");
        partTimeCol.setPrefWidth(100);
        partTimeCol.setCellValueFactory(
                new PropertyValueFactory<>("partTime"));
        partTimeCol.setCellFactory(CheckBoxTableCell.forTableColumn(partTimeCol));

        TableColumn<Employee, LocalDate> dojCol = new TableColumn<>("DOJ");
        dojCol.setPrefWidth(100);
        dojCol.setCellValueFactory(
                new PropertyValueFactory<>("doj"));

        tableView.getColumns().addAll(idCol, nameCol, salaryCol, partTimeCol, dojCol);

        tableView.setItems(data);
        tableView.setEditable(true);

        StackPane root = new StackPane();
        root.getChildren().add(tableView);
        
        Scene scene = new Scene(root, 450, 300);

        stage.setTitle("JavaFX TableView Sample");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
