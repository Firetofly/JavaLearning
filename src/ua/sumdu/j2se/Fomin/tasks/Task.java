package ua.sumdu.j2se.Fomin.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    //Constructor constructs an inactive task to run at a specified time without repeating with a given name.
    public Task(String title, int time) throws IllegalArgumentException {
        if (time >= 0) {
            this.title = title;
            this.time = time;
        } else
            throw new IllegalArgumentException("Time should not be negative.");
    }

    /*
     * Constructor constructs an inactive task to run within the specified time range
     * (including the start and the end time) with the set repetition interval and with a given name.
     */
    public Task(String title, int start, int end, int interval) throws IllegalArgumentException {
        if (start >= 0 && end >= 0 && interval >= 0) {
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
        } else
            throw new IllegalArgumentException("StartTime,EndTime and Interval should not be negative.");

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
    public int getTime() {
        return isRepeated() ? start : time;
    }

    public void setTime(int time) throws IllegalArgumentException {
        if (time >= 0) {
            this.time = time;
            this.interval = 0;
            this.start = 0;
            this.end = 0;
        } else
            throw new IllegalArgumentException("Time should not be negative.");
    }

    //Methods for reading and changing execution time for repetitive tasks
    public int getStartTime() {
        return isRepeated() ? start : time;
    }

    public int getEndTime() {
        return isRepeated() ? end : time;
    }

    public int getRepeatInterval() {
        return interval;
    }

    //If the task is a non-repetitive one, it should become repetitive.
    public void setTime(int start, int end, int interval) throws IllegalArgumentException {
        if (start >= 0 && end >= 0 && interval >= 0) {
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.time = 0;
        } else
            throw new IllegalArgumentException("StartTime,EndTime and Interval should not be negative.");
    }

    public boolean isRepeated() {
        return getRepeatInterval() != 0;
    }

    /*
     *Method that returns the next start time of the task execution after the current time.
     *If after the specified time the task is not executed anymore, the method must return -1.
     */
    public int nextTimeAfter(int current) throws IllegalArgumentException {
        if (isActive()) {
            if (current >= 0) {
                //For repetitive tasks
                if (isRepeated()) {
                    if (current > start && (current <= (end - start) / interval * interval + start)) {
                        if ((current - start) / interval == 0)
                            //Then is (current-interval)/interval=1
                            return start + interval;
                            //If current is between first and second intervals;
                        else if (current > ((current - start) / interval * interval + start) && (current < ((current - start) / interval * interval + start + interval)))
                            return ((current - start) / interval) * interval + start + interval;
                        else
                            return ((current - start) / interval) * interval + start;
                    } else return current <= start ? start : -1;
                }
                //For non-repetitive tasks
                else return current <= time ? time : -1;
            } else
                throw new IllegalArgumentException("Current should not be negative.");
        } else
            return -1;
    }
}

