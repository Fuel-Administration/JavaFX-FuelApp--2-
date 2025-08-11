package app.controllers;

import app.SceneController;
import app.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {
    @FXML private TextField firstName, lastName, email, idNumber, username, carReg;
    @FXML private PasswordField password, passwordConfirm;
    @FXML private ComboBox<String> cbCarMake, cbCarModel;
    @FXML private Text txtMessage;

    @FXML
    private void initialize() {
        loadCarMakes();
        cbCarMake.setOnAction(e -> loadModelsForSelectedMake());
    }

    private void loadCarMakes() {
        try {
            InputStream is = getClass().getResourceAsStream("/data/CarsInSouthAfrica.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
            NodeList makes = doc.getElementsByTagName("Car");
            for (int i = 0; i < makes.getLength(); i++) {
                Element el = (Element) makes.item(i);
                cbCarMake.getItems().add(el.getAttribute("brand"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadModelsForSelectedMake() {
        cbCarModel.getItems().clear();
        String make = cbCarMake.getValue();
        if (make == null) return;
        try {
            InputStream is = getClass().getResourceAsStream("/data/CarsInSouthAfrica.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
            NodeList makes = doc.getElementsByTagName("Car");
            for (int i = 0; i < makes.getLength(); i++) {
                Element el = (Element) makes.item(i);
                if (make.equals(el.getAttribute("brand"))) {
                    NodeList models = el.getElementsByTagName("Name");
                    for (int j = 0; j < models.getLength(); j++) {
                        cbCarModel.getItems().add(models.item(j).getTextContent());
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onSubmit() {
        if (!password.getText().equals(passwordConfirm.getText())) {
            txtMessage.setText("Passwords do not match.");
            return;
        }
        User u = new User();
        u.setFirstName(firstName.getText());
        u.setLastName(lastName.getText());
        u.setEmail(email.getText());
        u.setIdNumber(idNumber.getText());
        u.setUsername(username.getText());
        u.setPassword(password.getText());
        u.setCarMake(cbCarMake.getValue());
        u.setCarModel(cbCarModel.getValue());
        u.setCarReg(carReg.getText());

        saveUser(u);
        txtMessage.setText("Registered! You can now login.");
    }

    private void saveUser(User user) {
        try {
            File outDir = new File("data");
            if (!outDir.exists()) outDir.mkdirs();
            File f = new File(outDir, "users.json");
            Gson g = new Gson();
            List<User> users = new ArrayList<>();
            if (f.exists()) {
                users = g.fromJson(new FileReader(f), new TypeToken<List<User>>(){}.getType());
                if (users == null) users = new ArrayList<>();
            }
            users.add(user);
            try (FileWriter fw = new FileWriter(f)) {
                g.toJson(users, fw);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBack() {
        SceneController.show("login");
    }
}
