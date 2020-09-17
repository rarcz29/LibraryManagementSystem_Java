package javateam.Models;

import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.CheckBox;
import javafx.collections.FXCollections;

import javateam.User;
import javateam.Data.db_strategy.DelFromList;
import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.ShowList;

public class MyList
{
    private ObservableList<TableProduct> data;

    public ObservableList<TableProduct> getList()
    {
        if (data == null)
            data = FXCollections.observableArrayList();

        else data.clear();

        Operation operation = new ShowList();
        var result = operation.doOperation(User.getInstance().getUserIdAsString());

        for (int i = 1; i < result.size(); i++)
        {
            String[] arr = (String[])result.get(i);
            data.add(new TableProduct(arr[0], arr[2], arr[3], arr[5], arr[1]));
        }

        return data;
    }

    public void removeBookFromList(String userId, String bookId)
    {
        Operation operation = new DelFromList();
        operation.doOperation(userId, bookId);
    }

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
