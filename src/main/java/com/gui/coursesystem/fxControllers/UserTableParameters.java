package com.gui.coursesystem.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserTableParameters {

    private SimpleIntegerProperty userId = new SimpleIntegerProperty();
    private SimpleStringProperty userLogin = new SimpleStringProperty();
    private SimpleStringProperty userName = new SimpleStringProperty();
    private SimpleStringProperty userSurname = new SimpleStringProperty();
    private SimpleStringProperty userEmail = new SimpleStringProperty();
    private SimpleStringProperty userPosition = new SimpleStringProperty();
    private SimpleStringProperty userType = new SimpleStringProperty();
    private SimpleStringProperty dateCreated = new SimpleStringProperty();
    private SimpleStringProperty dateModified = new SimpleStringProperty();

    public UserTableParameters() {
    }

    public UserTableParameters(SimpleIntegerProperty userId, SimpleStringProperty userLogin, SimpleStringProperty userName, SimpleStringProperty userSurname, SimpleStringProperty userEmail, SimpleStringProperty userPosition, SimpleStringProperty userType, SimpleStringProperty dateCreated, SimpleStringProperty dateModified) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPosition = userPosition;
        this.userType = userType;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public int getUserId() {
        return userId.get();
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getUserLogin() {
        return userLogin.get();
    }

    public SimpleStringProperty userLoginProperty() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin.set(userLogin);
    }

    public String getUserSurname() {
        return userSurname.get();
    }

    public SimpleStringProperty userSurnameProperty() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname.set(userSurname);
    }

    public String getUserEmail() {
        return userEmail.get();
    }

    public SimpleStringProperty userEmailProperty() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail.set(userEmail);
    }

    public String getUserPosition() {
        return userPosition.get();
    }

    public SimpleStringProperty userPositionProperty() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition.set(userPosition);
    }

    public String getUserType() {
        return userType.get();
    }

    public SimpleStringProperty userTypeProperty() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType.set(userType);
    }

    public String getDateCreated() {
        return dateCreated.get();
    }

    public SimpleStringProperty dateCreatedProperty() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated.set(dateCreated);
    }

    public String getDateModified() {
        return dateModified.get();
    }

    public SimpleStringProperty dateModifiedProperty() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified.set(dateModified);
    }

    @Override
    public String toString() {
        return "UserTableParameters{" +
                "userId=" + userId +
                ", userLogin=" + userLogin +
                ", userName=" + userName +
                ", userSurname=" + userSurname +
                ", userEmail=" + userEmail +
                ", userPosition=" + userPosition +
                ", userType=" + userType +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                '}';
    }
}
