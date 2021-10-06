package java;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Tasks {

    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) {
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("Was set as a wrong parameters of this method!");
        }
        return StreamSupport.stream(tasks.spliterator(), false)
                .filter(task -> task.nextTimeAfter(from) != null)
                .filter(task -> !task.nextTimeAfter(from).isAfter(to))
                .collect(Collectors.toList());
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start,
                                                               LocalDateTime end) {
        Iterable<Task> iterableWithIncoming = incoming(tasks, start, end);

        TreeMap<LocalDateTime, Set<Task>> resultSortedMap = new TreeMap<>();

        for (Task value : iterableWithIncoming) {
            LocalDateTime taskIterate = value.nextTimeAfter(start);
            while (true) {
                if (taskIterate.isAfter(end) || taskIterate.isAfter(value.getEndTime())) {
                    break;
                }
                Set<Task> values;
                if (!resultSortedMap.containsKey(taskIterate)) {
                    values = new HashSet<>();
                } else {
                    values = resultSortedMap.get(taskIterate);
                }
                values.add(value);
                resultSortedMap.put(taskIterate, values);
                if (value.getRepeatInterval() == null) {
                    break;
                }
                taskIterate = taskIterate.plus(value.getRepeatInterval());
            }
        }
        return resultSortedMap;
    }

}
