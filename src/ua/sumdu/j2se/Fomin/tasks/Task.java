package ua.sumdu.j2se.Fomin.tasks;

import java.time.Period;

import java.time.LocalDateTime;


public class Task implements Cloneable {
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private Period interval;
    private boolean active;

    //Constructor constructs an inactive task to run at a specified time without repeating with a given name.
    public Task(String title, LocalDateTime time) throws NullPointerException {
        if (time != null) {
            this.title = title;
            this.time = time;
        } else
            throw new NullPointerException("Parameter of  this method was  set incorrect");
    }

    /*
     * Constructor constructs an inactive task to run within the specified time range
     * (including the start and the end time) with the set repetition interval and with a given name.
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, Period interval) throws NullPointerException {
        if (start == null || end == null || interval == null)
            throw new NullPointerException("Parameters 'Start', 'End' and 'Interval' should not be a null!");
        else {
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //Methods for reading and changing execution time for non-repetitive tasks
    public LocalDateTime getTime() {
        return isRepeated() ? start : time;
    }

    public void setTime(LocalDateTime time) throws NullPointerException {
        if (time != null) {
            this.time = time;
            this.interval = null;
            this.start = null;
            this.end = null;
        } else
            throw new NullPointerException("Incorrect parameter was set.");
    }

    //Methods for reading and changing execution time for repetitive tasks
    public LocalDateTime getStartTime() {
        return isRepeated() ? start : time;
    }

    public LocalDateTime getEndTime() {
        return isRepeated() ? end : time;
    }

    public Period getRepeatInterval() {
        return interval;
    }

    //If the task is a non-repetitive one, it should become repetitive.
    public void setTime(LocalDateTime start, LocalDateTime end, Period interval) throws NullPointerException {
        if (start == null || end == null || interval == null)
            throw new NullPointerException("Parameters 'Start' and 'End' should not be a null!");
        else {
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.time = null;
        }
    }

    public boolean isRepeated() {
        return getRepeatInterval() != null;
    }

    /*
     *Method that returns the next start time of the task execution after the current time.
     *If after the specified time the task is not executed anymore, the method must return -1.
     */

    public LocalDateTime nextTimeAfter(LocalDateTime current) {

        //For non repeatable task:
        if (!isRepeated()) {
            if (time.isBefore(current) || time.isEqual(current)) {
                return null;
            } else {
                return time;
            }
        }
        //For  repeatable task:
        if (current.isAfter(end) || current.isEqual(end)) {
            return null;
        }
        if (current.isBefore(start)) {
            return start;
        }

        LocalDateTime count = start;

        LocalDateTime lastIteration = start;

        while (lastIteration.isBefore(end) || lastIteration.isEqual(end)) {
            lastIteration = lastIteration.plus(getRepeatInterval());
        }
        lastIteration = lastIteration.minus(interval);
        //Calculation last iteration with current
        while (count.isBefore(current) || count.isEqual(current)) {
            count = count.plus(getRepeatInterval());
        }
        if (current.isEqual(lastIteration) || current.isAfter(lastIteration) || count.isAfter(lastIteration)) {
            return null;
        }
        return count;
    }
    /*public LocalDateTime nextTimeAfter(LocalDateTime current) throws NullPointerException {
        if (isActive()) {

            if (current != null) {
                //for non repeatable task:
                if (!isRepeated()) {
                    if (time.isBefore(current) || time.isEqual(current)) {
                        return null;
                    } else {
                        return time;
                    }
                }
                //for  repeatable task:
                if (current.isAfter(end) || current.isEqual(end)) {
                    return null;
                }
                if (current.isBefore(start)) {
                    return start;
                }
                LocalDateTime count = start;
                LocalDateTime lastIteration = start;
                while (lastIteration.isBefore(end) || lastIteration.isEqual(end)) {
                    lastIteration = lastIteration.plus(getRepeatInterval());
                }
                lastIteration = lastIteration.minus(interval);
                //calculation last Iteration with current
                while (count.isBefore(current) || count.isEqual(current)) {
                    count = count.plus(getRepeatInterval());
                }
                if (current.isEqual(lastIteration) || current.isAfter(lastIteration) || count.isAfter(lastIteration)) {
                    return null;
                }
                return count;
            } else
                throw new NullPointerException("'Current' should not be a null.");
        } else
            return null;
    }*/


    @Override
    public boolean equals(Object o) throws IllegalArgumentException {
        if (this == o) return true;
        else if (getClass() != o.getClass()) return false;
        Task task = (Task) o;
        if (!(title.equals(task.title)) || interval != task.interval || active != task.active) return false;
        else if (time != task.time || start != task.start || end != task.end) return false;
        else return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (interval != null ? interval.hashCode() : 0);
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + (isRepeated() ? 1 : 0);
        return result;

    }

    @Override
    public String toString() {
        if (this.isRepeated()) {
            return new StringBuilder("This is a repeatable task with parameters: ").append("\nTitle: ").append(title)
                    .append("\nStart time: ").append(start).append("\nEnd time: ").append(end).append("\nInterval: ")
                    .append(interval).append("\nActive: ").append(isActive()).append("\n").toString();
        } else
            return new StringBuilder("This is a non-repeatable task with parameters: ").append("\nTitle: ")
                    .append(title).append("\nTime: ").append(time).append("\nActive: ").append(isActive())
                    .append("\n").toString();
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }

}

    /*{
                //For repetitive tasks
                if (isRepeated()) {
                    if (current.(start) && (current <= (end - start) / interval * interval + start)) {
                        if ((current) - start) / interval == 0)
                            //Then is (current-interval)/interval=1
                            return start + interval;
                            //If current is between first and second intervals;
                        else if (current > ((current - start) / interval * interval + start) && (current < ((current - start) / interval * interval + start + interval)))
                            return ((current - start) / interval) * interval + start + interval;
                        else
                            return ((current - start) / interval) * interval + start;
                    } else return current <= start ? start : null;
                }
                //For non-repetitive tasks
                else return current <= time ? time : null;
            }*/



