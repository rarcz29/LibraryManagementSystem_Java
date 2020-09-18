package javateam.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

        if (!iterator.hasNext())
        {
            secondLabel.setText("List is empty");
            return;
        }

        //iterator.next();
        String[] arr = ((String[])iterator.next());
        String title = arr[2];
        String author = arr[3];
        String value = String.valueOf("Select: \"" + title + "\" " + author);
        secondLabel.setText(value);
    }

    @FXML
    public void nextButtonOnAction()
    {
        if (iterator.hasNext())
        {
            String[] arr = ((String[])iterator.next());
            String title = arr[2];
            String author = arr[3];
            String value = String.valueOf("Select: \"" + title + "\" " + author);
            secondLabel.setText(value);
        }
        else
        {
            while (iterator.hasPrevious())
                iterator.previous();

            String[] arr = ((String[])iterator.previous());
            String title = arr[2];
            String author = arr[3];
            String value = String.valueOf("Select: \"" + title + "\" " + author);
            secondLabel.setText(value);
        }
    }
}