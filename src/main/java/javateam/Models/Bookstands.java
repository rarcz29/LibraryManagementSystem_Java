package javateam.Models;

import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.AddBookstand;
import javateam.Data.db_strategy.ShowBookstand;

public class Bookstands
{
    ObservableList<TableProduct> data;

    public void AddBookstand(String description)
    {
        if (description.length() > 0)
        {
            Operation operation = new AddBookstand();
            operation.doOperation(description);
        }
    }

    public ObservableList<TableProduct> GetBookstands()
    {
        data.clear();

        Operation operation = new ShowBookstand();
        var result = operation.doOperation();

        for (int i = 1; i < result.size(); i++)
        {
            String[] arr = (String[])result.get(i);
            data.add(new TableProduct(arr[0], arr[1]));
        }

        return data;
    }

    //public void RemoveBookstands()

    public class TableProduct
    {
        private final SimpleStringProperty bookstandId;
        private final SimpleStringProperty description;
        private CheckBox checkbox;

        TableProduct(String id, String description)
        {
            this.bookstandId = new SimpleStringProperty(id);
            this.description = new SimpleStringProperty(description);
            this.checkbox = new CheckBox();
        }
    }
}