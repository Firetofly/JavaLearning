package ua.sumdu.j2se.Fomin.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active=active;
    }
    //Methods for reading and changing execution time for non-repetitive tasks
    public int getTime(){
        return time;
    }

    public void setTime(int time){
        this.time=time;
    }

    //Methods for reading and changing execution time for repetitive tasks
    public int getStartTime(){
        return start;
    }

    public int getEndTime(){
        return end;
    }

    public int getRepeatInterval(){
        return interval;
    }

    public void setTime(int start, int end, int interval){
        this.start=start;
        this.end=end;
        this.interval=interval;
    }

    //Constructor constructs an inactive task to run at a specified time without repeating with a given name.
    public Task(String title, int time){
        this.title=title;
        this.time=time;
    }
    /*
     * constructor constructs an inactive task to run within the specified time range
     * (including the start and the end time) with the set repetition interval and with a given name.
     */
    public Task (String title, int start, int end, int interval){
        this.title=title;
        this.start=start;
        this.end=end;
        this.interval=interval;
    }

    public boolean isRepeated(){
        return getRepeatInterval() != 0;
    }

    public int nextTimeAfter(int current){
        return isRepeated()
                ?current<getStartTime()
                ?getStartTime()
                :getEndTime()+getRepeatInterval()
                :current<getTime()
                ?getTime()
                :-1;
    }

    public static void main(String[] args) {

    }
}

