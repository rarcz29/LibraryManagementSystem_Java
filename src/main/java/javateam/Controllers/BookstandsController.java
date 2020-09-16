package javateam.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javateam.Models.Bookstands;

public class BookstandsController
{
    private Bookstands model = new Bookstands();

    @FXML private TextField descriptionTextField;

    @FXML
    public void AddBookstandButtonOnAction()
    {
        String description = descriptionTextField.getText();
        model.AddBookstand(description);
    }
}
