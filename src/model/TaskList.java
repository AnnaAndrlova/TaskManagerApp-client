package model;

import java.util.ArrayList;
import java.util.Objects;

public class TaskList {

    private ArrayList<Task> tasksList;

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public TaskList(){
        tasksList = new ArrayList<>();
    }

    public Task getTaskById(Long id){
        for (Task task: tasksList){
            if (Objects.equals(task.getId(), id)){
                return task;
            }
        }
        throw new RuntimeException("No task with such id found");
    }

    /**
     Replaces first task that has a same id by the task given as a parameter
     **/
    public void replaceTaskById(Task task){
        for (Task currentTask: tasksList){
            if (Objects.equals(currentTask.getId(), task.getId())){
                currentTask = task;
                return;
            }
        }
        throw new RuntimeException("No task with such id found");
    }

    public void addTask(Task task){
        tasksList.add(task);
    }



}