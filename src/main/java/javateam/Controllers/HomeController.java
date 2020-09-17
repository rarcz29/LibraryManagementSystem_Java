package javateam.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javateam.Models.home_model.*;
import javateam.User;

public class HomeController
{
    private Home model;

    @FXML private TextField titleTextField;
    @FXML private TextField authorTextField;
    @FXML private TextField genreTextField;
    @FXML private TextField bookstandIdTextField;

    public HomeController()
    {
        HomeFactory homeFactory = new HomeFactory();

        System.out.println(User.getInstance().getIsAdmin());

        if (User.getInstance().getIsAdmin())
            model = homeFactory.create("admin");

        else model = homeFactory.create("user");
    }

    @FXML
    public void addBookButtonOnAction()
    {
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String genre = genreTextField.getText();
        String bookstandId = bookstandIdTextField.getText();

        model.addBook(title, author, genre, "", bookstandId);
    }
}