package javateam.Models.home_model;

import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.Search;
import javateam.Data.db_strategy.AddBook;
import javateam.Data.db_strategy.DelBook;

public abstract class Home
{
    protected ObservableList<TableProduct> data;

    /*public Vector<?> search(String title, String author)
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
    }*/

    public ObservableList<TableProduct> search(String title, String author)
    {
        if (data == null)
            data = FXCollections.observableArrayList();

        else data.clear();

        Operation operation = new Search();
        var result = operation.doOperation(title, author);
        //int size = result.size() - 1;

        /*String msg = Integer.toString(size) + " books found.\n\n" +
                "Tip: leave title and author fields empty\n" +
                "\tto recive full list";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.showAndWait();*/

        for (int i = 1; i < result.size(); i++)
        {
            String[] arr = (String[])result.get(i);
            data.add(new TableProduct(arr[0], arr[2], arr[3], arr[5], arr[1]));
        }

        return data;
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
    public abstract void removeSelectedBooks();

    public class TableProduct
    {
        private final SimpleStringProperty id;
        private final SimpleStringProperty title;
        private final SimpleStringProperty author;
        private final SimpleStringProperty genre;
        private final SimpleStringProperty bookstandId;
        private CheckBox checkbox;

        TableProduct(String id, String title, String author, String genre, String bookstandId)
        {
            this.id = new SimpleStringProperty(id);
            this.title = new SimpleStringProperty(title);
            this.author = new SimpleStringProperty(author);
            this.genre = new SimpleStringProperty(genre);
            this.bookstandId = new SimpleStringProperty(bookstandId);
            this.checkbox = new CheckBox();
        }

        public String getId()
        {
            return id.get();
        }

        public String getTitle()
        {
            return title.get();
        }

        public String getAuthor() { return author.get(); }

        public String getGenre() { return genre.get(); }

        public String getBookstandId()
        {
            return bookstandId.get();
        }

        public CheckBox getCheckbox()
        {
            return checkbox;
        }
    }
}