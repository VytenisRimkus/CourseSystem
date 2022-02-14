package com.gui.coursesystem.fxControllers;

import com.gui.coursesystem.StartGui;
import com.gui.coursesystem.ds.Course;
import com.gui.coursesystem.ds.CourseMngSystem;
import com.gui.coursesystem.ds.User;
import com.gui.coursesystem.ds.UserType;
import com.gui.coursesystem.hibernate.CourseHibController;
import com.gui.coursesystem.hibernate.UserHibController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainProjectWindow implements Initializable {
    //public ListView myCourses;

    public TreeView subFolders;
    @FXML
    // public List userList;

    public TableView<UserTableParameters> userTable;
    public TableColumn<UserTableParameters, Integer> colId;
    public TableColumn<UserTableParameters, String> colLogin;
    public TableColumn<UserTableParameters, String> colName;
    public TableColumn<UserTableParameters, String> colSurname;
    public TableColumn<UserTableParameters, String> colPos;
    public TableColumn<UserTableParameters, String> colUserType;
    public TableColumn<UserTableParameters, String> colDateCreated;
    public TableColumn<UserTableParameters, String> colDateModified;
    public TableColumn<UserTableParameters, String> colEmail;

    public TableView<CourseTableParameters> courseTable;
    public TableColumn<CourseTableParameters, String> colCrsName;
    public TableColumn<CourseTableParameters, String> colStartDate;
    public TableColumn<CourseTableParameters, String> colEndDate;
    public TableColumn<CourseTableParameters, String> colCompleted;
    public TableColumn<CourseTableParameters, String> colDesc;
    public TableColumn<CourseTableParameters, Integer> colCrsId;

    public Button btnCrs;
    public Button btnDel;


    private User user;
    private Course course;
    private CourseMngSystem courseMngSystem;

    private User currentUser;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    UserHibController userHibController = new UserHibController(entityManagerFactory);
    CourseHibController courseHibController = new CourseHibController(entityManagerFactory);

    private ObservableList<UserTableParameters> userData = FXCollections.observableArrayList();
    private ObservableList<CourseTableParameters> courseData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCurrentUser(courseMngSystem, currentUser);
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("userLogin"));
        colLogin.setCellFactory(TextFieldTableCell.forTableColumn());
        colLogin.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setUserLogin(t.getNewValue());
                    user = userHibController.findUser(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUserId());
                    user.setLogin(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUserLogin());
                    user.setDateModified(LocalDate.now());
                    userHibController.editUser(user);
                }
        );

        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setUserName(t.getNewValue());
                    user = userHibController.findUser(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUserId());
                    user.setName(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUserName());
                    user.setDateModified(LocalDate.now());
                    userHibController.editUser(user);
                    try {
                        fillUsers();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );

        colSurname.setCellValueFactory(new PropertyValueFactory<>("userSurname"));
        colSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        colSurname.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setUserSurname(t.getNewValue());
                    user = userHibController.findUser(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUserId());
                    user.setLastName(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUserSurname());
                    user.setDateModified(LocalDate.now());
                    userHibController.editUser(user);
                    try {
                        fillUsers();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );

        colEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setUserEmail(t.getNewValue());
                    user = userHibController.findUser(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUserId());
                    user.setEmail(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUserEmail());
                    user.setDateModified(LocalDate.now());
                    userHibController.editUser(user);
                    try {
                        fillUsers();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );

        colPos.setCellValueFactory(new PropertyValueFactory<>("userPosition"));
        colPos.setCellFactory(TextFieldTableCell.forTableColumn());
        colPos.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setUserPosition(t.getNewValue());
                    user = userHibController.findUser(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUserId());
                    user.setPosition(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUserPosition());
                    user.setDateModified(LocalDate.now());
                    userHibController.editUser(user);
                    try {
                        fillUsers();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );

        colUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));

        colDateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        colDateModified.setCellValueFactory(new PropertyValueFactory<>("dateModified"));

        colCrsId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCrsId.setVisible(false);

        colCrsName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCrsName.setCellFactory(TextFieldTableCell.forTableColumn());
        colCrsName.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setCourseName(t.getNewValue());
                    course = courseHibController.findCourse(t.getTableView().getItems().get(t.getTablePosition().getRow()).getCourseId());
                    System.out.println(course);
                    course.setCourseName(t.getTableView().getItems().get(t.getTablePosition().getRow()).getCourseName());
                    courseHibController.edit(course);
                    try {
                        fillCourses();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );

        colStartDate.setCellValueFactory(new PropertyValueFactory<>("courseStartDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("courseEndDate"));
        colCompleted.setCellValueFactory(new PropertyValueFactory<>("courseCompleted"));

        colDesc.setCellValueFactory(new PropertyValueFactory<>("courseDesc"));
        colDesc.setCellFactory(TextFieldTableCell.forTableColumn());
        colDesc.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setCourseDesc(t.getNewValue());
                    course = courseHibController.findCourse(t.getTableView().getItems().get(t.getTablePosition().getRow()).getCourseId());
                    course.setCourseDescription(t.getTableView().getItems().get(t.getTablePosition().getRow()).getCourseDesc());
                    courseHibController.edit(course);
                    try {
                        fillCourses();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );


    }

    public void setCurrentUser(CourseMngSystem courseMngSystem, User user) {
        this.courseMngSystem = courseMngSystem;
        this.currentUser = user;
    }

    private static void alertMessage(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
//    public void saveToFile(ActionEvent actionEvent) {
//        RW.writeToFile(OUT_FILE, courseMngSystem);
//    }

    public void newUserForm(ActionEvent actionEvent) throws IOException {
        if (currentUser.getUserType() == UserType.ADMIN){
            FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("add-new-user-form.fxml"));

            Parent root = fxmlLoader.load();

            NewUserForm newUserForm = fxmlLoader.getController();


            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("New Course Form");
            stage.show();
        }else alertMessage("You are not authorized to perform this action", "Information dialog");

    }

    public void removeUser(ActionEvent actionEvent) throws IOException {
        UserTableParameters user;
        user = userTable.getSelectionModel().getSelectedItem();
        if (currentUser.getUserType() == UserType.ADMIN){
            if (user !=null){
                Boolean answer = yesOrNoDialog("Are you sure you want to delete this user?");
                if (answer){
                    userHibController.removeUser(user.getUserId());
                    alertMessage("This user has been removed", "Information dialog");
                }
            } else alertMessage("No user was selected", "Error");
        } else alertMessage("You are not authorized to perform this action", "Information dialog");

    }

    private Boolean yesOrNoDialog(String alertText) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.WARNING, alertText, yes, no);

        alert.setTitle("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.orElse(no) == yes){
            return true;
        } else return false;


    }


    private void fillCourses() throws SQLException {
        courseTable.getItems().clear();
        courseTable.setEditable(true);
        for (Course c: courseHibController.getCourseList()){
            for (User u: c.getResponsibleUsers()){
                if (u.getId() == currentUser.getId()){

                    CourseTableParameters courseTableParameters = new CourseTableParameters();
                    courseTableParameters.setCourseName(c.getCourseName());
                    courseTableParameters.setCourseStartDate(c.getStartDate().toString());
                    courseTableParameters.setCourseEndDate(c.getEndDate().toString());
                    courseTableParameters.setCourseCompleted(c.isCompleted());
                    courseTableParameters.setCourseDesc(c.getCourseDescription());
                    courseTableParameters.setCourseId(c.getId());
                    courseData.add(courseTableParameters);
                }

            }
        }
        courseTable.setItems(courseData);


    }

    public void newCourseForm(ActionEvent actionEvent) throws IOException {

        //System.out.println(currentUser);
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("new-course-form.fxml"));

        Parent root = fxmlLoader.load();

        NewCourseForm newCourseForm = fxmlLoader.getController();
        newCourseForm.setCurrentUser(courseMngSystem, currentUser);

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("New Course Form");
        stage.show();




    }

    public void userTab(Event event) throws SQLException {
        fillUsers();

    }

    public void fillUsers() throws SQLException {
        userTable.setEditable(true);
        userTable.getItems().clear();
        if (currentUser.getUserType().equals(UserType.ADMIN)){
            for(User u: userHibController.getUserList()) {
                UserTableParameters userTableParameters = new UserTableParameters();
                userTableParameters.setUserId(u.getId());
                userTableParameters.setUserLogin(u.getLogin());
                userTableParameters.setUserName(u.getName());
                userTableParameters.setUserSurname(u.getLastName());
                userTableParameters.setUserEmail(u.getEmail());
                userTableParameters.setUserPosition(u.getPosition());
                userTableParameters.setUserType(u.getUserType().toString());
                userTableParameters.setDateCreated(u.getDateCreated().toString());
                userTableParameters.setDateModified(u.getDateModified().toString());
                userData.add(userTableParameters);
            }
            userTable.setItems(userData);
        }

    }

    public void refreshUsers(ActionEvent actionEvent) throws SQLException {
        fillUsers();
    }

    public void logOut(ActionEvent actionEvent) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("login-window.fxml"));

        Parent root = fxmlLoader.load();

        LoginWindow mainProjectWindow = fxmlLoader.getController();

        mainProjectWindow.setCurrentUser(courseMngSystem, currentUser);

        //Scene scene = new Scene(root);

        Stage stage  = (Stage)btnCrs.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Course Management System");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();


    }

    public void deleteCourse(ActionEvent actionEvent) throws Exception {
        CourseTableParameters course = courseTable.getSelectionModel().getSelectedItem();
        if (course !=null){
            Boolean answer = yesOrNoDialog("Are you sure you want to delete this course?");
            if (answer){
                courseHibController.remove(course.getCourseId());
                alertMessage("This course has been removed", "Information dialog");
            }
        } else alertMessage("No course was selected", "Information dialog");



    }

    public void editCourse(ActionEvent actionEvent) throws IOException {

    }

    public void currentUser(ActionEvent actionEvent) throws SQLException {
        this.currentUser = currentUser;
        fillCourses();
    }

    public void saveToFile(ActionEvent actionEvent) {
    }

    public void addResponsibleUser(ActionEvent actionEvent) throws Exception {
        CourseTableParameters courseTableView = courseTable.getSelectionModel().getSelectedItem();

        if (courseTableView!=null){
            Course course = courseHibController.findCourse(courseTableView.getCourseId());
            FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("responsible-user-form.fxml"));

            Parent root = fxmlLoader.load();

            ResponsibleUserForm responsibleUserForm = fxmlLoader.getController();
            responsibleUserForm.setCourseMngSystem(courseMngSystem, course);

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Responsible User Form");
            stage.show();


        } else  alertMessage("No course is selected", "Information dialog");
    }

    public void removeResponsibleUser(ActionEvent actionEvent) throws IOException {
        CourseTableParameters courseTableView = courseTable.getSelectionModel().getSelectedItem();
        if (courseTableView!=null){
            Course course = courseHibController.findCourse(courseTableView.getCourseId());

            FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("remove-responsible-form.fxml"));

            Parent root = fxmlLoader.load();

            RemoveResponsibleForm removeResponsibleForm = fxmlLoader.getController();
            removeResponsibleForm.setCourseMngSystem(courseMngSystem, course);

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Responsible User Form");
            stage.show();
        }else  alertMessage("No course is selected", "Information dialog");

    }

    public void courseInfo(ActionEvent actionEvent) {
        CourseTableParameters courseTableView = courseTable.getSelectionModel().getSelectedItem();
        if (courseTableView!=null){
            Course course = courseHibController.findCourse(courseTableView.getCourseId());
            alertMessage("Course name: " + course.getCourseName() + "\n" +
                    "Course description: " + course.getCourseDescription() + "\n" +
                    "Course start date: " + course.getStartDate() + "\n" +
                    "Course end date: " + course.getEndDate() + "\n", "Course Information");
        } else  alertMessage("No course was selected", "Information dialog");

    }
}
