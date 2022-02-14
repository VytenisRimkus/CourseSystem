package com.gui.coursesystem.fxControllers;

import com.gui.coursesystem.StartGui;
import com.gui.coursesystem.ds.CourseMngSystem;
import com.gui.coursesystem.hibernate.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateUserForm implements Initializable {
    public TextField userLoginF;
    public TextField updatedValueF;
    public ChoiceBox<String> choiceBox = new ChoiceBox<>();
    public Label updateValueText;
    public Button cancelButton;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private CourseMngSystem courseMngSystem;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    public void updateB(ActionEvent actionEvent) throws SQLException, IOException {
        String choice = choiceBox.getValue().toLowerCase();
        String updatedChoice = choice.replace(' ', '_');
        if(updatedValueF.getText().isEmpty() || userLoginF.getText().isEmpty()){
            alertMessage("Error", "Please fill in the missing data", Alert.AlertType.ERROR);
        } else{
            try{

                alertMessage("Success!", "User was updated successfully", Alert.AlertType.INFORMATION);
                returnToPrevious();
            }catch (Exception e){
                alertMessage("Failed... :(", "Something has failed, failure message: " + e, Alert.AlertType.ERROR);
            }
        }

    }

    public void cancelB(ActionEvent actionEvent) throws IOException {
        returnToPrevious();
    }
    private void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-project-window.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage  = (Stage)cancelButton.getScene().getWindow();
        //stage.setScene(scene);
        stage.close();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll("Login", "Password", "Is Active", "Name", "Surname", "Position", "Email", "User Type");
        choiceBox.setValue("Login");

    }
    private static void alertMessage(String title, String s, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(s);
        alert.setHeaderText(null);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
