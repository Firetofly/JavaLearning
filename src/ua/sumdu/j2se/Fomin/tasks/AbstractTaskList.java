package ua.sumdu.j2se.Fomin.tasks;


public abstract class AbstractTaskList {

    private int size;

    public abstract boolean remove(Task task);

    public void increaseSize() {
        size++;
    }

    public void decreaseSize() {
        size--;
    }

    public int size() {
        return size;
    }

    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList returnedTaskList = null;

        if(this instanceof ArrayTaskList){
            returnedTaskList=new ArrayTaskList();
        }
        else if(this instanceof LinkedTaskList){
            returnedTaskList=new LinkedTaskList();
        }
        for (int i = 0; i < size; i++) {
            if (((getTask(i).getStartTime() >= from && getTask(i).getEndTime() <= to) || (getTask(i).getTime() >= from && getTask(i).getTime() <= to)) && getTask(i)!=null)
            {
                assert returnedTaskList != null;
                returnedTaskList.add(getTask(i));
            }
        }
        return returnedTaskList;
    }

    protected abstract void add(Task task);

    public abstract Task getTask(int index);

}
