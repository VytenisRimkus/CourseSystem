package com.gui.coursesystem.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String position;
    private String email;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    @Enumerated
    private UserType userType;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> myCourses;
    @OneToMany(mappedBy = "creator", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> foldersCreated;
    @ManyToOne
    private CourseMngSystem courseMngSystem;

    public User(int id, String login, String password, String name, String lastName, String position, String email, LocalDate dateCreated, LocalDate dateModified, UserType userType, List<Course> myCourses, List<Folder> foldersCreated, CourseMngSystem courseMngSystem) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.userType = userType;
        this.myCourses = myCourses;
        this.foldersCreated = foldersCreated;
        this.courseMngSystem = courseMngSystem;
    }

    public User(String login, String password, String name, String lastName, String position, String email, UserType userType) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.userType = userType;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public User(String login, String password, String name, String lastName, String position, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.userType = UserType.ADMIN;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    public List<Folder> getFoldersCreated() {
        return foldersCreated;
    }

    public void setFoldersCreated(List<Folder> foldersCreated) {
        this.foldersCreated = foldersCreated;
    }

    public CourseMngSystem getCourseMngSystem() {
        return courseMngSystem;
    }

    public void setCourseMngSystem(CourseMngSystem courseMngSystem) {
        this.courseMngSystem = courseMngSystem;
    }

    public void removeCourse(Course course) {
        this.getMyCourses().remove(course);
    }
    public void addCourse(Course course){
        this.getMyCourses().add(course);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", userType=" + userType +
                '}';
    }
}
