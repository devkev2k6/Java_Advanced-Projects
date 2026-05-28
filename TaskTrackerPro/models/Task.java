package models;

public class Task {

    private static int counter = 1;

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;

    public Task(String title, String description, Priority priority) {
        this.id = counter++;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = Status.PENDING;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + priority + " | " + status;
    }
}
