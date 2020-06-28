package javateam;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void goToSignUp() throws IOException {
        App.setRoot("signup");
    }

}
