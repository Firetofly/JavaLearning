package ua.sumdu.j2se.Fomin.tasks;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ArrayTaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    /*
     * A method that removes a task from the list and returns true, if such a task was in the list.
     * If the list contains several tasks of the same type, any of them should be removed.
     */
    boolean remove(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
            return true;
        } else
            return false;
    }

    //A method that returns several tasks from the list
    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    ArrayTaskList incoming(int from, int to) {
        ArrayTaskList taskList = new ArrayTaskList();
        taskList.setTasks(tasks.stream().
                filter(task -> (task.getStartTime() >= from && task.getEndTime() <= to) || (task.getTime() >= from && task.getTime() <= to)).
                collect(Collectors.toCollection(ArrayList::new)));
        return taskList;
    }
}
