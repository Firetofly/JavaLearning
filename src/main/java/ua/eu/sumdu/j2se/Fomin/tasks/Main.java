package ua.eu.sumdu.j2se.Fomin.tasks;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException, IOException {
        //Test for task 1
      /*  try {
            Task task = new Task("task1", 100, 150, 24);
            task.setActive(true);
            System.out.println("current: " + 90 + ", next: " + task.nextTimeAfter(90));
            System.out.println("current: " + 100 + ", next: " + task.nextTimeAfter(100));
            System.out.println("current: " + 110 + ", next: " + task.nextTimeAfter(110));
            System.out.println("current: " + 120 + ", next: " + task.nextTimeAfter(120));
            System.out.println("current: " + 124 + ", next: " + task.nextTimeAfter(124));
            System.out.println("current: " + 130 + ", next: " + task.nextTimeAfter(130));
            System.out.println("current: " + 140 + ", next: " + task.nextTimeAfter(140));
            System.out.println("current: " + 148 + ", next: " + task.nextTimeAfter(148));
            System.out.println("current: " + 149 + ", next: " + task.nextTimeAfter(149));
            System.out.println("current: " + 150 + ", next: " + task.nextTimeAfter(150));
        } catch (IllegalArgumentException e) {
            System.out.println("Exception message: "+e.getMessage());
        }*/

//        //Test for task 2
        //ArrayTaskList tasksList= new ArrayTaskList();
        //Task task1 = new Task("task1", 100, 150, 24);
        //Task task2 = new Task("task2", 105, 130, 22);
        /*try {
            tasksList.add(task1);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception message: "+e.getMessage());
            System.out.println("Cause: "+e.getCause());
        }
        try {
            tasksList.add(task2);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception message: "+e.getMessage());
            System.out.println("Cause: "+e.getCause());
        }
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

        try {
            tasksList.remove(task1);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception message: "+e.getMessage());
            System.out.println("Cause: "+e.getCause());
        }
        try {
            tasksList.remove(task2);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception message: "+e.getMessage());
            System.out.println("Cause: "+e.getCause());
        }
        System.out.println(tasksList.size());
        try {
            System.out.println(tasksList.incoming(90,160));
        } catch (IllegalArgumentException e) {
            System.out.println("Exception message: "+e.getMessage());
            System.out.println("Cause: "+e.getCause());
        }

        try {
            LinkedTaskList li= (LinkedTaskList) TaskListFactory.createTaskLIst(ListTypes.LINKED);
            System.out.println(li);
        }
        catch(IllegalArgumentException e){
            System.out.println("Exception message: "+e.getMessage());
            System.out.println("Cause: "+e.getCause());
        }

        */
/*        try {
            AbstractTaskList taskTest = TaskListFactory.createTaskLIst(ListTypes.ARRAY);
            taskTest.add(task1);
            taskTest.add(task2);
            System.out.println(taskTest);
            System.out.println("Task #0 hashCode: " + taskTest.getTask(0).hashCode());
            System.out.println("Task #1 hashCode: " + taskTest.getTask(1).hashCode());
            System.out.println("Sum of tasks 0 and 1: " + (taskTest.getTask(0)
                    .hashCode() + taskTest.getTask(1).hashCode()));
            System.out.println("HashCode of taskTest contains task 0 and task 1: "+taskTest.hashCode());
        } catch (IllegalArgumentException e) {
            System.out.println("Exception message: " + e.getMessage());
            System.out.println("Cause: " + e.getCause());
        }

    } */    //Test toString() method
/*        Task test = new Task("Train", 12, 24, 72);
        System.out.println(test);*/
        /*System.out.println("------------");
        System.out.println(test.toString());
        System.out.println("------------");
        Task test1 = test.clone();
        System.out.println(test1.toString());
*/
        /*
        Task task0 = new Task("Task0", LocalDateTime.of(2021,Month.SEPTEMBER,3,10,00));
        Task task3 = new Task("Task3",LocalDateTime.of(2021,Month.SEPTEMBER,30,21,43));
        Task task4 = new Task("Task4",LocalDateTime.of(2021, Month.AUGUST, 3, 14, 15)
                , LocalDateTime.of(2021, Month.AUGUST, 29, 14, 15), Period.ofDays(5));
        AbstractTaskList testclone0 = TaskListFactory.createTaskLIst(ListTypes.ARRAY);
        AbstractTaskList testclone1 = TaskListFactory.createTaskLIst(ListTypes.ARRAY);
        testclone0.add(task0);
        testclone0.add(task3);
        testclone0.add(task4);
        testclone1=testclone0.clone();
        System.out.println(testclone0);
        System.out.println("---------");
        System.out.println("Cloned task:");
        System.out.println("---------");
        System.out.println(testclone1);
    }*/

        //Test's for nonRepTasks:
/*
        AbstractTaskList taskList = TaskListFactory.createTaskLIst(ListTypes.LINKED);

        Task nonRep1 = new Task("nonRep1", LocalDateTime.now());
        Task nonRep2 = new Task("nonRep2", LocalDateTime.now());
        Thread.sleep(2000);
        Task nonRep3 = new Task("nonRep3", LocalDateTime.now());
        Task nonRep4 = new Task("nonRep4", LocalDateTime.now());
        taskList.add(nonRep1);
        taskList.add(nonRep2);
        taskList.add(nonRep3);
        taskList.add(nonRep4);
        Thread.sleep(2000);
        TreeMap<LocalDateTime, Set<Task>> sortedMap = (TreeMap) Tasks.calendar(taskList, LocalDateTime.now().minusDays(1), LocalDateTime.now())*/
        ;

        //Test's for RepTasks at Work7:

        //AbstractTaskList taskList = TaskListFactory.createTaskLIst(ListTypes.ARRAY);

        /*Task repetableTask1 = new Task("testtasik1!", LocalDateTime
                .of(2021, Month.AUGUST, 3,
                14, 15), LocalDateTime.of(2021, Month.AUGUST, 29, 14, 15,56),
                Period.ofDays(5));

        Task repetableTask2 = new Task("testtasik2!", LocalDateTime
                .of(2021, Month.AUGUST, 3,
                14, 15), LocalDateTime.of(2021, Month.AUGUST, 29, 14, 15,56),
                Period.ofDays(5));
        Task nonrepetableTask1 = new Task("titleNon", LocalDateTime
                .of(2022, Month.AUGUST, 1, 14, 15));

        taskList.add(repetableTask1);
        taskList.add(repetableTask2);*/

        /*DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy.MM.dd HH:mm:ss", Locale.ROOT);

        System.out.println(taskList.getTask(0).getStartTime().format(formatter).toString());*/
       // System.out.println(taskList.getTask(0).getRepeatInterval().toString());

       /*TreeMap<LocalDateTime, Set<Task>> sortedMap = (TreeMap) Tasks
                .calendar(taskList, LocalDateTime.now().minusMonths(3), LocalDateTime.now());

        for (Map.Entry<LocalDateTime, Set<Task>> entry : sortedMap.entrySet()) {
            System.out.println("\nKEY: " + entry.getKey() + ". \nVALUE: " + entry.getValue());
        }*/
 /*      try {
            File testJsonFile = new File("C:\\testjava\\testJson.txt");
            File testFile=new File("C:\\testjava\\test.txt");
            TaskIO.writeBinary(taskList, testFile);
            TaskIO.writeText(taskList, testJsonFile);
            //System.out.println(taskList);

            AbstractTaskList input1 = TaskListFactory.createTaskLIst(ListTypes.ARRAY);
            AbstractTaskList input2 = TaskListFactory.createTaskLIst(ListTypes.ARRAY);

            TaskIO.readBinary(input1, testFile);
            TaskIO.readText(input2, testJsonFile);

            System.out.println("Input1: \n"+input1);
            System.out.println("Input2: \n"+input2);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
*/
/*        taskList.events.subscribe("add",new ConsoleNotificationListener("User_Name"));
        taskList.events.subscribe("remove",new ConsoleNotificationListener("User_Name"));

        taskList.add(repetableTask1);
        taskList.remove(repetableTask1);*/
       Emulator emulator = new Emulator();

        try {
            emulator.startApplication();
        } catch (IOException e) {
            emulator.logger.error("Exception in main met", e);
        }





    }
}
