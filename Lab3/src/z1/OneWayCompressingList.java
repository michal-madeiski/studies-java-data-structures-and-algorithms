package z1;

import java.util.ArrayList;

public class OneWayCompressingList<E> implements IList<E> {
    private int c;
    private Knot head;
    private int basicKnotCounter;
    public OneWayCompressingList(int c) {
        this.c = c;
        this.head = null;
        this.basicKnotCounter = 0;
    }

    public void decompress() {
        Knot actElem = head;
        while (actElem != null) {
            if (actElem instanceof KnotArray<?>) {
                Knot tempElem = actElem.getNext();
                Knot prevElem = new Knot<>(((KnotArray<?>) actElem).getTab()[0]);
                for (int i = 1; i < ((KnotArray<?>) actElem).getTab().length; i++) {
                    actElem = new Knot<>(((KnotArray<?>) actElem).getTab()[i]);
                    prevElem.setNext(actElem);
                    prevElem = actElem;
                }
                actElem.setNext(tempElem);




            }
            actElem = actElem.getNext();
        }
    }

    public void compress() {
        decompress();
        int howManyKnotArrays = size() / c;

        int start = size() - howManyKnotArrays*c;

        Knot actElem = getElement(start);
        int lastElemIndex = start - 1;
        Knot lastElem = getElement(lastElemIndex);

        for (int i = 0; i < howManyKnotArrays; i++) {
            Knot tempArray = new KnotArray(c, actElem);
            lastElem.setNext(tempArray);
            for (int j = 0; j < c; j++) {
                actElem = actElem.getNext();
            }
            lastElemIndex++;
        }
        actElem.setNext(null);
    }

    @Override
    public boolean add(E value) {
        basicKnotCounter++;
        Knot newElem = new Knot(value);
        if (head == null) {
            head = newElem;
            return true;
        }

        Knot tail = head;

        while (tail.getNext() != null) {
            tail = tail.getNext();
        }

        tail.setNext(newElem);

        if (basicKnotCounter == c) {
            compress();
            basicKnotCounter = 0;
        }

        return true;
    }

    @Override
    public boolean add(int index, E value) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) throw new IllegalArgumentException();
        int pos = 0;
        E returnItem = null;
        Knot actElem = head;

        while(actElem != null)
        {
            if (actElem instanceof KnotArray<?>) {
                for (int i = 0; i < ((KnotArray<?>) actElem).knotArraySize(); i++) {
                    pos++;
                    if (pos == index) {
                        returnItem = (E) ((KnotArray<?>) actElem).getTab()[i];
                    }
                }
                actElem = actElem.getNext();
            } else {
                pos++;
                if (pos == index) {
                    returnItem = (E) actElem.getValue();
                }
            }
        }

        return returnItem;
    }

    @Override
    public E set(int index, E value) {
        if (index < 0 || index >= size()) throw new IllegalArgumentException();
        int pos = 0;
        E returnItem = null;
        Knot actElem = head;

        while(actElem != null)
        {
            if (actElem instanceof KnotArray<?>) {
                for (int i = 0; i < ((KnotArray<?>) actElem).knotArraySize(); i++) {
                    pos++;
                    if (pos == index) {
                        returnItem = (E) ((KnotArray<?>) actElem).getTab()[i];
                        ((KnotArray<?>) actElem).getTab()[i] = value;

                    }
                }
                actElem = actElem.getNext();
            } else {
                pos++;
                if (pos == index) {
                    returnItem = (E) actElem.getValue();
                    actElem.setValue(value);
                }
            }
        }

        return returnItem;
    }

    @Override
    public int indexOf(E value) {
        int index = 0;
        Knot actElem = head;

        while (actElem != null) {
            if (actElem instanceof KnotArray<?>) {
                Object[] tempTab = ((KnotArray<?>) actElem).getTab();
                for (int i = 0; i < tempTab.length ; i++) {
                    if (tempTab[i] == value) {
                        return index;
                    }
                    index++;
                }
                actElem = actElem.getNext();
            } else {
                if (actElem.getValue() == value) {
                    return index;
                }
                index++;
                actElem = actElem.getNext();
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        int pos = 0;
        Knot actElem = head;

        while(actElem != null)
        {
            if (actElem instanceof KnotArray<?>) {
                pos += ((KnotArray<?>) actElem).knotArraySize();
                actElem = actElem.getNext();
            } else {
                pos++;
                actElem = actElem.getNext();
            }
        }

        return pos;
    }

    private Knot getElement(int index){
        if(index<0) throw new IndexOutOfBoundsException();
        Knot actElem = head;
        while(index > 0 && actElem != null){
            index--;
            actElem = actElem.getNext();
        }
        if (actElem==null)
            throw new IndexOutOfBoundsException();
        return actElem;
    }



    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(E value) {
        return false;
    }









    public int palindromeBreakIndex() {
        if (isEmpty()) {
            return -1;
        }
        int palindromeBreakIndex = -1;
        int counter = 0;

        ArrayList<E> valueList = new ArrayList<>();
        Knot actElem = head;

        while (actElem != null) {
            if (actElem instanceof KnotArray<?>) {
                counter += ((KnotArray<?>) actElem).knotArraySize();
                for (Object o : ((KnotArray<?>) actElem).getTab()) {
                    valueList.add((E) o);
                }
                actElem = actElem.getNext();
            } else {
                counter++;
                valueList.add((E) actElem.getValue());
                actElem = actElem.getNext();
            }
        }

        int start = 0;
        int last = counter;
        while (palindromeBreakIndex == -1) {
            if (valueList.get(start) != valueList.get(last)) {
                palindromeBreakIndex = start;
                return palindromeBreakIndex;
            }
            start ++;
            counter--;
        }
        return start;
    }
}
