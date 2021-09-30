package ua.sumdu.j2se.Fomin.tasks;


import java.util.Iterator;

public class ArrayTaskList extends AbstractTaskList implements Iterable {
    private Task[] tasks;
    private int index;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayTaskList() {
        tasks = new Task[DEFAULT_CAPACITY];
    }

    public ArrayTaskList(int capacity) {
        tasks = new Task[capacity];
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task != null) {
            if (index == tasks.length)
                increaseCapacity();
            tasks[index] = task;
            index++;
            increaseSize();

        } else
            throw new IllegalArgumentException("Parameters of this method should not be NULL.");
    }

    protected void increaseCapacity() {
        Task[] newTasks = new Task[tasks.length + tasks.length >> 1];
        System.arraycopy(tasks, 0, newTasks, 0, index - 1);
        tasks = newTasks;
    }

    @Override
    public boolean remove(Task task) throws IllegalArgumentException {
        if (task != null) {
            int taskIndex = indexOf(task);
            System.arraycopy(tasks, taskIndex + 1, tasks, taskIndex, index - taskIndex);
            decreaseSize();
            this.index--;
            return true;
        } else
            throw new IllegalArgumentException("Parameters of this method should not be NULL.");
    }

    //If array does not contain an element, the method return -1
    public int indexOf(Task task) throws IllegalArgumentException {
        if (task != null) {
            int result = -1;
            for (int i = 0; i < size(); i++) {
                if (tasks[i] == task) {
                    result = i;
                    break;
                }
            }
            return result;
        } else
            throw new IllegalArgumentException("Parameters of this method should not be NULL.");
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index <= this.index) {
            return tasks[index];
        } else
            throw new IndexOutOfBoundsException("Index out of range.");

    }
    @Override
    public Iterator<Task> iterator(){
        return new Iterator<Task>() {
            int counter=0;
            @Override
            public boolean hasNext() {
                return !(counter==size());
            }

            @Override
            public Task next() {
                return getTask(counter++);
            }

            @Override
            public void remove(){
                ArrayTaskList.this.remove(getTask(counter));
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        ArrayTaskList taskList = (ArrayTaskList) obj;
        if (this.size() != taskList.size()) return false;
        for (int i = 0; i < this.size(); i++) {
            if (!(this.getTask(i).equals(taskList.getTask(i)))) {
                return false;
            } else result = true;
        }
        return result;
    }
}
