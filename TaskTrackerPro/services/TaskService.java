package services;

import interfaces.Manageable;
import models.Status;
import models.Task;

import java.util.ArrayList;

public class TaskService implements Manageable {

    private ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public void addTask(Object obj) {
        tasks.add((Task) obj);
        System.out.println("Task added");
    }

    @Override
    public void showTasks() {

        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void updateStatus(int id, Status status) {

        for (Task task : tasks) {

            if (task.getId() == id) {
                task.setStatus(status);
                System.out.println("Status updated");
                return;
            }
        }

        System.out.println("Task not found");
    }
}
