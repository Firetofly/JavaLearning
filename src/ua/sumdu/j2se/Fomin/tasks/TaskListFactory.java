package ua.sumdu.j2se.Fomin.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskLIst(ListTypes type) {
        AbstractTaskList returnType = null;
        switch (type) {
            case ARRAY -> returnType = new ArrayTaskList();
            case LINKED -> returnType = new LinkedTaskList();
        }
        return returnType;
    }
}
