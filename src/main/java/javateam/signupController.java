package javateam;

import java.io.IOException;

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