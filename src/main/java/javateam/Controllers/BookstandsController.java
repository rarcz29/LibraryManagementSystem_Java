package javateam.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

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
        var tableValues = model.GetBookstands();

    }

    @FXML
    public void AddBookstandButtonOnAction()
    {
        String description = descriptionTextField.getText();
        model.AddBookstand(description);
    }
}
