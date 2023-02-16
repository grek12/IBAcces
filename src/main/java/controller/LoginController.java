package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.AbstractFile;

import model.FileAccessRights;
import model.User;
import model.UserManagement;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;

import java.util.ResourceBundle;


public class LoginController implements Initializable {//класс контроллер формы авторизации

    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;

    private String ttt = null;

    Boolean con = true;
    Boolean resultSet = false;
    UserManagement userManagement ;
    @FXML
    private Controller2 controller;

    public LoginController(){

    }

    void ads (UserManagement userManagement){
        this.userManagement = userManagement;
    }
    public void asd2(){
        UserManagement userManagement = new UserManagement();
        this.userManagement = userManagement;
    }

    @FXML
    public void handleButtonAction(MouseEvent event) {//действие при нажатии на кнопку "войти"

        if (event.getSource() == btnSignin) {

            if (logIn().equals("Успешно")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(
                            "/AppForm.fxml"));

                    Parent root = (Parent) loader.load();
                    Controller2 controller2 = loader.getController();
                    controller2.setUserManagement(userManagement);
                    Scene scene = new Scene(root);
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    stage.getIcons().add(new Image(LoginController.class.getResourceAsStream("/icon.png")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }

    /*
@FXML
public void handleButtonAction(MouseEvent event) {//действие при нажатии на кнопку "войти"

    if (event.getSource() == btnSignin) {

        if (logIn().equals("Успешно")) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/AppForm.fxml"));

                Parent root = (Parent) loader.load();
                Controller2 controller2 = loader.getController();
                controller2.setUserManagement(userManagement);

                Scene newScene = new Scene(root);
                Stage newStage = new Stage();

                newStage.setScene(newScene);
                newStage.show();


                newStage.getIcons().add(new Image(LoginController.class.getResourceAsStream("/icon.png")));


            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        }
    }
}

*/

    void  tt(){
        this.ttt = "save";
    }

    public void loadData(){
        User admin = new User("admin", "admin");
        User user1 = new User("user1", "user1");
        User user2 = new User("user2", "user2");
        User user3 = new User("user3", "user3");
        User user4 = new User("user4", "user4");
        User user5 = new User("user5", "user5");
        User user6 = new User("user6", "user6");
        userManagement.addUser(admin);
        userManagement.addUser(user1);
        userManagement.addUser(user2);
        userManagement.addUser(user3);
        userManagement.addUser(user4);
        userManagement.addUser(user5);
        userManagement.addUser(user6);
        AbstractFile file1 = new AbstractFile("file1");
        AbstractFile file2 = new AbstractFile("file2");
        AbstractFile file3 = new AbstractFile("file3");
        AbstractFile file4 = new AbstractFile("file4");
        AbstractFile file5 = new AbstractFile("file5");
        AbstractFile file6 = new AbstractFile("file6");
        userManagement.addFile(file1);
        userManagement.addFile(file2);
        userManagement.addFile(file3);
        userManagement.addFile(file4);
        userManagement.addFile(file5);
        userManagement.addFile(file6);
        userManagement.setCurrentUser(admin);
        userManagement.getCurrentUser().setRight(true);
        userManagement.grantAccessRight("file1", "admin", FileAccessRights.FULL_ACCESS);
        userManagement.grantAccessRight("file2", "admin", FileAccessRights.FULL_ACCESS);
        userManagement.grantAccessRight("file3", "admin", FileAccessRights.FULL_ACCESS);
        userManagement.grantAccessRight("file4", "admin", FileAccessRights.FULL_ACCESS);
        userManagement.grantAccessRight("file5", "admin", FileAccessRights.FULL_ACCESS);
        userManagement.grantAccessRight("file6", "admin", FileAccessRights.FULL_ACCESS);
        userManagement.grantAccessRight("file1", "user1", FileAccessRights.READ);
        userManagement.grantAccessRight("file2", "user1", FileAccessRights.RECORD);
        userManagement.grantAccessRight("file3", "user1", FileAccessRights.READ);
        userManagement.grantAccessRight("file4", "user1", FileAccessRights.READ);
        userManagement.grantAccessRight("file5", "user1", FileAccessRights.READ);
        userManagement.grantAccessRight("file6", "user1", FileAccessRights.READ);
        userManagement.grantAccessRight("file1", "user2", FileAccessRights.READ);
        userManagement.grantAccessRight("file2", "user2", FileAccessRights.READ);
        userManagement.grantAccessRight("file3", "user3", FileAccessRights.RECORD);
        userManagement.grantAccessRight("file4", "user4", FileAccessRights.NO_ACCESS);
        userManagement.grantAccessRight("file5", "user5", FileAccessRights.FULL_ACCESS);
        userManagement.grantAccessRight("file6", "user6", FileAccessRights.RECORD);

        userManagement.grantAccessRight("file1", "user2", FileAccessRights.READ);
        userManagement.grantAccessRight("file2", "user2", FileAccessRights.READ);
        userManagement.grantAccessRight("file3", "user3", FileAccessRights.RECORD);
        userManagement.grantAccessRight("file4", "user4", FileAccessRights.NO_ACCESS);
        userManagement.grantAccessRight("file5", "user5", FileAccessRights.FULL_ACCESS);
        userManagement.grantAccessRight("file6", "user6", FileAccessRights.RECORD);


    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {//вывод сообщения после проверки соединение с бд
        // TODO
        if (con == false) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Ошибка подключение к серверу: проверьте подключение");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Подключение установлено");
        }



    }




    private String logIn() {






        String status = null;


        String username = txtUsername.getText();
        String password = txtPassword.getText();
        
        if (username.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Учетные данные не введены!");
            status = "Ошибка";
            
        }
            try {
                boolean isAuthorized = userManagement.authorize(username, password);
                if (isAuthorized) {
                    resultSet=true;
                    status = "Успешно";
                    setLblError(Color.GREEN, "Вход в систему прошел Успешно..Перенаправление..");

                    return status;
                }
                if (resultSet!=true) {
                    setLblError(Color.TOMATO, "Введите верные логин/пароль");
                    status = "Error";
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
            return status;
        }

       

    private void setLblError(Color color, String text) {//настройка сообщений
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    public void nextfield() {//перенос строки при нажатии 'enter'
        txtUsername.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtPassword.requestFocus();
            }
        });
    }

    @FXML
    public void nextBut() {//эмуляция нажатия на кнопку войти при нажатии 'enter'
        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                handleButtonAction2(event);
            }

        });

    }

    @FXML
    public void handleButtonAction2(KeyEvent event) {//запуск второй формы

        if (logIn().equals("Успешно")) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(fxmlLoader.load(getClass().getResource("/AppForm.fxml")));
                stage.setScene(scene);
                stage.show();
                Controller2 controller2 = fxmlLoader.getController();
                controller2.setMainController(this);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }


        }
    }

    public void DialogInfo() {//вывод информационного сообщения
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText("Для восстановления логина/пароля, обратитесь к системному администратору!");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/fullpackstyling.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

}
