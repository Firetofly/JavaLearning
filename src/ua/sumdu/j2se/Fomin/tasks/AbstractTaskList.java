package ua.sumdu.j2se.Fomin.tasks;

import java.util.Iterator;
import java.util.Objects;


public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {

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

    public AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from >= 0 && to >= 0) {
            AbstractTaskList returnedTaskList = null;

            if (this instanceof ArrayTaskList) {
                returnedTaskList = new ArrayTaskList();
            } else if (this instanceof LinkedTaskList) {
                returnedTaskList = new LinkedTaskList();
            }
            for (int i = 0; i < size; i++) {
                if (((getTask(i).getStartTime() >= from && getTask(i).getEndTime() <= to) || (getTask(i).getTime() >= from && getTask(i).getTime() <= to)) && getTask(i) != null) {
                    assert returnedTaskList != null;
                    returnedTaskList.add(getTask(i));
                } else if (getTask(i).nextTimeAfter(from) >= from && getTask(i).nextTimeAfter(from) <= to) {
                    returnedTaskList.add(getTask(i));
                }
            }
            return returnedTaskList;
        } else throw new IllegalArgumentException("Parameters of this method should not be negative.");
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                return !(current == size);
            }

            @Override
            public Task next() {
                return getTask(current++);
            }

            @Override
            public void remove() {
                AbstractTaskList.this.remove(getTask(current));
            }
        };
    }

    public int hashCode() {
        int result = 17;
        for (int i = 0; i < this.size(); i++) {
            result += getTask(i).hashCode();
        }
        return 31*result;
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
    public AbstractTaskList clone() throws CloneNotSupportedException{
        return (AbstractTaskList) super.clone();
    }
}
