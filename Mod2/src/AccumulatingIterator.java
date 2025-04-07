import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AccumulatingIterator implements Iterator<Double> {
    private double sum;
    private Iterator<Double> iter;

    public AccumulatingIterator(Iterator<Double> iter) {
        if (iter == null) {
            throw new NullPointerException("Given Iterator is null");
        }
        this.sum = 0;
        this.iter = iter;
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public Double next() {
        if (!iter.hasNext()) {
            throw new NoSuchElementException();
        }
        sum += iter.next();
        return sum;
    }

    public static void main(String[] args) {
        Double[] tab1 = {1.0, 2.0, 3.0, 4.5};
        Double[] tab2 = {1.0};
        Double[] tab3 = {};

        Iterator<Double> iter1 = Arrays.asList(tab1).iterator();
        Iterator<Double> iter2 = Arrays.asList(tab2).iterator();
        Iterator<Double> iter3 = Arrays.asList(tab3).iterator();

        AccumulatingIterator accumIter1 = new AccumulatingIterator(iter1);
        System.out.print(Arrays.asList(tab1) + " -> ");
        while (accumIter1.hasNext()) {
            System.out.print(accumIter1.next() + " ");
        }
        System.out.println();

        AccumulatingIterator accumIter2 = new AccumulatingIterator(iter2);
        System.out.print(Arrays.asList(tab2) + " -> ");
        while (accumIter2.hasNext()) {
            System.out.print(accumIter2.next() + " ");
        }
        System.out.println();

        AccumulatingIterator accumIter3 = new AccumulatingIterator(iter3);
        System.out.print(Arrays.asList(tab3) + " -> ");
        while (accumIter3.hasNext()) {
            System.out.print(accumIter3.next() + " ");
        }
        System.out.println();
    }
}