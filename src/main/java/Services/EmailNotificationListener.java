package Services;


import ua.eu.sumdu.j2se.Fomin.tasks.Task;

public class EmailNotificationListener implements EventListener {

    private String email;
    private String text;

    public EmailNotificationListener(String email) {
        this.email = email;
    }


    @Override
    public void createNotification(Task task, int size) {

            if (size== 0) text = "Hello ! You don't have any tasks";
            else if (size == 1) text = "Hello ! You have one task";
            else text = "Hello ! You have " + size + " tasks";
            EmailSender.sendEmail(text, email);

    }
}
