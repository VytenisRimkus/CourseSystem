package com.gui.coursesystem.fxControllers;

import com.gui.coursesystem.StartGui;
import com.gui.coursesystem.ds.CourseMngSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.gui.coursesystem.hibernate.UserHibController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class RemoveUserForm implements Initializable {
    public Button cancelB;
    public TextField idF;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private CourseMngSystem courseMngSystem;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        courseMngSystem = RW.loadFromFile(OUT_FILE);
//        if (courseMngSystem == null) {
//
//            courseMngSystem = new CourseMngSystem(1,"1.1.", new ArrayList<User>(), new ArrayList<Course>());
//        }
    }

    public void deleteButton(ActionEvent actionEvent) throws IOException, SQLException {

        if (idF.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please fill in the missing data");
            alert.setTitle("Warning!");
            alert.showAndWait();
        } else{
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete this user?", yes, no);

            alert.setTitle("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(no) == yes){
                //System.out.println(userHibController.findUser(Integer.parseInt(idF.getText())));
                userHibController.removeUser(Integer.parseInt(idF.getText()));
                alertMessage("This user has been removed");
                returnToPrevious();
            }


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

    public void cancelButton(ActionEvent actionEvent) throws IOException {
        returnToPrevious();
    }
    private void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-project-window.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage  = (Stage)cancelB.getScene().getWindow();
        //stage.setScene(scene);
        stage.close();

    }
    public void setCourseMngSystem(CourseMngSystem courseMngSystem) {
        this.courseMngSystem = courseMngSystem;
    }
}
