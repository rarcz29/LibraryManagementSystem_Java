package javateam;

import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class signupController {

    @FXML
    public void signUp(ActionEvent actionEvent) throws IOException {
        //tutaj jest dodawanie do bazy uzytkownika
    }

    public void goBack(ActionEvent actionEvent) throws IOException{
        App.setRoot("login");
    }
}