package app.controllers;

import app.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class RequestFuelController {
    @FXML private ComboBox<String> cbMake, cbModel;
    @FXML private TextArea taDetails;
    @FXML private Button btnRefreshPrices;
    @FXML private Text txtPriceInfo;

    @FXML
    private void initialize() {
        loadCarMakes();
        cbMake.setOnAction(e -> loadModels());
        btnRefreshPrices.setOnAction(e -> refreshFuelPrices());
    }

    private void loadCarMakes() {
        try {
            InputStream is = getClass().getResourceAsStream("/data/CarsInSouthAfrica.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            var doc = db.parse(is);
            var makes = doc.getElementsByTagName("Car");
            for (int i=0;i<makes.getLength();i++) {
                var el = (org.w3c.dom.Element)makes.item(i);
                cbMake.getItems().add(el.getAttribute("brand"));
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void loadModels() {
        cbModel.getItems().clear();
        String make = cbMake.getValue();
        if (make == null) return;
        try {
            InputStream is = getClass().getResourceAsStream("/data/CarsInSouthAfrica.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            var doc = db.parse(is);
            var makes = doc.getElementsByTagName("Car");
            for (int i=0;i<makes.getLength();i++) {
                var el = (org.w3c.dom.Element)makes.item(i);
                if (make.equals(el.getAttribute("brand"))) {
                    var models = el.getElementsByTagName("Name");
                    for (int j=0;j<models.getLength();j++) {
                        cbModel.getItems().add(models.item(j).getTextContent());
                    }
                    break;
                }
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void refreshFuelPrices() {
        String info = "Latest (mock) prices:\nPetrol 95: R20.15 / L\nDiesel 0.05%: R19.10 / L\n(For live prices embed AA script or call an API.)";
        txtPriceInfo.setText(info);
    }

    @FXML
    private void onRequestFuel() {
        String make = cbMake.getValue();
        String model = cbModel.getValue();
        if (make == null || model == null) {
            taDetails.setText("Select a car make and model first.");
            return;
        }
        taDetails.setText("Requesting fuel for: " + make + " " + model + "\n(Submission logic goes here.)");
    }

    @FXML
    private void onLogout() {
        SceneController.show("login");
    }
}
