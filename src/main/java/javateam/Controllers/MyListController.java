package javateam.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;

import javateam.Models.MyList;

public class MyListController
{
    private MyList model = new MyList();

    @FXML private TableView table;
    @FXML private TableColumn titleColumn;
    @FXML private TableColumn authorColumn;
    @FXML private TableColumn genreColumn;
    @FXML private TableColumn bookstandIdColumn;
    @FXML private TableColumn selectColumn;

    @FXML
    public void initialize()
    {
        ObservableList<MyList.TableProduct> tableValues = model.getList();

        titleColumn.setCellValueFactory(
                new PropertyValueFactory<MyList.TableProduct, String>("title")
        );
        authorColumn.setCellValueFactory(
                new PropertyValueFactory<MyList.TableProduct, String>("author")
        );
        genreColumn.setCellValueFactory(
                new PropertyValueFactory<MyList.TableProduct, String>("genre")
        );
        bookstandIdColumn.setCellValueFactory(
                new PropertyValueFactory<MyList.TableProduct, String>("bookstandId")
        );
        selectColumn.setCellValueFactory(
                new PropertyValueFactory<MyList.TableProduct, CheckBox>("checkbox")
        );

        table.setItems(tableValues);
    }

    @FXML
    public void removeBooksButtonOnAction()
    {
        model.removeSelectedBooks();
    }
}