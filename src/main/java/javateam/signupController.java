package javateam;

import java.io.IOException;
import javafx.fxml.FXML;

public class signupController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("login");
    }
}