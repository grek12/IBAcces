package controller;
import home.Main;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.AbstractFile;

import model.FileAccessRights;
import model.User;
import model.UserManagement;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class Controller2 implements Initializable {//класс контроллер главного экрана

    @FXML
    private ComboBox<String> accesBox;

    @FXML
    private TableColumn<User, String> file1Col;

    @FXML
    private Label file1txt;

    @FXML
    private TableColumn<User, String> file2Col;

    @FXML
    private Label file2txt;

    @FXML
    private TableColumn<User, String> file3Col;

    @FXML
    private Label file3txt;

    @FXML
    private TableColumn<User, String> file4Col;

    @FXML
    private Label file4txt;

    @FXML
    private TableColumn<User, String> file5Col;

    @FXML
    private Label rightTxt;

    @FXML
    private Label file5txt;

    @FXML
    private TableColumn<User, String> file6Col;

    @FXML
    private Label file6txt;

    @FXML
    private ComboBox<AbstractFile> fileBox;

    @FXML
    private Label nametxt;

    @FXML
    private TableColumn<User, String> passCol;

    @FXML
    private Label passtxt;

    @FXML
    private TableView<User> table1;

    @FXML
    private TableColumn<User, String> userCol;

    @FXML
    private ComboBox<User> usersBox;

    @FXML
    private Label file1txtT;
    @FXML
    private Label file2txtT;
    @FXML
    private Label file3txtT;
    @FXML
    private Label file4txtT;
    @FXML
    private Label file5txtT;
    @FXML
    private Label file6txtT;
    @FXML
    public Button closeButton;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private LoginController mainController;

        private UserManagement userManagement;


        public Controller2 (){

        }


    @FXML
    public void setMainController(LoginController mainController) {
        this.mainController = mainController;

    }

    public void setUserManagement(UserManagement userManagement) {//получение данных
        this.userManagement = userManagement;
        usersBox.getItems().addAll(userManagement.getAllUsers().values());
        fileBox.getItems().addAll(userManagement.getAllFiles().values());
        accesBox.getItems().addAll(FileAccessRights.READ,
                FileAccessRights.RECORD,
                FileAccessRights.FULL_ACCESS,
                FileAccessRights.NO_ACCESS,
                FileAccessRights.GRANT);
        test2();
        test();
        tableIns();
    }

    void tableIns(){
        ObservableList<User> users = FXCollections.observableArrayList(userManagement.getAllUsers().values());
        table1.setItems(users);
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        file1Col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> userCellDataFeatures) {
                User user = userCellDataFeatures.getValue();
                return new SimpleStringProperty(user.getFileAccessRight("file1"));
            }
        });
        file2Col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> userCellDataFeatures) {
                User user = userCellDataFeatures.getValue();
                return new SimpleStringProperty(user.getFileAccessRight("file2"));
            }
        });
        file3Col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> userCellDataFeatures) {
                User user = userCellDataFeatures.getValue();
                return new SimpleStringProperty(user.getFileAccessRight("file3"));
            }
        });
        file4Col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> userCellDataFeatures) {
                User user = userCellDataFeatures.getValue();
                return new SimpleStringProperty(user.getFileAccessRight("file4"));
            }
        });
        file5Col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> userCellDataFeatures) {
                User user = userCellDataFeatures.getValue();
                return new SimpleStringProperty(user.getFileAccessRight("file5"));
            }
        });
        file6Col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> userCellDataFeatures) {
                User user = userCellDataFeatures.getValue();
                return new SimpleStringProperty(user.getFileAccessRight("file6"));
            }
        });
    }

    void test(){
        nametxt.setText(userManagement.getCurrentUser().getUsername());
        passtxt.setText(userManagement.getCurrentUser().getPassword());
        if(userManagement.getCurrentUser().isRight()){
            rightTxt.setText("Есть права");
        } else{
            rightTxt.setText("Нет прав");
        }

        file1txt.setText(userManagement.getCurrentUser().getFileAccessRight("file1"));
        file2txt.setText(userManagement.getCurrentUser().getFileAccessRight("file2"));
        file3txt.setText(userManagement.getCurrentUser().getFileAccessRight("file3"));
        file4txt.setText(userManagement.getCurrentUser().getFileAccessRight("file4"));
        file5txt.setText(userManagement.getCurrentUser().getFileAccessRight("file5"));
        file6txt.setText(userManagement.getCurrentUser().getFileAccessRight("file6"));

        }

        void test2(){
            Map<String, AbstractFile> files = userManagement.getAllFiles();

            if (files != null) {
                int i = 1;
                for (Map.Entry<String, AbstractFile> entry : files.entrySet()) {
                    switch(i) {
                        case 1:
                            file1txtT.setText(entry.getKey());
                            break;
                        case 2:
                            file2txtT.setText(entry.getKey());
                            break;
                        case 3:
                            file3txtT.setText(entry.getKey());
                            break;
                        case 4:
                            file4txtT.setText(entry.getKey());
                            break;
                        case 5:
                            file5txtT.setText(entry.getKey());
                            break;
                        case 6:
                            file6txtT.setText(entry.getKey());
                            break;
                        default:
                            break;
                    }
                    i++;
                }
            }
        }

        @FXML
        void handleButtonAction(ActionEvent event){
            Stage stage = (Stage) closeButton.getScene().getWindow();
            userManagement.setCurrentUser(null);
            stage.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Login.fxml"));
                Parent root = fxmlLoader.load();
                LoginController controller = fxmlLoader.getController();
                controller.ads(userManagement);
                controller.tt();

            stage.setTitle("Права доступа");
            stage.setMaximized(false);
            stage.setResizable(false);
            root.setOnMousePressed(new EventHandler<MouseEvent>() {//установка курсора
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {//установка курсора
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));

            stage.setScene(scene);
            stage.show();

            }
            catch (IOException ex){

            }
        }
    @FXML
    public void updateTable() {
        ObservableList<User> users = FXCollections.observableArrayList(userManagement.getAllUsers().values());
        table1.setItems(users);

    }

        @FXML
        void grantAccesT(){

            String selectedUser = String.valueOf(usersBox.getValue());
            String selectedFile = String.valueOf(fileBox.getValue());
            String selectedAccess = accesBox.getValue();
            if (selectedUser != null &&  selectedAccess != null) {
                if (selectedAccess.equals("Выдача прав")) {
                    userManagement.grantRight(selectedUser,true);

                }
            }
            if (selectedUser != null && selectedFile != null && selectedAccess != null) {

                switch (selectedAccess) {
                    case "Полный доступ":
                        userManagement.grantAccessRight(selectedFile, selectedUser, FileAccessRights.FULL_ACCESS);
                        break;
                    case "Чтение":
                        userManagement.grantAccessRight(selectedFile, selectedUser, FileAccessRights.READ);
                        break;
                    case "Запись":
                        userManagement.grantAccessRight(selectedFile, selectedUser, FileAccessRights.RECORD);
                        break;
                    case "Нет доступа":
                        userManagement.grantAccessRight(selectedFile, selectedUser, FileAccessRights.NO_ACCESS);
                        break;
                }

                updateTable();
                table1.refresh();

            }

        }

        
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
