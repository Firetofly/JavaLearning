package ua.sumdu.j2se.Fomin.tasks;

public class Main {
    public static void main(String[] args) {
        //Test for task 1
        /*Task task = new Task("task",100,150,24);

        System.out.println("current: " + 90 + ", next: " + task.nextTimeAfter(90));
        System.out.println("current: " + 100 + ", next: " + task.nextTimeAfter(100));
        System.out.println("current: " + 110 + ", next: " + task.nextTimeAfter(110));
        System.out.println("current: " + 120 + ", next: " + task.nextTimeAfter(120));
        System.out.println("current: " + 124 + ", next: " + task.nextTimeAfter(124));
        System.out.println("current: " + 130 + ", next: " + task.nextTimeAfter(130));
        System.out.println("current: " + 140 + ", next: " + task.nextTimeAfter(140));
        System.out.println("current: " + 148 + ", next: " + task.nextTimeAfter(148));
        System.out.println("current: " + 149 + ", next: " + task.nextTimeAfter(149));
        System.out.println("current: " + 150 + ", next: " + task.nextTimeAfter(150));*/

        //Test for task 2
        ArrayTaskList tasksList= new ArrayTaskList();
        Task task1= new Task("task1",100,150,24);
        Task task2 = new Task("task2",105,130,22);
        tasksList.add(task1);
        tasksList.add(task2);
        System.out.println(tasksList.getTask(0));
        tasksList.remove(task1);
        tasksList.remove(task2);
        System.out.println(tasksList.size());
        System.out.println(tasksList.inner(90,160));
    }
}
