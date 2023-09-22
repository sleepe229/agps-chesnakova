package com.example.agpsappchesnakova;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class HelloController {

    private final Map<String, int[]> districtData = new HashMap<>();

    @FXML
    private TextField numberFireDepartmentTextField;

    @FXML
    private TextField numberACTextField;

    @FXML
    private TextField numberANRTextField;

    @FXML
    private TextField numberALTextField;

    @FXML
    private Label resultLabel;

    @FXML
    private void enterData() {
        String numberDistrict = numberFireDepartmentTextField.getText();
        String chText = numberACTextField.getText();
        String oiOfEIText = numberANRTextField.getText();
        String oiOfTransportText = numberALTextField.getText();

        if (numberDistrict.isEmpty() || chText.isEmpty() || oiOfEIText.isEmpty() || oiOfTransportText.isEmpty()) {
            showError("Поля номера района, а также площадей, должны быть заполнены.");
            return;
        }

        int carelessHandling = Integer.parseInt(chText);
        int operationalIrregularityOfEI = Integer.parseInt(oiOfEIText);
        int operationalIrregularityOfTransport = Integer.parseInt(oiOfTransportText);

        // Проверяем, существует ли уже запись для этого района
        if (districtData.containsKey(numberDistrict)) {
            int[] currentData = districtData.get(numberDistrict);
            currentData[0] += carelessHandling;
            currentData[1] += operationalIrregularityOfEI;
            currentData[2] += operationalIrregularityOfTransport;
        } else {
            // Если записи для района нет, создаем новый массив
            int[] newData = {carelessHandling, operationalIrregularityOfEI, operationalIrregularityOfTransport};
            districtData.put(numberDistrict, newData);
        }

        showInfo("Данные сохранены для района №" + numberDistrict);
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void showResult() {
        int totalCarelessHandling = 0;
        int totalOperationalIrregularityOfEI = 0;
        int totalOperationalIrregularityOfTransport = 0;
        int totalDistricts = districtData.size();
        int totalCount = 3;

        for (int[] data : districtData.values()) {
            totalCarelessHandling += data[0];
            totalOperationalIrregularityOfEI += data[1];
            totalOperationalIrregularityOfTransport += data[2];
        }

        int avgCarelessHandling = totalCarelessHandling / totalDistricts;
        int avgOperationalIrregularityOfEI = totalOperationalIrregularityOfEI / totalDistricts;
        int avgOperationalIrregularityOfTransport = totalOperationalIrregularityOfTransport / totalDistricts;
        int avgSum = (avgCarelessHandling + avgOperationalIrregularityOfEI+avgOperationalIrregularityOfTransport) / totalCount;

        showInfo("Среднее количество пожаров во всех районах:" + avgSum + "\n" +
                "Среднее для неосторожного обращения с огнем: " + avgCarelessHandling + "\n" +
                "Среднее для нарушения правил устройства и эксплуатации электроустановок: " + avgOperationalIrregularityOfEI + "\n" +
                "Среднее для нарушения правил устройства и эксплуатации транспортных средств: " + avgOperationalIrregularityOfTransport + "\n"
        );
    }


    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void exit() {
        Stage stage = (Stage) resultLabel.getScene().getWindow();
        stage.close();
    }
}
