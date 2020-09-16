package javateam.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javateam.App;
import javateam.Models.Login;

public class LogInController
{
    private Login loginModel = new Login();

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;

    @FXML
    public void SignUpButtonOnAction() throws IOException
    {
        App.setRoot("SignUp");
    }

    @FXML
    public void LogInButtonAction(ActionEvent actionEvent) throws IOException
    {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (loginModel.logMethod(username, password))
        {
            App.setRoot("Menu");
        }
        else
        {
            String msg = "Wrong login or password.";
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}
