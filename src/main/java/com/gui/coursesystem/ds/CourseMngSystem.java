package com.gui.coursesystem.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class CourseMngSystem implements Serializable {
    @Id
    private int id;
    private String version;
    private String name;
    @OneToMany(mappedBy = "courseMngSystem", cascade= CascadeType.MERGE, orphanRemoval=true)
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> allSysUsers;
    @OneToMany(mappedBy = "courseMngSystem", cascade= CascadeType.MERGE, orphanRemoval=true)
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> allSysCourses;

    public CourseMngSystem(int id, String version, List<User> allSysUsers, List<Course> allSysCourses) {
        this.id = id;
        this.version = version;
        this.allSysUsers = allSysUsers;
        this.allSysCourses = allSysCourses;
    }

    public CourseMngSystem(String version, String name, List<User> allSysUsers, List<Course> allSysCourses) {
        this.version = version;
        this.name = name;
        this.allSysUsers = allSysUsers;
        this.allSysCourses = allSysCourses;
    }

    public CourseMngSystem() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<User> getAllSysUsers() {
        return allSysUsers;
    }

    public void setAllSysUsers(List<User> allSysUsers) {
        this.allSysUsers = allSysUsers;
    }

    public List<Course> getAllSysCourses() {
        return allSysCourses;
    }

    public void setAllSysCourses(List<Course> allSysCourses) {
        this.allSysCourses = allSysCourses;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return "CourseMngSystem{" +
                "version='" + version + '\'' +
                ", allSysUsers=" + allSysUsers +
                ", allSysCourses=" + allSysCourses +
                '}';
    }
}
