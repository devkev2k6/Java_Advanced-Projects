import models.*;
import services.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AuthService authService = new AuthService();
        TaskService taskService = new TaskService();

        System.out.println("===== TASKTRACKER PRO =====");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        User user = authService.login(username);

        while (true) {

            System.out.println("\n1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Status");
            System.out.println("4. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Task title: ");
                    String title = sc.nextLine();

                    System.out.print("Task description: ");
                    String desc = sc.nextLine();

                    Task task = new Task(title, desc, Priority.HIGH);
                    taskService.addTask(task);
                    break;

                case 2:
                    taskService.showTasks();
                    break;

                case 3:
                    taskService.showTasks();

                    System.out.print("Enter task id: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    taskService.updateStatus(id, Status.COMPLETED);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
