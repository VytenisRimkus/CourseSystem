package com.gui.coursesystem.fxControllers;

import com.gui.coursesystem.StartGui;
import com.gui.coursesystem.ds.CourseMngSystem;
import com.gui.coursesystem.ds.User;
import com.gui.coursesystem.ds.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.gui.coursesystem.hibernate.UserHibController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignUpForm implements Initializable {
    @FXML
    public TextField loginF;
    @FXML
    public PasswordField passwordF;
    @FXML
    public RadioButton radioStudent;
    @FXML
    public RadioButton radioTeacher;
    @FXML
    public TextField personNameF;
    @FXML
    public TextField personSurnameF;
    @FXML
    public TextField personEmailF;
    @FXML
    public TextField personPositionF;
    @FXML
    public ToggleGroup userType;
    public PasswordField passwordRepeatF;

    private CourseMngSystem courseMngSystem;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private String userAddedMessage = "A new user has been added successfully";

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    UserHibController userHibController = new UserHibController(entityManagerFactory);


    public void createUser(ActionEvent actionEvent) throws IOException, SQLException {
        if (loginF.getText().isEmpty() || passwordF.getText().isEmpty() || passwordRepeatF.getText().isEmpty() || personNameF.getText().isEmpty() || personSurnameF.getText().isEmpty() || personEmailF.getText().isEmpty() || personPositionF.getText().isEmpty() || personEmailF.getText().isEmpty()){
            alertWindow("Please fill in missing data", Alert.AlertType.ERROR);
        } else if (passwordF.getText().equals(passwordRepeatF.getText())){
            if(radioStudent.isSelected()){
                try{
                    User user = new User(loginF.getText(), passwordF.getText(), personNameF.getText(), personSurnameF.getText(), personPositionF.getText(), personEmailF.getText(), UserType.STUDENT);
                    userHibController.createUser(user);
                    alertWindow(userAddedMessage, Alert.AlertType.INFORMATION);
                }catch (Exception e){
                    alertWindow("Something went wrong", Alert.AlertType.ERROR);
                }
            } else if(radioTeacher.isSelected()){
                try{
                    User user = new User(loginF.getText(), passwordF.getText(), personNameF.getText(), personSurnameF.getText(), personPositionF.getText(), personEmailF.getText(), UserType.TEACHER);
                    userHibController.createUser(user);
                    alertWindow(userAddedMessage, Alert.AlertType.INFORMATION);

                }catch (Exception e){
                    alertWindow("Something went wrong", Alert.AlertType.ERROR);
                }


            }
           // RW.writeToFile(OUT_FILE, courseMngSystem);
            returnToPrevious();
        } else{
            alertWindow("Passwords do not match", Alert.AlertType.ERROR);
            passwordRepeatF.setText(null);
            passwordRepeatF.requestFocus();
        }



    }
    private void alertWindow(String s, Alert.AlertType type) throws IOException {
        Alert alert = new Alert(type, s);
        alert.setHeaderText(null);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES){
            returnToPrevious();
        }
    }

    private void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("login-window.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage  = (Stage)loginF.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login window");
        stage.show();
    }

    public void returnToLogin(ActionEvent actionEvent) throws IOException {
        returnToPrevious();
    }

    public void setCourseMngSystem(CourseMngSystem courseMngSystem) {
        this.courseMngSystem = courseMngSystem;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        courseMngSystem = RW.loadFromFile(OUT_FILE);
//
//        if (courseMngSystem == null) {
//
//            courseMngSystem = new CourseMngSystem(1,"1.1.", new ArrayList<User>(), new ArrayList<Course>());
//        }

    }
}
