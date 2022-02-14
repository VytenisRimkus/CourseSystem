package com.gui.coursesystem.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String courseName;
    private String courseDescription;
    private LocalDate dateCreated;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean completed;

    @ManyToMany(mappedBy = "myCourses", cascade = {CascadeType.PERSIST})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> responsibleUser;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> courseFolders;

    @ManyToOne
    private CourseMngSystem courseMngSystem;

    public Course(int id, String courseName, String courseDescription, LocalDate dateCreated, LocalDate startDate, LocalDate endDate, boolean completed, List<User> responsibleUser, List<Folder> courseFolders) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.dateCreated = dateCreated;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completed = completed;
        this.responsibleUser = responsibleUser;
        this.courseFolders = courseFolders;
    }

    public Course(String courseName, String courseDescription, LocalDate startDate, LocalDate endDate, ArrayList<User> responsibleUser) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.dateCreated = LocalDate.now();
        this.startDate = startDate;
        this.endDate = endDate;
        this.completed = false;
        this.responsibleUser = responsibleUser;
        this.courseFolders = new ArrayList<Folder>();
    }

    public Course(String courseName, String courseDescription) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.dateCreated = LocalDate.now();
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
        this.completed = true;
    }

    public Course() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setResponsibleUser(List<User> responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public List<Folder> getCourseFolders() {
        return courseFolders;
    }

    public void setCourseFolders(ArrayList<Folder> courseFolders) {
        this.courseFolders = courseFolders;
    }

    public List<User> getResponsibleUsers() {
        return responsibleUser;
    }

    public void removeUser(User user) {
        this.getResponsibleUsers().remove(user);
    }

    public void addUser(User user) {
        this.getResponsibleUsers().add(user);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", dateCreated=" + dateCreated +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", completed=" + completed +
                '}';
    }
}
