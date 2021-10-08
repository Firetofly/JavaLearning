package ua.eu.sumdu.j2se.Fomin.tasks;

import ua.eu.sumdu.j2se.Fomin.tasks.AbstractTaskList;
import ua.eu.sumdu.j2se.Fomin.tasks.ArrayTaskList;
import ua.eu.sumdu.j2se.Fomin.tasks.LinkedTaskList;
import ua.eu.sumdu.j2se.Fomin.tasks.ListTypes;

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
