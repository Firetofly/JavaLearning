package ua.eu.sumdu.j2se.Fomin.tasks;

import org.apache.log4j.Logger;

import java.util.Iterator;

public class LinkedTaskList extends AbstractTaskList {

    transient Node first;
    transient Node last;

    private static final Logger log = Logger.getLogger(LinkedTaskList.class);

    private static class Node {
        Task item;
        Node next;
        Node prev;

        Node(Task element, Node next, Node prev) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task != null) {
            Node l = last;
            Node newNode = new Node(task, l, null);
            last = newNode;
            if (l == null)
                first = newNode;
            else
                l.next = newNode;
            increaseSize();
            log.info("A task "+task.getTitle()+" has been to LinkedTaskList");
        } else {
            throw new IllegalArgumentException("Parameters of this method should not be NULL");
        }
    }

    @Override
    public boolean remove(Task task) {
        if (task != null) {
            if (task == null) {
                for (Node x = first; x != null; x = x.next) {
                    if (x.item == null) {
                        unlink(x);
                        log.info("A task "+task.getTitle()+" has been removed.");
                        return true;
                    }
                }
            } else {
                for (Node x = first; x != null; x = x.next) {
                    if (task.equals(x.item)) {
                        unlink(x);
                        log.info("A task "+task.getTitle()+" has been removed.");
                        return true;
                    }
                }
            }
            return false;
        } else {
            log.error("Exception! Was entered a non-existent task!");
            throw new IllegalArgumentException("Parameters of this method should not be null");
        }
    }

    public Task unlink(Node x) throws IllegalArgumentException {
        if (x != null) {
            //assert x != null
            Task element = x.item;
            Node next = x.next;
            Node prev = x.prev;

            if (prev == null) first = next;
            else {
                prev.next = next;
                x.prev = null;
            }

            if (next == null) last = prev;
            else {
                next.prev = prev;
                x.next = null;
            }

            x.item = null;
            decreaseSize();
            return element;
        } else throw new IllegalArgumentException("Parameter of this method should not be null.");
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index <= size()) {
            return node(index).item;
        } else{
            log.error("Exception! Trying return a Task by index out of range!");
            throw new IndexOutOfBoundsException("Index out of range.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        LinkedTaskList taskList = (LinkedTaskList) obj;
        if (this.size() != taskList.size()) return false;
        if (this.first == taskList.first && this.first == taskList.last) {
            result = true;
        }
        return result;
    }

    //set with index
    public Task set(Task task, int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < size()) {
            Node x = node(index);
            Task oldVal = x.item;
            x.item = task;
            return oldVal;
        } else
            throw new IndexOutOfBoundsException("Index out of range.");
    }

    Node node(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index <= size()) {
            if (index < (size() >> 1)) {
                Node x = first;
                for (int i = 0; i < index; i++) {
                    x = x.next;
                }
                return x;
            } else {
                Node x = last;
                for (int i = size() - 1; i > index; i--) {
                    x = x.prev;
                }
                return x;
            }
        } else throw new IndexOutOfBoundsException("Index out of range.");
    }

    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            Node counter = first;

            @Override
            public boolean hasNext() {
                return counter.next != null;
            }

            @Override
            public Task next() {
                counter = counter.next;
                return counter.item;
            }
            @Override
            public void remove() {
                LinkedTaskList.this.remove(counter.item);
            }
        };
    }
}







