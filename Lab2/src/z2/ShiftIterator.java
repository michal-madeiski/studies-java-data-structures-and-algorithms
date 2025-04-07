package z2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ShiftIterator<E> implements Iterator<E> {
    private int currentIndex;
    private int counter;
    private List<E> tempList;
    public ShiftIterator(Iterator<E> iter) {
        if (iter == null) {
            throw new NullPointerException("Given Iterator is null");
        }
        this.currentIndex = 0;
        this.counter = 0;
        this.tempList = new ArrayList<>();
        while (iter.hasNext()) {
            tempList.add(iter.next());
        }
    }

    @Override
    public boolean hasNext() {
        return counter < tempList.size();
    }

    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException();

        E returnItem = tempList.get(currentIndex);
        currentIndex++;
        if (currentIndex == tempList.size()) {
            counter++;
            currentIndex = counter;
        }
        return returnItem;
    }
}
