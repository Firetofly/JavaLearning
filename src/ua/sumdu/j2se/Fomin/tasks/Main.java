package ua.sumdu.j2se.Fomin.tasks;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        //Test for task 1
//        try {
//            Task task = new Task("task1", 100, 150, 24);
//            task.setActive(true);
//            System.out.println("current: " + 90 + ", next: " + task.nextTimeAfter(90));
//            System.out.println("current: " + 100 + ", next: " + task.nextTimeAfter(100));
//            System.out.println("current: " + 110 + ", next: " + task.nextTimeAfter(110));
//            System.out.println("current: " + 120 + ", next: " + task.nextTimeAfter(120));
//            System.out.println("current: " + 124 + ", next: " + task.nextTimeAfter(124));
//            System.out.println("current: " + 130 + ", next: " + task.nextTimeAfter(130));
//            System.out.println("current: " + 140 + ", next: " + task.nextTimeAfter(140));
//            System.out.println("current: " + 148 + ", next: " + task.nextTimeAfter(148));
//            System.out.println("current: " + 149 + ", next: " + task.nextTimeAfter(149));
//            System.out.println("current: " + 150 + ", next: " + task.nextTimeAfter(150));
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }

        //Test for task 2
        ArrayTaskList tasksList= new ArrayTaskList();
        Task task1= new Task("task1",100,150,24);
        Task task2 = new Task("task2",105,130,22);
        tasksList.add(task1);
        tasksList.add(task2);
        try {
            System.out.println(tasksList.getTask(0));
        }
        catch (IndexOutOfBoundsException o){
            System.out.println("Exception message: "+o.getMessage());
            System.out.println("Cause: "+o.getCause());
        }


        for (int i = 0; i <tasksList.size() ; i++) {
            System.out.println("Start time: "+tasksList.getTask(i).getStartTime());
            System.out.println("End time: "+tasksList.getTask(i).getEndTime());
            System.out.println("Title: "+tasksList.getTask(i).getTitle()+"\n");
        }

        tasksList.remove(task1);
        tasksList.remove(task2);
        System.out.println(tasksList.size());
        System.out.println(tasksList.incoming(90,160));

        LinkedTaskList li= (LinkedTaskList) TaskListFactory.createTaskLIst(ListTypes.LINKED);
        System.out.println(li);
    }
}
