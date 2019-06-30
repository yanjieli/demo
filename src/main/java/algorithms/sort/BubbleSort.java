package algorithms.sort;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BubbleSort {

    public static void main(String[] args) {
        int[] arry = { 3, 8, 1, 2, 6, 7, 5, 4 };
        log.info("{}", Arrays.toString(arry));
        log.info("{}", bubleSort(arry));

    }

    public static int[] bubleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // arry[j] = arry[j] + arry[j + 1];
                    // arry[j + 1] = arry[j] - arry[j + 1];
                    // arry[j] = arry[j] - arry[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }
        }
        return array;
    }
}
