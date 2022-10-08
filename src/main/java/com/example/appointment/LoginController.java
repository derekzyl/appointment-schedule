package com.example.appointment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField login_username;
    @FXML
    private Button login_button;
    @FXML
    private TextField login_password;
    @FXML
    private Button signup_route;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



        login_button.setOnAction(event -> {
            String username = login_username.getText();
            String password = login_password.getText();
            if (username.equals("admin") && password.equals("admin")) {
                DataBaseUtility.PageHandler(event, "src/appointment.fxml", "Appointment", username);
            } else {
                DataBaseUtility.PageHandler(event, "src/login.fxml", "Login", null);
            }
        });
        signup_route.setOnAction(event -> {
            DataBaseUtility.PageHandler(event, "src/signup.fxml", "Signup", null);
        });
    }
}




