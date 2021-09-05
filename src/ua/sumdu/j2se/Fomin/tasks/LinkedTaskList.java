package ua.sumdu.j2se.Fomin.tasks;

import java.util.LinkedList;

public class LinkedTaskList {

    transient Node first;
    transient Node last;
    private int size;

    private static class Node{
        Task item;
        Node next;
        Node prev;

        Node(Task element, Node next, Node prev){
            this.item=element;
            this.next=next;
            this.prev=prev;
        }
    }

    public void add(Task task){
        addLast(task);
    }

    public boolean remove(Task task){
        if (task==null){
            for(Node x=first;x!=null;x=x.next){
                if(x.item==null){
                    unlink(x);
                    return true;
                }
            }
        }
        else{
            for(Node x =first; x!=null; x=x.next){
                if(task.equals(x.item)){
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public int size(){
        return size;
    }
    void linkLast(Task task){
        Node l =last;
        Node newNode=new Node(task,l,null);
        last=newNode;
        if (l == null)
            first=newNode;
        else
            l.next=newNode;
        size++;
    }

    void addLast(Task task){
        linkLast(task);
    }

    Task unlink(Node x){
        //assert x != null
        Task element=x.item;
        Node next=x.next;
        Node prev=x.prev;

        if(prev==null)first=next;
        else{
            prev.next=next;
            x.prev=null;
        }

        if(next==null)last=prev;
        else{
            next.prev=prev;
            x.next=null;
        }

        x.item=null;
        size--;
        return element;
    }
    public Task get(int index) throws IndexOutOfBoundsException{
        if(index>=0 && index<=size){
            return node(index).item;
        }
        else
            throw new IndexOutOfBoundsException("Index out of range.");
        }
    //set with index
    public Task set(Task task, int index){
        if(index>=0 && index<size){
            Node x = node(index);
            Task oldVal = x.item;
            x.item= task;
            return oldVal;
        }
        else
            throw new IndexOutOfBoundsException("Index out of range.");


    }

    Node node(int index) {
        if(index<(size>>1)){
            Node x=first;
            for (int i = 0; i <index ; i++) {
                x=x.next;
            }
            return x;
        }
        else{
            Node x=last;
            for (int i = size-1; i >index ; i--){
                x=x.prev;
            }
            return x;
        }
    }

    /*public LinkedTaskList incoming(int from, int to){
        LinkedTaskList linkedTaskList = new LinkedTaskList();
        linkedTaskList.

    }*/
}





