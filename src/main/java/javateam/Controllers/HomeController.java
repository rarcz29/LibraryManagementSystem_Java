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

        if (User.getInstance().getIsAdmin())
            model = homeFactory.create("admin");

        else model = homeFactory.create("user");
    }

    @FXML
    public void initialize()
    {
        ObservableList<Home.TableProduct> tableValues = model.getBooks();

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
        model.getBooks();
        clearTextFields();
    }

    @FXML
    public void searchButtonOnAction()
    {
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String genre = genreTextField.getText();
        String bookstandId = bookstandIdTextField.getText();

        model.search(title, author, genre, bookstandId);
        clearTextFields();
    }

    @FXML
    public void addToMyListButtonOnAction()
    {
        if (model.addSelectedToMyList())
        {
            String msg = "Books added successfully.";
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    public void removeBooksOnAction()
    {
        model.removeSelectedBooks();
        model.getBooks();
    }

    private void clearTextFields()
    {
        titleTextField.setText("");
        authorTextField.setText("");
        genreTextField.setText("");
        bookstandIdTextField.setText("");
    }
}