package javateam;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class loginController {

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
