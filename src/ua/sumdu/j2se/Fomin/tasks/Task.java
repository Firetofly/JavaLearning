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
            if (current>start && current<end){
                if (current>(end-start)/interval*interval+start)
                    return -1;
                else if((current-start)/interval==0)
                    //Then is (current-interval)/interval=1
                    return 1*interval+start;
                //If current is between first and second intervals;
                else if(current>((current-start)/interval*interval+start) && (current<((current-start)/interval*interval+start+interval)) && current<=((end/ interval) * interval+start)){
                    return ((current-start)/interval)*interval+start+interval;
                }
                //If current > when the last part will be end;
                else if (current > (end/ interval) * interval+start)
                    return -1;
                else
                    return ((current-start)/interval)*interval+start;
            }
            else if (current<=start)
                return start;
            else
                return -1;
        }
        //For non-repetitive tasks
        else{
            if(current<=time)
                return time;
            else
                return -1;
        }
    }


    public static void main(String[] args) {
        Task task = new Task("task",100,150,24);

        System.out.println("current: " + 90 + ", next: " + task.nextTimeAfter(90));
        System.out.println("current: " + 100 + ", next: " + task.nextTimeAfter(100));
        System.out.println("current: " + 110 + ", next: " + task.nextTimeAfter(110));
        System.out.println("current: " + 120 + ", next: " + task.nextTimeAfter(120));
        System.out.println("current: " + 124 + ", next: " + task.nextTimeAfter(124));
        System.out.println("current: " + 130 + ", next: " + task.nextTimeAfter(130));
        System.out.println("current: " + 140 + ", next: " + task.nextTimeAfter(140));
        System.out.println("current: " + 148 + ", next: " + task.nextTimeAfter(148));
        System.out.println("current: " + 149 + ", next: " + task.nextTimeAfter(149));
        System.out.println("current: " + 150 + ", next: " + task.nextTimeAfter(150));
    }
}

