package app.controllers;

import app.SceneController;
import app.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class LoginController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Text txtMessage;
    @FXML private Hyperlink linkRegister;
    @FXML private Hyperlink linkForgot;

    @FXML
    private void onLogin() {
        String u = txtUsername.getText().trim();
        String p = txtPassword.getText();
        if (u.isEmpty() || p.isEmpty()) {
            txtMessage.setText("Enter username and password");
            return;
        }

        boolean ok = checkCredentials(u, p);
        if (ok) {
            txtMessage.setText("Login successful!");
            SceneController.show("request");
        } else {
            txtMessage.setText("Invalid username or password.");
        }
    }

    @FXML
    private void onRegister() {
        SceneController.show("register");
    }

    @FXML
    private void onForgot() {
        SceneController.show("forgot");
    }

    private boolean checkCredentials(String username, String password) {
        try {
            File f = new File("data/users.json");
            if (!f.exists()) return false;
            Gson g = new Gson();
            List<User> users = g.fromJson(new FileReader(f), new TypeToken<List<User>>(){}.getType());
            if (users == null) return false;
            return users.stream().anyMatch(u -> username.equals(u.getUsername()) && password.equals(u.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
