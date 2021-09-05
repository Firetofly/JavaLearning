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

    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (index>0 && index<=this.index){
            return tasks[index];
        }
        else
            throw new IndexOutOfBoundsException("Index out of range.");

    }

    public void setTasks(Task[] tasks){
        this.tasks = tasks;
    }

    public void add(Task task) throws IllegalArgumentException{
        if (task != null) {
            if (index == tasks.length)
                increaseCapacity();
            tasks[index] = task;
            index++;
            size++;
        }
        else
            throw new IllegalArgumentException("Parameters of this method should not be NULL.");
    }

    protected void increaseCapacity(){
        Task[] newTasks = new Task[tasks.length + tasks.length>>1];
        System.arraycopy(tasks,0,newTasks,0,index-1);
        tasks=newTasks;
    }

    boolean remove(Task task) throws IllegalArgumentException{
        if(task != null){
            int taskIndex=indexOf(task);
            System.arraycopy(tasks,taskIndex+1,tasks,taskIndex,index-taskIndex);
            size--;
            this.index--;
            return true;
        }
        else
            throw new IllegalArgumentException("Parameters of this method should not be NULL.");
    }

    //If array does not contain an element, the method return -1
    public int indexOf(Task task) throws IllegalArgumentException {
        if(task != null) {
            int result = -1;
            for (int i = 0; i < size; i++) {
                if (tasks[i] == task) {
                    result = i;
                    break;
                }
            }
            return result;
        }
        else
            throw new IllegalArgumentException("Parameters of this method should not be NULL.");
    }

    public int size(){
        return size;
    }

    public ArrayTaskList incoming(int from, int to) throws IllegalArgumentException{
        if (from>0 && to>0) {
            ArrayTaskList taskList = new ArrayTaskList();
            taskList.setTasks(Arrays.stream(tasks).filter(Objects::nonNull).filter(task -> (task.getStartTime() >= from && task.getEndTime() <= to) || (task.getTime() >= from && task.getTime() <= to)).
                    toArray(Task[]::new));
            return taskList;
        }
        else
            throw new IllegalArgumentException("Arguments of this method should not be negative.");
    }
}
