import list.IList;

import java.util.Iterator;
import java.util.ListIterator;

public class TwoWayLinkedList<E> implements IList<E> {
    private class Element{
        private E value;
        private Element next;
        private Element prev;

        public E getValue() {
            return value;
        }
        public void setValue(E value) {
            this.value = value;
        }
        public Element getNext() {
            return next;
        }
        public void setNext(Element next) {
            this.next = next;
        }
        public Element getPrev() {
            return prev;
        }
        public void setPrev(Element prev) {
            this.prev = prev;
        }
        Element(E data){
            this.value=data;
        }

        public void insertAfter(Element elem){
            elem.setNext(this.getNext());
            elem.setPrev(this);
            this.getNext().setPrev(elem);
            this.setNext(elem);
        }

        public void insertBefore(Element elem){
            elem.setNext(this);
            elem.setPrev(this.getPrev());
            this.getPrev().setNext(elem);
            this.setPrev(elem);
        }

        public void remove(){
            this.getNext().setPrev(this.getPrev());
            this.getPrev().setNext(this.getNext());
        }
    }

    Element sentinelStart;
    Element sentinelEnd;
    Element head = null;
    Element tail = null;

    public TwoWayLinkedList() {
        sentinelStart = new Element(null);
        sentinelEnd = new Element(null);
        sentinelStart.setNext(sentinelEnd);
        sentinelEnd.setPrev(sentinelStart);
    }

    @Override
    public boolean add(E value) {
        Element newElement = new Element(value);
        if (head == null) {
            head = newElement;
            tail = newElement;
            sentinelStart.setNext(newElement);
            sentinelStart.insertAfter(newElement);
            sentinelEnd.insertBefore(newElement);
            sentinelEnd.setPrev(newElement);
            return true;
        } else {
            tail = newElement;
            sentinelEnd.insertBefore(newElement);
            sentinelEnd.setPrev(newElement);
            return true;
        }
    }

