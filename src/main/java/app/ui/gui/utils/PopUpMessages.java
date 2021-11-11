package app.ui.gui.utils;

import app.controller.App;
import app.controller.SerializationClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PopUpMessages {

    public PopUpMessages() {

    }

    public void errorMessage(ActionEvent event, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error window");
        alert.setHeaderText(message);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ok");

        if (alert.showAndWait().get() == ButtonType.OK) {
            event.consume();
        }
    }

    public void warningMessage(ActionEvent event, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Error window");
        alert.setHeaderText(message);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ok");

        if (alert.showAndWait().get() == ButtonType.OK) {
            event.consume();
        }
    }

    public void informationMessage(ActionEvent event, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("information window");
        alert.setHeaderText(message);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ok");

        if (alert.showAndWait().get() == ButtonType.OK) {
            event.consume();
        }
    }

    public void exitMessage(Stage stage, WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


        alert.setTitle("App");
        alert.setHeaderText("Confirmation of action.");
        alert.setContentText("Do you really want to close the application?");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

        if (alert.showAndWait().get() == ButtonType.OK) {
            SerializationClass serializationClass = new SerializationClass();
            serializationClass.serializeCompany();
            stage.close();
        } else {
            if (event != null) {
                event.consume();
            }
        }

    }
}
