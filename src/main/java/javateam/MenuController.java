package javateam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController
{

    @FXML
    private BorderPane borderPane;

    @FXML
    public void initialize()
    {
        loadUI("LogIn");
    }

    @FXML
    public void homeButtonOnAction(ActionEvent actionEvent)
    {
        loadUI("Home");
    }
    @FXML
    public void myListButtonOnAction(ActionEvent actionEvent)
    {
        loadUI("MyList");
    }
    @FXML
    public void readingNowButtonOnAction(ActionEvent actionEvent)
    {
        loadUI("ReadingNow");
    }
    @FXML
    public void bookstandsButtonOnAction(ActionEvent actionEvent)
    {
        loadUI("Bookstands");
    }
    @FXML
    public void LogOutButtonOnAction(ActionEvent actionEvent)
    {
        loadUI("LogIn");
    }

    private void loadUI(String ui)
    {
        Parent root = null;

        try
        {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
            borderPane.setCenter(root);
        }
        catch (Exception ex)
        {
            // Logger
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);

            // MessageBox
            String msg = "It's not working";
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}