    @Override
    public boolean add(int index, E value) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == 0) {
                Element newElement = new Element(value);
                sentinelStart.insertAfter(newElement);
                sentinelStart.setNext(newElement);
                head = newElement;
                return true;
            } else {
                Element newElement = new Element(value);
                Element tempElement = getElement(index -1);
                tempElement.insertAfter(newElement);
                return true;
            }
        }
    }

    @Override
    public E remove(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Element removeElement = getElement(index);
        if (index == 0) {
            head = removeElement.getNext();
            head.setPrev(null);
            sentinelStart.setNext(removeElement.getNext());
            return removeElement.getValue();
        }
        if (index == size() - 1) {
            sentinelEnd.setPrev(removeElement.getPrev());
            tail = removeElement.getPrev();
            tail.setNext(null);
            return removeElement.getValue();
        }
        removeElement.remove();
        return removeElement.getValue();
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        if (index < 0) {
            return false;
        }
        Element removeElement = getElement(index);
        if (index == 0) {
            head = removeElement.getNext();
            head.setPrev(null);
            sentinelStart.setNext(removeElement.getNext());
            return true;
        }
        if (index == size() - 1) {
            sentinelEnd.setPrev(removeElement.getPrev());
            tail = removeElement.getPrev();
            tail.setNext(null);
            return true;
        }
        removeElement.remove();
        return true;
    }

    @Override
    public void clear() {
        head = null;
        sentinelStart.setNext(sentinelEnd);
        sentinelEnd.setPrev(sentinelStart);
        tail = null;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public E get(int index) {
        Element actElem = getElement(index);
        return actElem.getValue();
    }

    @Override
    public E set(int index, E value) {
        Element actElem = getElement(index);
        E elemData = actElem.getValue();
        actElem.setValue(value);
        return elemData;
    }

    @Override
    public int indexOf(E value) {
        int index = 0;
        Element actElem = head;
        while (actElem != null && !actElem.equals(sentinelStart) && !actElem.equals(sentinelEnd)) {
            if (actElem.getValue().equals(value)) {
                return index;
            }
            index++;
            actElem = actElem.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        int n = 0;
        Element actElem = head;
        while (actElem != null && !actElem.equals(sentinelStart) && !actElem.equals(sentinelEnd)) {
            n++;
            actElem = actElem.getNext();
        }
        return n;
    }

    private Element getElement(int index) {
        if(index < 0) throw new IndexOutOfBoundsException();
        Element actElem = head;
        while(index > 0 && actElem != null){
            index--;
            actElem = actElem.getNext();
        }
        if (actElem == null)
            throw new IndexOutOfBoundsException();
        return actElem;
    }

    public void print() {
        Element actElement = head;
        while (actElement != null && !actElement.equals(sentinelStart) && !actElement.equals(sentinelEnd)) {
            System.out.printf("%-4s", actElement.getValue());
            actElement = actElement.getNext();
        }
        System.out.println();
    }

    //Mod4
    public void rotateLeft(int n) {
        int listSize = size();
        if (n >= 0 && listSize > 1) {
            if (n > listSize) {
                n %= listSize;
            }
            Element actElement = head;
            int i = 0;
            while (i < n && actElement != null && !actElement.equals(sentinelStart) && !actElement.equals(sentinelEnd)) {
                i++;
                actElement = actElement.getNext();
            }
            Element tempElement = head;
            head = actElement;
            head.setPrev(null);
            tail.setNext(tempElement);
            sentinelStart.setNext(tempElement);
            Element actElement2 = tail;
            int j = 0;
            while (j < n && actElement2 != null && !actElement2.equals(sentinelStart) && !actElement2.equals(sentinelEnd)) {
                j++;
                actElement2 = actElement2.getNext();
            }
            actElement2.setNext(null);
            tail = actElement2;
            sentinelEnd.setPrev(actElement2);

        }

    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }


    public static void main(String[] args) {
        TwoWayLinkedList<Integer> list = new TwoWayLinkedList<>();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("add(1); add(2); add(3); add(4) - zwykłe dodawanie:");
        list.add(1); list.add(2); list.add(3); list.add(4);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("add(2,5) - dodawanie na index:");
        list.add(2, 5);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("add(0,6) - dodawanie na index (początek):");
        list.add(0,6);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("add(5,7) - dodwanie na index (przed końcem):");
        list.add(5,7);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("remove(4) - usuwanie z indexu:");
        list.remove(4);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("remove(0) - usuwanie z indexu (początek):");
        list.remove(0);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("remove(list.size()-1) - usuwanie z indexu (koniec):");
        list.remove(list.size()-1);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("remove(5) - usuwanie wartości:");
        Integer x = 5;
        list.remove(x);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("size() - rozmiar:");
        System.out.println(list.size());
        System.out.println("---------------------------------------------------------------------");
        System.out.println("indexOf(4) - index wartości 4:");
        System.out.println(list.indexOf(4));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("indexOf(7) - index wartości 7:");
        System.out.println(list.indexOf(7));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("get(1) - wartość na indexie 1: ");
        System.out.println(list.get(1));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("set(1,8) - ustawienie wartości na indexie 1 na 8: ");
        list.set(1,8);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("contains(8) - czy zawiera wartość 8: ");
        System.out.println(list.contains(8));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("contains(9) - czy zawiera wartość 9: ");
        System.out.println(list.contains(9));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("isEmpty() - czy pusta: ");
        System.out.println(list.isEmpty());
        System.out.println("---------------------------------------------------------------------");
        System.out.println("clear() - wyczyszczenie listy: ");
        list.clear();
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("isEmpty() - czy pusta: ");
        System.out.println(list.isEmpty());
        System.out.println("---------------------------------------------------------------------");
        System.out.println("add(1); add(2); add(3); add(4); add(5); add(6); add(7) - zwykłe dodawanie:");
        list.add(1); list.add(2); list.add(3); list.add(4); list.add(5); list.add(6); list.add(7);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("rotateLeft(3): ");
        list.rotateLeft(3);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("rotateLeft(10): ");
        list.rotateLeft(10);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("rotateLeft(1): ");
        list.rotateLeft(1);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("rotateLeft(0): ");
        list.rotateLeft(0);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("clear(); rotateLeft(2) - próba obrotu na pustej liście: ");
        list.clear(); list.rotateLeft(2);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("add(1); rotateLeft(5) - próba obrotu na jednoelementowej liście: ");
        list.add(1); list.rotateLeft(5);
        list.print();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("add(2); rotateLeft(1) - próba obrotu na dwuelementowej liście: ");
        list.add(2); list.rotateLeft(1);
        list.print();
        System.out.println("---------------------------------------------------------------------");
    }
}