package javateam.Models.home_model;

import java.util.Vector;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.AddToList;

public class HomeUser extends Home
{
    @Override
    public void addToMyList(String userId, String bookId)
    {
        Operation operation = new AddToList();
        operation.doOperation(userId, bookId);
    }

    @Override
    public void removeBook(String bookId)
    {
        cannotProceedInfo();
    }

    private void cannotProceedInfo()
    {
        String msg = "You cannot execute that operation.\n" +
                "Log in as an admin to do that.";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}