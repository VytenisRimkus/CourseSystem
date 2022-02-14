package com.gui.coursesystem.fxControllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CourseTableParameters {
    private SimpleIntegerProperty courseId = new SimpleIntegerProperty();
    private SimpleStringProperty courseName = new SimpleStringProperty();
    private SimpleStringProperty courseStartDate = new SimpleStringProperty();
    private SimpleStringProperty courseEndDate = new SimpleStringProperty();
    private SimpleBooleanProperty courseCompleted = new SimpleBooleanProperty();
    private SimpleStringProperty courseDesc = new SimpleStringProperty();

    public CourseTableParameters() {
    }

    public CourseTableParameters(SimpleIntegerProperty courseId, SimpleStringProperty courseName, SimpleStringProperty courseStartDate, SimpleStringProperty courseEndDate, SimpleBooleanProperty courseCompleted, SimpleStringProperty courseDesc) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseCompleted = courseCompleted;
        this.courseDesc = courseDesc;
    }

    public int getCourseId() {
        return courseId.get();
    }

    public SimpleIntegerProperty courseIdProperty() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId.set(courseId);
    }

    public String getCourseDesc() {
        return courseDesc.get();
    }

    public SimpleStringProperty courseDescProperty() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc.set(courseDesc);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public SimpleStringProperty courseNameProperty() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public String getCourseStartDate() {
        return courseStartDate.get();
    }

    public SimpleStringProperty courseStartDateProperty() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate.set(courseStartDate);
    }

    public String getCourseEndDate() {
        return courseEndDate.get();
    }

    public SimpleStringProperty courseEndDateProperty() {
        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate.set(courseEndDate);
    }

    public boolean isCourseCompleted() {
        return courseCompleted.get();
    }

    public SimpleBooleanProperty courseCompletedProperty() {
        return courseCompleted;
    }

    public void setCourseCompleted(boolean courseCompleted) {
        this.courseCompleted.set(courseCompleted);
    }
}
