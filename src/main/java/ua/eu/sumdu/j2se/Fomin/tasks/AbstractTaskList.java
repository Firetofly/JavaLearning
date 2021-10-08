package ua.eu.sumdu.j2se.Fomin.tasks;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Cloneable,Iterable<Task>, Serializable {

    private int size;

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

   /* public AbstractTaskList incoming(Date from, Date to) throws IllegalArgumentException, NullPointerException {
        if (from == null && to == null) throw new NullPointerException("Parameters was set null!");
        if (to.before(from) || from.before(to) || from.getTime() < 0 || to.getTime() < 0)
            throw new IllegalArgumentException("Incorrect of method parameters was set!");

        AbstractTaskList returnedTaskList = null;

        if (this instanceof ArrayTaskList) {
            returnedTaskList = new ArrayTaskList();
        } else if (this instanceof LinkedTaskList) {
            returnedTaskList = new LinkedTaskList();
        }
        for (int i = 0; i < size; i++) {
            if (((getTask(i).getStartTime().getTime() >= from.getTime() && getTask(i).getEndTime().getTime() <= to
                    .getTime()) || (getTask(i).getTime().getTime() >= from.getTime() && getTask(i).getTime()
                    .getTime() <= to.getTime())) && getTask(i) != null) {
                assert returnedTaskList != null;
                returnedTaskList.add(getTask(i));

            } else if (getTask(i).nextTimeAfter(from).getTime() >= from.getTime() && getTask(i).nextTimeAfter(from)
                    .getTime() <= to.getTime()) {
                returnedTaskList.add(getTask(i));
            }
        }
        return returnedTaskList;
    }*/

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
            result.append("This is ArrayTaskList contains following tasks:\n");
        else
            result.append("This is LinkedTaskList contains following tasks:\n");
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



/*    final public AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) throws IllegalArgumentException, NullPointerException {
        if (from == null && to == null) throw new NullPointerException("Parameters was set null!");
        if (to.isBefore(from) || from.isBefore(to))
            throw new IllegalArgumentException("Incorrect of method parameters was set!");

        AbstractTaskList result = null;
        if (this instanceof ArrayTaskList) result = new ArrayTaskList();
        if (this instanceof LinkedTaskList) result = new LinkedTaskList();

        this.getStream().filter(Objects::nonNull).filter(task -> (task.getStartTime().equals(from) || task
                .getStartTime().isAfter(from) && task.getEndTime().equals(to) || task.getEndTime()
                .isBefore(to)) || (task.getTime().equals(from) || task.getTime().isAfter(from) && task
                .getTime().isBefore(to) || task.getTime().equals(to)) &&(task.nextTimeAfter(from).equals(from) || task
                .nextTimeAfter(from).isAfter(from) && task.nextTimeAfter(from).equals(to) || task
                .nextTimeAfter(from).isBefore(to))).forEach(result::add);

        *//*assert result != null;
        this.getStream().filter(Objects::nonNull).filter(task -> (task.getStartTime() >= from && task
                .getEndTime() <= to.getTime()) || (task
                .getTime() >= from.getTime() && task. <= to) || (task.nextTimeAfter(from) >= from && task
                .nextTimeAfter(from) <= to.getTime())).forEach(result::add);*//*
        return result;
    }*/
}

