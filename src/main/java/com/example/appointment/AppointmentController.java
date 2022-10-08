package com.example.appointment;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class AppointmentController implements Initializable {
    @FXML
    private Button ap_check;
    @FXML
    private Button ap_confirm;
    @FXML
    private TextField ap_username;
    @FXML
    private TextField ap_name_doc;
    @FXML
    private DatePicker ap_day;
    @FXML
    private DatePicker ap_time;
    @FXML
    private Label ap_welcome;
    @FXML
    private Button ap_signout;
     @Override
    public void initialize(URL location, ResourceBundle rb) {
         ap_signout.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
DataBaseUtility.PageHandler(actionEvent, "Login.fxml", "Login", null);
                                    }
                                    }

         );

     }



    public void  welcomeUser(String username) {
         ap_welcome.setText("Welcome " + username);}




     }
        // TODO

