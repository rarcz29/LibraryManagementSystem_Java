package javateam.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.collections.ObservableList;

import javateam.Models.Bookstands;
import javateam.User;

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
        ObservableList<Bookstands.TableProduct> tableValues = model.GetBookstands();

        bookstandId.setCellValueFactory(
                new PropertyValueFactory<Bookstands.TableProduct, String>("bookstandId")
        );
        description.setCellValueFactory(
                new PropertyValueFactory<Bookstands.TableProduct, String>("description")
        );
        select.setCellValueFactory(
                new PropertyValueFactory<Bookstands.TableProduct, CheckBox>("checkbox")
        );

        table.setItems(tableValues);
    }

    @FXML
    public void AddBookstandButtonOnAction()
    {
        String description = descriptionTextField.getText();
        model.AddBookstand(description);
        descriptionTextField.setText("");
        model.GetBookstands();
    }

    @FXML
    public void RemoveSelectedBookstands()
    {
        if (User.getInstance().getIsAdmin())
            model.RemoveSelectedBookstands();

        else
        {
            String msg = "Log in as an admin to remove bookstands.";
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}
