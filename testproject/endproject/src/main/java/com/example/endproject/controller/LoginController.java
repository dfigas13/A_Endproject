package com.example.endproject.controller;

import com.example.endproject.DatabaseConnect;
import com.example.endproject.app.Constants;
import com.example.endproject.app.LoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginController{

    @FXML
    private Button guestButton;
    @FXML
    private TextField usernameTf;
    @FXML
    private PasswordField passwordTf;
    @FXML
    public static Button logIn;
    @FXML
    private Label loginMessage;

    @FXML
    private void logIn(ActionEvent actionEvent) throws SQLException {
       if (checkField()) {
            DatabaseConnect.checkUsernameAndPassword(usernameTf.getText(), passwordTf.getText());
        } else {
          // loginMessage.setText("Falsche Username oder Password");
      }
        // changeView(Constants.ADMIN_PAGE, "Welcome Admin");
        // changeView(Constants.TRAINER_PAGE, "Welcome Trainer");
    }

    @FXML
    private void guestLog(ActionEvent actionEvent) {
        changeView(Constants.ADMIN_VIEW, "Stundenplan");

    }

    public boolean checkField() {
        if (usernameTf.getText()==null || passwordTf.getText()==null) {
            return false;
        }
        return true;
    }

    public void changeView(String path, String title) {
        Stage stage = getCurrentStage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource(path));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());

        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private Stage getCurrentStage() {
        return (Stage) guestButton.getScene().getWindow();
    }


    @FXML
    private void onLoginClick(ActionEvent actionEvent) {
    }

    @FXML
    private void onGuestClick(ActionEvent actionEvent) {
        changeView(Constants.ADMIN_VIEW, "Admin View");
    }
}