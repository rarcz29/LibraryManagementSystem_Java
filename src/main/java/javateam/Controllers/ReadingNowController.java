package javateam.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javateam.Models.ReadingNow;

public class ReadingNowController
{
    private ReadingNow model = new ReadingNow();

    @FXML private Label mainLabel;
    @FXML private Label secondLabel;

    @FXML
    public void initialize()
    {
        var iterator = model.getReadingNowIterator();
        var value = String.valueOf(((String[])iterator.previous())[3]);

        secondLabel.setText(value);
    }

    @FXML
    public void nextButtonOnAction()
    {
        //var iterator = model.getReadingNowIterator();
        //var value = iterator.
    }
}