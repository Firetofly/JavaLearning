package ua.eu.sumdu.j2se.Fomin.tasks;

import Services.EventManager;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Cloneable,Iterable<Task>, Serializable {

    private int size;

    protected EventManager events;

    public abstract boolean remove(Task task);

    protected abstract void add(Task task);

    public abstract Task getTask(int index);

    public void increaseSize() {
        size++;
    }

    public void decreaseSize() throws IllegalArgumentException {
        if (size > 0) {
            size--;
        } else {
            throw new IllegalArgumentException("Size should not be negative");
        }
    }

    public int size() {
        return size;
    }

    public int hashCode() {
        int result = 17;
        for (int i = 0; i < this.size(); i++) {
            result = 31 * result + getTask(i).hashCode();
        }
        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        if (this instanceof ArrayTaskList)
            result.append("The number of tasks: "+size+" following tasks:\n");
        else
            result.append("The number of tasks: "+size+" following tasks:\n");
        for (int i = 0; i < this.size(); i++) {
            result.append("Task #").append(i).append("\n").append(getTask(i).toString()).append("\n");
        }
        return result.toString();
    }

    public AbstractTaskList clone() throws CloneNotSupportedException {
        if (this instanceof ArrayTaskList) return (ArrayTaskList) super.clone();
        if (this instanceof LinkedTaskList) return (LinkedTaskList) super.clone();

        return null;
    }

    public Stream<Task> getStream() {
        Stream.Builder<Task> builder = Stream.builder();
        Stream<Task> streamList = null;
        for (int i = 0; i < this.size(); i++) {
            streamList = builder.add(this.getTask(i)).build();
        }
        return streamList;
    }

    public void setEvents(String... operations){
        events = new EventManager(operations);
    }


}

