package javateam.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

import javateam.Models.home_model.*;
import javateam.User;

public class HomeController
{
    private Home model;

    @FXML private TextField titleTextField;
    @FXML private TextField authorTextField;
    @FXML private TextField genreTextField;
    @FXML private TextField bookstandIdTextField;

    @FXML private TableView table;
    @FXML private TableColumn titleColumn;
    @FXML private TableColumn authorColumn;
    @FXML private TableColumn genreColumn;
    @FXML private TableColumn bookstandIdColumn;
    @FXML private TableColumn selectColumn;

    public HomeController()
    {
        HomeFactory homeFactory = new HomeFactory();

        System.out.println(User.getInstance().getIsAdmin());

        if (User.getInstance().getIsAdmin())
            model = homeFactory.create("admin");

        else model = homeFactory.create("user");
    }

    @FXML
    public void initialize()
    {
        ObservableList<Home.TableProduct> tableValues = model.search("", "");

        titleColumn.setCellValueFactory(
                new PropertyValueFactory<Home.TableProduct, String>("title")
        );
        authorColumn.setCellValueFactory(
                new PropertyValueFactory<Home.TableProduct, String>("author")
        );
        genreColumn.setCellValueFactory(
                new PropertyValueFactory<Home.TableProduct, String>("genre")
        );
        bookstandIdColumn.setCellValueFactory(
                new PropertyValueFactory<Home.TableProduct, String>("bookstandId")
        );
        selectColumn.setCellValueFactory(
                new PropertyValueFactory<Home.TableProduct, CheckBox>("checkbox")
        );

        table.setItems(tableValues);
    }

    @FXML
    public void addBookButtonOnAction()
    {
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String genre = genreTextField.getText();
        String bookstandId = bookstandIdTextField.getText();

        if (bookstandId.length() == 0)
        {
            String msg = "Please enter Bookstand ID";
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.showAndWait();

            return;
        }

        model.addBook(title, author, genre, "", bookstandId);
        model.search("", "");
        clearTextFields();
    }

    @FXML
    public void searchButtonOnAction()
    {
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String genre = genreTextField.getText();
        String bookstandId = bookstandIdTextField.getText();

        if (genre.length() > 0 || bookstandId.length() > 0)
        {
            String msg = "You can search only by title and author.";
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        model.search(title, author);
        clearTextFields();
    }

    @FXML
    public void addToMyListButtonOnAction()
    {
        String msg;

        if (model.addSelectedToMyList())
            msg = "Books added to the list.";

        else msg = "There is a problem with adding books to the list.";

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    public void removeBooksOnAction()
    {
        model.removeSelectedBooks();
        model.search("", "");
    }

    private void clearTextFields()
    {
        titleTextField.setText("");
        authorTextField.setText("");
        genreTextField.setText("");
        bookstandIdTextField.setText("");
    }
}