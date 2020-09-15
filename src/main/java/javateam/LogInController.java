package javateam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LogInController {
    @FXML
    private VBox loginVBox;

    @FXML
    public void SignUpButtonOnAction() throws IOException
    {
        App.setRoot("SignUp");
    }

    public void LogInButtonAction(ActionEvent actionEvent) {

    }


}
