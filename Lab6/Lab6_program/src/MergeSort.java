import core.AbstractSwappingSortingAlgorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeSort<T> extends AbstractSwappingSortingAlgorithm<T> {

    public MergeSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        if (list == null || list.size() <= 1) {
            return list;
        }

        LinkedList<List<T>> queue = new LinkedList<>();

        for (T elem : list) {
            List<T> oneElemList = new ArrayList<>();
            oneElemList.add(elem);
            queue.addLast(oneElemList);
        }

        while (queue.size() > 1) {
            List<T> firstToMerge = queue.removeFirst();
            List<T> secondToMerge = queue.removeFirst();
            List<T> merged = merge(firstToMerge, secondToMerge);
            queue.addLast(merged);
        }

        List<T> sortedList = queue.poll();
        list.clear();
        list.addAll(sortedList);

        return list;
    }

    private List<T> merge(List<T> firstList, List<T> secondList) {
        List<T> merged = new ArrayList<>();
        int firstIdx = 0; int secondIdx = 0;

        while (firstIdx < firstList.size() && secondIdx < secondList.size()) {
            T firstElem = firstList.get(firstIdx); T secondElem = secondList.get(secondIdx);
            if (compare(firstElem, secondElem) <= 0) {
                merged.add(firstElem);
                firstIdx++;
            } else {
                merged.add(secondElem);
                secondIdx++;
            }
        }

        while (firstIdx < firstList.size()) {
            merged.add(firstList.get(firstIdx));
            firstIdx++;
        }

        while (secondIdx < secondList.size()) {
            merged.add(secondList.get(secondIdx));
            secondIdx++;
        }

        return merged;
    }
}
