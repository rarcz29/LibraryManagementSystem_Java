package javateam.Models.home_model;

import java.util.Vector;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.Search;
import javateam.Data.db_strategy.AddBook;

public abstract class Home
{
    public Vector<?> search(String title, String author)
    {
        Operation operation = new Search();
        var result = operation.doOperation(title, author);
        int size = result.size() - 1;

        String msg = Integer.toString(size) + " books found.\n\n" +
                "Tip: leave title and author fields empty\n" +
                "\tto recive full list";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.showAndWait();

        return result;
    }

    public void addBook(String title, String author, String type,
                                 String description, String bookstandId)
    {
        Operation operation = new AddBook();
        var result = operation.doOperation(bookstandId, title, author, type, description);
        boolean success = (boolean)(result.get(0));

        if (!success)
        {
            String msg = "There's a problem with adding that book to list.\n" +
                    "Check if the bookstand with given id exist.\n" +
                    "Try again.";
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public abstract void addToMyList(String userId, String bookId);
    public abstract void removeBook(String bookId);
}