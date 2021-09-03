package ua.sumdu.j2se.Fomin.tasks;


import java.util.Arrays;
import java.util.Objects;

public class ArrayTaskList {
    private Task[] tasks;
    private int size;
    private int index;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayTaskList() {
        tasks = new Task[DEFAULT_CAPACITY];
    }

    public ArrayTaskList(int capacity){
        tasks = new Task[capacity];
    }

    public Task getTask(int index){
        return tasks[index];
    }

    public void setTasks(Task[] tasks){
        this.tasks = tasks;
    }

    public void add(Task task){
        if (index==tasks.length)
            increaseCapacity();
        tasks[index]=task;
        index++;
        size++;
    }

    protected void increaseCapacity(){
        Task[] newTasks = new Task[tasks.length+10];
        System.arraycopy(tasks,0,newTasks,0,index-1);
        tasks=newTasks;
    }

    boolean remove(Task task){
        int taskIndex=indexOf(task);
        System.arraycopy(tasks,taskIndex+1,tasks,taskIndex,index-taskIndex);
        size--;
        this.index--;
        return true;
    }

    //If array does not contain an element, the method return -1
    public int indexOf(Task task){
        int result=-1;
        for (int i = 0; i <size ; i++) {
            if(tasks[i]==task){
                result=i;
                break;
            }
        }
        return result;
    }

    public int size(){

        return size;
    }

    public ArrayTaskList inner(int from, int to){
        ArrayTaskList taskList = new ArrayTaskList();
        taskList.setTasks(Arrays.stream(tasks).filter(Objects:: nonNull).filter(task -> (task.getStartTime()>= from && task.getEndTime() <= to) || (task.getTime() >= from && task.getTime() <= to)).
                toArray(Task[]::new));
        return taskList;
    }

    //
    public static void main(String[] args) {
        ArrayTaskList tasksList= new ArrayTaskList();
        Task task1= new Task("task1",100,150,24);
        Task task2 = new Task("task2",105,130,22);
        tasksList.add(task1);
        tasksList.add(task2);
        //System.out.println(task1.getStartTime());
        System.out.println(tasksList.inner(90,160));
    }
}
