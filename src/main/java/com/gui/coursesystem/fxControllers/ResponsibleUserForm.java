package com.gui.coursesystem.fxControllers;

import com.gui.coursesystem.StartGui;
import com.gui.coursesystem.ds.Course;
import com.gui.coursesystem.ds.CourseMngSystem;
import com.gui.coursesystem.ds.User;
import com.gui.coursesystem.hibernate.CourseHibController;
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
import java.util.ResourceBundle;

public class ResponsibleUserForm implements Initializable {
    public TextField responsibleTextF;
    public Button cancelB;

    CourseMngSystem courseMngSystem;
    Course course;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    UserHibController userHibController = new UserHibController(entityManagerFactory);
    CourseHibController courseHibController = new CourseHibController(entityManagerFactory);

    public void addResponsibleUser(ActionEvent actionEvent) throws Exception {
        try {
            User user = userHibController.findUser(Integer.parseInt(responsibleTextF.getText()));
            courseHibController.AddResponsibleUserToCourse(course, user);
            alertMessage("New responsible user was added");
            returnToPrevious();
        } catch (Exception e){
            alertMessage("No such user was found or this user is already responsible");
        }

    }
    private static void alertMessage(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setContentText(s);
        alert.setHeaderText(null);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
    public void cancel(ActionEvent actionEvent) throws IOException {
        returnToPrevious();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCourseMngSystem(CourseMngSystem courseMngSystem, Course course) {
        this.courseMngSystem = courseMngSystem;
        this.course = course;
    }
    private void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-project-window.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage  = (Stage)cancelB.getScene().getWindow();
        //stage.setScene(scene);
        stage.close();

    }

}
