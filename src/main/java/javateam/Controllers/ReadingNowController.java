package javateam.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

import javateam.Models.ReadingNow;

public class ReadingNowController
{
    private ReadingNow model = new ReadingNow();
    private ReadingNow.ReadingNowIterator iterator;

    @FXML private Label mainLabel;
    @FXML private Label secondLabel;

    @FXML
    public void initialize()
    {
        iterator = model.getReadingNowIterator();

        if (iterator.hasNext())
        {
            String[] arr = ((String[])iterator.next());
            String title = arr[2];
            String author = arr[3];
            String value = String.valueOf(getSecondLabelText(title, author));
            secondLabel.setText(value);

            String str = model.getCurrentReadingBook();
            mainLabel.setText(str);
        }
    }

    @FXML
    public void nextButtonOnAction()
    {
        if (iterator.hasNext())
        {
            String[] arr = ((String[])iterator.next());
            String title = arr[2];
            String author = arr[3];
            String value = String.valueOf(getSecondLabelText(title, author));
            secondLabel.setText(value);
        }
        else
        {
            while (iterator.hasPrevious())
                iterator.previous();

            String[] arr = ((String[])iterator.previous());
            String title = arr[2];
            String author = arr[3];
            String value = String.valueOf(getSecondLabelText(title, author));
            secondLabel.setText(value);
        }
    }

    @FXML
    public void confirmButtonOnAction()
    {
        if (iterator.confirm())
        {
            String str = model.getCurrentReadingBook();
            mainLabel.setText(str);
        }
        else
        {
            String msg = "This book is currently reserved.";
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    private String getSecondLabelText(String title, String author)
    {
        return "Select:\n\"" + title + "\"\n" + author;
    }
}