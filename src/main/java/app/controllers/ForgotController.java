package app.controllers;

import app.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ForgotController {
    @FXML private TextField txtUsernameOrEmail;
    @FXML private Text txtMessage;

    @FXML
    private void onReset() {
        String v = txtUsernameOrEmail.getText().trim();
        if (v.isEmpty()) {
            txtMessage.setText("Enter username or email.");
        } else {
            txtMessage.setText("If account exists, a reset link was sent (mock).");
        }
    }

    @FXML
    private void onBack() {
        SceneController.show("login");
    }
}
