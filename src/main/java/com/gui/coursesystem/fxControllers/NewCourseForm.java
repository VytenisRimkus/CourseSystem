package com.gui.coursesystem.fxControllers;

import com.gui.coursesystem.StartGui;
import com.gui.coursesystem.ds.Course;
import com.gui.coursesystem.ds.CourseMngSystem;
import com.gui.coursesystem.ds.User;
import com.gui.coursesystem.hibernate.CourseHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewCourseForm implements Initializable {
    public TextField crsId;
    public TextField crsTitle;
    public TextArea crsDesc;
    public DatePicker crsStartDate;
    public Button btnCancel;
    private String login;
    public DatePicker crsEndDate;


    private User currentUser;
    private CourseMngSystem courseMngSystem;



    private Connection connection;
    private PreparedStatement preparedStatement;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    CourseHibController courseHibController = new CourseHibController(entityManagerFactory);

    public void setCurrentUser(CourseMngSystem courseMngSystem, User user){
        this.courseMngSystem = courseMngSystem;
        this.currentUser = user;
    }
    public void createCourse(ActionEvent actionEvent) throws Exception {
        ArrayList<User> responsible = new ArrayList<>();

        Course course = new Course(crsTitle.getText(), crsDesc.getText(), crsStartDate.getValue(), crsEndDate.getValue(), new ArrayList<>());

        courseHibController.createCourse(course);
        courseHibController.AddResponsibleUserToCourse(course, currentUser);

        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-project-window.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage  = (Stage)crsTitle.getScene().getWindow();
        //stage.setScene(scene);
        stage.close();
    }
    public void setCourseMngSystem(CourseMngSystem courseMngSystem) {
        this.courseMngSystem = courseMngSystem;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        courseMngSystem = RW.loadFromFile(OUT_FILE);
//        if (courseMngSystem == null) {
//
//            courseMngSystem = new CourseMngSystem(1, "1.1.", new ArrayList<User>(), new ArrayList<Course>());
//        }
    }

    public void cancelBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-project-window.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage  = (Stage)btnCancel.getScene().getWindow();
        //stage.setScene(scene);
        stage.close();

    }

}
