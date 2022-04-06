package com.example.endproject;


import com.example.endproject.app.Constants;
import com.example.endproject.app.LoginApplication;
import com.example.endproject.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.*;

public class DatabaseConnect {

    public static final String DB_URL = "jdbc:mariadb://localhost:3306/room_planning?useSSL=false&characterEncoding=utf8";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "";

    public static String loginQuery = "{call getLoginData(?,?)}";


    public static Connection connect() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }


    public static boolean checkUsernameAndPassword(String username, String password) throws SQLException {
        Connection conn = DatabaseConnect.connect();
        CallableStatement statement;
        ResultSet result;

        try {
            statement = conn.prepareCall(loginQuery);
            statement.setString(1, username);
            statement.setString(2, password);
            result = statement.executeQuery();


            while (result.next()) {
                if (result.getString("authorization").equals("admin")) {
                    System.out.println("ADMIN");
                    //changeView(Constants.ADMIN_PAGE, "Welcome Admin", LoginController.logIn);
                } else if (result.getString("authorization").equals("coach")){
                    System.out.println("COUCH");
                }
//                System.out.println(result.getString("authorization"));

            }

        }catch (SQLException e) {
            e.printStackTrace();

        }

        return true;
    }

   /* public static void changeView(String path, String title, Button button) {
        Stage stage = getCurrentStage(button);
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

    public static Stage getCurrentStage(Button button) {
        return (Stage) button.getScene().getWindow();
    }
*/

}
