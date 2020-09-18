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
    public void returnBookButtonOnAction()
    {
        boolean result = iterator.delete();

        if (!result)
            secondLabel.setText("The list is empty.");

        while (iterator.hasPrevious())
            iterator.previous();

        if (result)
        {
            String[] arr = ((String[])iterator.previous());
            String title = arr[2];
            String author = arr[3];
            String value = String.valueOf(getSecondLabelText(title, author));
            secondLabel.setText(value);
        }

        mainLabel.setText("Nothing");
    }

    @FXML
    public void nextButtonOnAction()
    {
        String[] arr;

        if (!iterator.hasNext())
        {
            while (iterator.hasPrevious())
                iterator.previous();

            arr = ((String[])iterator.previous());
        }

        else arr = ((String[])iterator.next());

        String title = arr[2];
        String author = arr[3];
        String value = String.valueOf(getSecondLabelText(title, author));
        secondLabel.setText(value);
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