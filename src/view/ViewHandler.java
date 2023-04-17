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
  private ViewController viewController;
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

  public void openView(ViewController viewController, String id)
  {
    Region root = null;
    switch (id)
    {
      case "projects":{
        root = loadViewController(viewController, viewModelFactory.getProjectsViewModel(), "ProjectsView.fxml");
        break;}
      case "tasks":{
        root = loadViewController(viewController, viewModelFactory.getTasksViewModel() ,"TasksView.fxml");
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
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      viewController.reset();
    }
    return viewController.getRoot();
  }
}
