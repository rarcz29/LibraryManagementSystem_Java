package javateam.Models.home_model;

import java.util.Vector;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.DelBook;
import javateam.Data.db_strategy.DelAllFromList;
import javateam.Data.db_strategy.AddBookstand;

public class HomeAdmin extends Home
{
    @Override
    public boolean addSelectedToMyList()
    {
        String msg = "Admin cannot execute that operation.\n" +
                "Log in to your account, or create a new one.";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.showAndWait();

        return false;
    }

    @Override
    public void removeSelectedBooks()
    {
        ObservableList<TableProduct> dataListRemove = FXCollections.observableArrayList();

        for (TableProduct entity : data)
        {
            if (entity.getCheckbox().isSelected())
            {
                dataListRemove.add(entity);

                Operation operation = new DelBook();
                operation.doOperation(entity.getId());

                operation = new DelAllFromList();
                operation.doOperation(entity.getId());
            }
        }

        data.removeAll(dataListRemove);
    }
}