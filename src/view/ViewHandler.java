package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModel;
import viewmodel.ViewModelFactory;

import javax.swing.text.View;

public class ViewHandler
{
  private Scene currentScene;
  private Stage primaryStage;
  private ViewModelFactory viewModelFactory;
  private ProjectsViewController projectsViewController;
  private TasksViewController tasksViewController;
  private AddTaskViewController addTaskViewController;
  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("projects");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "projects":{
        root = loadViewController(projectsViewController, viewModelFactory.getProjectsViewModel(), "ProjectsView.fxml");
        break;}
      case "tasks":{
        root = loadViewController(tasksViewController, viewModelFactory.getTasksViewModel() ,"TasksView.fxml");
        break;}
      case "addTask":{
        root = loadViewController(addTaskViewController, viewModelFactory.getAddTaskViewModel() ,"AddTaskToProjectView.fxml");
        break;}
    }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }
  private Region loadViewController(ViewController viewController, ViewModel viewModel, String fxmlFile)
  {
    Region root = null;
    if (viewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        viewController = loader.getController();
        viewController.init(this,viewModel,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      viewController.reset();
      System.out.println("reset");
    }
    return viewController.getRoot();
  }
}
