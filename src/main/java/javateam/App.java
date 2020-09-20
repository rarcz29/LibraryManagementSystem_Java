package javateam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LogIn"));

        String style= getClass().getResource("Css/style.css").toExternalForm();
        scene.getStylesheets().add(style);

        this.stage = stage;
        this.stage.setScene(scene);
        this.stage.show();
        this.stage.setHeight(520);
        this.stage.setWidth(720);
        this.stage.setResizable(false);
        this.stage.setTitle("Home library");
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setStageTitle(String title)
    {
        stage.setTitle(title);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}