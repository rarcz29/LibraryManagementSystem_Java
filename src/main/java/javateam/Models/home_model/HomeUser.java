package javateam.Models.home_model;

import java.util.Vector;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

import javateam.User;
import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.AddToList;

public class HomeUser extends Home
{
    @Override
    public boolean addSelectedToMyList()
    {
        ObservableList<TableProduct> dataList = FXCollections.observableArrayList();

        for (TableProduct entity : data)
        {
            if (entity.getCheckbox().isSelected())
            {
                dataList.add(entity);

                Operation operation = new AddToList();
                var result = operation.doOperation(User.getInstance().getUserIdAsString(), entity.getId());
            }
        }

        return true;
    }

    @Override
    public void removeSelectedBooks()
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