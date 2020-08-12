package home_model;

import java.util.Vector;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import db_strategy.Operation;
import db_strategy.Search;
import db_strategy.AddBook;

public class HomeAdmin implements IHome
{
    public Vector<?> search(String title, String author)
    {
        Operation operation = new Search();
        return operation.doOperation(title, author);
    }

    public Vector<?> addBook(String tittle, String author, String type,
                             String description, String bookstandId)
    {
        Operation operation = new AddBook();
        return operation.doOperation();
    }

    public Vector<?> addToMyList(String userId, String bookId)
    {
        String msg = "Admin cannot execute that operation.\n" +
                "Log in to your account, or create a new one.";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.showAndWait();

        return null;
    }

    public Vector<?> removeBooks(String bookId)
    {
        Operation operation = new DelBook(bookId);
        return operation.doOperation()
    }
}