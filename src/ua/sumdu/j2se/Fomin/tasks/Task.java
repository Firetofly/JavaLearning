package ua.sumdu.j2se.Fomin.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

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
        if (isRepeated()) return start;
        else return time;
    }

    public void setTime(int time){
        this.time=time;
        this.interval=0;
        this.start=0;
        this.end=0;
    }

    //Methods for reading and changing execution time for repetitive tasks
    public int getStartTime(){
        if (isRepeated())
            return start;
        else
            return time;
    }

    public int getEndTime(){
        if(isRepeated())
            return end;
        else
            return time;
    }

    public int getRepeatInterval(){
        return interval;
    }
    //If the task is a non-repetitive one, it should become repetitive.
    public void setTime(int start, int end, int interval){
        if(isRepeated()) {
            this.start=start;
            this.end=end;
            this.interval=interval;
            this.time=0;
        }
    }

    public boolean isRepeated(){
        return getRepeatInterval() != 0;
    }

    /*
     *Method that returns the next start time of the task execution after the current time.
     *If after the specified time the task is not executed anymore, the method must return -1.
     */
    public int nextTimeAfter(int current){
        //For repetitive tasks
        if (isRepeated()){
            if (current>start && (current<=(end-start)/interval*interval+start)){
                if((current-start)/interval==0)
                    //Then is (current-interval)/interval=1
                    return start+interval;
                //If current is between first and second intervals;
                else if(current>((current-start)/interval*interval+start) && (current<((current-start)/interval*interval+start+interval))){
                    return ((current-start)/interval)*interval+start+interval;
                }
                //If current > when the last part will be end;
                else if (current > (end/ interval) * interval+start)
                    return -1;
                else
                    return ((current-start)/interval)*interval+start;
            }
            else return current<=start?start:-1;
        }
        //For non-repetitive tasks
        else return current<=time?time:-1;

    }
}

