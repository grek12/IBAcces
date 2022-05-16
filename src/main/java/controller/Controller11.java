package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAOLawyer;
import model.Lawyer;

public class Controller11 {//класс контроллер формы добавления адвоката

    @FXML
    private TextField experienceField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField numberPhoneField;

    @FXML
    private TextField patronymicField;
    @FXML
    private Controller10 mainController;
    private Controller2 controller2;

    @FXML
    private void initialize() {

    }

    @FXML
    public void setMainController(Controller10 mainController) {
        this.mainController = mainController;
    }//получение данных от 10-го контроллера

    @FXML
    private void handleCancel(javafx.event.ActionEvent event) {//нажатие на кнопку отмена
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    private boolean isInputValid() {//проверка условий
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Заполните поле фамилии адвоката!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Заполните поле имени адвоката!\n";
        }
        if (patronymicField.getText() == null || patronymicField.getText().length() == 0) {
            errorMessage += "Заполните поле отчества адвоката!\n";
        }

        if (experienceField.getText() == null || experienceField.getText().length() == 0) {
            errorMessage += "Заполните поле стажа работы адвоката!\n";
        }
        if (numberPhoneField.getText() == null || numberPhoneField.getText().length() == 0) {
            errorMessage += "Заполните поле номера телефона адвоката!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Поля ввода не заполнены!");
            alert.setHeaderText("Заполните все поля ввода!");
            alert.setContentText(errorMessage);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/fullpackstyling.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void handleOk(javafx.event.ActionEvent event) {//нажатие на кнопку ок

        Lawyer lawyer = new Lawyer();
        if (isInputValid()) {
            lawyer.setFirstName(firstNameField.getText());
            lawyer.setLastName(lastNameField.getText());
            lawyer.setPatronymic(patronymicField.getText());
            lawyer.setExperience(Integer.parseInt(experienceField.getText()));
            lawyer.setNumberPhone(numberPhoneField.getText());

            try {
                DAOLawyer dao = new DAOLawyer();
                dao.insertLawyer(lawyer);
                mainController.updateTable();

                mainController.DialogInfo("Адвокат успешно добавлен!");


            } catch (Exception e) {
                mainController.DialogError("Не удалось добавить адвоката!");

                e.printStackTrace();
            }

        }

        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    public void test() {//ограничение ввода
        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-ZА-Яа-я*")) {
                lastNameField.setText(newValue.replaceAll("[^\\sa-zA-ZА-Яа-я]", ""));
            }
        });
        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-ZА-Яа-я*")) {
                firstNameField.setText(newValue.replaceAll("[^\\sa-zA-ZА-Яа-я]", ""));
            }
        });
        patronymicField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-ZА-Яа-я*")) {
                patronymicField.setText(newValue.replaceAll("[^\\sa-zA-ZА-Яа-я]", ""));
            }
        });
        experienceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                experienceField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

}
