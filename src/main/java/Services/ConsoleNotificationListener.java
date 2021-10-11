package Services;

import ua.eu.sumdu.j2se.Fomin.tasks.Task;


public class ConsoleNotificationListener implements EventListener {

    private String name;

    public String getName(){
        return name;
    }

    public ConsoleNotificationListener(String name) {
        this.name=name;
    }

    @Override
    public void createNotification(Task task, int size) {
        if (size == 0) System.out.println("Hello " + name + "! You don't have any tasks");
        else if (size == 1) System.out.println("Hello " + name + "! You have one task");
        else System.out.println("Hello " + name + "! You have " + size + " tasks");
    }
}
