package javateam.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;

import javateam.Models.Bookstands;

public class BookstandsController
{
    private Bookstands model = new Bookstands();

    @FXML private TextField descriptionTextField;
    @FXML private TableView table;
    @FXML private TableColumn bookstandId;
    @FXML private TableColumn description;
    @FXML private TableColumn select;

    @FXML
    public void initialize()
    {
        model.GetBookstands();
/*
        if (true)
            return;

        bookstandId.setCellValueFactory(
                new PropertyValueFactory<Bookstands.TableProduct, String>("bookstandId")
        );
        description.setCellValueFactory(
                new PropertyValueFactory<Bookstands.TableProduct, String>("description")
        );
        select.setCellValueFactory(
                new PropertyValueFactory<Bookstands.TableProduct, String>("checkbox")
        );

        table.setItems(tableValues);
        */

    }

    @FXML
    public void AddBookstandButtonOnAction()
    {
        String description = descriptionTextField.getText();
        model.AddBookstand(description);
        descriptionTextField.setText("");
    }
}
