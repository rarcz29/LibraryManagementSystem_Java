package home_model;

import java.util.Vector;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import db_strategy.Operation;
import db_strategy.Search;

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

    public abstract void addBook(String title, String author, String type,
                                 String description, String bookstandId);
    public abstract void addToMyList(String userId, String bookId);
    public abstract void removeBook(String bookId);
}