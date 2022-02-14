package com.gui.coursesystem.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Folder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String folderName;
    private LocalDate dateCreated;
    private LocalDate lastModified;
    private LocalDate deploymentDate;
    @OneToMany(mappedBy = "parentFolder", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> subFolders;
    @ManyToOne
    private Folder parentFolder;
    @ManyToOne
    private User responsiblePerson;
    @ManyToOne
    private User creator;
    @ManyToOne
    private Course course;


    public Folder(int id, String folderName, User responsiblePerson, User creator, LocalDate dateCreated, LocalDate lastModified, LocalDate deploymentDate, List<Folder> subFolders, Folder parentFolder, Course course) {
        this.id = id;
        this.folderName = folderName;
        this.responsiblePerson = responsiblePerson;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.deploymentDate = deploymentDate;
        this.subFolders = subFolders;
        this.parentFolder = parentFolder;
        this.course = course;
        //this.folderFiles = folderFiles;
    }

    public Folder(String folderName, User responsiblePerson, User creator) {
        this.folderName = folderName;
        this.responsiblePerson = responsiblePerson;
        this.creator = creator;
        this.dateCreated = LocalDate.now();
    }

    public Folder() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public User getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(User responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public LocalDate getDeploymentDate() {
        return deploymentDate;
    }

    public void setDeploymentDate(LocalDate deploymentDate) {
        this.deploymentDate = deploymentDate;
    }

    public List<Folder> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(List<Folder> subFolders) {
        this.subFolders = subFolders;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

//    public List<File> getFolderFiles() {
//        return folderFiles;
//    }
//
//    public void setFolderFiles(List<File> folderFiles) {
//        this.folderFiles = folderFiles;
//    }
}
