package app.ui.gui;

import app.controller.ReportTaskController;
import app.controller.SerializationClass;
import app.ui.gui.utils.PopUpMessages;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class MainApp extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        SerializationClass serializationClass = new SerializationClass();

        serializationClass.deserializeCompany();

        new ReportTaskController().run();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setTitle("Many Labs Application");
        stage.setScene(scene);

        stage.setResizable(false);

        String css = this.getClass().getResource("/Styles/Styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        PopUpMessages popUpMessages = new PopUpMessages();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                popUpMessages.exitMessage(stage, event);
            }
        });

        stage.show();
    }
}