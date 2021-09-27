package ua.sumdu.j2se.Fomin.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskLIst(ListTypes type) throws IllegalArgumentException {
        AbstractTaskList returnType = null;
        if (type != null) {
            switch (type) {
                case ARRAY: returnType = new ArrayTaskList(); break;
                case LINKED: returnType = new LinkedTaskList(); break;
            }
        }
        else {
            throw new IllegalArgumentException("Parameters of this method should not be NULL.");
        }
        return returnType;
    }
}
