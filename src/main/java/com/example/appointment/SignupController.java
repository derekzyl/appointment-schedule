package com.example.appointment;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
    private TextField sign_username;
    @FXML
    private Button sign_register;
    @FXML
    private Button login_route;

    @FXML
    private TextField sign_password;
    @FXML
    private TextField sign_firstname;
    @FXML
    private TextField sign_age;
    @FXML
    private TextField sign_lastname;
    @FXML
    private TextField sign_city;
    @FXML
    private TextField sign_address;
    @FXML
    private RadioButton sign_male;
    @FXML
    private RadioButton sign_female;

    @Override
    public void initialize(URL location, ResourceBundle resource){
        ToggleGroup toggleGroup = new ToggleGroup() ;
        sign_male.setToggleGroup(toggleGroup);
        sign_female.setToggleGroup(toggleGroup);


sign_register.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent actionEvent) {
        String toggleName  = ((RadioButton)toggleGroup.getSelectedToggle()).getText();
        String username = sign_username.getText();
        String firstname = sign_firstname.getText();
        String lastname = sign_lastname.getText();
        int age = Integer.parseInt(sign_age.getText());
        String city = sign_city.getText();
        String address = sign_address.getText();
        String password = sign_password.getText();
        if(!username.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !city.isEmpty() && !address.isEmpty()){
            DataBaseUtility.signUp(actionEvent, username, firstname, lastname,password, address, toggleName, city, age);
            DataBaseUtility.PageHandler(actionEvent, "src/login.fxml", "Login", null);
        }
        else{
          System.out.println("Please fill all the fields");
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill all the fields");
            alert.showAndWait();
        }
    }
});
login_route.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            DataBaseUtility.PageHandler(actionEvent, "src/Login.fxml", "Login", null);
        }
    });

    }
}
