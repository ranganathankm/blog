package com.blogspot.ranganathankm.javafx.treetable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import static javafx.application.Application.launch;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.application.Application.launch;

/**
 *
 * @author ranga
 */
public class TreeTableViewSample extends Application
{

    @Override
    public void start(Stage stage)
            throws Exception
    {
        final TreeItem dummyRoot = new TreeItem();
        TreeTableView treeTableView = new TreeTableView(dummyRoot);
        treeTableView.setShowRoot(false);

        TreeTableColumn idColumn = new TreeTableColumn("Id");
        idColumn.setPrefWidth(50);
        idColumn.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj)
            {
                final Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Department) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Department) dataObj).getId()));
                }
                else if (dataObj instanceof Employee) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Employee) dataObj).getId()));
                }
                return null;
            }
        });
        TreeTableColumn nameColumn = new TreeTableColumn("Name");
        nameColumn.setPrefWidth(150);

        nameColumn.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj)
            {
                final Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Department) {
                    return new ReadOnlyStringWrapper(((Department) dataObj).getName());
                }
                else if (dataObj instanceof Employee) {
                    return new ReadOnlyStringWrapper(((Employee) dataObj).getName());
                }
                return null;
            }
        });
        TreeTableColumn dojColumn = new TreeTableColumn("Doj");
        dojColumn.setPrefWidth(100);
        dojColumn.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj)
            {
                final Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Employee) {
                    return new ReadOnlyStringWrapper(((Employee) dataObj).getDoj().toString());
                }
                return null;
            }
        });

        treeTableView.getColumns().setAll(idColumn, nameColumn, dojColumn);

        //add data to tree
        List<Department> deptList = buildData();
        deptList.stream().forEach((department) -> {
            final TreeItem deptTreeItem = new TreeItem(department);
            dummyRoot.getChildren().add(deptTreeItem);
            department.getEmployeeList().stream().forEach((employee) -> {
                deptTreeItem.getChildren().add(new TreeItem(employee));
            });
        });

        StackPane root = new StackPane();
        root.getChildren().add(treeTableView);

        Scene scene = new Scene(root, 340, 250);

        stage.setTitle("JavaFX TreeTableView Sample");
        stage.setScene(scene);
        stage.show();
    }

    private List<Department> buildData()
    {
        List<Department> departmentList = new ArrayList<>();

        LocalDate doj = LocalDate.now();
        List<Employee> empList1 = new ArrayList<>();
        empList1.add(new Employee(1, "Kiran", doj));
        empList1.add(new Employee(2, "Prabhu", doj));
        empList1.add(new Employee(3, "Murugavel", doj));
        Department dept1 = new Department(1, "Sales", empList1);

        List<Employee> empList2 = new ArrayList<>();
        empList2.add(new Employee(4, "Deiveegan", doj));
        empList2.add(new Employee(5, "Saravanan", doj));
        Department dept2 = new Department(2, "Production", empList2);

        departmentList.add(dept1);
        departmentList.add(dept2);

        return departmentList;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the
     * application can not be launched through deployment artifacts, e.g., in IDEs with limited FX support. NetBeans
     * ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
