package home;

import controller.LoginController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {//Главный метод main


    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private LoginController mainController;
    @Override
    public void start(Stage stage) throws Exception {//запуск формы авторизации
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Login.fxml"));
        Parent root = fxmlLoader.load();
        LoginController mainController = fxmlLoader.getController();
        mainController.asd2();
        mainController.loadData();
        stage.initStyle(StageStyle.DECORATED);

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


    public static void main(String[] args) {
        launch(args);
    }

}
