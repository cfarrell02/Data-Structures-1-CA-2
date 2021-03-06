package Utils;

import java.util.Comparator;
import java.util.Iterator;

public class CoolLinkedList<F> implements Iterable<F> {
    public node<F> head;
    public node<F> tail ;
    private int size;

    public boolean add(F e) { //Add element to tail of list
        node<F> fn = new node<>();
        fn.setContents(e);
        if(tail != null){
            tail.next = fn;
            fn.previous = tail;}
        tail = fn;
        if(size ==0)
            head=fn;
        ++size;
        return true;
    }
    public F set(int oldIndex, F newObject){
        F oldObject = get(oldIndex);
        getNode(oldIndex).setContents(newObject);
            return oldObject;
    }

    public void swap(int i,int j){
        F swapped = get(j);
        set(j,get(i));
        set(i,swapped);
    }
    public int size(){
        return size;}
    public void clear() { //Empty list
        head = null;
        tail = null;
        size = 0;
    }

    public void sort(Comparator<F> c){
//        if(size>=1000)
            mergeSort(c);
//        else
//            shellSort(c);
    }
//    public void shellSort(Comparator<F> c){
//        CoolLinkedList<Integer> gaps = new CoolLinkedList();
//        gaps.add(1);
//        for(int i = 0 ; Math.pow(2,i)<size;++i){
//            gaps.add(0,(int) Math.pow(2,i) + 1); // finds an amount of gaps
//        }
//        for(Integer g : gaps) {
//
//            for(int e=g;e<size;e++){
//                F elem=get(e);  //takes e the first gap
//                int i;
//
//                for(i=e;i>=g && c.compare(get(i-g),elem)>0;i-=g)
//                    set(i,get(i-g));
//
//                set(i,elem);
//            }
//        }
//    }


    public void mergeSort(Comparator<F> c) {
        if (size > 1) {
            //Create 2 sublists as evenly sized as possible
            int x = size / 2, y = size - x;
            CoolLinkedList<F> xa = new CoolLinkedList<>();
            CoolLinkedList<F> ya = new CoolLinkedList<>();
            int i;
            for (i = 0; i < x; i++) xa.add(get(i));
            for (int i2 = 0; i2 < y; i2++, i++) ya.add(get(i)); //copies elements into sub lists
            //Recursively merge sort the sub lists independently
            xa.mergeSort(c);
            ya.mergeSort(c);
            //Merge the 2 sorted sub lists
            i = 0;
            int xai = 0, yai = 0;
            while (xai < xa.size() && yai < ya.size())
                set(i++,(c.compare(xa.get(xai),ya.get(yai)))<0 ? xa.get(xai++) : ya.get(yai++)); //check sub list to see which one comes first then set the element to be the first one repeats till list becomes 0
            while (xai < xa.size()) set(i++,xa.get(xai++)); //then add rest of sublist back to main list, whichever is not zero
            while (yai < ya.size()) set(i++,ya.get(yai++));
        }
    }


    public boolean isEmpty(){
        return size == 0;
    }
    public F remove(int index){
        node<F> deletedNode = getNode(index);

        if(size ==1)
            clear();
        else {
            if (index == 0) {
                head = head.next;
                head.previous = null;
            } else if (index == size - 1) {
                tail = tail.previous;
                tail.next = null;
            } else {
                deletedNode.next.previous = deletedNode.previous;
                deletedNode.previous.next = deletedNode.next;
            }
        }
        size--;
        return deletedNode.getContents();

    }

    public node<F> getNode(int index) {
        node<F> temp = head;
        for (int i = 0; i <= index && temp != null; ++i) {
            if (i == index)
                return temp;
            temp = temp.next;
        }
        return null;
    }
    public F get(int index){
        if(getNode(index)!=null)
            return getNode(index).getContents();
        return null;
    }

//    public void add(CoolLinkedList<F> list){
//        node lastItem = getNode(size-1);
//        node firstItem = list.getNode(0);
//        lastItem.next=firstItem;
//        firstItem.previous=lastItem;
//    }


    public void add(int index, F element) {
        node<F> fn = new node<>();
        fn.setContents(element);
        if(head==null)
            head = fn;
        if(tail==null)
            tail = fn;
        if(index == 0){
            fn.next = head;
            head = fn;
        }
        else if(index == size-1){
            fn.previous = tail;
            tail = fn;
        }else{
            fn.previous=getNode(index-1);
            fn.next=getNode(index);
            fn.previous.next = fn;
            fn.next.previous = fn;
        }

        size ++;
    }



    public int indexOf(Object o) {
        node<F> temp = head;
        for(int i=0;temp!=null;++i){
            temp = temp.next;
            if(temp.getContents().equals(o))
                return i;
        }
        return -1;
    }




    public boolean contains(F object){
        node<F> temp = head;
        while(temp!=null){
            if(temp.getContents().equals(object))
                return true;

            temp = temp.next;
        }
        return false;
    }

    public String toString(){
        StringBuilder list = new StringBuilder();
        for(F item: this) list.append("\n").append(item.toString());
        return list.toString();
    }

    public Iterator<F> iterator() {
        return new CoolIterator<F>(head);
    }



}