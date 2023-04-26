package viewmodel.ProjectView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import model.*;
import viewmodel.TaskView.TasksTable;
import viewmodel.ViewModel;
import viewmodel.ViewState;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectsViewModel implements ViewModel
{
    private Model model;
    private StringProperty titleProperty;
    private StringProperty descriptionProperty;
    private ProjectList projectList;
    private ObservableList<ProjectsTable> projectsObservableList;
    private ViewState viewState;
    public ProjectsViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        titleProperty = new SimpleStringProperty();
        descriptionProperty = new SimpleStringProperty();
        projectList = new ProjectList();
        projectsObservableList = FXCollections.observableArrayList();
        load();
    }

    public void load(){
        projectList = model.getAllProjectsByWorkingNumber(1);
        for (int i = 0; i < projectList.size(); i++) {
            projectsObservableList.add(new ProjectsTable(projectList.get(i)));
        }
        System.out.println(projectsObservableList.get(0));
    }

    public StringProperty getTitleProperty() {
        return titleProperty;
    }

    public ObservableList<ProjectsTable> getProjectsObservableList() {
        return projectsObservableList;
    }

    public void setProjectsObservableList(ObservableList<ProjectsTable> projectsObservableList) {
        this.projectsObservableList = projectsObservableList;
    }

    public StringProperty getDescriptionProperty() {
        return descriptionProperty;
    }

    public void setProject(Long id){
        Project project = projectList.getProjectByID(id);
        titleProperty.setValue(project.getName());
        descriptionProperty.setValue(project.getDescription());
        viewState.setProject(project);


    }
}