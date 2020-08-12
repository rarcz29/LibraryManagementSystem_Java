package home_model;

import java.util.Vector;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import db_strategy.Operation;
import db_strategy.Search;
import db_strategy.AddBook;
import db_strategy.DelBook;

import db_strategy.AddBookstand;

public class HomeAdmin implements IHome
{
    public Vector<?> search(String title, String author)
    {
        Operation operation = new Search();
        return operation.doOperation(title, author);
    }

    public void addBook(String title, String author, String type,
                        String description, String bookstandId)
    {
        Operation operation = new AddBook();
        var result = operation.doOperation(bookstandId, title, author, type, description);

        if (!result.get(0))
        {
            String msg = "There's a problem with adding that book to list.\n" +
                    "Try again.";
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

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

    public void removeBook(String bookId)
    {
        Operation operation = new DelBook();
        operation.doOperation(bookId);
    }
}