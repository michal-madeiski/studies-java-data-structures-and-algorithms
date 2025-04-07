import java.util.Comparator;
import java.util.List;

import core.AbstractSwappingSortingAlgorithm;

public class ShakerSort<T> extends AbstractSwappingSortingAlgorithm<T> {

    public ShakerSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    public List<T> sort(List<T> list) {
        int _min = 0;
        int _max = list.size() - 2;
        int temp = 0;

        while (temp >= 0) {
            temp = -1;
            for (int i = _min; i <= _max; i++) {
                if (compare(list.get(i), list.get(i + 1)) > 0) {
                    swap(list, i, i + 1);
                    temp = i;
                }
            }
            if (temp < 0) break;
            _max = temp - 1;
            temp = -1;
            for (int i = _max; i >= _min; i--) {
                if (compare(list.get(i), list.get(i + 1)) > 0) {
                    swap(list, i, i + 1);
                    temp = i;
                }
            }
            _min = temp + 1;
        }
        return list;
    }
}
