package algorithms.sort;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsertSort {

    public static void main(String[] args) {

        int[] array = { 3, 8, 1, 2, 6, 7, 5, 4 };
        log.info("{}", Arrays.toString(array));
        log.info("{}", insertSort(array));
    }

    private static int[] insertSort(int[] array) {
        int len = array.length;
        int preIndex, currentValue;
        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            currentValue = array[i];
            while (preIndex >= 0 && array[preIndex] > currentValue) {
                log.info("{}", array[preIndex + 1]);
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = currentValue;
        }
        return array;
    }
}
