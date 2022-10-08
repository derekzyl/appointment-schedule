package com.example.appointment;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;

public class DataBaseUtility {
    public static void PageHandler(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DataBaseUtility.class.getResource(fxmlFile));
                root = loader.load();
                AppointmentController controller = loader.getController();
                controller.welcomeUser(username);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {

                root = FXMLLoader.load(DataBaseUtility.class.getResource(fxmlFile));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void signUp(ActionEvent event,
            String username,
            String firstName,
            String lastName,
            String password,
            String address,
            String gender,
            String city,
            int age) {
        Parent root = null;
        Connection connection = null;
        PreparedStatement postRequest = null;
        PreparedStatement postChecks = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appointment", "root", "test");
            postChecks = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            postChecks.setString(1, username);
            resultSet = postChecks.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("User already exists");
                alert.setContentText("Please choose a different username");
                alert.showAndWait();

            } else {
                postRequest = connection.prepareStatement(
                        "INSERT INTO user (username, firstName, lastName, password, address,gender, city, age VALUES (?,?,?,?,?,?,?,?)");
                postRequest.setString(1, username);
                postRequest.setString(2, firstName);
                postRequest.setString(3, lastName);
                postRequest.setString(4, password);
                postRequest.setString(5, address);
                postRequest.setString(6, city);
                postRequest.setInt(7, age);
                postRequest.executeUpdate();
                System.out.println("User created");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User created");
                alert.setContentText("You can now log in");
                alert.showAndWait();
                DataBaseUtility.PageHandler(event, "src/Login.fxml", "Login", null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (postChecks != null) {
                try {
                    postChecks.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (postRequest != null) {
                try {
                    postRequest.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void login(ActionEvent event, String username, String password) {
        Parent root = null;
        Connection connection = null;
        PreparedStatement postRequest = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appointment", "root", "test");
            postRequest = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            postRequest.setString(1, username);

            resultSet = postRequest.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("User not found");
                alert.setContentText("Please check your username and password");
                alert.showAndWait();
                DataBaseUtility.PageHandler(event, "Login.fxml", "Login", null);

            } else {

                while (resultSet.next()) {
                    String getPassword = resultSet.getString("password");
                    if (getPassword.equals(password)) {
                        System.out.println("User found");
                        DataBaseUtility.PageHandler(event, "Appointment.fxml", "Appointment", username);
                    } else {
                        System.out.println("Password incorrect");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Password incorrect");
                        alert.setContentText("Please check your username and password");
                        alert.showAndWait();
                        DataBaseUtility.PageHandler(event, "Login.fxml", "Login", null);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (postRequest != null) {
                try {
                    postRequest.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
