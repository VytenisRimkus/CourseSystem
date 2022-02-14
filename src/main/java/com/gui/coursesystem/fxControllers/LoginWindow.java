package com.gui.coursesystem.fxControllers;

import com.gui.coursesystem.StartGui;
import com.gui.coursesystem.ds.Course;
import com.gui.coursesystem.ds.CourseMngSystem;
import com.gui.coursesystem.ds.User;
import com.gui.coursesystem.hibernate.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginWindow implements Initializable {
    @FXML
    public TextField loginF;
    @FXML
    public PasswordField passwordF;
    @FXML
    public Button btnLogin;

    private CourseMngSystem courseMngSystem = new CourseMngSystem("1.1","Amazing", new ArrayList<User>(), new ArrayList<Course>());

    private User currentUser;


    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    UserHibController userHibController = new UserHibController(entityManagerFactory);


    public void validateAndLoad(ActionEvent actionEvent) throws Exception {



        User user = userHibController.getUserList().stream().filter(c->c.getLogin().equals(
                loginF.getText())).filter(c->c.getPassword().equals(
                        passwordF.getText())).findFirst().orElse(null);
        if (user != null){

            currentUser = user;
            //System.out.println(currentUser);

            FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-project-window.fxml"));

            Parent root = fxmlLoader.load();

            MainProjectWindow mainProjectWindow = fxmlLoader.getController();
            mainProjectWindow.setCurrentUser(courseMngSystem, currentUser);

            //Scene scene = new Scene(root);

            Stage stage  = (Stage)loginF.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Course Management System");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } else{
            alertMessage("Wrong  input data, no such user found");
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

    public void openNewUserForm(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("sign-up-form.fxml"));

        Parent root = fxmlLoader.load();

        SignUpForm signUpForm = fxmlLoader.getController();
        signUpForm.setCourseMngSystem(courseMngSystem);

        Scene scene = new Scene(root);

        Stage stage = (Stage) loginF.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("New User Form");
        stage.show();
    }

    public void setCourseMngSystem(CourseMngSystem courseMngSystem) {this.courseMngSystem = courseMngSystem;}

    public void loadMainWindow() throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-project-window.fxml"));

        Parent root = fxmlLoader.load();

        MainProjectWindow mainProjectWindow = fxmlLoader.getController();
        mainProjectWindow.setCurrentUser(courseMngSystem, currentUser);

        //Scene scene = new Scene(root);

        Stage stage  = (Stage)loginF.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Course Management System");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //courseMngSystem = RW.loadFromFile(OUT_FILE);

        if (courseMngSystem == null) {

            courseMngSystem = new CourseMngSystem(1,"1.1.", new ArrayList<User>(), new ArrayList<Course>());
        }
        //Person person = new Person("Vytenis", "123", UserType.ADMIN, "Vytenis", "Rimkus", "Adminas", "vytenis100@gmail.com");
        //courseMngSystem.getAllSysUsers().add(person);


    }

    public void setCurrentUser(CourseMngSystem courseMngSystem, User currentUser) {
        this.currentUser = null;
        this.courseMngSystem = courseMngSystem;
    }
}
