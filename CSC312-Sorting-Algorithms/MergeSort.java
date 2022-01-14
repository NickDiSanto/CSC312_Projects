public class MergeSort {
    public static void main(String[] args) {
        int[] numbers = new int[10];
        numbers[0] = 12;
        numbers[1] = 4;
        numbers[2] = 2;
        numbers[3] = 9;
        numbers[4] = 11;
        numbers[5] = 16;
        numbers[6] = 1;
        numbers[7] = 5;
        numbers[8] = 3;
        numbers[9] = 8;

        merge(numbers, 0, numbers.length - 1);
        for (int i = 0; i < numbers.length - 1; i++)
            System.out.print(numbers[i] + ", ");
        System.out.println(numbers[numbers.length - 1]);
    }

    public static int[] merge(int[] numbers, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            merge(numbers, first, mid);
            merge(numbers, mid + 1, last);
            sort(numbers, first, last);
        }
        return numbers;
    }

    public static int[] sort(int[] numbers, int first, int last) {
        int gap = last - first + 1;
        for (gap = nextGap(gap); gap > 0; gap = nextGap(gap)) {
            for (int i = first; i + gap <= last; i++) {
                int j = i + gap;
                if (numbers[i] > numbers[j]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        return numbers;
    }

    public static int nextGap(int gap) {
        if (gap <= 1)
            return 0;
        return (int)Math.ceil(gap / 2.0);
    }

}