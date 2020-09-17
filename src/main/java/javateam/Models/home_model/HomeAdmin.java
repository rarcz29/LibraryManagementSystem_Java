package javateam.Models.home_model;

import java.util.Vector;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.DelBook;
import javateam.Data.db_strategy.AddBookstand;

public class HomeAdmin extends Home
{
    @Override
    public void addToMyList(String userId, String bookId)
    {
        String msg = "Admin cannot execute that operation.\n" +
                "Log in to your account, or create a new one.";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @Override
    public void removeBook(String bookId)
    {
        Operation operation = new DelBook();
        operation.doOperation(bookId);
    }
}