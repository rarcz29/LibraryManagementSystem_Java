package javateam;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.swing.text.html.ImageView;

public class loginController {
    //InputStream stream = this.getClass().getClassLoader().getResourceAsStream("@../../img/ksiazka.png");
    @FXML
    private void goToSignUp() throws IOException {
        App.setRoot("signup");
    }

    public void loginToSystem(ActionEvent actionEvent) throws IOException {
        //tutaj trzeba najpierw sprawdzić, czy user jest w bazie.
        //Jeśli tak to jest przekierowanie go do  findBook.fxml
        App.setRoot("findBook");
    }
}
