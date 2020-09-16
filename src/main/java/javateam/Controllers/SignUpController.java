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

import javateam.Models.Register;
import javateam.App;

public class SignUpController
{
    private Register model = new Register();

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;

    @FXML
    public void SignUpButtonOnAction(ActionEvent actionEvent) throws IOException
    {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        System.out.println(password);
        System.out.println(confirmPassword);

        if (!password.equals(confirmPassword) || !model.regMethod(username, password))
        {
            String msg = "Try again.";
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else
        {
            App.setRoot("LogIn");
        }
    }

    @FXML
    public void GoBackButtonOnAction(ActionEvent actionEvent) throws IOException
    {
        App.setRoot("LogIn");
    }
}
