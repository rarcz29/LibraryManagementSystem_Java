package javateam;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.html.ImageView;

public class loginController {
    @FXML
    private TextField userField;
    @FXML
    private Label label;

    @FXML
    private void goToSignUp(ActionEvent event) throws IOException {
        App.setRoot("signup");

    }

    public void loginToSystem(ActionEvent actionEvent) throws IOException {
        //tutaj trzeba najpierw sprawdzić, czy user jest w bazie.
        //Jeśli tak to jest przekierowanie go do  findBook.fxml
        String getUserField = userField.getText();
        if (getUserField.equals("Ola")){
            App.setRoot("findBook");
        }
        else{
            label.setText("Wrong username or password!");
            label.setVisible(true);
        }

    }
}
